package multiThreading.forkJoinPool;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolExample {

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool(4);
        int[] arr = {93,904,4,393,6873,12,1,4,6,5,2,3,6,78,9,76,4,4,4,55,4,5445,5,5,77,6,55,5,4,3,3,33,3,111};
        AdvanceMergeSort advanceMergeSort = new AdvanceMergeSort(arr,0,arr.length-1);

        Thread monitor  = new Thread(()->{
            while(!Thread.currentThread().isInterrupted()) {
                System.out.println("parallelism: " + pool.getParallelism());
                System.out.println("Pool Size: " + pool.getPoolSize());
                System.out.println("Active Threads: " + pool.getActiveThreadCount());
                System.out.println("Running Threads: " + pool.getRunningThreadCount());
                System.out.println("Queued Task Count: " + pool.getQueuedTaskCount());
                System.out.println("Steal Count: " + pool.getStealCount());
            }
            try{
                Thread.sleep(500);
            }catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        });

        //start the monitor thread
        monitor.start();
        //execute your advanced merge sort
        pool.execute(advanceMergeSort);
        //wait here and go ahead after completing the sorting
        advanceMergeSort.join();
        //now logs must have been printed interrupt it, but it only sets the interrupt flag, does not gaurantee to stop.
        monitor.interrupt();
        // Wait for monitor to stop before printing the result.
        monitor.join();
        System.out.println("Sorted Array: " + Arrays.toString(arr));
        pool.shutdown();
    }
}
