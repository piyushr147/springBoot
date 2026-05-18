package multiThreading.readWriteLock;

import multiThreading.readWriteLock.SharedResource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {

    public static void main(String[] args){
        SharedResource sharedResource = new SharedResource();
        List<Thread> threadList = new ArrayList<>();

        for(int i=0;i<10;i++){
            Thread thread = new Thread(()->{
                try{
                    while(true){
                        sharedResource.read();
                    }
                }
                catch (Exception e){}
            });
            threadList.add(thread);
        }
        Thread write = new Thread(()->{
            try {
                while(true){
                    //Thread.sleep(100);
                    sharedResource.write();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        for(Thread t:threadList){
            t.start();
        }
        write.start();
    }
}
