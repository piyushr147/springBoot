# Multithreading
    Multithreading is the concept where a program executes and manages multiple threads at the same time, which results in better functioning of the program, threads are ligtweight and they share the same address space of the program and so the same code, executing multiple threads at the same time is a complex process and we have to be careful while using threads.
    for now we are looking for the following things
    ->Creating the threads and providing the code executed by the thread.
    ->Accessing common data and code through synchronization.
    ->Transition between thread states.

# Main thread
    Whenever a program executes one main thread is automatically created to execute the main() method of the program, we need to create our custom thread to support mutlti-tasking, all the threads created by us will be the children of this main thread.
    The main method might finish but the program will keep running until all the threads are completed.

# User and Dameon thread
    All custom threads including the main thread are user threads, they are given higher prefference then the dameon thread, a program will only be said completed after the execution of the main and all the other user threads irrespective of the dameon thread, even if there are dameon threads running the program will be marked as completed.
    Daemon thread in Java is a low-priority thread that performs background operations such as garbage collection, finalizer, Action Listeners, Signal dispatches, etc.
    Daemon thread in Java is also a service provider thread that helps the user thread. Its life is at the mercy of user threads; when all user threads expire, JVM immediately terminates this thread.
    In simple words, we can say that it provides services to user threads for background-supporting tasks. Daemon thread in Java has no role in life other than to serve user threads.

# Creating threads
    There are two ways of creating a thread one by implementing java.lang.Runnable interface and one by using java.lang.Thread
    Using Thread class you'll have to define the run() method of thread class by overriding it like
    
    public class Thread1 extends Thread {

        @Override
        public void run(){
            for(int i=0;i<1000;i++)
                System.out.println(Thread.currentThread()+" "+i);
        }
    }