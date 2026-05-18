package multiThreading.threadCreation;

public class MyThread2 implements Runnable{
    @Override
    public void run() {
        System.out.println("MyThread2 created from implementing Runnable class is running");
    }
}
