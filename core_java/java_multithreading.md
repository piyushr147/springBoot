# What happens inside JVM when a java program is created and executed
    When a Java program is created and executed, several steps take place inside and around the Java Virtual Machine (JVM). Here's a breakdown of what happens from code creation to execution:

    1. Writing the Java Code
        You create a file like MyProgram.java that contains human-readable Java source code.

    2. Compilation (javac)
        Before the JVM gets involved, the Java source code must be compiled:
            You run: 
                javac MyProgram.java
            The Java Compiler (javac) translates the source code into bytecode, producing a file like MyProgram.class.
            Bytecode is a platform-independent, intermediate representation understood by the JVM.

    3. Class Loading
        You then execute the program using:
            java MyProgram
        At this point, the JVM starts up and begins the following processes:

        a. Class Loader Subsystem
            Loads MyProgram.class and any required classes into memory.
            Uses a hierarchy of class loaders (Bootstrap, Extension, Application).
            Performs verification to ensure the bytecode is valid and doesn't break JVM rules.

    4. Bytecode Verification
        The Bytecode Verifier checks the bytecode for:
            Stack overflows/underflows.
            Type safety (no invalid type casting).
            Access rights violations.
            Prevents JVM crashes due to corrupted or malicious code.

    5. JVM Runtime Data Areas
        The JVM creates memory structures to manage program execution:
            a. Method Area
                Stores class-level data like field and method information, constant pool, etc.
            b. Heap
                Allocates memory for all Java objects and class instances.
            c. Stack (Java Stacks)
                Each thread has its own stack.
                Stores method frames (local variables, operand stack, return address).
            d. Program Counter (PC) Register
                Each thread has one PC.
                Points to the current instruction being executed in bytecode.
            e. Native Method Stack
                Used for native (non-Java) methods, typically written in C/C++.

    6. Execution Engine
        This is the heart of the JVM and executes the bytecode:
            a. Interpreter
                Reads and executes bytecode instructions line by line.
                Fast startup, but slower execution.
            b. JIT Compiler (Just-In-Time)
                Compiles frequently-used bytecode sections into native machine code.
                Speeds up performance on subsequent executions.
                Native code is cached and reused.

    7. Garbage Collection
        JVM automatically manages memory.
            Unused objects in the heap are identified and removed by the Garbage Collector.
            Helps avoid memory leaks.

    8. Program Termination
        When main() ends or all non-daemon threads finish, the JVM begins shutting down.
        Finalizers (if used) may run, and the memory is cleaned up.

# Manually Defining Memory to a process
    You can explicitly set memory parameters like this:
    java -Xms512m -Xmx2g -Xss512k MyProgram
        Key Options:
            Option	                Description
            -Xms	                Initial heap size
            -Xmx	                Maximum heap size
            -Xss	                Stack size per thread
            -XX:MaxMetaspaceSize	Max metaspace size (Java 8+)

# Working of process and threads
    What is a Process?
        A process is an independent running program. 
        It has its own memory space | own heap, stack, file descriptors | Isolated from other processes | Created by the Operating System
        Examples: Chrome browser, IntelliJ IDEA, java MyApp (each run = one process)
    
    What is a Thread?
        A thread is a lightweight unit of execution inside a process.
        Threads share process memory | Each thread has its own stack | Faster to create than a process| Communicate via shared variables
        In Java main is a thread and every new Thread() creates another thread inside the same process that shares the memory inside heap allocated to the process.
    
    Use Processes when: Strong isolation is needed | Security matters | Fault tolerance is critical
    Use Threads when: Tasks share data | High performance is needed | Low latency

# What are threads
    Whenever we start a program a process is created which has its own memory space, system resources, and at least one thread (the main thread).
    when a main function is called, main is the first thread that is created, in the program you can create multiple other threads.
    Heap, Code segment, Data segment are shared among all the threads.
    Stack, Registers, Program counters are individual for each thread, they do not share data of these memory areas.

# Java thread lifecycle
    Java thread life cycle is as follows:
    New – When the instance of the thread is created and the start() method has not been invoked, the thread is considered to be alive and hence in the NEW state.

    Runnable – Once the start() method is invoked, before the run() method is called by JVM, the thread is said to be in RUNNABLE (ready to run) state. This state can also be entered from the Waiting or Sleeping state of the thread.

    Running – When the run() method has been invoked and the thread starts its execution, the thread is said to be in a RUNNING state.

    Non-Runnable (Blocked/Waiting) – When the thread is not able to run despite the fact of its aliveness, the thread is said to be in a NON-RUNNABLE state. Ideally, after some time of its aliveness, the thread should go to a runnable state.
        A thread is said to be in a Blocked state if it wants to enter synchronized code but it is unable to as another thread is operating in that synchronized block on the same object. The first thread has to wait until the other thread exits the synchronized block.
        A thread is said to be in a Waiting state if it is waiting for the signal to execute from another thread, i.e it waits for work until the signal is received.
        
    Terminated – Once the run() method execution is completed, the thread is said to enter the TERMINATED step and is considered to not be alive.

# Ways of creating threads
    1. By Extending the Thread Class.
        class MyThread extends Thread {
            public void run() {
                System.out.println("Thread is running...");
            }

            public static void main(String[] args) {
                MyThread t1 = new MyThread();
                t1.start();  // Start a new thread
            }
        }

    2. By Implementing Runnable Interface, Thread class also implements Runnable
        Runnable is the code you want to run and Thread is the class that created the tread and completes that task.
        class MyRunnable implements Runnable {
            public void run() {
                System.out.println("Runnable thread is running...");
            }

            public static void main(String[] args) {
                Thread t1 = new Thread(new MyRunnable());
                t1.start();
            }
        }

    3. Using Lambda Expression (Java 8+)
        public class Main {
            public static void main(String[] args) {
                Thread t1 = new Thread(() -> {
                    System.out.println("Lambda thread running...");
                });
                t1.start();
            }
        }

# start() vs run() method
    The run() method contains the code that the thread will execute.
        When you create a thread, you override run() to define the task.
    The start() Method
        MyThread t = new MyThread();
        t.start(); // creates a new thread
        The start() method actually creates a new thread and tells the JVM to execute run() in that new thread and then the thread automatically calls run() method.
    Working of a thread is whole different from creation of it so both logic needs to seperated, because what's the point if thread gets created and never gets chance to run
    What Happens if You Call run() Directly?
        If you call run() yourself:
            MyThread t = new MyThread();
            t.run();   // WRONG if you want multithreading
        The code will run immediately but in the same thread, no new thread is created.
        The method runs just like a normal method call in the main thread.

