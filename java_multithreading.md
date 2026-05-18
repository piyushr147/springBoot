# what happens inside JVM when a java program is created and executed
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
                Each thread has one.
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
    JVM has memory segments inside it, these can be Heap, Stack, Code segment, Data segment, Registers, Program counters.
    Now when a progrma is executed like java Main class.
    A new process is created
    A new JVM instance is created and allocate to that process.

# What are threads
    Whenever we start a program a process is created which has its own memory space, system resources, and at least one thread (the main thread).
    when a main function is called, main is the first thread that is created, in the program you can create multiple other threads.
    Heap, Code segment, Data segment are shared among all the threads.
    Stack, Registers, Program counters are individual for each thread, they do not share data of these memory areas.

# Ways of creating threads
    1. By Extending the Thread Class
        class MyThread extends Thread {
            public void run() {
                System.out.println("Thread is running...");
            }

            public static void main(String[] args) {
                MyThread t1 = new MyThread();
                t1.start();  // Start a new thread
            }
        }

    2. By Implementing Runnable Interface
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

# What is a Monitor Lock (intrinsic lock)?
    Every Java object has an associated monitor, which can be thought of as a lock. When a thread wants to execute synchronized code, it must first acquire the object's monitor lock. While one thread holds the lock, all other threads trying to access the same monitor (i.e., synchronized block/method) are blocked until the lock is released.

    Conceptually:
        Only one thread can hold a monitor lock on a particular object at any time.
        It's used to ensure atomic access to critical sections of code.
        The lock is automatically released when the synchronized method or block completes (or throws an exception).  
    Working:
        Every Java object has an associated monitor lock.
        The JVM checks if the thread can acquire the object's monitor.
        If no other thread holds it, the thread acquires it and continues
        If another thread already holds the monitor, the current thread waits (blocks).
        When the thread exits the synchronized code, the JVM releases the monitor.
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
     
# wait notify and notifyAll
    In Java, the wait(), notify(), and notifyAll() methods are used for thread communication — they let threads cooperate rather than just compete for locks.
    The Problem They Solve:
        Sometimes threads need to coordinate their actions.
        Example:
            Thread A (producer) adds data to a shared queue.
            Thread B (consumer) takes data from the same queue.
        If the queue is empty, the consumer shouldn’t spin uselessly — it should wait until the producer adds something.
        That’s where wait() and notify() come in.
    wait()
        Causes the current thread to wait until another thread calls notify() or notifyAll() on the same object.
        The thread releases the lock on the object and enters the WAITING state.
    notify()
        Wakes one of the threads waiting on the object’s monitor(chosen by JVM).
        The awakened thread cannot proceed until it re-acquires the monitor lock.
    notifyAll()
        Wakes all threads waiting on that monitor. Only one will reacquire the lock first.
    States Transition Diagram:
        State	            Trigger
        RUNNING → WAITING	wait()
        WAITING → BLOCKED	notify() or notifyAll()
        BLOCKED → RUNNING	Thread reacquires the monitor lock

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
    Both optimistic and pessimistic locking are strategies to handle concurrent access to shared data and prevent race conditions or inconsistent updates.
    Strategy	            Core Idea
    Pessimistic Locking	    “Assume conflict will happen.” → Lock the data before accessing it.
    Optimistic Locking	    “Assume conflict is rare.” → Allow concurrent access and check for conflicts after access.

    1. Pessimistic(Blocking) Locking (Syncronized, ReadWrite)
        Assumes conflicts are likely so it protects shared data by locking it before access.
        Blocks other threads until the lock is released, it follows a blocking mechanism.
        Use When:
            High chance of data being modified by multiple threads
            Conflicts must be strictly avoided.
            In Java:
                synchronized (object) {
                    // exclusive access
                }
                or
                lock.lock();  // e.g. ReentrantLock, ReadWriteLock (Explicit locks)
                try {
                    // access shared data
                } finally {
                    lock.unlock();
                }
        Pros:
            Safe and consistent.
            Simple to reason about.
        Cons:
            Reduces concurrency(threads spend time waiting).
            Can lead to deadlocks or thread starvation.
        When to Use:
            When conflicts are frequent.
            When operations are long-running and must be consistent.
            When you need strong consistency (e.g., banking transactions).

    2. Optimistic Locking (AtomicInteger, Stamped, CAS)
        Assumes conflicts are rare so it reads data without locking but before writing it checks whether data was changed in the meantime, it follows a non-blocking mechanism.
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
        When to Use:
            When conflicts are rare.
            When operations are short-lived.
            When high throughput and parallel reads are desired.

    Comparison table:
        | Concept            | Optimistic Locking            | Pessimistic Locking          |
        | ------------------ | ----------------------------- | ---------------------------- |
        | Lock Type          | Non-blocking                  | Blocking                     |
        | Typical Use        | Atomic variables, StampedLock | synchronized, ReentrantLock  |
        | Conflict Detection | After modification            | Before modification          |
        | Performance        | High under low contention     | Stable under high contention |
        | Failure Handling   | Retry mechanism               | Thread waits                 |
        | Use Case           | Read-heavy systems            | Write-heavy systems          |

