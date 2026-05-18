# JRE, JVM and JDK 
    https://www.geeksforgeeks.org/java/differences-jdk-jre-jvm/

# Data types are divided into two groups:
    Primitive data types - includes byte, short, int, long, float, double, boolean and char
    Non-primitive data types - such as String, Arrays and Classes (you will learn more about these in a later chapter)
    primitive data types makes java a not 100% pure OOPS language.

# Access Specifiers
    Defines accessibility of a method i.e who can use the method
    There are 4 types of access specifiers in Java methods :
    1 -> Public : can be accessed through any class in any package .
    2 -> Private : can be accessed by methods only in the same class
    3 -> Protected : can be accessed by other classes in same package or other sub classes in different package by extending.
    4 -> Default : It can only be accessed by classes in same package and not inside other sub classes in different package by even by extending.
    If we do not mention anything , then Default access specifier is used by Java.

# Variable Arguments (Varargs) :
    Variable numberof inputs in the parameter.
    Only one variable argument can be present in the method.
        It should be the last argument in the list.
        Used when we don't know the number of arguments
    public int sum(int a,int b, int ...variables);
    
# Java uses pointers like C++ but internally uses them:
    In Java:
        You don’t get access to memory addresses.
        Instead, Java gives you references, which are safe, opaque pointers managed by the JVM.
    Example:
        String name = "Alice";
        String another = name;
    Here, both name and another refer to the same object in memory — like a pointer — but:
        You can’t see the memory address.
        You can’t perform pointer arithmetic (e.g., another++ is illegal).
    A reference variable (like an object or array) is actually a pointer to an object in the heap, primitive types (like int, float) are not references, and are stored by value.
    Object variables are essentially handles to memory locations.
    Security is also compromised if pointers are used because the users can directly access memory with the help of pointers.
    The usage of pointers can make the procedure of garbage collection quite slow and erroneous

# Enum class
    Because it implicitly extends java.lang.Enum class, and multiple extension is not supported by java.
        It can implement interfaces
        It can have variables, constructors, methods
        No other class can extend it, it's constructor are private.
        It can have abstract method and all the constants should implement that method.

    All the constants(enums) declared will have there own set of variables and methods inside enum, white and black will have each set of printDescription and variable for them.

    If you want a method to be common for all enums declare it as static.
    You can overrride the default printDescription to constant specific.
    Abstract method implementation should be provided by each constant.
        public enum EnumSample{
            WHITE(0,"i am white") {
                @Override
                public void printType() {
                    System.out.println("white type: " + type);
                }
            },
            BLACK(1,"i am black"){
                @Override
                public void printDescription(){
                    System.out.println("white type: " + type);
                }

                @Override
                public void printType() {
                    System.out.println("black type: " + type);
                }
            };

            final int type;
            final String description;

            EnumSample(int type, String description){
                this.type = type;
                this.description = description;
            }

            public void printDescription(){
                System.out.println(description);
            }
            
            public abstract void printType();
        }

# why constructor do not have a return type
    1. They are not regular methods
        Constructors are special functions whose purpose is to initialize a new object. They're called automatically when you use the new keyword.
            MyClass obj = new MyClass(); // Constructor is called here
    2. They don’t return anything — they return the object being constructed (implicitly)
        Unlike regular methods, constructors don’t need a return value(int,string, even void) because Java automatically returns the new object.
            new MyClass(); // returns a MyClass object — implicitly, without writing 'return'
        If constructors had return types, they’d behave like normal methods — which would break the entire object creation mechanism.
    3. If you put a return type, it becomes a method, not a constructor

# why constructor cannot be final static or abstract
    1. Why can't a constructor be final?
        The final keyword means a method cannot be overridden by subclasses.
        But constructors are never inherited, and therefore cannot be overridden anyway, So marking a constructor as final would be meaningless.

    2. Why can't a constructor be static?
        static means the method belongs to the class, not to an instance.
        But a constructor’s job is to create an instance.
            You cannot create an instance (an object) from a static context, because the instance doesn't exist yet.
            If constructors were static, they would have no access to instance members, which defeats their purpose.

    3. Why can't a constructor be abstract?
        abstract methods are meant to be overridden by subclasses.
        But constructors are not inherited, so they can’t be overridden.
            Also, you can’t instantiate abstract classes, and a constructor's main purpose is to enable instantiation.

# Constructor chaining using (this and super)
    Constructor chaining in Java means one constructor calls another constructor in the same class or in the superclass. It helps avoid code duplication and manage object initialization in a clean, organized way.

    Two Types of Constructor Chaining:
        1. Within the same class — using this(...)
            You use this() to call another constructor in the same class.
            class MyClass {
                MyClass() {
                    this(10);  // calls the parameterized constructor
                    System.out.println("No-arg constructor");
                }

                MyClass(int x) {
                    System.out.println("Parameterized constructor: " + x);
                }
            }
            Output:
                Parameterized constructor: 10  
                No-arg constructor

        2. From subclass to superclass — using super(...)
            You use super() to call a constructor from the parent class.
            class Parent {
                Parent() {
                    System.out.println("Parent constructor");
                }
            }
            class Child extends Parent {
                Child() {
                    super(); // optional if Parent has a no-arg constructor
                    System.out.println("Child constructor");
                }
            }

            Output:
                Parent constructor  
                Child constructor
        
    Rules of Constructor Chaining:
        this() and super() must be the first statement in a constructor.
        You cannot use both this() and super() in the same constructor.
        If you don’t explicitly call super(), the compiler adds super() by default, but if you want to call a parameterized constructor of parent you have to mention the super(int age,String name) in the very first line of your child constructor.

    Why Use Constructor Chaining?
        To reduce redundancy in constructor logic
        To ensure base class initialization is done before subclass construction
        To create a clear flow of object construction

# Can we mark a class as private or protected in java?
    You can't declare top-level classes as private or protected however, nested classes (also known as inner classes) can be declared with private or protected access modifiers. Tn Java comes down to accessibility rules and how Java's compilation model works.
    Here's Why You Can't Do It:
        1. Top-Level Classes Must Be Accessible to the JVM
            javac compiles each top-level class into its own .class file, so for the JVM (Java Virtual Machine) to load and execute your program it must be able to find and access the class.
            If a class were marked private, no code — not even the JVM could access it, which would make the class useless.

        2. protected Only Makes Sense in Inheritance
            protected means accessible to subclasses and same-package classes.
            But top-level classes aren't inherited — only their members (fields/methods) are.
            So protected on a top-level class has no meaningful interpretation.

        3. Java’s Design Philosophy: Simplicity and Predictability
            Restricting top-level classes to only public or package-private:
            Keeps visibility rules simple.
            Avoids confusion and potential abuse of encapsulation at the top level.

    But Why Do Nested Classes Allow This?
        class Outer {
                private class Inner {
                    // Used only inside Outer
                }
            }
        Because nested classes are part of another class’s scope, so the outer class can control how they are accessed:
        This is very useful for encapsulating helper classes that should never be used outside their parent.

