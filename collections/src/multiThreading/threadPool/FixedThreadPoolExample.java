package multiThreading.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolExample {

    //min and max size is same, linked queue is used which can grow infinitely
    static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

    //pool size is 0 and max pool size is INT_MAX, blocking queue with size 0(no queue is used)
    static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    //pool size and max size 1, with no queue and only one thread can be executed at one time
    static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    public static void main(String[] args){
        fixedThreadPool.execute(()->{
            try {
                Thread.sleep(10000);
                System.out.println("fixedThreadPool: "+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
