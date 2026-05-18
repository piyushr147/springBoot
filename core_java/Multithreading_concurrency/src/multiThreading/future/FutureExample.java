package multiThreading.future;

import java.util.concurrent.*;

public class FutureExample {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<?> future = executorService.submit(() -> {
            try {
                System.out.println("executing some time-taking video processing");
                Thread.sleep(6000);
                System.out.println("video processing completed!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        //do some task in between the async function is doing the work
        try {
            //future.get();
            Thread.sleep(4000);
            System.out.println("performing db calls");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        try{
            future.get();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

        System.out.println("done");
        executorService.shutdown();
    }
}
