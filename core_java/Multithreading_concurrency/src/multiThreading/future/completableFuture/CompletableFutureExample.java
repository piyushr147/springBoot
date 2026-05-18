package multiThreading.future.completableFuture;

import java.util.concurrent.*;

public class CompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        Future<Integer> future = executor.submit(() -> 10+2);

        //without executor
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> 10+2);

        //with executor
        completableFuture = CompletableFuture.supplyAsync(() -> 10+2, executor);

        //thenApply
        int answer =  completableFuture.thenApply(x -> x+10).get();
        System.out.println(answer);

        completableFuture.cancel(true);
    }
}
