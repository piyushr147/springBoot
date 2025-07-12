package multiThreading.readWriteLock;

import multiThreading.readWriteLock.SharedResource;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {

    public static void main(String[] args){
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        SharedResource sharedResource = new SharedResource();

        Thread read = new Thread(()->{
            try {
                while(--SharedResource.readCounter>0){
                    Thread.sleep(1000);
                    sharedResource.read(readWriteLock);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        Thread write = new Thread(()->{
            try {
                while(--SharedResource.writeCounter>0){
                    sharedResource.write(readWriteLock,SharedResource.writeCounter);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        read.start();
        write.start();
    }
}
