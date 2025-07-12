package multiThreading;

public class Threading1 {

    public static class MyRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println("MyRunnable thread is called: " + Thread.currentThread().getName());
        }
    }
    public static void main(String[] args) {
        System.out.println("main thread called: " + Thread.currentThread().getName());
        System.out.println("before executing runnable thread");
        MyRunnable myRunnable = new MyRunnable();
        Thread  thread = new Thread(()->{
            System.out.println("MyRunnable thread is called: " + Thread.currentThread().getName());
        });
        thread.start();
        System.out.println("after executing runnable thread");
    }
}
