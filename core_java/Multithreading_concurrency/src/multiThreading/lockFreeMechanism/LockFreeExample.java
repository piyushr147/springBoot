package multiThreading.lockFreeMechanism;

import multiThreading.lockFreeMechanism.SharedResource;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LockFreeExample {

    public static void main(String[] args) throws InterruptedException {
        SharedResource sharedResource = new SharedResource();
        Executor executor = Executors.newCachedThreadPool();
        Thread[] threads = new Thread[1000];

        for(int i=0;i<threads.length;i++){
            threads[i] = new Thread(()->{
                for(int j=0;j<1000;j++){
                    sharedResource.incrementCounter();
                    sharedResource.incrementCounterWithoutCAS();
                }
            });
        }
        for(Thread t:threads){
            t.start();
        }
        //You're telling the main thread (or the thread running main()) to:
        //"Wait until each thread in the threads[] array has finished running before moving forward."
        for(Thread t:threads){
            t.join();
        }

        System.out.println("counter: "+sharedResource.getCounter());
        System.out.println("counterWithoutCAS: "+sharedResource.getCounterWithoutCAS());
    }
}
