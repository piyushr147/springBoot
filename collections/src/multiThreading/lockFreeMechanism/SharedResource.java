package multiThreading.lockFreeMechanism;

import java.util.concurrent.atomic.AtomicInteger;

public class SharedResource {

    private final AtomicInteger counter = new AtomicInteger(0);
    private int counter2 = 0;

    public int getCounter() {
        return counter.get();
    }

    public int getCounterWithoutCAS() {
        return counter2;
    }

    public void incrementCounter() {
        counter.incrementAndGet();
    }

    public void incrementCounterWithoutCAS() {
        counter2++;
    }
}