# Thread priority
    In Java, thread priority is an integer value that hints to the thread scheduler about the relative importance of a thread. Higher priority threads are more likely to be scheduled before lower-priority ones — but it's not guaranteed.
    When new thread is created, it inherits it's priority from the parent.
    Priority ranges from 1 to 10, we can set it using thread.setPriority().

# Daemon thread
    A daemon thread in Java is a background thread that runs to support other threads, typically non-essential for the application’s execution. When all user (non-daemon) threads finish, the JVM will terminate, even if daemon threads are still running.

    What Is a Daemon Thread?
        It’s a low-priority thread that provides services to user threads.
        JVM automatically exits once all user threads are done — daemon threads won’t keep the JVM alive.
        Example: Garbage Collector (GC) runs as a daemon thread.
    How to create:
        set your thread as a daemon thread by setting thread.setDaemon(true);
    Use Cases
        1. Garbage Collection (GC)
            JVM runs the GC as a low-priority background task to free up memory.
            It doesn’t interfere with main program logic and is automatically killed when the application ends.

        2. Background Logging
            Writing logs to a file or remote server in the background.
            Prevents logging overhead from affecting the main thread’s performance.

        3. Caching & Lazy Initialization
            Background caching of data or preloading objects.
            If the main thread finishes, the cache thread should exit gracefully.