# Diamond Problem
    Java does not support multiple inheritance with classes to avoid ambiguity and complexity — particularly the "Diamond Problem"
    Let's say in C++ you have 4 classes A,B,C and D:
        class A {
        void show() { cout << "A"; }
        }
        class B extends A {
            void show() { cout << "B"; }
        }
        class C extends A {
            void show() { cout << "C"; }
        }
        class D extends B, C { }  // Multiple inheritance!
    Now, what happens if you call show() from D? Does it use B’s version or C’s version? This creates ambiguity — known as the diamond problem.
    Java’s Solution: Use Interfaces, java allows multiple inheritance with interfaces.
    Java avoids this issue by:
        Allowing multiple interface inheritance
        interface A {
            default void show() { System.out.println("A"); }
        }
        interface B extends A {
            default void show() { System.out.println("B"); }
        }
        interface C extends A {
            default void show() { System.out.println("C"); }
        }
        class D implements B,C {
            @Override
            public void show() {
                B.super.show();  // You must choose which one and resolve ambiguity
                System.out.println("D");
            }
        }

# Instance and Local variables
    Instance variables are those variables that are accessible by all the methods in the class. They are declared outside the methods and inside the class. These variables describe the properties of an object and remain bound to it at any cost.
    Local variables are those variables present within a block, function, or constructor and can be accessed only inside them. The utilization of the variable is restricted to the block scope. Whenever a local variable is declared inside a method, the other class methods don’t have any knowledge about the local variable.

    Default value of variablea:
        In Java, variables have default values only when they are instance variables (fields of a class) or static variables.
        Local variables do not get default values — you must initialize them before use otherwise you will get a compilation error.

# .equals vs == 
    https://www.interviewbit.com/java-interview-questions/#how-is-java-different-from-cpp
    if ever asked to compare two objects by their value, override equals method from Object class.
    question -> 11

# Copy contructor, deep copy vs shallow copy
    In Java, a copy constructor is a special constructor used to create a new object as a copy of an existing object.
    Unlike C++, Java does not provide a built-in copy constructor — but you can easily define your own.
        
    If your class contains references to mutable objects (e.g., arrays, lists, other objects):
        Shallow copy just copies the reference → both objects share the same internal object.
        Deep copy creates a full independent copy of the internal object(s).

        Student s1 = new Student("piyush",24);
        Student s2 = s1;         //shallow copy
        Student s3 = s1.clone(); //deep copy
    https://www.interviewbit.com/java-interview-questions/#static-methods-static-variables-and-static-classes-in-java
    question 30

