package multiThreading.monitorLock;

public class MonitorLockExample {

    public synchronized void task1(){
        try{
            System.out.println("task1 lock accquired on object");
            Thread.sleep(2000);
            System.out.println("task1 end releasing lock on object");
            notifyAll();

        }
        catch(Exception e){
            System.out.println("task1 end exception: "+e.getMessage());
        }
    }

    public void task2(){
        System.out.println("task2 before synchronized");
        synchronized(this){
            System.out.println("task2 after synchronized");
        }
    }

    public void task3(){
        System.out.println("task3 begin");
    }

    public static synchronized void task4(){
        System.out.println("task4 lock accquired on class");
        try {
            Thread.sleep(2000);
        }
        catch(Exception e){
            System.out.println("task1 end exception: "+e.getMessage());
        }
        System.out.println("task4 lock released on class");
    }

    static void main(String[] args) {
        MonitorLockExample monitorLockExample = new MonitorLockExample();
        MonitorLockExample monitorLockExample2 = new MonitorLockExample();

        Thread t1 = new Thread(monitorLockExample::task1);
        Thread t2 = new Thread(monitorLockExample::task2);
        Thread t3 = new Thread(monitorLockExample::task3);
        Thread t4 = new Thread(MonitorLockExample::task4);

        t4.start();
        t1.start();
        t2.start();
        t3.start();
    }
}
