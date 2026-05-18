package multiThreading.threadCreation;

import multiThreading.Threading1;

public class MyThread1 extends Thread {

    public void run(){
        System.out.println("MyThread created from extending Thread class is running");
    }
}
