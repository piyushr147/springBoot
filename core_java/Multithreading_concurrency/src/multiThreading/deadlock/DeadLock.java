package multiThreading.deadlock;

public class DeadLock {

    Resource resource1 = new Resource();
    Resource resource2 = new Resource();

    public void method1(){
        synchronized(resource1){
            System.out.println("method1 locked resource 1");
            try{ Thread.sleep(2000); }
            catch (Exception ignored){}
            synchronized(resource2){
                System.out.println("method1 locked resource 2");
            }
        }
    }

    public void method2(){
        synchronized(resource2){
            System.out.println("method2 locked resource 2");
            try{ Thread.sleep(2000); }
            catch (Exception ignored){}
            synchronized(resource1){
                System.out.println("method2 locked resource 1");
            }
        }
    }
}