# Object cloning
    Object cloning means creating an exact copy of an existing object — including all its fields — without calling the constructor again.
    In Java, cloning is typically done using the clone() method of the Object class.
    Suppose you have Student and you want to create multiple copies of a student object, for testing or dummy purpose.
    To do this you have to first implement the Cloneable interface and override the clone() method.

    public class Student implements Cloneable{
        private int age;
        private String name;
        private Address address;
        
        //getters and setters;

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
    System.out.println(s1.hashCode() == s2.hashCode()); //false
    System.out.println(s1.getAddress().hashCode() == s2.getAddress().hashCode()); //true

    This creates a shallow clone which means that only primitive values and references are copied but referrenced objects are shared, this means that an Address object will still be shared between s1 and s2;
    Remember Object.clone() Does NOT clone referenced objects like College, String, Integer

    To solve this and create a full deep clone we need can do two things:
        Manually effort:
            Make Address and every other Non-primitive data-type implement Cloneable
            This might be hectic in case of multiple classes.
        Serialization-based cloning:

# Final keywords in java
    final variable:
        When a variable is declared as final in Java, the value can’t be modified once it has been assigned.
        If any value has not been assigned to that variable, then it can be assigned only by the constructor of the class.
    final method:
        A method declared as final cannot be overridden by its children's classes.
        A constructor cannot be marked as final because whenever a class is inherited, the constructors are not inherited. Hence, marking it final doesn't make sense. Java throws compilation error saying - modifier final not allowed here
    final class:
        No classes can be inherited from the class declared as final. But that final class can extend other classes for its usage.

# Why the main method is static
    public class MyApp {
        public static void main(String[] args) {
            // program starts here
        }
    }
    When you run a Java program:
        The JVM loads the class (MyApp).
        Then it calls MyApp.main(args) without creating an object.
        Since main() is static, the JVM can call it directly using the class name.

    What if main() were not static?

    public void main(String[] args) {
        // Not static!
    }
    The JVM would need to do this:
        new MyApp().main(args);
        But to do that, it would need to instantiate MyApp first, which requires calling a constructor — and maybe that constructor needs values, resources, etc, so how will JVM provide those parameteres without having them at first.

    That leads to a chicken-and-egg problem: how can you run code if you must run other code first? So making main() static solves this problem.

# Working without static main
    Internal flow:
    Part 1 — Java Launcher starts (the java executable)
        command -> java nonStaticMain.NonStaticMain
        The launcher is not the JVM yet.
        Its job:
            Locate the class -> Load it in memory ->Find a valid entry point
        The launcher loads the class before checking for a main method.

    Part 2 — Class Loading (Triggers the Static Block)
        The launcher asks the JVM:
            Load class nonStaticMain.NonStaticMain
        JVM’s class loading pipeline:
            Bootstrap ClassLoader → loads core classes
            Application ClassLoader → loads your class
            When class is loaded, JVM performs: Verification, Preparation, Resolution, Initialization

        Initialization of the class
            This is when static blocks run.
            **Your static block runs BEFORE Java looks for any main method.

# Explain public static void main(String[] args)
    Meaning of Each Keyword
        public:
            Makes the method accessible to the JVM (which is outside your class). If it’s not public, the JVM can’t call it, and you’ll get a runtime error.
        static:	
            The JVM doesn’t create an object of your class — it expects to call main() statically.
            Allows the JVM to call main() without creating an object of your class. It belongs to the class, not to any instance.
        void:
            Means the method doesn’t return anything.
        main:	
            The name the JVM looks for as the starting point.
        String[] args:	
            An array of command-line arguments passed when running the program. For example, if you run java MyApp hello world, then args[0] = "hello", args[1] = "world".
        

# Can static method be overloaded and overriden
    overloading
        Yes! There can be two or more static methods in a class with the same name but differing input parameters.
    overriding
        No! Declaration of static methods having the same signature can be done in the subclass but run time polymorphism can not take place in such cases.
        Overriding or dynamic polymorphism occurs during the runtime, but the static methods are loaded and looked up at the compile time statically, so static method declare in parent class is loaded to run from child class at compile time. Hence, these methods can't be overridden.

        You can declare a static method with the same signature in a subclass, but it's method hiding, not overriding. This is not method overriding — it’s called method hiding.
            class Parent {
                static void show() {
                    System.out.println("Parent static show");
                }
            }

            class Child extends Parent {
                static void show() {
                    System.out.println("Child static show");
                }
            }

            public class Test {
                public static void main(String[] args) {
                    Parent p = new Child();
                    p.show();  // Output: Parent static show
                }
            }
        Why is the output from Parent?
            Because static methods are resolved at compile time using the reference type, not the object type.
            p is declared as Parent, so Parent.show() is called — even though the object is a Child.

# Why can't a static method access instance variables or instance methods directly?
    Consider this:
        public class MyClass {
            int x = 10;  // instance variable
            static int y = 10;

            static void printX() {
                System.out.println(y);  // works fine
                System.out.println(x);  // Compilation error
            }
        }
        printX() is static — it runs without creating an object.
        x is an instance variable — it lives inside an object.

        But in static context, there's no object, so Java says:
            Cannot make a static reference to the non-static field x
    Static variable and class gets memory where the class is loaded. And these can directly be called with the help of class names.

# Static nested class
    A class in the java program cannot be static except if it is the inner class. Static nested classes do not depend on an instance of the outer class, you can use them without creating an outer class object.
    Therefore, they are shared just like static methods or variables — they belong to the class, not the instances.

    Static variables and static methods:
        These are shared across all instances of a class. There's only one copy in memory for the entire class, not per object.
        class Example {
            static int count = 0; // Shared across all objects
            static void showCount() { ... }
        }
        Calling Example.count or Example.showCount() accesses the same static member regardless of how many objects you create.

    But what about static classes?
        In Java, only nested classes can be static. A top-level class cannot be static. 
        So static nested class means:
            It does not have a reference to the enclosing instance.
            It does not behave like a static variable or method — it’s not shared.
            It’s just defined in a static context, meaning you don't need an outer object to instantiate it.

        Example:
            class Outer {
                static class StaticNested {
                    void display() {
                        System.out.println("Static nested class");
                    }
                }
            }
            public class Test {
                public static void main(String[] args) {
                    Outer.StaticNested obj1 = new Outer.StaticNested();
                    Outer.StaticNested obj2 = new Outer.StaticNested();
                    sout(obj1.hashCode() == obj2.hashCode()) 
                    // prints false, obj1 and obj2 are separate objects — not shared
                }
            }
    A static nested class is not "shared" like a static variable/method.
    Note -> Unlike normal classes static nested class can be Public, Protected and Private.

# Strings are immutable in Java
    The String Pool (also called the intern pool) is a special memory region in the Java heap where Java stores string literals.
    If you create a string using a string literal, Java checks if that exact string already exists in the pool:
    If yes, it reuses it (no new object).
    If not, it adds it to the pool.

    force pooling:
        You can force pooling using .intern():
            String s1 = new String("hello").intern();
            String s2 = "hello";
            System.out.println(s1 == s2);  // true ✅
        .intern() checks if "hello" is in the pool. If yes, returns pooled reference. If not, adds it and returns it.

    Note -> String str1 = new String("InterviewBit"), this will create a new object inside heap not inside the string pool;

    https://www.interviewbit.com/java-interview-questions/#static-methods-static-variables-and-static-classes-in-java
    intermediate question 1

    why are String immutable in java:
        Thread-Safety
            Immutable objects are inherently thread-safe.
            Multiple threads can read the same String without synchronization.
        Hashcode Caching
            Strings are heavily used as keys in HashMaps and HashSets.
            Hash code is computed once and cached — so hashCode() is very fast after the first call. If strings were mutable, changing their value would make their hash code inconsistent, breaking collections like HashMap.
        String Pooling (Interning)
            Java optimizes memory by reusing common literals in the String Pool.
            If strings were mutable, changing one pooled string would affect all references to it 
            Immutability ensures that pool values remain constant.

# What part of memory - Stack or Heap - is cleaned in garbage collection process?
    Garbage collection cleans the Heap, Not the Stack.
    Heap Memory:
        Stores: Objects and class instances
        Managed by: Garbage Collector
        When an object has no more references, it becomes eligible for GC
        GC reclaims this memory to avoid memory leaks and OutOfMemoryErrors
    Stack Memory:
        Stores: method calls, local variables, and reference variables
        Managed: automatically via the call stack
        Freed when method execution completes — no need for GC

# Java memory management
    Java works on two types of places to store memory that is stack and a heap, both stack and heap are created by JVM and stored in RAM
    Stack Memory:
        Stack stores temporary variables & separate memory block for methods.
        Store primitive data types, they store the value and not referrence to a memory location.
        Store reference of heap objects:
            Strong reference
            Weak reference
            Soft reference
        Each thread has its own stack, but all the thread operate on one single HEAP memory.
            Variables within a scope is only visible and as soon as any variable goes out of the Score, it gets deleted from the Stack (in LF0 order)
        When stack memory goes full, it throws "java.lang.StackOverflowError"

    As we know now objects are created inside the heap and there referrences are stored in stack:
        Suppose i call a method helloMemory() where i initialize some primitive data types and some objects of a class
        They get created and their referrence is stored inside stack but their scope is limited to the method call.
        As the method execution gets done these referrence are also deleted from the memory
        But what about the actual objects that got created inside the heap? how will we delete those objects, here comes the garbage collector.
    
    Garbage collector
        Garbag collector removes the unreferrenced objects inside the heap memory, the execution of gc is automatically controlled by JVM, if heap memory is running out of space then JVM will run the gc more perodically.
        Garbage Collector runs periodically & JVM controls when to run the garbage collector. We can also tell the JRE to run the garbage collector using System.gc() but this doesn't guarantee that gc will run that is why all of this is automatic memory management.

        MyObject obj = new MyObject();  // object created
        obj = null;                     // eligible for GC now because no reference of this is present in stack memory.

# Strong, Weak, Soft and phantom referrences in java
    In Java, references to objects control how the Garbage Collector (GC) treats those objects. Java provides four types of references in the java.lang.ref package:
    1. Strong Reference (default)
        This is the normal reference you use all the time:
            MyObject obj = new MyObject();
        GC will never collect obj as long as it has a strong reference.
        This is the most common and safest reference type.
        Implication: 
            Use when you need to ensure the object stays in memory.

    2. Weak Reference
        Used for objects that can be garbage collected if no strong references exist.
            WeakReference<MyObject> weakRef = new WeakReference<>(new MyObject());
        If the only reference to an object is weak, it will be collected during GC.
        Useful in caches or memory-sensitive situations.
        Implication: 
            Object may vanish at any GC cycle if only weakly reachable.

    3. Soft Reference
        Softer than weak, used for objects that should be kept in memory as long as possible, but can be collected under memory pressure.
            SoftReference<MyObject> softRef = new SoftReference<>(new MyObject());
        GC collects soft references only when memory is low.
        Ideal for implementing memory-sensitive caches.
        Implication: 
            Acts like a backup that disappears only if absolutely necessary.

    4. Phantom Reference (advanced)
        Used for post-mortem cleanup — you can find out exactly when an object is collected.
            PhantomReference<MyObject> phantomRef = new PhantomReference<>(obj, referenceQueue);
        You cannot access the object via this reference.
        Must be used with a ReferenceQueue.
        Mostly used in resource management or low-level memory handling.

# Heap Memory Structure (in modern JVMs)
    Java heap is divided into generations to improve GC performance:
        1. Young Generation (Young Gen)
            Where new objects are created.
            Subdivided into:
                Eden Space
                Survivor Space (S0 & S1)
            Objects that survive several minor GCs are promoted to the Old Gen.

        2. Old Generation (Tenured Gen)
            Stores long-lived objects.
            Major GCs are more expensive and less frequent here.

        3. Metaspace (since Java 8)
            Stores class metadata (used to be "PermGen" in Java 7 and below).
            Grows automatically with memory demand.

    Object Lifecycle in Heap
        1. Object Creation
            Where Objects Are Created:
                Objects are created in the Young Generation (specifically the Eden space) when they are instantiated using the new keyword.
                    MyObject obj = new MyObject();
            What Happens During Object Creation:
                When new MyObject() is called, the JVM allocates memory for the object in the Eden space of the Young Generation.
                Local variables and method arguments are stored on the stack, while the object itself (its instance variables) is stored in the heap.

        2. Minor Garbage Collection (GC)
            What Is Minor GC?
                A Minor GC is triggered when the Young Generation (Eden and Survivor spaces) fills up.
                The GC reclaims memory in the Young Generation by removing objects that are no longer referenced.
                Surviving objects are moved from the Eden space to one of the Survivor spaces (S0 or S1).

            Phases of Minor GC:
                Mark Phase: The GC marks all live objects (those still referenced by active threads).
                Sweep Phase: The GC reclaims memory used by unreachable objects.
                Copy Phase: Surviving objects are copied from Eden to one of the Survivor spaces (S0 or S1).

            Object Survival in Young Generation:
                If an object survives a Minor GC, it is moved to a Survivor space (S0 or S1).
                The object may survive multiple Minor GCs, after which it will be promoted to the Old Generation.

        3. Object Promotion to Old Generation
            When Does Promotion Happen?
                Objects that have survived several Minor GCs are promoted to the Old Generation (Tenured Generation).
                Promotion to old generation means that this object is used frequently and might have multiple referrence in the current application, which makes it very important to survive and less chances of being of no use anytime sooner.

            Promotion Process:
                When an object is promoted to the Old Generation, it is no longer part of the Young Generation. It stays in the Old Gen, where objects are typically long-lived.
                Old Generation objects are cleaned up during a Major GC (or Full GC), this is a time taking process.

            Promotion Threshold:
                Objects that are large or have been around for a while (inside eden and s0, s1) are more likely to be promoted quickly.

        4. Major (Full) Garbage Collection (GC)
            What Is Major GC?
                A Major GC (Full GC) is a more expensive garbage collection process compared to Minor GC.
                It cleans up both the Young Generation and Old Generation.
                It happens less frequently than Minor GC but is more time-consuming and can cause longer pause times because it needs to traverse all objects in the heap.

            When Does Full GC Happen?
                When the Old Generation fills up and there isn’t enough space to allocate new objects.
                When memory pressure is high (when the JVM tries to reclaim memory).
                When system limits are reached (like max heap size or memory thresholds).

            Process of Major GC:
                Mark Phase: Mark all live objects (both in Young and Old generations).
                Sweep Phase: Reclaim memory from objects that are unreachable.
                Compact Phase: (Optional) Compact the Old Generation to reduce fragmentation.

# Why do we have two sections young and old for memory management
    The Young Generation and Old Generation are parts of the heap memory in Java, and they are used to optimize the performance of the Garbage Collector (GC). The main reason for having these two sections in heap memory is to increase the efficiency of garbage collection by separating objects based on their lifespan. Let’s break down why these sections exist:
        1. Efficient Memory Management
            Young Generation is where most newly created objects live, while the Old Generation holds longer-lived objects.
            By separating the two, Java can optimize the garbage collection process based on the age and size of objects. This allows for faster collection of short-lived objects without the need to check older, long-lived objects frequently.

        2. Garbage Collection Frequency
            Young Generation:
                Minor Garbage Collection occurs more often in the Young Generation. The majority of objects are short-lived, meaning they become eligible for garbage collection quickly.
                New objects are allocated in the Eden Space, and if they survive the GC cycles, they are promoted to one of the Survivor spaces.
                Since these objects are mostly short-lived, frequent and fast collection is beneficial. Minor GCs are less expensive in terms of time because they deal with fewer objects in the Young Generation.

            Old Generation:
                Major Garbage Collection (or Full GC) happens less frequently. The Old Generation holds objects that have survived multiple Minor GCs, meaning they are long-lived.
                These objects are typically larger and less frequent, so they do not need to be checked as often. Major GC is more expensive in terms of time and resources because it needs to check through all objects in the Old Generation.
                By dividing the heap into these generations, Java can minimize the impact of GC pauses on long-running applications by collecting younger, short-lived objects quickly without affecting older, long-lived objects.

        3. Heap Fragmentation
            If all objects were in a single space, frequent creation and deletion of short-lived objects could result in heap fragmentation, where memory becomes inefficiently allocated.
            By dividing the heap into Young and Old Generation, the JVM reduces the likelihood of fragmentation in the Old Generation, which is where long-lived objects reside. As objects are promoted to the Old Generation, they are less likely to be deallocated frequently.

        4. Heap Space Allocation
            Young Generation is typically smaller compared to the Old Generation because it is optimized for new object allocations and frequent GCs.
            Old Generation is larger because objects that survive for a long time (after several GCs) are moved here. It is tuned for longevity, not for frequent allocation and deallocation.
            The ratio of them is generally 20:80 but can be fine tuned.

# Singelton pattern in java, is it thread safe?
    1. Eager Initialization (Thread-Safe)
        public class Singleton {
            private static final Singleton instance = new Singleton();

            private Singleton() {} // private constructor

            public static Singleton getInstance() {
                return instance;
            }
        }
    Simple and thread-safe, but instance is created even if not used.

    2. Lazy Initialization (Not Thread-Safe)
        public class Singleton {
            private static Singleton instance;

            private Singleton() {}

            public static Singleton getInstance() {
                if (instance == null) {
                    instance = new Singleton();
                }
                return instance;
            }
        }
    Not thread-safe — multiple threads could create multiple instances.

    3. Thread-Safe Lazy Initialization with Synchronization
        public class Singleton {
            private static Singleton instance;

            private Singleton() {}

            public static synchronized Singleton getInstance() {
                if (instance == null) {
                    instance = new Singleton();
                }
                return instance;
            }
        }
        Thread safe, but accquires a lock everytime getInstance is called which causes performance issue as getInstance might be getting called massively.
    
    4. Double-Checked Locking (Best Practice)
        public class Singleton {
            private static volatile Singleton instance;

            private Singleton() {}

            public static Singleton getInstance() {
                if (instance == null) {
                    synchronized (Singleton.class) {
                        if (instance == null) {
                            instance = new Singleton();
                        }
                    }
                }
                return instance;
            }
        }
        Efficient and thread-safe.
        What is the use of volatile keyword here:
            What happens is in a multi-core cpu where each core works on memory, l1 level cache is on every core so
            Core1 enters the getInstance and creates a object stores it in cache for faster access but it hasn't stored it in memory yet meanwhile Core2 enters and checks the existence of object in it's own cache(obviously no object found) and memory where core1 hasn't stored the object.
            This creates ambiguity, to avoid this volatile keyword is , it tells Core1 to create the object inside memory first.
           
    5. Bill Pugh Singleton (Recommended)
        public class Singleton {
            private Singleton() {}

            private static class Holder {
                private static final Singleton INSTANCE = new Singleton();
            }

            public static Singleton getInstance() {
                return Holder.INSTANCE;
            }
        }
        Thread-safe, lazy-loaded, no synchronization overhead.
        When Singleton is loaded, the Holder class is not loaded.
            The JVM loads a class only when it is actively used, such as:
                Creating an instance | Accessing a static field | Invoking a static method | Using reflection
            Nested classes are NOT loaded with the outer class automatically.
        Only when getInstance() is called, the JVM loads Holder and initializes INSTANCE.
        The JVM guarantees class initialization is thread-safe, so no explicit synchronization is needed.

# Imuutable list
    private final List<String> list;
    Meaning of final
        final means the variable reference cannot point to a new object after initialization.
        It does not make the object itself immutable.

    public List<String> getList() {
        //this return the list with same referrence, this allows manipulation of the list even if you mark it as final.
        return list;

        //this will return a newly created list with diff referrence, manipulaiton to it will not effect our final list.
        return new ArrayList<>(list);

        //If you want immutability with your provided list.
        return Collections.unmodifiableList(list);
    }

    Use an immutable collection (Java 9+)
        private final List<String> list = List.of("a", "b", "c");
    This creates an immutable list — any modification throws UnsupportedOperationException.

# Interface points
    Variables in interface are by default public static final, and they connot be made non-static or protected or private.
    Functions are also public abstract by default, we can't mark them private or protected.
    Overriding method cannot have more restricitve access specifiers:
        public method walk of Animal cannot be overriden as private or protected.
    
# Can methods be defined inside an interface in java?
    In Java, methods defined in an interface cannot have a body (implementation) unless they are explicitly declared with:
        default
        static
        or private (since Java 9)
    
    interface MyInterface {
        default void doTask() {
            helper(); // calling private method
        }

        private void helper() {
            System.out.println("Helper method in interface");
        }

        public static void print(){
            System.out.println("static method in interface");
        }
    }

    default Methods (Since Java 8)
        Allow interfaces to provide concrete implementation.
        Can be overridden by implementing classes.
        Use Case: 
            Backward compatibility. Add new behavior to interfaces without breaking old implementations.

    static Methods (Since Java 8)
        Belong to the interface itself, not instances.
        Not inherited by implementing classes.
        It cannot be overriden by the class extending or implementing classes.

    private Methods (Since Java 9)
        Help avoid duplication inside default or static methods.
        These can only be called within the interface itself, not from implementing classes.

    private static Methods (Since Java 9)
        Can be used to share code between static methods inside the interface.
    
# abstract class vs interface comparison chart
    Feature Comparison
    Feature	                            Abstract Class	                  Interface
    Can contain abstract methods	    ✅	                            ✅
    Can contain concrete methods	    ✅	                            ✅ (via default, static)
    Can contain constructors	        ✅	                            ❌
    Can have instance fields	        ✅ (non-final)	                ❌ (only public static final)
    Can extend one class	            ✅ (single inheritance)	        ❌
    Can implement multiple interfaces	✅	                            ✅
    Can extend multiple interfaces	    ❌	                            ✅
    Can be used with access modifiers	✅ (protected, private, etc.)	Limited (private methods only inside interface,  everything else is public)

# can we mark interfaces and abstract classes as final?
    No, in Java, you cannot mark either interfaces or abstract classes as final.
    Why?
        final means no subclassing or implementation allowed.
        abstract classes and interfaces are explicitly designed to be extended or implemented, respectively.
        Using final would directly contradict their intended use.

# Why is a character array preferred over string for storing confidential information?
    In Java, a string is basically immutable i.e. it cannot be modified. After its declaration, it continues to stay in the string pool as long as it is not removed in the form of garbage. In other words, a string resides in the heap section of the memory for an unregulated and unspecified time interval after string value processing is executed.
    As a result, vital information can be stolen for pursuing harmful activities by hackers if a memory dump is illegally accessed by them. Such risks can be eliminated by using mutable objects or structures like character arrays for storing any variable. After the work of the character array variable is done, the variable can be configured to blank at the same instant. Consequently, it helps in saving heap memory and also gives no chance to the hackers to extract vital data.

# Arrays.asList return immutable list, how can we add objects using Arrays.asList
    When you call:
        List<String> list = Arrays.asList("a", "b", "c");
    It returns an instance of a private static class inside java.util.Arrays, called Arrays$ArrayList.
    This class is not normal ArrayList class, but it does extend AbstractList and does not overrride the add and remove methods.
    This class implements List, but internally stores the passed array and overrides add() and remove() to throw UnsupportedOperationException.

    That’s why:
    ✅ You can modify elements: list.set(0, "z") → OK
    ❌ You cannot resize the list: list.add("d") → Exception
    ArrayList How to make it resizable:
        Wrap it in an ArrayList:
            List<String> modifiableList = new ArrayList<>(Arrays.asList("a", "b", "c"));
            modifiableList.add("d"); // works fine

# What is Heap Pollution in Java?
    Heap pollution occurs when a variable of a parameterized type (e.g., List<String>) references an object that is not of that parameterized type (e.g., a List<Integer>).
    In other words:
        The runtime type of the object doesn’t match its compile-time generic type declaration.

    How Does It Happen?
        This usually happens because Java generics use type erasure — type information (<String>, <Integer>, etc.) exists only at compile time, not at runtime.
        So the JVM can’t check actual generic types — it only sees raw types (like List).

    Example of Heap Pollution:
        public class HeapPollutionExample {
            public static void main(String[] args) {
                List<String> strings = new ArrayList<>();
                addUnsafe(strings);

                // This will cause a runtime exception
                for (String s : strings) {
                    System.out.println(s.toUpperCase());
                }
            }

            static void addUnsafe(List list) { // raw type warning
                list.add(42); // Adds an Integer into a "List<String>"
            }
        }

    At runtime, due to type erasure, the JVM only sees a List — not a List<String>.
    So when you call:
        list.add(42);
        It happily adds an Integer, polluting your heap with an invalid type.
        Later, when you try to use that list as List<String>, you get a ClassCastException.
    
    Preventing Heap Pollution
        Avoid raw types:
            List<String> list = new ArrayList<>();
        
        Use @SafeVarargs for methods that take varargs of generic types:
            @SafeVarargs
            public static <T> void addToList(List<T>... lists) {
                // safe use of varargs
            }

        Use @SuppressWarnings("unchecked") Judiciously: Only use this annotation when you are absolutely certain that the operation is safe, and only on the smallest scope possible (e.g., a single method or line, not a whole class).

        Use bounded wildcards:
            void process(List<? extends Number> numbers) { }
        
        Avoid casting between different generic types:
            List<String> list = (List<String>) (List<?>) someObject;
    
    Arrays of Parameterized Types
        This is already not supported for a reason:
            List<String>[] array = new List<String>[10]; // compile error
        Arrays are reified (they know their element type at runtime),
        but generics are erased — so combining them causes unsoundness.

        Imagine Java did allow creating an array of a parameterized type:
            List<String>[] stringListArray = new List<String>[10]; // Hypothetically allowed
            Type Erasure and Array Conversion: At runtime, due to type erasure, the component type of stringListArray would be erased to the raw type List[].

            Unsafe Assignment (The Pollution Event): You could then assign a List<Integer> to an element of the array because, at runtime, the check only sees List and the assignment is valid.
                List<Integer> intList = new ArrayList<>();
                intList.add(42);
                // This assignment would succeed because the array only checks if the 
                // object being stored is a raw 'List' (due to erasure).
                stringListArray[0] = intList; // POLLUTION!

        Runtime Crash (The ClassCastException): When you retrieve the element, the compiler assumes it's a List<String> and implicitly inserts a cast when accessing the list's contents.
            String s = stringListArray[0].get(0); // This causes a ClassCastException!

# Error vs Exception
    Error – What It Is
        Represents serious system-level failures.
        Thrown by the JVM.
        Your code should not catch or handle these.
        Usually means something is critically wrong (like memory or stack issues).
        Examples:
        throw new OutOfMemoryError();
        throw new StackOverflowError();

    Exception – What It Is
        Represents runtime problems or anticipated failures in your application logic.
        You can catch and handle them using try-catch.
        Types of Exceptions:
            Checked Exception – Must be handled or declared.
                IOException, SQLException, ParseException, ClassNotFoundException
            Unchecked Exception – Runtime exceptions, don’t require handling.
                ClassCastException, NullPointerException, ArrayIndexOutOfBoundsException, StringIndexOutOfBoundsException, IllegalArgumentException

        Example:
        try {
            int a = 5 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Divide by zero error");
        }
        Inheritance Hierarchy:
            Throwable
            ├── Error               → (serious system errors)
            └── Exception
                ├── RuntimeException → (unchecked)
                └── IOException      → (checked)

# Key Uses of finally
    Purpose	Example
        Releasing resources	Closing files, sockets, DB connections
        Cleaning up	Deleting temp files, resetting states
        Ensuring code always runs	Logging, final calculations

    File closing:  
        BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader("file.txt"));
                String line = reader.readLine();
                System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
    Note -> if JVM related issue happens like out of memory, system shutdown or process is killed forcefully only then finally block is not executed.