# What is a Monitor Lock (intrinsic lock)?
    Every Java object has an associated monitor, which can be thought of as a lock. When a thread wants to execute synchronized code, it must first acquire the object's monitor lock. While one thread holds the lock, all other threads trying to access the same monitor (i.e., synchronized block/method) are blocked until the lock is released.
    
    What is a Monitor Lock?
        Conceptually:
            Only one thread can hold a monitor lock on a particular object at any time.
            It's used to ensure atomic access to critical sections of code.
            The lock is automatically released when the synchronized method or block completes (or throws an exception).

        A monitor is a synchronization construct that combines:
            A lock (mutex) → ensures only one thread executes critical code at a time
            A condition queue → enables threads to wait and be notified (wait/notify)
        In Java, every object automatically acts as a monitor.
  
    Where It's Used
        1. Synchronized Instance Method
            public synchronized void increment() {
                count++; // only one thread at a time can execute this
            }
            Here, the monitor lock is on the instance (this object).

        2. Synchronized Static Method
            public static synchronized void incrementStatic() {
                staticCount++;
            }
            Lock is on the Class object (ClassName.class).

        3. Synchronized Block
            synchronized (someObject) {
                // critical section
            }
            Here, the monitor lock is explicitly on someObject.
    
    Reentrant Nature of Monitor Locks
        Java monitor locks are reentrant.
            synchronized void outer() { inner();}
            synchronized void inner() { // same thread can re-enter }
        Same thread can acquire the same lock multiple times, so JVM tracks a lock count.
    
# wait notify and notifyAll
    These methods only work with monitor lock.
    Key Rules
        Must be called inside synchronized code
        The thread must own the monitor

    wait()
        Causes the current thread to wait until another thread calls notify() or notifyAll() on the same object.
        The thread releases the lock on the object and enters the WAITING state.

    notify()
        Wakes one thread that is waiting on the object’s monitor.
        The awakened thread cannot proceed until it re-acquires the monitor lock.

    notifyAll()
        Wakes all threads waiting on that monitor.

# notify vs notifyAll
    Pitfall with notify()
        If multiple threads are waiting for different conditions on the same object and you use notify(), you might accidentally wake a thread that can't proceed:

        Thread 1: wait until buffer is not empty  
        Thread 2: wait until buffer is not full

        Producer calls `notify()`  
        → might wake Thread 2 (waiting for space), but buffer is still full  
        → Thread 2 goes back to wait  
        → Deadlock or wasted wakeup
    notifyAll() avoids this by waking all, and each thread re-checks its own condition.

# join() method
    What Is Thread.join()?
        It tells the calling thread (like main) to wait for the thread on which join() is called to complete.
    Syntax:
        thread.join();          // Waits indefinitely until 'thread' finishes
        thread.join(millis);    // Waits up to 'millis' milliseconds
    How It Works Internally
        The calling thread enters a WAITING or TIMED_WAITING state (based on method variant).
        Once the target thread (t) completes, the waiting thread is notified and resumes execution.
        It uses wait() internally (native implementation at JVM level), which is why join() throws InterruptedException.
    Real-Life Use Case
        Scenario: Download a file in one thread and only process it after the download completes.
            downloadThread.start();
            downloadThread.join(); // Wait until download is complete
            processFile();         // Safe to call now

# Producer consumer problem
    class Buffer {
        private final Queue<Integer> queue = new LinkedList<>();
        private final int capacity = 5;

        public synchronized void produce(int val) throws InterruptedException {
            while (queue.size() == capacity) {
                wait(); // Thread enters wait set and releases lock
            }
            queue.add(val);
            notifyAll(); // Wake up waiting consumers
        }

        public synchronized int consume() throws InterruptedException {
            while (queue.isEmpty()) {
                wait(); // Thread enters wait set and releases lock
            }
            int val = queue.poll();
            notifyAll(); // Wake up waiting producers
            return val;
        }
    }

    Step 1: Producer Thread Starts
        Thread-Producer enters produce() method.
        Acquires monitor lock on Buffer object.
        Checks while (queue.size() == capacity), suppose queue.size() is 5, i.e., the buffer is full.

    Step 2: wait() is Called in produce()
        Producer thread releases the monitor lock on Buffer.
        Thread enters the object's wait set and is suspended, it is added to the wait set of the Buffer object.
        Producer is now paused, not consuming CPU.

    Step 3: Consumer Thread Runs
        Thread-Consumer enters consume() method.
        Acquires monitor lock on Buffer.
        Finds that queue is not empty, calls queue.poll() to remove an item.
        Calls notifyAll(), it wakes up all threads in the wait set of Buffer:
            Buffer wait set: [Thread-Producer] → notified
            But Thread-Producer cannot resume immediately — it has to reacquire the monitor lock.

    Step 4: Thread-Consumer Exits synchronized
        Thread-Consumer exits the consume() method.
        This releases the monitor lock on Buffer.
        Now, Thread-Producer (which was waiting) is moved from waiting → blocked, waiting to reacquire the lock.

    Step 5: Thread-Producer Reacquires Lock
        Thread-Producer finally gets the monitor lock.
        Resumes execution after the wait() line.
        Adds item to the queue
        Calls notifyAll() to wake up any consumer(s).
        Exits synchronized block, releasing the lock again.    

# Optimistic vs Pessimistic Locking
    These are two different approaches to handle concurrent access to shared data.
    1. Pessimistic Locking
        Assumes conflicts are likely.
        Protects shared data by locking it before access.
        Blocks other threads until the lock is released.

        Use When:
            High chance of data being modified by multiple threads.
            Conflicts must be strictly avoided.
            In Java:
                synchronized (object) {
                    // exclusive access
                }
                or
                lock.lock();  // e.g. ReentrantLock, ReadWriteLock
                try {
                    // access shared data
                } finally {
                    lock.unlock();
                }

        Pros:
            Safe and consistent.
            Simple to reason about.

        Cons:
            Reduces concurrency.
            Can lead to deadlocks or thread starvation.

    2. Optimistic Locking
        Assumes conflicts are rare.
        Reads data without locking.
        Before writing, checks whether data was changed in the meantime.
        If changed → retry or fail.

        Use When:
            Many reads, very few writes.
            High concurrency is desired.
            In Java (with java.util.concurrent.atomic):
                AtomicInteger counter = new AtomicInteger(0);

                // Read-modify-write cycle
                int prev, next;
                do {
                    prev = counter.get();
                    next = prev + 1;
                } 
                while (!counter.compareAndSet(prev, next)); // CAS check
            Or in databases: using a version number or timestamp field for compare-and-update.

        Pros:
            High performance for read-heavy workloads.
            No blocking = more scalability.

        Cons:
            Risk of repeated failures (CAS retries).
            Requires additional logic (e.g., version tracking).

# ReadWriteLock
     What is a ReadWriteLock?
        A ReadWriteLock is a special type of lock in Java (java.util.concurrent.locks.ReadWriteLock) that allows:
        Multiple readers to access a resource simultaneously, if no thread is writing.
        One writer to access the resource exclusively, i.e., no other readers or writers can access it.
        Java provides ReentrantReadWriteLock as a concrete implementation.

    Why is it used?
        To improve concurrency when frequent reads and occasional writes are required.

    Problem with synchronized or ReentrantLock:
        Traditional locks are exclusive — whether reading or writing, only one thread at a time can hold the lock.
        This limits scalability and performance in read-heavy scenarios.

    ReadWriteLock Solution:
        Multiple threads can read in parallel, improving throughput.
        Only blocks reads when a write is ongoing, or vice versa.

    How it works?
        ReentrantReadWriteLock provides:
            readLock() — Acquires a shared read lock.
            writeLock() — Acquires an exclusive write lock.
    
    Real-World Use Cases
        1. Caching Systems
            Frequently read cached data.
            Occasionally update the cache (e.g., eviction or refresh).
            ReadWriteLock allows concurrent cache reads and exclusive updates.

        2. Configuration Management
            App reads config values frequently.
            Admin updates config occasionally (e.g., reloading settings).
            Readers don’t block each other, but wait if an update is happening.

        3. Routing Tables / Dictionary Structures
            Multiple threads read routes or look up words.
            Only one thread updates the structure at a time.

        4. Analytics / Reporting Dashboards
            Many users query data.
            Only backend thread writes updated statistics.

    Important Considerations
        Deadlocks: Mixing read and write locks carelessly can cause deadlocks.
        Starvation: Readers may starve writers if reads are continuous.
        ReentrantReadWriteLock can be constructed as fair to avoid starvation.
        Not suitable for write-heavy scenarios: If writes are frequent, it may degrade performance compared to simpler locks.

# StampedLock
    What is StampedLock?
        StampedLock provides a lightweight, highly scalable, and flexible lock mechanism supporting three modes of access:
            Write Lock (exclusive)
            Read Lock (shared)
            Optimistic Read Lock (optimistic, no actual locking)

        Unlike traditional locks, StampedLock returns a stamp (a long token) representing the lock state, which must be used to release the lock or validate optimistic reads.

    What Problem Does StampedLock Solve?
        Traditional read-write locks like ReentrantReadWriteLock allow multiple readers or one writer, but:
            Read locks are pessimistic: readers block writers and vice versa.
            Performance issues under high contention, especially when reads are frequent and writes are rare.
            Readers can block writers even if they only want to read and the write is waiting.

        StampedLock introduces optimistic reading, which allows threads to:
            Proceed without blocking or acquiring a heavy read lock.
            Later validate if no write occurred during their read.

        This is particularly efficient when writes are rare, enabling much better throughput and scalability for mostly-read scenarios.
    
    Optimistic Read
        Does not block writers.
        Returns a stamp that can be used to validate later.
        If no write occurred since the stamp was issued, the read is valid.
        If validation fails (write occurred), you must retry or fallback to acquiring a read lock.

    Real-World Use Cases
        High-performance caching systems
            Multiple threads reading cached data.
            Writes (cache updates) happen rarely.
            Optimistic reads improve throughput by avoiding blocking.

        In-memory databases or data structures
            Heavy read workloads with occasional writes.
            Consistency checked after reads instead of locking.

        Analytics and monitoring systems
            Many concurrent read queries.
            Infrequent updates to statistics or counters.

        Gaming / real-time systems
            Multiple threads reading shared game state.
            Occasional state updates.

    When to Use and Limitations
        Use when read operations dominate and writes are rare.
        If writes are frequent or heavy, the optimistic approach loses advantage.
        Not reentrant, so careful design is required.
        Validation failures can cause retries, adding complexity.

# Why are StampedLocks Non-Reentrant?
    What is reentrancy?
        Reentrancy means that a thread can acquire the same lock multiple times without blocking itself. For example, with ReentrantLock or synchronized, a thread can safely enter the same critical section again if it already holds the lock.

    Why StampedLock is not reentrant:
        -> StampedLock is designed for maximum performance and low memory footprint, especially for optimistic reads.
        -> To achieve this, it avoids maintaining complex internal state about which thread holds the lock, unlike           ReentrantLock which keeps ownership and count.
        -> There is no internal tracking of thread identity — the lock only returns a stamp (long) when acquired, and that stamp is required to release it.
        -> Because it doesn’t track ownership:
            If the same thread calls writeLock() twice, it blocks itself (deadlock).
            If a thread tries to release a lock it didn’t acquire (wrong stamp), the result is undefined behavior.

    So, to keep StampedLock fast and lightweight, thread-ownership tracking (needed for reentrancy) is skipped, making it non-reentrant by design.

# What Is a Lock-Free Mechanism?
    A lock-free mechanism allows multiple threads to operate on shared data concurrently without using locks. Instead of blocking or waiting, threads use atomic operations like compareAndSet() (CAS) to try updates and retry if there's a conflict.
    Key Properties of Lock-Free Algorithms
        Property	    Meaning
        Non-blocking	No thread ever gets suspended or waits for another to release a lock.
        Lock-free	    At least one thread always makes progress, even under contention.
        Wait-free	    Every thread completes its operation in a bounded number of steps.
        Note: All wait-free algorithms are lock-free, but not all lock-free are wait-free.

    Why Lock-Free Avoids Deadlocks
        Deadlocks in lock-based systems happen when:
        Threads hold locks and wait for others to release them in a circular dependency.

    A thread gets blocked indefinitely.
        Lock-free systems avoid deadlocks because:
        No blocking or waiting: threads don’t hold exclusive locks.
        No ownership tracking: resources aren’t held, so no circular waits.
        Even if a thread is suspended, others can continue progressing.
        Progress is guaranteed for at least one thread (lock-freedom).
    
    Challenges of Lock-Free
        Complexity: Harder to write and reason about.
        Livelocks: Threads may keep retrying due to CAS failures.
        No fairness: Some threads may starve indefinitely.
    
    ABA Problem
        What Is the ABA Problem?
            The ABA problem happens when:
                Thread T1 reads a value 
                Thread T2 changes A → B → A
                Thread T1 performs compareAndSet(A, newValue)
                CAS succeeds, but the state is not the same logically
            CAS checks value equality, not history.
            Example
                AtomicInteger x = new AtomicInteger(100);
                // Thread T1
                int value = x.get();   // sees 100
                // ... paused ...

                // Thread T2
                x.set(200);
                x.set(100);            // ABA happens

                // Thread T1 resumes
                boolean success = x.compareAndSet(100, 101); // ✅ succeeds


        This is Solvable with AtomicStampedReference.
        Example:
            AtomicStampedReference<Integer> ref = new AtomicStampedReference<>(100, 0);

            // Thread T1
            int[] stampHolder = new int[1];
            int value = ref.get(stampHolder);
            int stamp = stampHolder[0];

            // Thread T2
            ref.compareAndSet(100, 200, stamp, stamp + 1);
            ref.compareAndSet(200, 100, stamp + 1, stamp + 2);

            // Thread T1 resumes
            boolean success = ref.compareAndSet(
                100, 101,
                stamp, stamp + 1
            ); // FAILS
        ABA detected
        CAS correctly fails

# What is CAS?
    CAS (Compare-And-Swap) is an atomic operation used in lock-free programming to safely update a value in a multi-threaded environment without using locks.

    How Does It Work?
        CAS compares the current value of a variable to an expected value, and if they match, updates it to a new value — atomically (no other thread can interfere in between).

    Why CAS is Useful?
        It lets multiple threads try to update a shared variable without blocking each other.
        Each thread:
            Reads the value -> erforms logic locally -> try to update using compareAndSet -> if fail then retry

    Typical CAS Retry Pattern
        AtomicInteger counter = new AtomicInteger(0);

        void increment() {
            int prev;
            int next;
            do {
                prev = counter.get();
                next = prev + 1;
            } while (!counter.compareAndSet(prev, next));
        }
        This loop retries until it successfully updates the value.

    AtomicInteger works by combining:
        volatile memory semantics | CPU-level CAS (Compare-And-Set) | Retry loops (spin loops) | Optimistic concurrency: try, verify, retry.

    Why It's Atomic
        CAS is typically a single CPU instruction (e.g., LOCK CMPXCHG on x86).
        It executes atomically even on multi-core CPUs — no locks needed.

# Make a blog on speed difference between lock-based and lock-free mechanisms
    Consider two scenarios, read-heavy less writes high concurrency required(lock-free mechanism preffered) and one write-heavy, less reads and consistency expected.

# Volatile keyword in java
    The volatile keyword in Java is used to ensure visibility of changes to variables across threads. It is part of the Java Memory Model and plays a crucial role in concurrent programming.
    How is Data Cached Locally?
        CPU Caches
            Modern CPUs have multiple layers of cache (L1, L2, L3).
            When a thread is running on a CPU core accesses a variable, the CPU loads it into its core-local cache to speed up access.
            Reads and writes happen mostly in this local cache, not directly in main memory.
        Java Memory Model (JMM) & Thread Caches
            Java threads can also have thread-local caches — the JMM allows JVM and CPU to cache variables per thread to optimize performance.
            Without proper synchronization, a thread might keep using a stale value from its cache instead of reading the latest value from main memory.
            This is why changes made by one thread may not be immediately visible to others.

    What Happens Without volatile?
        Thread A writes to a variable flag = true.
        Thread B may keep reading from its cached copy of flag which is still false.
        So Thread B never sees the update unless there's some synchronization or happens-before relationship.

    How volatile Prevents Stale Reads
        The volatile keyword tells the JVM and CPU:
        Always read and write this variable directly from/to main memory, not from caches.
        Internally:
            When a thread writes to a volatile variable:
                It flushes the updated value from its local cache to main memory.
            When a thread reads a volatile variable:
                It invalidates its local cached copy and reads fresh value from main memory.
        This ensures:
            No thread ever caches the volatile variable's value locally for long.
            Each read sees the most recent write by any thread.

    Example Without volatile (Wrong Behavior)
        class MyTask extends Thread {
            boolean running = true;

            public void run() {
                while (running) {
                    // do something
                }
            }

            public void stopTask() {
                running = false;
            }
        }
        Here, the main thread might call stopTask(), but the thread running run() might not see the updated false value, because running could be cached locally.

    With volatile (Correct Behavior)
        class MyTask extends Thread {
            volatile boolean running = true;

            public void run() {
                while (running) {
                    // do something
                }
            }

            public void stopTask() {
                running = false;
            }
        }
        Now, running is always read from main memory, not from thread-local cache.


# Thread pool
    In Java, a ThreadPool is a managed collection of reusable threads used to execute tasks concurrently. Instead of creating a new thread for every task (which is expensive), a thread pool reuses existing threads, improving performance and resource utilization.
    https://notebook.zohopublic.in/public/notes/74tdo0e297bb7d6dd4d45a837d13f60fedc3f
    This also has an important conceptual question of determining the pool size for your application.

    Why Thread Pools Exist (The Core Problem)
        Creating a thread is expensive:
            OS thread creation | Stack allocation | Context switching | Unbounded resource usage
            Bad pattern
                new Thread(task).start();
            Problems: Unbounded thread creation, No lifecycle control, CPU oversubscription, Memory exhaustion

    ThreadPool Architecture
        Java provides thread pools through the java.util.concurrent package — mainly using the Executor Framework.
        Main components:
            Executor: Interface for executing tasks.
            ExecutorService: Extends Executor and adds methods to manage and control the execution of threads.
            ThreadPoolExecutor: Core implementation of thread pooling.
            Executors: Utility class for creating commonly used thread pools.
        Tasks are queued -> Worker threads pull & execute -> Threads are reuse `
        All standard pools are configurations of ThreadPoolExecutor.
    
    ThreadPoolExecutor — The Heart
        Constructor (Critical)
        ThreadPoolExecutor(
            int corePoolSize,    //Minimum number of threads, threads are kept even when idle
            int maximumPoolSize, //Absolute upper bound, used only when queue is full
            long keepAliveTime,  //How long extra threads live, Core threads don’t time out by default
            TimeUnit unit,
            BlockingQueue<Runnable> workQueue, //LinkedBlockingQueue, ArrayBlockingQueue, SynchronousQueue
            ThreadFactory threadFactory,
            RejectedExecutionHandler handler
        )
        Every thread pool decision comes from these 7 parameters.
    
    Rejection Policies(implemented by RejectedExecutionHandler)
        What happens when the pool is saturated?
        Policy	            Behavior
        AbortPolicy	        Throws exception (default)
        CallerRunsPolicy	Caller executes task
        DiscardPolicy	    Silently drops
        DiscardOldestPolicy	Drops oldest queued
    
    Lifecycle Management
        executor.shutdown();
            Initiates a graceful shutdown.
            The executor stops accepting new tasks and continues running already submitted tasks (those in the queue or currently executing).
            The executor is not immediately terminated — use when you need to wait for completion of all tasks.

        executor.awaitTermination(10, TimeUnit.SECONDS);
            Ilocks the calling thread until either all tasks are finished and executor is fully terminated or the given timeout expires.
            Returns: true if the executor terminated within the timeout and false if the timeout expired before termination.
            Use when you want to call shutdown(), to wait for completion within a time window.

        List<Runnable> unstartedTasks = executor.shutdownNow();
            This attempts to stop all actively executing tasks, revents waiting tasks from starting and returns a List<Runnable> of tasks that were submitted but not started yet.
            It interrupts running tasks, but tasks must be written to respond to interruptions (i.e., check Thread.currentThread().isInterrupted()).
            Use in emergencies — when you need to stop everything immediately or when tasks are blocking or stuck and must be terminated now.
    
    CPU vs IO Bound Pools (Sizing Rules)
        CPU-Bound
            pool size ≈ number of cores
            Runtime.getRuntime().availableProcessors();
        IO-Bound
            pool size ≈ cores × (1 + wait/compute)
        Often much larger than CPU count.

    execute() vs submit()
        threadPool.execute():
            It is fire and forget, doesn't look for any result and you cannot check the completion of the task.
            If the task throws an exception, it’s handled by the thread’s UncaughtExceptionHandler.
        threadPool.submit():
            It is used when a return is expected, return type is a Future<?> object.
            Can accept Runnable or Callable.
            It can get the result check the completion and even can cancel the task.
                Can retrieve the result (future.get()).
                Can cancel the task (future.cancel()).

# ThreadPoolExecutor and it's internal working(to be added)
    newFixedThreadPool(int nThreads),  
        Use When:
            You have a fixed number of threads to process tasks concurrently.
            You want to limit resource usage.
            Tasks are predictable in execution time.

        //min and max size is same, linked queue is used which can grow infinitely
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        Potential issue: If too many tasks are submitted too quickly, queue grows endlessly → risk of OutOfMemoryError.

    newCachedThreadPool()
        Use When:
            Tasks are very short-lived.
            You have many bursty tasks.
            You want maximum responsiveness.

        Uses unbounded max thread count and unbounded queue.
        Threads are cached and reused if idle.

        //pool size is 0 and max pool size is INT_MAX, blocking queue with size 0(no queue is used)
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        If task rate is high and tasks are slow → creates too many threads, risking CPU/memory exhaustion.

    newSingleThreadExecutor()
        Use When:
            Tasks must be executed sequentially (one after another).
            You need to serialize access to a resource.
            Useful for logging, file writes, or DB writes.

        //pool size and max size 1, with no queue and only one thread can be executed at one time
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

# Fork join pool executor
    Core Building Blocks
        1. Worker Threads (ForkJoinWorkerThread)
            Each worker has its own deque (double-ended queue).
            Each runs a work loop, executing or stealing tasks.

        2. Task Class (ForkJoinTask)
            Lightweight, forkable units of work.
            Subclasses:
                RecursiveTask<V> → returns a result.
                RecursiveAction → no result.

        3. Deque (Work Queue)
            Each thread has its own work-stealing queue.
            Owner thread pushes/pops from the bottom (LIFO).
            Stealer thread steals from the top (FIFO).

    Execution Flow (Step-by-Step)
        Step 1: Task Submission
            You call ForkJoinPool.invoke(task) or submit().
            Root task goes into a worker thread's work queue (deque).

        Step 2: Computing and Task Forking (Divide Phase)
            Suppose you are dividing in two tasks left and right. First implement the compute() method this is where the work happens.
            Inside compute(), you call fork() on subtasks:
                left.fork(), this task is pushed to the bottom of the current thread's deque.
            Current thread continues with another task right.compute().

        Step 3: Task Execution
            The thread pops tasks from its deque from the bottom (LIFO).
            If a thread’s deque becomes empty, it enters work-stealing mode.

        Step 4: Work Stealing (Load Balancing)
            Idle thread picks another thread at random.
            Tries to steal from the top of that thread’s deque (FIFO).
            This minimizes contention since owners push/pop from the bottom.

        Step 5: Join Phase
            After forking subtasks, the main task uses join() to wait for results.
            If subtasks aren't done, current thread may: left.join()
                Help run pending tasks (help join),
                Or wait for others to finish.
        
# Workstealing pool (V imp)
    The Work Stealing Pool Executor is a type of thread pool introduced in Java 8, built on top of the Fork/Join framework. It is created via:
        ExecutorService executor = Executors.newWorkStealingPool();
        //internally it calls forkjoinpool.commonPool();
    Under the hood, it uses the ForkJoinPool to allow parallel execution of tasks using work stealing – an algorithm that improves thread utilization and throughput by allowing idle threads to "steal" work from busy threads.

    What is Work Stealing?
        In a work-stealing algorithm:
            Each thread maintains a local task queue.
        When a thread finishes its tasks and becomes idle, instead of staying idle, it:
            Steals tasks from the tail of another thread’s queue.
            This reduces contention and improves CPU usage across cores.
            This model is ideal for short, independent tasks that can be run in parallel.
    
    Benefits
        Highly parallel: uses multiple cores effectively.
        Better CPU utilization: through work stealing.
        Automatic load balancing: no need to manually partition tasks.
        Lightweight threads with local queues avoid contention.

    Limitations
        Not suitable for blocking tasks (e.g., I/O, waiting on locks).
        Unpredictable ordering: tasks may execute in non-deterministic order.
        No task management like invokeAll() or Future.get() guarantees in sequencing.

# Make a blog on speed difference between normal sort and fork join sort and post it.

# Check how many parallel threads are running during execution of my program
    You can monitor how many threads are actively running in parallel in your ForkJoinPool during execution using the following methods:

    1. Use ForkJoinPool Monitoring Methods
        If you're using a custom ForkJoinPool (which is recommended for isolation and observability), you can use these built-in methods:
            ForkJoinPool pool = new ForkJoinPool();
                System.out.println("Parallelism: " + pool.getParallelism());
                System.out.println("Pool Size: " + pool.getPoolSize());
                System.out.println("Active Threads: " + pool.getActiveThreadCount());
                System.out.println("Running Threads: " + pool.getRunningThreadCount());
                System.out.println("Queued Task Count: " + pool.getQueuedTaskCount());
                System.out.println("Steal Count: " + pool.getStealCount());
                
            Method meanings:
                Method	Description
                getParallelism()	Configured parallelism level (number of worker threads)
                getPoolSize()	Current number of threads in the pool
                getActiveThreadCount()	Threads currently executing tasks
                getRunningThreadCount()	Threads not blocked/waiting
                getQueuedTaskCount()	Estimated number of tasks awaiting execution
                getStealCount()	Number of tasks "stolen" for load balancing


# what is a ThreadLocal
    What is ThreadLocal?
        ThreadLocal<T> is a class in java.lang that allows you to store variables that are local to the thread — each thread sees a completely separate value, even though the variable is declared only once.
        public class ThreadLocal<T>
        
    Real-World Analogy
        Imagine a thread is a student, and ThreadLocal is like a notebook. Every student gets their own personal notebook, even though there's only one class notebook variable. Each student writes in and reads from their own copy.

    Why Use ThreadLocal?
        To avoid:
            Shared mutable state between threads
            The need for synchronization
            Passing variables manually through method chains in thread-specific operations

    Common Use Cases
        User session data in web applications, Database connection per thread, Logging context (MDC in Log4j/SLF4J)

    Example: Using ThreadLocal
        public class ThreadLocalExample {

            private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

            public static void main(String[] args) {
                Runnable task = () -> {
                    Integer value = threadLocal.get();
                    System.out.println(Thread.currentThread().getName() + " initial: " + value);
                    threadLocal.set(value + 1);
                    System.out.println(Thread.currentThread().getName() + " updated: " + threadLocal.get());
                };

                Thread t1 = new Thread(task, "Thread-A");
                Thread t2 = new Thread(task, "Thread-B");

                t1.start();
                t2.start();
            }
        }
        Output (order may vary):
            Thread-A initial: 1
            Thread-A updated: 2
            Thread-B initial: 1
            Thread-B updated: 2
            Each thread has its own Integer value — no interference, no locking.

    Cleaning Up: threadLocal.remove()
        You should always call threadLocal.remove() when you're done — especially in:
            Thread pools | Web servers | Async frameworks
        Why? Because threads are reused, and leftover values may leak across requests.    

# Future and callable in java
    https://notebook.zohopublic.in/public/notes/74tdo4a0bd722efc748d0909fbb498fa21b18
    What is a Future?
        In Java, a Future represents the result of an asynchronous computation — something that may not be available yet, but will be in the future.
        You can think of it like a "promise" — when you submit a task, you get back a Future.
        Typical Usage
            You don’t create Future objects manually — instead, you get them from ExecutorService when you submit a Callable or Runnable.
            Example -> inside multithreading notebook

        Key Methods Explained:
            get()	Blocks until the result is ready.
            get(timeout, unit)	Waits for the given time, else throws TimeoutException.
            isDone()	Returns true if the computation is completed (successfully, failed, or cancelled).
            cancel(boolean mayInterruptIfRunning)	Attempts to cancel the task.
            isCancelled()	Returns true if the task was cancelled.
        
        Important: Future.get() is Blocking
            Calling get() halts the current thread until the computation finishes.
            If you have multiple futures, this can cause inefficiency.
            That’s why newer APIs like CompletableFuture exist — they support non-blocking async callbacks.
        
        Limitations of Future
            Blocking -> get() blocks until result is available.
            No Chaining	-> You can’t specify “when result is ready, do X”.
            No Combining ->	You can’t combine multiple futures easily.
            No Exception Handling -> Limited support for handling async errors.
        
        Replaced / Enhanced by CompletableFuture
            Introduced in Java 8, CompletableFuture extends Future and CompletionStage, adding:
                Non-blocking methods (thenApply, thenAccept)
                Chaining and composition
                Exception handling (exceptionally)
                Async execution
            Example:
                CompletableFuture.supplyAsync(() -> 10)
                    .thenApply(x -> x * 2)
                    .thenAccept(System.out::println);
            Output: 20 (executed asynchronously, no blocking)

    What is callable?
        Callable represent the task which needs to be executed just like a Runnable, but the difference is Runnable does not return anything while callable returns an output or can throw a checked exception.

        Callable Interface Definition
            public interface Callable<V> {
                V call() throws Exception;
            }
            Key points:
                V → return type (generic)
                call() → method executed by the thread
                can return a value or can throw checked exceptions
        
        Callable Interface Definition
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Callable<Integer> task = () -> { return 10 + 20; };
            Future<Integer> future = executor.submit(task);
            Integer result = future.get();

        That's why our ExecutorService has 3 variations of submit:
            void submit(Runnable r)
            T submit(Runnable r, T result)
            <T> submit(Callable<T>)

        How Callable Works Internally
            1. When you call executor.submit(callable), the executor wraps it in a FutureTask.
            2. FutureTask implements both:
                Runnable → so it can be executed by a thread.
                Future<V> → so it can return a result or exception later.
            3. The worker thread runs call().
            4. When call() completes:
                The return value or exception is stored inside the FutureTask.
                Any thread waiting on get() is unblocked.
                
        Useful Methods with Callable
            submit(Callable<V> task) -> ExecutorService	executes a task and returns a Future<V>.
            invokeAll(Collection<Callable<V>>) -> ExecutorService executes multiple callables, waits for all.
            invokeAny(Collection<Callable<V>>) -> ExecutorService executes multiple callables, returns result of first completed.

    What is CompletableFuture?
        The Problem with Future
                future.get() blocks the current thread.
                if you have 10 tasks and you call future.get() continuously just after submitting each task then it won't work parallely in your executor service instead first submit all the tasks then call .get() method for any thread.
                There is a better modern approach: CompletableFuture, with which comes more power.

        Core Idea
            CompletableFuture is built around pipelines of tasks (called stages) where each stage can:
                Transform the result (thenApply)
                Consume it (thenAccept)
                Run something afterward (thenRun)
                Combine it with another (thenCombine, thenCompose)
                React to completion or exception (whenComplete, exceptionally, handle)
                
            And each of these can run:
                Synchronously (in the same thread), or
                Asynchronously (in a separate thread or pool), all method uses Async in their end.

                Normal Method	Async Version	    Description
                thenApply()	    thenApplyAsync()	Transforms result in another thread
                thenAccept()	thenAcceptAsync()	Consumes result asynchronously
                thenRun()	    thenRunAsync()	    Runs next stage asynchronously
                thenCombine()	thenCombineAsync()	Combines two async results
                thenCompose()	thenComposeAsync()	Chains dependent async stages

            
            CompletableFuture is a class in java.util.concurrent that implements both:
                Future<T> (represents an async result)
                CompletionStage<T> (represents a stage in an async computation pipeline)
                Common Factory Methods
                    Method	                    Description
                    runAsync(Runnable)	        Runs a task that doesn’t return a value.
                    supplyAsync(Supplier<U>)	Runs a task that returns a value.
                    completedFuture(value)	    Returns an already completed future with the given value.          
        
        NOTE: By default if no Executor service is provided it uses ForkJoinPool as default, you can define your own pool too.
            ExecutorService executor = Executors.newFixedThreadPool(3);
            CompletableFuture.supplyAsync(() -> 10, executor)
                .thenApplyAsync(x -> x * 2, executor)
                .thenAcceptAsync(System.out::println, executor);

        Exception Handling
            You can gracefully handle exceptions using:
            exceptionally()
                CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                    if (true) throw new RuntimeException("Error!");
                    return 42;
                }).exceptionally(ex -> {
                    System.out.println("Exception: " + ex.getMessage());
                    return 0;
                });

                System.out.println(future.get()); // prints "0"

            handle()
                Handles both result and exception:
                future.handle((result, ex) -> {
                    if (ex != null) return 0;
                    return result;
                });

# CountDownLatch
    CountDownLatch is a synchronization utility from java.util.concurrent that allows one or more threads to wait until a set of operations being performed in other threads completes.
    Think of it like a gate (latch) that stays closed until a counter reaches zero. Once the counter becomes 0, all waiting threads are released.
    1. Key Idea
        A CountDownLatch is initialized with a count value.
            Threads call countDown() → decreases the count
            Threads call await() → wait until the count reaches 0
        Once the count reaches 0, all waiting threads continue execution.
        Important: The latch cannot be reset, once it recheas 0 it's work is finished.

    2. Basic Example
        Scenario:
            Main thread waits until 3 worker threads finish their work.

        public class CountDownLatchExample {
            public static void main(String[] args) throws InterruptedException {
                int numberOfWorkers = 3;
                CountDownLatch latch = new CountDownLatch(numberOfWorkers);

                for (int i = 1; i <= numberOfWorkers; i++) {
                    new Thread(new Worker(latch, i)).start();
                }
                System.out.println("Main thread waiting for workers...");
                latch.await(); // wait until count becomes 0
                System.out.println("All workers finished. Main thread continues.");
            }
        }

        class Worker implements Runnable {
            private CountDownLatch latch;
            private int id;

            public Worker(CountDownLatch latch, int id) {
                this.latch = latch;
                this.id = id;
            }

            @Override
            public void run() {
                System.out.println("Worker " + id + " started");
                try {
                    Thread.sleep(2000); // simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Worker " + id + " finished");
                latch.countDown(); // reduce count
            }
        }
        Possible Output
        Main thread waiting for workers...
        Worker 1 started
        Worker 2 started
        Worker 3 started
        Worker 1 finished
        Worker 2 finished
        Worker 3 finishe
        All workers finished. The main thread continues after all workers call countDown().

    3. Real-World Use Cases
        Wait for services to start, ex -> Start 3 microservices before allowing requests.
        Parallel task completion
        Testing concurrent systems

# CyclicBarrier
    CyclicBarrier is a synchronization utility in java.util.concurrent that allows a group of threads to wait for each other until all reach a common barrier point.
    Once the required number of threads reach the barrier, all threads are released simultaneously and execution continues.
    The word "cyclic" means the barrier can be reused multiple times.
    1. Concept
        Imagine 3 runners reaching a checkpoint:
            Runner 1 arrives → waits
            Runner 2 arrives → waits
            Runner 3 arrives → barrier opens
        Now all runners continue together.

    2. Key Features
        Threads wait for each other
        Barrier releases all threads together
        Barrier can be reused (cyclic)
    
    3. Important Methods
        Method	                    Description
        CyclicBarrier(int parties)	Number of threads that must reach the barrier
        await()	                    Thread waits until all threads reach the barrier
        await(timeout, unit)	    Wait with timeout
        getNumberWaiting()	        Threads currently waiting
        reset()                 	Reset the barrier
    
    4. Example
        public class CyclicBarrierExample {
            public static void main(String[] args) {
                CyclicBarrier barrier = new CyclicBarrier(3, () -> {
                    System.out.println("All threads reached the barrier. Proceeding...");
                });
                for (int i = 1; i <= 3; i++) {
                    new Thread(new Worker(barrier, i)).start();
                }
            }
        }

        class Worker implements Runnable {
            private CyclicBarrier barrier;
            private int id;

            public Worker(CyclicBarrier barrier, int id) {
                this.barrier = barrier;
                this.id = id;
            }

            @Override
            public void run() {
                try {
                    System.out.println("Worker " + id + " performing task...");
                    Thread.sleep((long)(Math.random() * 3000));

                    System.out.println("Worker " + id + " waiting at barrier");
                    barrier.await(); // wait for others

                    System.out.println("Worker " + id + " passed the barrier");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        Output:
            Worker 1 performing task...
            Worker 2 performing task...
            Worker 3 performing task...

            Worker 2 waiting at barrier
            Worker 1 waiting at barrier
            Worker 3 waiting at barrier

            All threads reached the barrier. Proceeding...

            Worker 2 passed the barrier
            Worker 1 passed the barrier
            Worker 3 passed the barrier
    
    5. Barrier Action
        You can define a task that runs when the barrier is reached.
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println("All threads arrived. Starting next phase.");
        });
        This is called barrier action.
    
    6. Reusable Barrier (Why it’s called Cyclic)
        Unlike CountDownLatch, the barrier can be reused.
    
    7. CyclicBarrier vs CountDownLatch
        Feature	    CountDownLatch	                CyclicBarrier
        Reusable	No	                            Yes
        Wait logic	One thread waits for others	    All threads wait for each other
        Counter	    Decreases to zero	            Fixed number of threads
        Reset	    No                              Yes
    
# What is Core and what is a Thread and relation between them, how can i calculate max threads that my spring boot applicaiton can support up on a server(AWS EC2) for CPU and I/O tasks.
    What is a Core?
        A CPU core is a physical processing unit inside a processor.
            Think of a core as an actual worker that can execute instructions.
            Modern CPUs have multiple cores (2, 4, 8, 16, …).
            Each core can execute one instruction stream at a time (or more with special techniques like Hyper-Threading).
        Example:
            A quad-core CPU has 4 physical cores
            Each core can run tasks independently and in parallel
        
    What is a Thread?
        A thread is a logical unit of execution — basically a sequence of instructions.
        There are two common meanings:
            OS / Application Thread
                Created by a program (Java thread, C++ thread, etc.)
                Managed by the Operating System scheduler
                Represents “what needs to be executed”

            Hardware Thread (Logical CPU)
                Some CPUs support running multiple threads on one core like Intel Hyper-Threading
                One physical core appears as 2 logical cores
                Example:
                    4 cores with Hyper-Threading → 8 logical threads
    
    Core vs Thread (Simple Analogy)
        Imagine a kitchen:
            Concept     Analogy
            Core	    A chef
            Thread	    A recipe being cooked
            CPU	        The whole kitchen
            Scheduler	The manager assigning recipes
            
        One chef (core) can cook one recipe at a time
        With hyper-threading, the chef can switch quickly between two recipes
    
# How Java thread pools map to CPU cores
    Let’s go from OS → JVM → Thread Pools → CPU cores, no hand-waving.
    How Java Thread Pools Map to CPU Cores
    The Big Picture (End-to-End)
        Java Thread -> OS Thread (native) -> Logical CPU (core or hyper-thread) -> Physical Core
        Java threads are NOT green threads (since Java 1.3+):
            Each Java thread maps to one native OS thread
            OS scheduler decides which CPU core runs it

    JVM’s View of the CPU   
        int cpus = Runtime.getRuntime().availableProcessors();
        Returns logical processors which includes Hyper-Threading
        Example: 
            8 physical cores + HT → returns 16
        JVM does NOT know Which cores are physical and which are HT siblings

    What a Java Thread Pool Actually Is
        A thread pool is just a fixed or dynamic collection of Java threads waiting for tasks which is reused to avoid creation overhead
        Example: ExecutorService pool = Executors.newFixedThreadPool(8);
        These 8 threads are created in the JVM -> Each maps to one OS thread -> OS schedules them on available logical CPUs

    OS Scheduling Reality
        The OS:
            Sees N runnable threads
            Sees M logical CPUs
            Uses time slicing + priority
        If:
            Threads ≤ logical CPUs → can run in parallel
            Threads > logical CPUs → context switchinginte
        Java has no direct control over core assignment (without OS tricks)

    CPU-Bound Thread Pools (CRITICAL)
        CPU-Bound Task | Heavy computation | Minimal I/O | Tight loops
        Optimal Pool Size:
            threads ≈ physical cores
        But JVM only exposes logical cores! so practical rule is: 
            int threads = Runtime.getRuntime().availableProcessors() / 2; //on HT systems
            Why?
                HT threads compete for execution units so two CPU-heavy threads on same core will slow each other

    I/O-Bound Thread Pools
        Waiting on network, disk, DB | Threads mostly blocked
        Optimal Pool Size
            threads >> cores
        Rule of thumb:
            threads = cores * (1 + wait_time / compute_time)
        Example:
            16 logical CPUs
            Tasks wait 80% of the time -> 64+ threads may be ideal

    Affinity & Advanced Control (Rare but Powerful) 🧠
        Java does not support CPU pinning natively
        But:
            OS-level tools (taskset, cgroups)
            Container limits (Kubernetes CPU requests)
            JVM flags (-XX:ActiveProcessorCount)
        Example:
            java -XX:ActiveProcessorCount=4 MyApp
            JVM behaves as if only 4 CPUs exist

    Real-World Java Server Example
        16-core HT server → 32 logical CPUs
        Web Server Setup
            Request threads: 200 (I/O-bound) | DB pool: 50 | CPU workers: 16–20
        Separate pools for:
        I/O | CPU | Blocking work


# Async vs threaded architecture
    
# Java Virtual threads
    
# Lock-free vs Wait-free algorithms