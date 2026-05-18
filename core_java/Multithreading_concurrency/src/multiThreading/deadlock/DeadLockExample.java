package multiThreading.deadlock;

public class DeadLockExample {
    public static void main(String[] args){
        DeadLock deadlock = new DeadLock();
        Thread thread1 = new Thread(deadlock::method1);
        Thread thread2 = new Thread(deadlock::method2);

        thread1.start();
        thread2.start();
    }
}