# What Is a Deadlock?
    A deadlock is a situation where two or more threads are permanently blocked, waiting for each other to release locks they need.
    In simpler words:
        A deadlock happens when Thread A holds Lock 1 and waits for Lock 2,
        while Thread B holds Lock 2 and waits for Lock 1.
    Neither thread can proceed → system freezes.

    According to Coffman’s conditions, all 4 must hold true for a deadlock to occur:
        Condition	        Meaning
        Mutual Exclusion	A resource can be held by only one thread at a time.
        Hold and Wait	    A thread holds one resource and waits for another.
        No Preemption	    Resources can’t be forcibly taken away from a thread.
        Circular Wait	    A circular chain of threads each waiting on resources held by the next thread.
    If you can break any one of these conditions, you can avoid deadlock.

    How to avoid a deadlock:
        Consistent Lock Ordering
            Always acquire locks in a fixed global order, if resource r1 and r2 are to be locked in a program they should be locked in the same order everywhere(r1->r2 or r2->r1).
        Use Try-Lock (with Timeout)
            Instead of blocking forever, attempt to acquire a lock for a limited time.
        Use Higher-Level Concurrency Tools
            java.util.concurrent classes (ConcurrentHashMap, BlockingQueue, etc.)
            ExecutorService instead of manually controlling threads.

    How Optimistic Locks Can Cause Deadlocks
        Normally, optimistic locks (like CAS or StampedLock) are non-blocking and designed to avoid deadlocks.
        But under some conditions, they can simulate deadlock-like behavior or cause live locks
        Example:
            Both threads read value=10
            Thread A tries to write 11
            Thread B tries to write 12
            Both fail CAS -> retry -> both fail again
            Infinite loop of retries -> livelock
        StampedLock Deadlocks (Write + Read Conflicts)
            StampedLock is not reentrant — meaning if a thread holding a write lock tries to reacquire it or a read lock, it will deadlock itself.
            Example:
                StampedLock lock = new StampedLock();
                void doWork() {
                    long stamp = lock.writeLock();
                    try {
                        // Deadlock if we try to get another write/read lock here
                        long readStamp = lock.readLock();  // DEADLOCK!
                    } finally {
                        lock.unlockWrite(stamp);
                    }
                }
                Thread holds the write lock and waits for itself — a self-deadlock

    Difference: Deadlock vs Livelock
        Concept	Description
        Deadlock	Threads are blocked forever, waiting for each other.
        Livelock	Threads keep retrying or yielding, but no real progress is made.

# ReadWriteLock
    In real-world concurrent systems, most threads just read shared data, and only a few modify it. Using a regular synchronized lock or ReentrantLock causes unnecessary contention because:
        Traditional locks are exclusive - Only one thread can hold the lock — even if others just want to read.
        This limits scalability and performance in read-heavy scenarios.
    So Java provides the ReadWriteLock to increase concurrency:
        Multiple readers can hold the read lock simultaneously if no thread is writing.
        Only one writer can hold the write lock, and no readers can read while it writes.
        ReentrantReadWriteLock is the implemtation of it.
    Why is it used?
        To improve concurrency when frequent reads and occasional writes are required.
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
            No optimistic reading — readers still need to acquire a real lock, which adds overhead.            
            Readers can block writers even if they only want to read and the write is waiting.
        StampedLock introduces optimistic reading, which allows threads to:
            Proceed without blocking or acquiring a heavy read lock.
            Later validate if no write occurred during their read using a stamp.
        Hence the name “StampedLock”, because every operation returns a stamp (long value) that uniquely identifies the lock state. This is particularly efficient when writes are rare, enabling much better throughput and scalability for mostly-read scenarios.

    Locking Modes in Detail
        Mode	                Description	                            Blocking?   Exclusive?	Use Case
        writeLock()	            Blocks all readers/writers	            Yes	        Yes	        When writing or updating data
        readLock()	            Blocks writers, allows multiple readers	Yes	        No	        Safe, concurrent reads
        tryOptimisticRead()	    Doesn’t block at all, returns a stamp	No	        No	        Fast read; verify with validate()
            
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
    Reentrancy Needs Ownership Tracking
        For a lock to be reentrant, it must track:
            Which thread currently owns the lock.
            How many times that thread has entered the lock (hold count).
            That means maintaining:
                A Thread → Count mapping,
                Atomic checks to identify if the calling thread is the owner.
            All of that adds:
                Extra memory cost,
                Synchronization overhead on every lock() and unlock() call.
    StampedLock Uses a Stamp Instead of Ownership
        Instead of keeping track of “who” owns the lock, StampedLock uses a stamp (a long number) as a token.
        Each call to:
            long stamp = lock.readLock();
            or
            long stamp = lock.writeLock();
        returns a unique number representing the lock state at that moment.
        The lock doesn’t care which thread holds that stamp — it only knows that someone does.
        This design:
            Removes thread tracking,
            Makes lock operations cheaper and faster,
            But also means it can’t detect if the same thread is reacquiring the lock → hence non-reentrant. 
    What Happens If You Try to Re-enter a StampedLock
        Example:
            StampedLock lock = new StampedLock();
            long stamp1 = lock.writeLock();
            long stamp2 = lock.writeLock(); // 🚫 blocks forever (deadlock)
        The thread will wait for itself to release the first lock —but since it never releases it (it’s the same thread), it deadlocks.
        There’s no “this thread already owns it” check — because StampedLock doesn’t track thread ownership.   
    So, to keep StampedLock fast and lightweight, thread-ownership tracking (needed for reentrancy) is skipped, making it non-reentrant by design.