# What Are Built-in Functional Interfaces?
    Java provides a rich set of predefined functional interfaces in the package java.util.function, so you don’t have to write your own every time. They're designed for lambdas, streams, and functional programming.
    All of them are annotated with @FunctionalInterface and are part of java.util.function.

    Main Categories of Built-in Functional Interfaces
    Here’s a breakdown with syntax, use-case, and examples:

    1. Function<T, R>
        Takes a parameter of type T, returns a result of type R
            Function<String, Integer> lengthFn = str -> str.length();
            System.out.println(lengthFn.apply("Hello")); // 5
            
    2. BiFunction<T, U, R>
        Takes 2 parameters (T, U), returns R
            BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;
            System.out.println(sum.apply(5, 3)); // 8
        
    3. Predicate<T>
        Takes T, returns boolean
            Predicate<String> isEmpty = str -> str.isEmpty();
            System.out.println(isEmpty.test("")); // true
            Used heavily in .filter() in Streams!

    4. BiPredicate<T, U>
        Takes two inputs, returns boolean
            BiPredicate<String, Integer> longerThan = (str, len) -> str.length() > len;
            System.out.println(longerThan.test("Java", 3)); // true
        
    5. Consumer<T>
        Takes T, returns nothing (void)
            Consumer<String> print = msg -> System.out.println(">> " + msg);
            print.accept("Lambda Rocks!"); // >> Lambda Rocks!
    
    6. BiConsumer<T, U>
        Two arguments, still returns nothing
            BiConsumer<String, Integer> printer = (name, age) -> System.out.println(name + " is " + age);
            printer.accept("Alice", 25);
            
    7. Supplier<T>
        Takes nothing, returns T
            Supplier<String> greeting = () -> "Hello, world!";
            System.out.println(greeting.get()); // Hello, world!


