package multiThreading.readWriteLock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SharedResource {

    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    Lock readLock = readWriteLock.readLock();
    Lock writeLock = readWriteLock.writeLock();
    private int cachedData = 1000;
    static int readCounter = 1000;
    static int writeCounter = 50;

    public void read() {
        readLock.lock();
        try{
            Thread.sleep(100); // Simulate reading time
            System.out.println("Reading cache data value: "+cachedData);
        } catch (Exception e) {
            System.out.println("Exception in read: "+e.getMessage());
        }finally {
            readLock.unlock();
        }
    }

    public void write() throws InterruptedException {
        writeLock.lock();
        try{
            cachedData = cachedData - 1;
            System.out.println("Write the value in data: "+ cachedData);
        }catch (Exception e){
            System.out.println("Exception in write: "+e.getMessage());
        }finally {
            writeLock.unlock();
            Thread.sleep(1000); //simulate writing time
        }
    }
}
