package multiThreading.threadPool;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;

public class ThreadPoolExecutorExample {

    public static void main(String... args) {
        int task = 0;
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 4, 1, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(2)
                , new ThreadFactory() {
                    @Override
                    public Thread newThread(@NotNull Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setPriority(Thread.MAX_PRIORITY);
                        //thread.setDaemon(true);
                        return thread;
                    }},
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("Rejected task: " + r.toString());
                    }}
        );

        for(int i=0;i<7;i++){
            poolExecutor.submit(() -> {
                try {
                    Thread.sleep(5000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("Submitted thread: " + Thread.currentThread().getName());
            });
        }

        poolExecutor.shutdown();
    }
}