# trasient keyword in java1
    The transient keyword in Java is a small but very important concept when it comes to serialization.
    Let’s break it down clearly

    1. Definition
    The transient keyword in Java is used to mark a variable not to be serialized.
    In simple terms:
        When you serialize an object (convert it into a byte stream to save to a file or send over a network),
        transient fields are skipped — they are not saved.

    2. Why do we use it?
        We mark a field as transient when:
            It contains sensitive information (like passwords).
            It’s derived/computed data (can be recalculated).
            It’s not serializable (and would otherwise cause an exception).
        Example: 
            private transient String password;  // won't be serialized
        Cannot be used anywhere else(method, local variable, class)

    What Happens Internally
        When an object is serialized:
            The ObjectOutputStream writes only non-transient and non-static fields.
            Transient fields are skipped.
        During deserialization, skipped fields are restored to default values:
            0 for numbers
            false for boolean
            null for object references

# finalize keyword in java
    Prior to the garbage collection of an object, the finalize method is called so that the clean-up activity is implemented. Example:
        public static void main(String[] args) {
            String example = new String("InterviewBit");
            example = null;
            System.gc(); // Garbage collector called
        }
        public void finalize() {
            // Finalize called
        } 


# string vs string buffer vs string builder
    The difference between String, StringBuffer, and StringBuilder in Java primarily revolves around Mutability, Thread Safety, and Performance.
    In short:
        String is immutable (cannot be changed) and thread-safe.
        StringBuffer 
            It is mutable (can be changed) and thread-safe (Methods are synchronized), faster than String but slower than StringBuilder.
            Mutability: The content of a StringBuffer can be modified (appended, inserted, deleted) without creating a new object. It uses an internal character array that grows dynamically.
        StringBuilder 
            Iis mutable and not thread-safe (Methods are not synchronized), making it the fastest option.
            Mutability: Like StringBuffer, it is mutable and allows in-place modifications without creating new objects.
            It is the preferred choice for string manipulation in single-threaded environments, which covers the vast majority of standard Java code
        
