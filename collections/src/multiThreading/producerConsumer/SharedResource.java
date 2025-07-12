package multiThreading.producerConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResource {

    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity = 5000;
    static int counter = 1000;

    public synchronized void produce(int value){
        while(queue.size()==capacity){
            try {
                System.out.println("Producer Thread: "+Thread.currentThread().getName()+ " is waiting");
                wait();
            }catch (Exception e){
                System.out.println("some exception occurred: "+e.getMessage());
            }
        }
        queue.add(value);
        System.out.println("produced the item value: "+value);
        notifyAll();
    }

    public synchronized void consume(){
        while(queue.isEmpty()){
            try {
                System.out.println("Consumer Thread: "+Thread.currentThread().getName()+ " is waiting");
                wait();
            }catch (Exception e){
                System.out.println("some exception occurred: "+e.getMessage());
            }
        }
        int value = queue.poll();
        System.out.println("consumed the item value: "+value);
        notifyAll();
    }
}