# What Is a Lock-Free Mechanism?
    The Problem with Locks
        When multiple threads access shared data, we usually use locks to prevent race conditions.
            Example:
            synchronized void increment() {
                count++;
            }
        This ensures only one thread can modify count at a time which is Safe — but not always fast.
        Problems with Locks:
            Blocking: If one thread holds the lock, others must wait.
            Deadlocks: Two threads may wait forever for each other’s locks.
            Priority inversion: A low-priority thread can block a high-priority one.
            Context switching: Waiting threads may cause OS scheduling overhead.
        These problems grow worse in high-contention, multi-core, and real-time environments.

    A lock-free mechanism allows multiple threads to operate on shared data concurrently without using locks. Instead of blocking or waiting, threads use atomic operations like compareAndSet() (CAS) to try updates and retry if there's a conflict.
    They don’t “own” resources — they just retry if a conflict happens.
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
        ABA Problem: CAS may pass when the value was changed and restored. Solvable with AtomicStampedReference.

# What is CAS?
    CAS (Compare-And-Swap) is an atomic operation used in lock-free programming to safely update a value in a multi-threaded environment without using locks.

    How Does It Work?
        CAS compares the current value of a variable to an expected value, and if they match, updates it to a new value — atomically (no other thread can interfere in between).

    Why CAS is Useful?
        It lets multiple threads try to update a shared variable without blocking each other.
        Each thread:
            Reads the value.
            Performs logic locally.
            Tries to update using compareAndSet.
            If fails, retries.

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

    Why It's Atomic
        CAS is typically a single CPU instruction (e.g., LOCK CMPXCHG on x86).
        It executes atomically even on multi-core CPUs — no locks needed.

# why so we use AtomicCounter for a shared variable in CAS and not normal variable
    The Root of the Problem — Shared Variable Updates Are Not Atomic
        Let’s start simple.
        If multiple threads update a normal variable like this:
            int counter = 0;
            void increment() {
                counter++; // not atomic
            }
        It looks like one simple operation, but under the hood it’s three separate CPU-level steps:
            Read counter from memory into a register
            Add 1
            Write the result back to memory
        Now imagine two threads do this at the same time, the result might not have that extra 1
        Using scynchronized can fix the problem but it takes resources puts lock, context switches and slows down the operation.
    How AtomicInteger Fixes It
        Internally, AtomicInteger maintains:
            A volatile int value, and
            Uses Unsafe.compareAndSwapInt() (or VarHandle CAS).
        This achieves two critical things:
            Volatile visibility → all threads see the most recent value.
            Atomic CAS at CPU level → updates are indivisible.
    What Happens Under the Hood
        When you call:
            counter.compareAndSet(oldVal, newVal);
        The JVM invokes Unsafe.compareAndSwapInt() (or VarHandle.compareAndSet()).
        This maps to the CPU instruction CMPXCHG (on x86 processors).
        The CPU performs this atomically on the variable’s memory location.
        Cache coherence protocols ensure all cores see the new value immediately.
    If two threads read the same oldValue, only one CAS succeeds.
    -> The loser retries until it succeeds.
    -> No locking, no blocking, no lost updates.

# Make a blog on speed difference between lock-based and lock-free mechanisms
    create 10000 threads with each thread incrementing the counter 10000 times in both approaches.