# What is Composition and Aggregation,
    Understanding the difference between Composition and Aggregation is key to mastering "has-a" relationships in Java (and OOP in general).

    “Has-a” Relationships
        Both Composition and Aggregation represent association between two classes — but they differ in ownership and lifecycle dependency.

        Composition (Strong Association)
            Definition:
                Composition is a strong form of association where one object cannot exist without the other.
            In other words — the containing class controls the lifetime of the contained object.
            Example:
                A Car class always needs and Engine class, Car is destroyed then engine is destroyed too, engine's lifecycle is dependent on car.
                Relationship: "Car owns Engine".

        Aggregation (Weak Association)
            Definition:
                Aggregation is a weaker form of association, where one object contains or references another, but they have independent lifecycles.
            The parent and child can exist separately.
            Example:
                Department has a list of Students, but if the department is deleted students still exist, student lifecycle is not dependent on department.
                Relationship: "Department has Students", but no ownership.
        
        Comparison Table
            Aspect	        Composition                             Aggregation
            Relationship	Strong “has-a”	                        Weak “has-a”
            Lifecycle	    Dependent	                            Independent
            Object creation	Usually created inside the container	Passed from outside
            Ownership	    Full ownership	                        Shared or partial ownership
            Example	        Car → Engine	                        Department → Student
            UML Notation	Filled diamond (◆)	                   Empty diamond (◇)

