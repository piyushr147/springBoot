package multiThreading.forkJoinPool;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class NormalVsForkJoinSorting {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(64);
        int size = 200000000;
        int[] arr = new int[size];
        Random random = new Random();

        for(int i=0;i<size;i++) {
            arr[i] = random.nextInt();
        }

        int[] normalSort = Arrays.copyOf(arr,arr.length);
        int[] forkSort = Arrays.copyOf(arr,arr.length);
        AdvanceMergeSort advanceMergeSort = new AdvanceMergeSort(forkSort,0,forkSort.length-1);


        long start,end;
        start = System.currentTimeMillis();
        //Arrays.sort(normalSort);
        end = System.currentTimeMillis();
        System.out.println("Time to normal sort: " + (end-start));

        start = System.currentTimeMillis();
        pool.execute(()->{
            try{
                Thread.sleep(5000);
            }catch (Exception e) {
                e.printStackTrace();
            }
        });
        //advanceMergeSort.join();
        end = System.currentTimeMillis();
        pool.shutdown();
        System.out.println("shutdown has been called");
        try{
            boolean isTerminated  = pool.awaitTermination(2, TimeUnit.SECONDS);
            System.out.println("Has the pool terminated: "+isTerminated);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Time to fork sort with parallelism of: " + pool.getParallelism() +" is: "+ (end-start));
    }
}
