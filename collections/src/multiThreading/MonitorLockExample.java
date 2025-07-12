package multiThreading;

public class MonitorLockExample {

    public synchronized void task1(){
        try{
            System.out.println("task1 begin");
            Thread.sleep(1000);
            System.out.println("task1 end completed");
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
}