# Volatile keyword in java
    The volatile keyword in Java is used to ensure visibility of changes to variables across threads. It is part of the Java Memory Model and plays a crucial role in concurrent programming.
    How is Data Cached Locally?
        CPU Caches
            Modern CPUs have multiple layers of cache (L1, L2, L3).
            When a thread running on a CPU core accesses a variable, the CPU loads it into its core-local cache to speed up access.
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

    What volatile does NOT guarantee:
        Atomicity of compound operations
            Example:
                volatile int count = 0;
                count++; // not atomic (read-modify-write)
            Two threads may overwrite each other’s increments.
        Mutual exclusion
            It doesn’t block other threads — multiple threads can read/write simultaneously.
        Consistency for composite states
            It can’t guarantee thread-safe updates of multiple variables together.
    
    When to use volatile
        Use volatile when:
            You have a single shared variable.
            One thread writes, others read.
            You need visibility, not atomicity.

# why don't we use the volatile keyword directly on variable instead of AtomicInteger
    First, what volatile does
    When you mark a variable as volatile, it ensures visibility — i.e., when one thread updates the variable, other threads immediately see the new value.
    But volatile does not make compound operations atomic.
    For example:
        volatile int count = 0;
        public void increment() {
            count++; // not atomic!
        }
    The count++ operation actually breaks down into 3 steps:
        Read current value of count
        Add 1 to it
        Write new value back
    If two threads run this simultaneously:
        Both read count = 0
        Both compute count + 1 = 1
        Both write 1
        Final value = 1, not 2 
    So, volatile only ensures visibility, not atomicity.

# Thread pool
    In Java, a ThreadPool is a managed collection of reusable threads used to execute tasks concurrently. Instead of creating a new thread for every task (which is expensive), a thread pool reuses existing threads, improving performance and resource utilization.
    https://notebook.zohopublic.in/public/notes/74tdo0e297bb7d6dd4d45a837d13f60fedc3f
    This also has an important conceptual question of determining the pool size for your application.

# Thread pool executor
    newFixedThreadPool(int nThreads)
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

        Step 2: Task Forking (Divide Phase)
            Inside compute(), you call fork() on subtasks:
            The task is pushed to the bottom of the current thread's deque.
            Current thread continues with another task (typically the other subtask).

        Step 3: Task Execution
            The thread pops tasks from its deque from the bottom (LIFO).
            If a thread’s deque becomes empty, it enters work-stealing mode.

        Step 4: Work Stealing (Load Balancing)
            Idle thread picks another thread at random.
            Tries to steal from the top of that thread’s deque (FIFO).
            This minimizes contention since owners push/pop from the bottom.

        Step 5: Join Phase
            After forking subtasks, the main task uses join() to wait for results.
            If subtasks aren't done, current thread may:
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
            steals tasks from the tail of another thread’s queue.
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

# shutdown, awaitTermination and shutdownNow
    In Java's ExecutorService, the methods shutdown(), awaitTermination(), and shutdownNow() are used to gracefully or forcefully shut down a thread pool. Understanding how and when to use each one is crucial for clean multithreaded applications.

    shutdown()
        executor.shutdown();
        What it does:
            Initiates a graceful shutdown.
        The executor:
            Stops accepting new tasks.
            Continues running already submitted tasks (those in the queue or currently executing).
            The executor is not immediately terminated — you need to wait for completion.
        When to use:
            When you want to stop the executor after all current tasks finish.

    awaitTermination(timeout, unit)
        executor.awaitTermination(10, TimeUnit.SECONDS);
        What it does:
            Blocks the calling thread until either:
            All tasks are finished and executor is fully terminated.
            The given timeout expires.
        Returns:
            true if the executor terminated within the timeout.
            false if the timeout expired before termination.
        When to use:
            After calling shutdown(), to wait for completion within a time window.

    shutdownNow()
        List<Runnable> unstartedTasks = executor.shutdownNow();
        What it does:
            Attempts to stop all actively executing tasks.
            Prevents waiting tasks from starting.
            Returns a List<Runnable> of tasks that were submitted but not started yet.
            It interrupts running tasks, but tasks must be written to respond to interruptions (i.e., check Thread.currentThread().isInterrupted()).
        When to use:
            In emergencies — when you need to stop everything immediately.
            When tasks are blocking or stuck and must be terminated now.

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
        User session data in web applications
        Database connection per thread
        Logging context (MDC in Log4j/SLF4J)
        Request-scoped variables in multi-threaded environments

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

    Cleaning Up: remove()
        You should always call threadLocal.remove() when you're done — especially in:
            Thread pools, Web servers, Async frameworks
        Why? Because threads are reused, and leftover values may leak across requests.
        threadLocal.remove();
