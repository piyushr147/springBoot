package multiThreading.stampedLock;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.StampedLock;

public class StampedLockExample {

    public static void main(String[] args) {
        GpsTracker gpsTracker = new GpsTracker();

        Thread read = new Thread(() -> {
            while(--GpsTracker.readCounter>0){
                try {
                    Thread.sleep(100);
                    gpsTracker.readPosition();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread write = new Thread(() -> {
            while(--GpsTracker.writeCounter>0){
                gpsTracker.updatePosition(ThreadLocalRandom.current().nextDouble(1,100), ThreadLocalRandom.current().nextDouble(1,100));
            }
        });
        read.start();
        write.start();
    }
}