# Marker interface in java
    What is a Marker Interface?
        A Marker Interface in Java is an interface with no methods or fields —
        it simply “marks” or tags a class to indicate that it has a special property or behavior.
        In short: a marker interface conveys metadata to the JVM or frameworks through type checking.
    Example: The Simplest Marker Interface
        public interface Serializable {
            // No methods — it's a marker interface
        }
    Any class that implements Serializable is telling the JVM:
        “Hey, I can be serialized (converted into a byte stream).”

    Why Do Marker Interfaces Exist?
        Marker interfaces existed before annotations were added to Java (pre–Java 5).
        They were a way to communicate metadata about classes to the JVM or APIs using the type system itself.
        They serve as a flag that can be checked using instanceof.

    How JVM / APIs Use Marker Interfaces Internally
        Let’s see how the JVM actually uses them:
        Example 1 — Serializable
            When you serialize an object using ObjectOutputStream, the JVM checks:
                if (!(obj instanceof Serializable)) {
                    throw new NotSerializableException(obj.getClass().getName());
                }
            So the JVM doesn’t care about any methods — it just checks the presence of the marker interface.
        Example 2 — Cloneable
            The Object.clone() method internally checks:
            if (!(obj instanceof Cloneable)) {
                throw new CloneNotSupportedException();
            }
            Again, no methods needed — the marker interface itself controls behavior.

    But modern java uses Annotations instead of marker interfaces for the same reason.

