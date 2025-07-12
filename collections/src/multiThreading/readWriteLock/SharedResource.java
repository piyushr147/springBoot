package multiThreading.readWriteLock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReadWriteLock;

public class SharedResource {

    private int cachedData = 0;
    static int readCounter = 1000;
    static int writeCounter = 50;

    public synchronized void read(ReadWriteLock lock) {
        lock.readLock().lock();
        try{
            Thread.sleep(100); // Simulate reading time
            System.out.println("Reading cache data value: "+cachedData);
        } catch (Exception e) {
            System.out.println("Exception in read: "+e.getMessage());
        }finally {
            lock.readLock().unlock();
        }
    }

    public synchronized void write(ReadWriteLock lock,int value){
        lock.writeLock().lock();
        try{
            cachedData = value;
            Thread.sleep(1000); //simulate writing time
            System.out.println("Write the value in data: "+value);
        }catch (Exception e){
            System.out.println("Exception in write: "+e.getMessage());
        }finally {
            lock.writeLock().unlock();
        }
    }
}