# BufferedReader
    BufferedReader is a character-based input stream class in Java that is used to read text from input sources efficiently, such as files or the console.
    It buffers characters — meaning it reads a chunk of data into memory first, instead of reading one character at a time from the underlying source.
    Used for reading input from keyboard,network or a file, always remember to close it after the use otherwise it can cause memory leaks in java e.g bufferReader.close();

    Key Methods of BufferedReader
        Method	                                Description
        read()	                                Reads one character (returns its Unicode int value)
        read(char[] cbuf, int off, int len)	    Reads multiple characters into an array
        readLine()	                            Reads a line of text (returns null if end of file)
        close()	                                Closes the stream and releases resources
        mark() / reset()	                    Marks a position in the stream and resets to it later (optional)
    Reading from console:
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter your name: ");
            String name = br.readLine();

            System.out.println("Hello, " + name + "!");
            br.close();
        System.in is a byte stream
        InputStreamReader converts it to a character stream
        BufferedReader adds buffering + readLine() convenience

    Reading from a file:
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

# Autoboxing in java
    Autoboxing in Java is the automatic conversion that the Java compiler performs between primitive types (like int, double, boolean) and their corresponding wrapper classes (Integer, Double, Boolean).
    The reverse process is called unboxing.

    Why autoboxing exists?
        Because many Java APIs (like collections) work only with objects, not primitives.
        Autoboxing Example
            int num = 10;
            Integer boxed = num;   // autoboxing: int → Integer
        Unboxing Example
            Integer boxed = 20;
            int num = boxed;       // unboxing: Integer → int

    Common Use Case: Collections
        List<Integer> list = new ArrayList<>();
        list.add(5);    // autoboxes primitive 5 -> Integer.valueOf(5)

        int x = list.get(0);  // unboxes Integer -> int

# Immutable class in java
    An immutable class in Java is a class whose instances cannot be modified after they are created. Once constructed, the object’s state never changes. This is a core concept in Java design and heavily used in Spring, concurrency, and functional-style programming.

    Why use immutable classes?
        Thread-safe by default (no synchronization needed)
        Safe to share between components (important in Spring beans)
        Prevents accidental or malicious state changes

    Rules to create an immutable class
        Declare the class as final to prevents subclassing
        Make all fields private and final
        Do not provide setters
        Initialize all fields via constructor
        Return defensive copies for mutable fields

    Simple Immutable Class Example
        public final class Person {

            private final String name;
            private final int age;

            public Person(String name, int age) {
                this.name = name;
                this.age = age;
            }

            // Only getters
            public String getName() {
                return name;
            }

            public int getAge() {
                return age;
            }
        }
        Once created, Person cannot be changed.
    
    Immutable Class with Mutable Fields (Important!)
        If your class contains mutable objects (like Date, List, Map), you must protect them.
        public final class Employee {
            private final String id;
            private final Date joiningDate;

            public Employee(String id, Date joiningDate) {
                this.id = id;
                // Defensive copy
                this.joiningDate = new Date(joiningDate.getTime());
            }

            public String getId() {
                return id;
            }

            public Date getJoiningDate() {
                // Defensive copy
                return new Date(joiningDate.getTime());
            }
        }
        Without defensive copying, immutability is broken
    
    Immutability in Spring Boot
        DTOs & Value Objects → Immutable recommended
        Configuration properties → Often immutable
        Singleton beans → Safer when immutable
    
    Pro Tip (Java 16+)
        Use records — they are immutable by default:
        public record User(String username, String email) {}
        Clean, concise, and immutable.

# Java 8+ features
    Java 8 Core Features (Game Changers)
        Lambda Expressions
            Enable functional-style programming.
            Runnable r = () -> System.out.println("Hello Java 8");
            Less boilerplate
            Cleaner code
            Enables Streams & functional APIs

        Functional Interfaces
            Interfaces with exactly one abstract method.
            Examples: Runnable | Callable | Comparator | Predicate<T> | Function<T, R> | Consumer<T>

            @FunctionalInterface
            interface Calculator {
                int add(int a, int b);
            }

        Stream API
            Process collections declaratively.
            List<String> names = List.of("Alice", "Bob", "Charlie");
            names.stream().filter(n -> n.startsWith("A")).map(String::toUpperCase).forEach(System.out::println);
            Lazy evaluation, Parallel execution support and No mutation of source

        Method References
            Shorthand for lambdas.
            names.forEach(System.out::println);
            Types:
                Class::staticMethod
                object::instanceMethod
                Class::instanceMethod

        Default & Static Methods in Interfaces
            Interfaces can now have implementations.
            interface Vehicle {
                default void start() {
                    System.out.println("Vehicle starting...");
                }
                static void info() {
                    System.out.println("Vehicle interface");
                }
            }
            Enables backward compatibility

        Optional<T>
            Avoid NullPointerException.
            Optional<String> name = Optional.ofNullable(getName());
            name.ifPresent(System.out::println);

        New Date & Time API (java.time)
            Thread-safe, immutable, modern.
            LocalDate today = LocalDate.now();
            LocalDateTime now = LocalDateTime.now();
            Replaces Date and Calendar

    Java 10 – 11 Features
        var (Local Variable Type Inference)
            Cleaner code with compiler inference.
            var list = new ArrayList<String>();
        Only for local variables

        HTTP Client API (Java 11)
            Standard HTTP client.
            HttpClient client = HttpClient.newHttpClient();
            Supports HTTP/2
            Async calls

        String Enhancements (Java 11)
            "  ".isBlank();
            "Hello\nWorld".lines().count();

    Java 12 – 17 (Modern Java)
        Switch Expressions
            int result = switch (day) {
                case MONDAY, FRIDAY -> 6;
                case TUESDAY -> 7;
                default -> 0;
            };

        Records (Java 16)
            Immutable data carriers.
            record User(String name, int age) {}
            Auto-generates constructor, getters, equals, hashCode

        Pattern Matching (Java 16+)
            Cleaner instanceof.
            if (obj instanceof String s) {
                System.out.println(s.length());
            }

        Sealed Classes (Java 17)
            Restrict inheritance.
            sealed class Shape permits Circle, Rectangle {}

# Comparator vs Comparable
    Comparable Interface
        Comparable is used when a class defines its natural sorting order.
        Interface definition:
            public interface Comparable<T> {
                int compareTo(T o);
            }
        If you have a class and you naturally want to order it based on your loginc, the implement Comparable and override compareTo method of it.
        Collections.sort(listOfYourClass);
    
    Comparator Interface
        Comparator is used when you want multiple sorting strategies or external sorting logic.
        Interface definition:
            public interface Comparator<T> {
                int compare(T o1, T o2);
            }
        Mostly we use it as a lambda function like:
            Collections.sort(list, (a,b) -> a.getAge() - b.getAge());
        
        Advance usage in chaining sort:
            list.sort(Comparator.comparing((Student s) -> s.age).thenComparing(s -> s.name));
