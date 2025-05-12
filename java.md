# java

## JVM
    JVM makes the machine platform independent, with any machine having JVM installed on it can run the java application, but JVM 
    itself is platform dependent which means it has to created according to different operating systems.
    JVM runs on the top of the operating system
    JVM underatands Byte-code and not java code, so your compile converts your java code to byte-code which is executed by JVM
    JVM converts the bytecode to machinecode, this machine code is platfor independent, jvm uses JIT(just in time) compiler to do this conversion. 

    On your IDE write a java code and compile it using javac className.java -> this will return a bytecode which can be used inside any machine with jvm installed in it and will return the output, that's how jvm makes java platform independent.

# JRE
    Java Runtime Environment contains the JVM which contains all the class librarires we need for development purposes and a place where we can write our code.
    JRE also does byte-code checking which validates it and then it runs on the JVM

    int a = Maths.java
    Math library is already present for us to work with, how we get it automatically, this is because of JRE which comes with pre-installed class libraries.
    Now your jvm bytecode from a mobile device used such libraries but running the bytecode on another machine like laptop with only JVM installed will not be able to convert it into machine code because it does not know about Math class, using JRE on another device makes it compatible to run all kind of bytecodes.

    So JVM is necessary to compile bytecode to machine code and JRE is necessary for resoving class libraries.

# JDK
    You have places to execute the java code but where can you write the java code at first place?
    JDK contains both JVM and JRE for you and other features like debugger, PLs.

# STACK AND HEAP
    Objects are stored in the heap memory and their reference are stored in stack wherever it is called.
    for e.g -> Animal Lion = new Animal() now the object created through new keyword is stored in Heap memory but the Lion 
    (reference variable of object) is stored in the stack memory of the function executing this line like:-
    key->value
    Lion->100, this 100 is the address of the object created in the Heap
    The instance variable of the object has their memory stored in heap but methods are stored in stack, they are just 
    defined in the Heap.

# STRINGS
    While creating the strings like String s = "piyush", the JVM looks for "piyush" in the (String constant pool) and if it finds
    one already present then it return the reference to the string otherwise it'll create a new one in the Heap.
    So once you create a string you can't change it you can only reference your string object to refer to any other string inside the Heap, so String Class creates immutable strings in java unlike int and double which are mutable.
    To create mutable string we can use other classes like StringBuffer
    StringBuffer s = new StringBuffer(), it creates a string with a buffer size of genereally 16 Bytes so that it can solve the problem of continuous space allocation

# STATIC VARIABLES
    Now what if you want to create a class with every object having the same name for a particular instance variable, suppose Apple wants to create a class for their mobile "Mobile" in which they want to create an Instance vaiable as "Brand" 
    which will obviously have the same value as "Apple" for all the objects created from that class.
    if we create a normal String Brand and use it then it'll create multiple variables for each object with the same value in the Heap which is a waste of memory, so to save the space here comes the static keyword.
    static String Brand, now all the ojects will refer to the same address where Brand is initialised.
    JVM has a different space for static variables neither in heap nor in stack, everytime the value of this variable is changed it'll reflect on all the objects of that class.
    static variables are class variables and not object variables, so they should be called from the class 
    Class.varible like Mobile.Brand

# STATIC METHODS
    Just like static variables we can create static methods which can be called with Class.Method 
    we can't access instance variables inside a static method but we can use static variables inside static methods of that class.

# STATIC BLOCK
    What if you want to initialise the static variable created inside your Mobile Class, initalising it inside the constructor will initialise it everytime the 
    constructor is being called for every object, so to avoid it we have a static block which is exectued only once no matter how many object of that class are created

    static{
    brand = "Apple";
    }
    this code is executed before the constructor this is because the class is loaded is called before object creation, we can verify it by manually loading the class
    with Class.forName("your classname") and code inside static block will execute.

    class Mobile{
        String name;
        int price;
        static String brand;

        static{
            System.out.println("called brand");
            brand = "Apple";
        }

        public Mobile(String name,int price){
            System.out.println("called constructor");
            this.name = name;
            this.price = price;
        }
        public void show(){
            System.out.println(name+": "+price+": "+brand);
        }

    }

    class Hello{
        public static void main(String a[]) throws ClassNotFoundException{
            Class.forName("Mobile");

            Mobile m = new Mobile("iphone 10",1000);
            Mobile n = new Mobile("ipone 11",1100);
            Mobile.brand = "Apple";
            m.show();
            n.show();
        }
    }

# INHERITANCE
    java works with single and multi-level inheritance but not with multiple interitance, that is a child having multiple parents

# SUPER
    By default the constructor method of every class has a super() method which calls the constructor of it's parent class and then continues with the code of the child class,
    if you want to call the paramterised constructor then you have to explicitly mention the parameterised constructor of the parent class e.g super(int a);

# PACKAGES
    java.lang package is always imported as default in every java file
    if you want to access a variable outside a package you have to make it public otherwise it will be default, which means it is accessible in files under the same package 
    but not outside of the it.

# POLYMORPHISM
    Polymorphism means different behaviours for the same method, it is of two type compile-time and run-time, in compile-time the behaviour is decided during code compilation, for e.g Method Overloading 
    add(int a,int b),add(int a,int b,int c) 
    which method is to be run is decided at the compile time, 
    now run-time is Method Overriding 
    Class A-> add(int a){return a}, Class B extends A->add(int a){return a+10}
    which method is to be run out of these two on calling ObjectOfB.add() is decided at the run-time

# FINAL
    The final keyword when used for variables declares them as constant e.g final int PI = 3.14;
    final used with class makes it non-inheritable which means that no other class would be able to inherit from your defined final class e.g final class A, class B extends A will show an error.
    final used with a method will stop method overrriding which means that another class can inherit from your class but can't overrite the menthod you declared as final e.g final int add();

# ABSTRACT
    Suppose we have a class called Car(), now all the cars extending this class like BMQ,Toyota would have a drive method which is specific to each and every type of car itself, 
    so instead of writing something like this:- public void drive(){} in your Car class we can mark it as abstract method :- public abstract void drive;, now here we have just 
    given the skeleton of the drive function and all the children classes will extend it.Remember marking the function abstract means you should also mark the parent class as 
    abstract like := abstract class Car{} and it becomes mandatory for all the child classes to define the method drive() in their class otherwise it will throw an error.
    You can't create an object of abstract class, you can have abstract method defined in abstract class it won't be an error.

# ANONYMOUS INNER CLASS
    Suppose you have a class A with a method like show(), now you want to override the show() method so what you'll do is extend this class. 
    class B extends A{ public void show(){ /your implementation/ } }, '
    but what if you want to use this method only once then there is no sense of creating a new class and overriding the method, 
    instead here what you can do is you can create anonymous class from class A and change the implementation of method show() after you create a object of class A, here's the code:-
    A obj = new A()
    {
        public void show(){
            //your implementation.
        }
    }
    obj.show(); //calling the new changed method.

# INTERFACE
     suppose you want to have an abstract class that will only contain abstract methods that means all the child classes will
     have to define those methods, so you're kinda providing the skeleton of that class, here you can use interface instead of absract class.
     Interface is not a class it's a design for creating classes.All the methods inside it are public abstract by default.
     Interface can also contain variables which are final and static by default which means you can't change it.
     interface Human{
        int max_age = 100;
        void walk();
        void eat();
     }
     class Piyush implements Human{
        public void walk(){}
        public void eat(){}
     }

     interface can have multilevel implements, which means a class can implement 2 or more interfaces
        interface Human{
            int max_age = 100;
            void walk();
            void eat();
        }
        interface Lion{
            void eat();
            void hunt();
        }
        class Piyush implements Human,Lion{
            public void walk(){}
            public void eat(){}
            public void hunt(){}
        }

        class Hello{
            public static void main(String a[]){
                Piyush piyush = new Piyush();
                piyush.walk();
                piyush.hunt();
            }
        }
    We can create reference of a runnable and object of a class e.g Human obj = new Piyush();

# FUNCTIONAL INTERFACE
    single abstract method(SAM) or functional interface are the type of interfaces which contains only one abstract method, they are used in lambda expressions 
    which can only work with functional interfaces.

# LAMBDA EXPRESSIONS
    It is used as a syntactical sugar to reduce the lines of code for functional interface e.g:-
    A innerA = new A() 
    {
        public void show() {
            System.out.println("inside inner hello by Anonymous inner class");
        }
    };
    this can be written as
    A innerA = () -> { System.out.println("inside inner hello by Anonymous inner class") };

# BUFFERREADER
    used for reading input from keyboard,network or a file, always remember to close it after the use otherwise it can cause memory leaks in java e.g bufferReader.close();

# THREADS
    Thread is a functionality through which you can execute multiple tasks at the same time, you have to create a object extending the thread class and define the run() method there so that thread.start() knows to execute run()
    class A extends Thread{
        public void run(){
            // implementation
        }
    }
    A obj = new A();
    obj.start();

# THREAD PRIORITY
    you can customize the scheduling of your threads by assigning them priority, it ranges from 1 to 10, 10 is the maximum priority you can give and 5 is the default priority.
    obj.setPriority(your_priority);
    Different schedulers have different algorithms so we can just suggest to give our thread a priority but it might not relect in the desired way on the system.
    Thread.sleep(miliseconds) can set the thread to a waiting stage.
    you can create threads by extending the Thread class or even implementing the Runnable interface that is implemented by the Thread class to, the Runnable class is 
    a functional interface with just one method declared run()(which runs the thread).

    class A implements Runnable {
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("inside A");
                try {
                    Thread.sleep(5);
                } catch (Exception e) {
                    e.getStackTrace();
                    // TODO: handle exception
                }
            }
        }
    }
    public class Hello {
        public static void main(String a[]) {
            A myA = new A();
            Thread t1 = new Thread(myA);
            t1.start();
        }
    }

# RACE CONDITION
    If two threads increment or change the value of a variable at the same time or even execute the same method at the same time the operation will get done only once, 
    this is called a race condition and to avoid it you have to mark your method as synchronized 
    e.g public synchronized increment(){ count++ };
    this will make sure that your function is called only once.
    class A {
        public int count;
        public synchronized void increment() {
            count++;
        }
    }

    public class Hello {
        public static void main(String a[]) throws InterruptedException {

            A obj = new A();
            Runnable a1 = new Runnable(){
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        obj.increment();
                    }
                }
            };
            Runnable a2 = new Runnable(){
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        obj.increment();
                    }
                }
            };

            Thread t1 = new Thread(a1);
            Thread t2 = new Thread(a2);

            t1.start();
            t2.start();

            t1.join();
            t2.join();

            System.out.println(obj.count);
        }
    }

# JAR FILES
    libraries in java are called jar files
    using jdbc to connet with mysql you need an jar file for it, but for hibernates you need a jar file for it but hibernate itself is dependent upon multiple jar files which is called Transitive Dependencies.

# MAVEN | GRADLE
    Suppose you are working on spring 5 framework and you need other libraries like hibernates, so it might be possible that the version you are yousing of hibernates 
    would not work with spring, for e.g spring 5 does not work with hibernate 4, similarly there can be multiple conflicts with the version and dependencies of one 
    library or framework to another one, managing that manually would be a cumbersome task, here comes the use of a tool which can help you with 
    running,testing,debugging,packaging,deploying which are tools like maven,gradel etc.

# GAV
    GroupID->ArtifactID->VersionID
    This will create a project ID that will be unique

# EFFECTIVE POM
    Effective pom can be refered as the parent of the pom.xml file, the chagnes you make to the pom.xml file are reflected on this file and this is the file that is 
    read by maven for managing all the stuff and not the pom.xml file.
    All the plugins are declared in effective pom or you can also call it as super pom.

# JDBC
    jdbc is a connector which is used to connect to databases like mySql,postgres,mongodb

# IOC
    supoose for example you create a spring boot project and you are managing data and other things through creating your objects bu yourself(using the new keyword), 
    now what happens is every object has a life-cycle (creation->managing->destroying),so you'll always wish that something else could handle the complex task of object 
    management for you so that you can solely focus on the business logic, this is achievable by taking the help of spring framework.This concept is called 
    inversion of control, spring manages object's lifecycle through itself (creation->managing->destroying).
    To achieve this we have something in spring called the Ioc container, spring will create this container and object will be stored in here which would be managed solely
    by your spring framework. 

# Beans
    The object created by spring for IOC are called beans.

# Dependency Injection
    This is a design pattern that is used to achieve the Ioc concept, for example suppose you have a laptop class but this Laptop class is dependent on a Cpu class, in a normal
    scenarion you will use a new keyword for creating objects of both laptop and cpu class somethinglike this.

    //main application class
    Laptop laptop = new Laptop();

    //laptop class
    public class Laptop{
        private Cpu cpu;

        Laptop(){
            Cpu = new Cpu();
        }
    }

    //cpu class
    public class Cpu{

    }

    Doing this is fine, but as a developer this is a hectic process of managing the object's(laptop and cpu) lifecycle, we need to avoid this and we can easily achieve this 
    with the help of DI.
    Dependency injection will tell the spring framework to store and manage objects of these classes inside the Ioc container, then it'll be the duty of spring framework 
    to inject the Cpu class inside the Laptop class when the object of laptop class is created.
    How this DI works in spring boot is by annotating your class with @Component annotaion.

# @Component
    suppose you have a car class and you want spring to create and manage it for you through spring container, now what happens is that, you might have 100 classes in your project 
    so spring doesn't wants to make it's work hectic by managing all 100 classes by itself, it says that you have tell him which classes you want to get created, managed and destroyed 
    by spring, here is where annotaions comes into play.
    You have to annotate your class with @Component to tell the spring framework to manage your object lifecycle by it.
    Note that on running your application spring framework does a component scan of your entire application and it gets the idea that you want the Human class to be managed 
    by spring through this @Component annotaion.


    @Component //telling spring to manage the object of this class
    public class Human {
        public void walk(){
            System.out.println("walking...");
        }
    }

    @SpringBootApplication
    public class ProjectApplication {

        public static void main(String[] args) {
            ApplicationContext context = SpringApplication.run(ProjectApplication.class, args); //getting context object to get bean
            Human a = context.getBean(Human.class); //Dependency Injection of human class into main method
            a.walk();
        }
    }
    ApplicationContext is a way that you can communicate with Ioc container where beans(objects) are created and managed.

# AUTOWIRING @Autowierd
    Suppose your walk method in human class is dependent on another method walk() defined in body class then if you declare your body class and use it's object inside 
    the human class the main method will throw an error like this.body is null.Exception in thread "main" 
    java.lang.NullPointerException: Cannot invoke "com.springboot.project.Body.walk()". 
    It happens because "this.body" is null because of the following reasons:-
    -> Although we have used @Component in both clases but spring still doesn't know that we need to inject object of Body class inside the Human class.
    -> We can't have the access of ApplicationContext anywhere except inside the main class, so we can't get the bean of body inside the human class.we can surely get
       the object of Body inside our main class using context.getBean(Laptop.class,args) but not inside the Human class.
    To solve it we need to use @Autowired annotaion so that during the component scan spring will know that Human class object needs to inject object 
    of Body class.
    This is the concept of wiring, we need to tell the spring that Human class is dependent on Body class so it's your responsibility 
    to manage Body class's object lifecycle.

    @Component
    public class Body {
        public void walk(){
            System.out.println("legs are there and ready");
        }
    }

    @Component
    public class Human {

        @Autowired //adding this annotation to tell spring about the wiring between these 2 classes
        Body body;
        public void walk(){
            body.walk();
        }
    }

# Application Context
    This class or interface is used to talk to the IOC-container

# Java code | XML File | Anootations
    These are the three methods through which we can communicate with the spring framework.

# Singelton vs Prototype
    By default if you create 2 object from a class that is in IOC and maintained by spring then both of the references will contain the reference to the same object that is singelton behaviour which is default behaviour also, but if you want to create a new object when each time the getBean method is called then you have to change 
    the scope of that bean where it is declared this is called prototype.
    For XML file:-
    <bean id="human" class="com.spring.springDemo.Human" scope="singleton"></bean>
    <bean id="human" class="com.spring.springDemo.Human" scope="prototype"></bean>
    when singleton method is used the costructor is called(object creation) is on this line:-
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    but when prototype method is used then object creation is done at the time of call to object creation:-
        Human h = (Human) context.getBean("human");

# Setters
    Now your object is getting created directly through IOC-container so what if you want to initialse your data using constructors, one way of doing it is by using 
    <property> tag inside the bean, suppose i have a variable age inside human class so we can cofigure it like this
    <bean id="human" class="com.spring.springDemo.Human" scope="prototype">
        <property name="age" value="21">
        </property>
    </bean>
    make sure you generate the setter for all the variables, to assign

# Wiring in xml file
    now to use one class inside the another we again need wiring so for using xml in spring we must wire them in property tag inside the bean of that class;

# Field Injection | Constructor Injection | Setter Injection
    @Autowired can be injected at all the three levels but it is preffered to you use it at the field part
    
# SERVELTS
    Servlet is a class that extends the capabilities of the servers and responds to the incoming requests. It can respond to any request. Servlet is a web component that is deployed on the server to create a dynamic web page.

    CGI technology enables the web server to call an external program and pass HTTP request information to the external program to process the request. For each request, it starts a new process and the creation is limited plus if the no of clients increases it takes more time for response, instead of this servlets use mutithreading for multiple request handling at the same time. Threads have many benefits over the Processes such as they share a common memory area, lightweight, cost of communication between the threads are low.
    public static void main( String[] args ) throws LifecycleException {
        System.out.println( "Hello World!" );
        Tomcat tomcat = new Tomcat();
        tomcat.start();
        tomcat.getServer().await();//gets the server and awaits it to process request response until you want to close the server
    }
    Now that dynamic(html,css) content can be send through servlet but we don't prefer this because it becomes bulky which would make it more time taking, instead we use JSPs (java servlet pages), servlets do their processing part and send the data to jsp where data is populated inside html pages and rendered to the client side.

# NOTE
    Normally to run a spring project we need a war file which has to be kept inside out externally downloaded tomcat, which is a server that runs out project but spring
    boot comes an embedded tomcat which creates the server and runs our jar file which starts our project.

# MVC
    model,view,controller
    so what happens is for eg a client asks for a page with dynamic content in our web application, so this request goes to the controller which is a servlet, this 
    servlet fetches data from the model(database) and sends the data(object) to the view which might be react,angular or a jsp, this data gets populated inside the view
    which is sended as a response to the client where the html page is shown.

# Controller
    The @Controller annotation used in spring boot is used to tell the spring framework that this is a servlet.

# NOTE
    Tomcat server does not work on jsp directly instead what it does is it converts the jsp into a servlet so that it can work with it, to convert it we use a 
    dependency called tomcat jasper.

# Model
    Model is an Interface in the spring core package under com.springframework.ui is used for transferring the data or attributes from our business logic to the 
    rendering view pages. Its primary use is to add attributes to the model and can be simply viewed and accessed similar to the java.util.Map Interface.

# @ModelAttribute
    The @ModelAttribute annotation in Spring MVC serves multiple roles, providing a robust solution for data mapping between a client's request and the server's model 
    object. This annotation can be applied to method parameters, method return types, or even methods themselves
    At its core, @ModelAttribute is designed for binding form data, query parameters, or even attributes in the session to Java objects. In simple terms, 
    it binds an HTML form's input fields to the properties of a Java object. This is highly beneficial when dealing with forms that contain a large number of 
    sfields, as it eliminates the need to manually extract each form parameter.

    @RequestMapping("/addAlien")
    public ModelAndView add(@ModelAttribute("alien1") Alien alien, ModelAndView mav){
        mav.addObject("alien",alien);
        mav.setViewName("result");
        return mav;
    }
    each field in the request with alien property in the alien class is automatically populated in the alien object.
    Spring then takes each form field and matches it against the properties in the model object, populating them using their corresponding setter methods and If no existing model attribute is found, Spring will instantiate a new object of the corresponding class.

    https://medium.com/@AlexanderObregon/data-mapping-with-springs-modelattribute-annotation-b41704c2521a#:~:text=%40ModelAttribute%20is%20often%20used%20in,%2C%20like%20user%2Ddefined%20objects.

# Dispatcher servlet(configuring a springMvc project)
    create a maven project using ecllipse or any other id, then install this dependency to create a spring mvc project
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>6.1.0</version>
    </dependency>
    While running a spring MVC project,you'll encounter an issue which would be 404 external error saying requested source is not available, even after correctly creating and mapping request to your controller and sendind the flow to the view to display it to the ui you are still encountering this issue, why??
    i'll help you out with this issue.
    so what happens is a spring boot project comes with an embedded tomcat servlet container which runs your project without any issue, but for spring MVC project you need an external tomcat because spring does not comes with an embedded apache tomcat, so you have to download a apache tomcat servlet container from internet and configure your project inside the webapp folder in tomcat package installed.
    Tomcat does not know how to work with jsp(java servlet pages),so what it does it treats the jsp pages as servlets and for that it requires tomcat jasper, we need to install this dependency from mvn.
    <dependency>
		<groupId>org.apache.tomcat</groupId>
		<artifactId>tomcat-jasper</artifactId>
		<version>10.1.17</version>
	</dependency>
    Now inside the spring mvc project your external tomcat has no idea of the request mapping(request->servlet), in your project you might have 100s of controllers that might serve different functionalities for different use-cases, so it's impossible for your tomcat servlet container to map those requests, so your tomcat says that "hey, i have my own front controller that will map your request to the right controller so why don't you just configure it according to your request mapping" this front controlelr is called the dispatcher servlet which is responsible for mapping your requests to the right controller.
    But for mapping request in dispatcher servlet you have to configure it for spring too, you have to tell you spring framework that you want to map all the incoming request to dispatcher servlet, and the dispatcher servlet will take care of other processes.
    spring->dispatcher servlet->all the controllers
    to configure dipatcher servlet to spring you have to make changes to web.xml file.

    <servlet>
        <!-- Provide a fully qualified servlet name -->
        <servlet-name>demoServlet</servlet-name>
        <!-- Provide a fully qualified path to the DispatcherServlet class -->
        <servlet-class>
            org.springframework.web.servlet.DispatcherServlet
        </servlet-class>
    </servlet>

    <servlet-mapping>
        <!-- Provide a Servlet Name that you want to map -->
        <servlet-name>demoServlet</servlet-name>
        <!-- Provide a url pattern -->
        <url-pattern>
            /
        </url-pattern>
    </servlet-mapping>

    Now we need to configure our dispatcher servlet and you can do this by using xml file or java based annotations,so in our code we have used java based annotations on the controllers so we just need to tell our dispatcher that hey all the requests are mapped in controller you just scan them.
    For this you have to create a new xml file named as demoServlet-servlet.xml, the name demoServlet has arrived from the servlet-name tag defined in web.xml file and the name should be in this exact format otherwise it'll throw an error.Create this file inside WEB-INF folder which contains your web.xml file too.

    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:ctx="http://www.springframework.org/schema/context"
        xmlns:p="http://www.springframework.org/schema/p"
        xmlns:mvc="http://www.springframework.org/schema/mvc"
        xsi:schemaLocation="http://www.springframework.org/schema/beans 
                            http://www.springframework.org/schema/beans/spring-beans.xsd
                            http://www.springframework.org/schema/context 
                            http://www.springframework.org/schema/context/spring-context.xsd
                            http://www.springframework.org/schema/mvc
                            http://www.springframework.org/schema/mvc/spring-mvc.xsd">
    
        <ctx:component-scan base-package="com.telusko" />
        <ctx:annotation-config/>
        
        <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
            <property name="prefix" value="/views/"></property>
            <property name="suffix" value=".jsp"></property>
        </bean>
    </beans>

    component-scan tells the dispatcher to look for this whole bas-package and annotation-config tells it that inside them we are using annotations for mapping requests.
    you have to also configure you views like you do in spring-boot if you're customising your views folder(im just creating a views folder and adding my index.jsp,result.jsp files in it), so i have told the spring from where you should look for my jsp pages and suffix tells the type of file they are which is a jsp file.

# NOTE
    By default a controller looks for a view to return but if you want to return some json data instead of a view(you might be using react instead of jsp) the you have to tell the spring that "Hey, i want to send some data instead of a view", how can you do this, yes you guessed it right through annotations which is @RequestBody.
    And if all the request works on json data you can name the controller as @RestController no need of @RequestBody on requests.

# @RequestParam vs @PathVariable
    RP is used for accessing data send in key-value pairs for.eg-> getposts.com/type='sports'?limit=8, but PV takes the value from the url for eg-> getPost.com/101 where 101 is the id of a post, RP can handle cases for default cases and optional cases while PV makes it necessary to have a id or a slug with it.

# Jackson library
    The data we are sending from the server are java objects but on the postman we can see that the data is in the json format, so someone is in between who is converting java objects -> json object and that is the jackson library, by default it supports conversion for json but not any other format like xml, to convert data to xml format you have to install a library known as 

# Spring ORM
    ORM stands for object relational mapping. suppose you have want to create a student table which contains a rollNo, name and marks, what you can do is you can create a 
    student class with the same properties in your spring boot project and let any ORM tool map your class to a table in a relational database, this mapping will make a developers 
    work easier.One of such tools is Hibernate.
    Every object of that class will map to a row in database, creating and saving a new object will create a new row in the table.

# Spring data jpa
    There are different tools for ORM like hibernate, and different projects might use another different tools, so now if you want to shift to some other ORM tool from hibernate, 
    you might have to change the whole code, so can't there be any standardized specification on which all these ORM tools can agree on so that you don't have to change a lot of code in your project.
    Here comes JPA(java persistence API) which is an implementation on which all the other ORM tools work.

# JPA vs JDBC
    JPA is simpler, cleaner, and less labor-intensive than JDBC, SQL, and hand-written mapping. JPA is suitable for non-performance oriented complex applications. The main advantage of JPA over JDBC is that, 
    in JPA, data is represented by objects and classes while in JDBC data is represented by tables and records. It uses POJO to represent persistent data that simplifies database programming. There are some other advantages of JPA
    JPA allows us to save and load Java objects and graphs without any DML language at all.
    It supports for cross-store persistence. It means an entity can be partially stored in MySQL and Neo4j (Graph Database Management System).

# @Entity annotation
    In Spring Boot, the @Entity annotation is used to mark a class as a JPA entity. This means that the class represents a table in your database, and its instances can be persisted and retrieved using JPA.
    @Id: This annotation marks the field as the primary key of the entity.
    @GeneratedValue: This annotation tells JPA to automatically generate the value of the primary key.
    @Column(name = "name", columnDefinition="VARCHAR(128)") to give your defined name and type to columns seperately.
    @Table to specify the table name if it's different from the class name.
    @MappedSuperclass for common attributes that should be inherited by multiple entities, this means that you create a base class and define some properties in it that are common to all the other entities that will extend it.
    @Lob annotation specifies that the database should store the property as Large Object,LOB is datatype for storing large object data. There’re two varieties of LOB which is called BLOB and CLOB. BLOB is for storing binary data, while CLOB is for storing text data.

# @Query Annotation
    @Query annotation is used to declare a query with a method name, the query is not written in sql but in jpql, here is an example:-
    @Query("Select s from Student s where s.name = ?1)
    Public Student findByName(String name)
    if there are multiple parameters then they are specified by ?1,?2?3... in the query

# Note
    you won't need the query annotation while declaring most of the queries because JPA is intelligent enough that it already perpares many of the queries which you can use using the correct method names, it does create the queries by using common logic that we apply on creating queries that allows developers to focus on business logic and high-level query methods.
    for eg-> "select * from student where marks > 80";
    jpa knows the common conventions and will already have a method by the name -> findByMarksGreaterThan(int marks);
    In this example, Spring Data JPA recognizes the findBy prefix and interprets the remaining part of the method name (name) as properties of the Student entity.

# ResponseEntity
    ResponseEntity is used to represent an HTTP response, including headers, status code, and body. It's commonly used when you want to return a custom HTTP response from a Spring controller. For example, if you want to return a 201 (created) status code along with a newly created resource, you could use ResponseEntity like this:
    @PostMapping("/resources")
    public ResponseEntity<Resource> createResource(@RequestBody Resource resource) {
        Resource createdResource = resourceService.create(resource);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdResource);
    }

# @JsonFormat 
    It is a Jackson annotation that allows us to configure how values of properties are serialized or deserialized. For example, we can specify how to format Date and Calendar values according to a SimpleDateFormat format.

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private date calendar;
    now even if we pass the date object in milliseconds the jsonformat annotaion will tell spring to convert it into "yyy-mm-dd" pattern when sending the response;

# Spring AOP(Aspect oriented programming)
    AOP stands for aspect oriented programming and it's not a competitor to oop instead it complements oop, through AOP you can do certain things that are not possible with oop.It addressses the issue of cross-cutting-concerns(concerns that appear at multiple places).
    Now your application might be running completely fine but what if one day your application faces downtime and users are not happy with you, so in this scenario you would have no idea what happened if you didn't implement logs for your application, through logs you can check the code flow which methods were called and debugging would be faster and easier.Here AOP comes to help you with logging functionality and much more.
    AOP helps you with security of your application, transaction management, performance monitoring, exceptions, validations and logging in your code.It's main focus is to seperate the business logic from the dev related prospects.
    With AOP you can create a class and connect it with where you want to apply the logging, this connection is done through annotations which is called weaving.
    You can also create methods to address these issues but wouldn't it be a cumbersome task to write those methods like(log(),validateInput()) inside every method inside your spring application.AOP can help you here.
    AOP solves these cross-cutting-concerns by creating a seperate class and using annotations to address the specific issue of methods and classes inside your spring application.

    @Component
    @Aspect
    public class LoggerAspect {
        private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class);

        //return type, fully qualified classname, method name, arguments.
        @Before("execution(* package com.springBoot.springEcom.*(..)"))
        public void logMethodCall(){
            LOGGER.info("method called");
        }
    }
    @Before annotation will log "method called" on console before the execution of the method, you have to specify before execution what kind of functinons you want to apply it on, * means wildcard.
    This is categorised by 
    1st return type(set as *)
    2nd fully qualified classname package com.springBoot.springEcom
    3rd method name(set as *) 
    4th No of arguments(set as ..)

    @Aspect->this is the aspect
    @Before->this is the advice
    execution(* package com.springBoot.springEcom..*(..))->this is point cut(an expression of when your advice needs to get called)

    if you want to run for a specific class
    execution(* com.springBoot.springEcom.service.ProductService.*(..))
 
    further denoting the method you can do it for a specific method too.
    execution(* com.springBoot.springEcom.service.ProductService.addProduct(..))

    you can even mention it for two comnination of classes and methods seperating them with ||
    @Before("execution(* com.springBoot.springEcom.service.ProductService.getAllProducts(..)) || execution(* com.springBoot.springEcom.controller.ProductController.*(..))")

    When you want to get hold on a method you can use a JoinPoint
    @Before("execution(* com.springBoot.springEcom.service.ProductService.getAllProducts(..))")
    public void logMethodCall(JoinPoint jp){
        LOGGER.info("method called"+jp.getSignature().getName());
    }
    2024-09-26T17:05:59.056+05:30  INFO 18520 --- [nio-8080-exec-2] c.s.springEcom.aop.LoggingAspect         : method called getAllProducts

    Similarly you can log after a method is called using @After, after an exception is thrown using @AfterThrowing and after return using @AfterReturning

    @After("execution(* com.springBoot.springEcom.service.ProductService.getAllProducts(..))")
    public void logMethodExecuted(JoinPoint jp){
        LOGGER.info("method executed "+jp.getSignature().getName());
    }

    @AfterThrowing("execution(* com.springBoot.springEcom.service.ProductService.getAllProducts(..))")
    public void logMethodError(JoinPoint jp){
        LOGGER.info("method error "+jp.getSignature().getName());
    }

    @AfterReturning("execution(* com.springBoot.springEcom.service.ProductService.getAllProducts(..))")
    public void logMethodReturn(JoinPoint jp){
        LOGGER.info("method return "+jp.getSignature().getName());
    }

    2024-09-26T17:16:38.660+05:30  INFO 35196 --- [nio-8080-exec-2] c.s.springEcom.aop.LoggingAspect         : method called getAllProducts
    2024-09-26T17:16:38.741+05:30  INFO 35196 --- [nio-8080-exec-2] c.s.springEcom.aop.LoggingAspect         : method return getAllProducts
    2024-09-26T17:16:38.741+05:30  INFO 35196 --- [nio-8080-exec-2] c.s.springEcom.aop.LoggingAspect         : method executed getAllProducts

    What if you want to measure the performance of a method for eg-> a database query by the time it takes to execute, so you will require to use @Before to start a timer before the execution of method and @After to end the timer after execution of the method plus in the mean time you would require the access to the method object and proceed it in between the start and end of the timer.
    Here comes the use of @Around annotation, It executes before and after a join point. It is the most powerful advice. It also provides more control for end-user to get deal with ProceedingJoinPoint.
    If we do not invoke the ProceedingJoinPoint.proceed() then the original method will be executed.
    At last you have to return the object;

# servlet filter
    When a request is made to a Spring application, the Spring DispatcherServlet receives the request and passes it through a chain of filters before reaching the actual servlet. Each filter in the chain can perform certain operations on the request or response, such as modifying headers or checking for authentication. The order in which filters are applied can be specified in the application configuration. Once the request passes through all the filters, it reaches the servlet for further processing.
    client->tomcat->dispatcher servlet(front controller)->controller
    The Filter serves as a middleware positioned between the web container and the DispatcherServlet
    In fact most of the time we are going to work with multiple filters called Filter Chain , The Filter Chain is a sequence of filters that are applied in a specific order to incoming requests. Each filter in the chain can perform different operations on the request or response.
    tomcat->filter1->filter2->filter3->dispatcher servlet

# Spring security
    these are main articles for spring security
    https://www.marcobehler.com/guides/spring-security
    https://wankhedeshubham.medium.com/spring-boot-security-with-userdetailsservice-and-custom-authentication-provider-3df3a188993f
    flow
    authentication request->authentication manager->authentication provider->userDetailsService
    user modelling
    https://medium.com/@adigunolamide200/spring-security-for-beginners-part-1-introduction-to-user-modeling-c96ad1960415
    SecurityConfigClass 
    This is the class created by you with @Configuration and @EnableWebSecurity annotation that tells the spring MVC that you are going to customise the spring security.
    UserDetailsService
    extending userDetailsService which return userDetails object(userDetailsModel object because userDetails is itself an interface and we need to implement it) which is crucial for authentication provider
    By default userDetailsService will check for username password inside application properties file but if you want it to read from somwhere else you need to define and return it's object from your securityConfig class.
    You can return an object of InMemoryUserDetails class which reads credentials from your application memory , you just need to create a user of java User class and configure these users in the object of InMemoryDetails and return it.

    @Bean //creating a bean by ourself so that spring uses our configured userDetailsService object
    public UserDetailsService userDetailsService() {
        //creating users with username password and their roles.
        UserDetails user= User.withDefaultPasswordEncoder().username("navin").password("n@123").roles("USER").build();
        UserDetails  admin=User.withDefaultPasswordEncoder().username("admin").password("admin@789").roles("ADMIN").build();
        //adding them to the InMemoryUserDetailsManager object
        return new InMemoryUserDetailsManager(user,admin);
    }
    https://medium.com/@adigunolamide200/spring-security-for-beginners-part-2-userdetailsservice-and-database-communication-3661832624f9

    Authentication Provider
    whenever you configure spring security it will provide you with a custom authentication provider, now the role of authentication provider is to authenticate your user, you can have multiple authentication provider depending on your use case.
    you need to create a bean of this provider inside your custom security class which is securityConfig and return it's object.
    AuthenticationProvider is itself an interface so you need a class which implements this interface.
    Now there are different types of authentication in a website, it depends on which type of authentication you are using inside your application, here we are using database authentication so we will return an object of DaoAuthenticationProvider class which implements AuthenticationProvider interface.

    @Bean //tells the spring that we are returning AuthenticationProvider object for you to use it.
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        return daoAuthenticationProvider;
    }
    Now the question is how will the DaoAuthenticationProvider class know about our database and tables, some of you might have guessed it,we need to set an object of UserDetailsService inside the getUserDetailsService method of DaoAuthenticationProvider class.
    Now as we are using database to verify user and not application memory we can't set an object of InMemoryUserDetailsManager, instead we will have to create our custom userDetailsService class which will override loadUserByUsername method.

    @Service
    public class CustomUserDetailsService extends UserDetailsService {

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return null;
        }
    }
    UserDetailsService is an interface that has has a method loadUserByUsername, this method is responsible for fetching user data from the database and returns an object of UserDetails, you have to implement this method for working of DaoAuthenticationProvider.
    Here we need to create a new class called UserPrincipal that implements UserDetails and overrides all the methods.
    But before that we need to create a UserRepository class as we are working with spring-data-jpa and declare a method to findByUsername which finds the user on the basis of username.
    Remember to autowire object of UserRepository in CustomUserDetailsService so that we can use this object to query the database.

    @Repository
    public interface UserRepository extends JpaRepository<User,Integer> {
        User findByUsername(String username);
    }

    create UserPrincipal class:
    public class UserPrincipal implements UserDetails {
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of();
        }
        @Override
        public String getPassword() {
            return "";
        }
        @Override
        public String getUsername() {
            return "";
        }
        @Override
        public boolean isAccountNonExpired() {
            return true;
        }
        @Override
        public boolean isAccountNonLocked() {
            return true;
        }
        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }
        @Override
        public boolean isEnabled() {
            return true;
        }
    }

    Now we can query the database using object of UserRepository class and return the UserDetails object from UserPrincipal class inside CustomUserDetailsService and then we will set an object of this class inside the custom AuthenticationProvider we are returning as a bean inside spring security class

    We also need to enable a password encoder because storing passwords as plain texts would make your application vulnerable.

# AuthenticationManager
    https://hyperskill.org/learn/step/40291#the-role-of-authenticationmanager

    Creating your application you would like to secure your API requests using spring security, but you won't like the user to authenticate himself before hitting register and login requests, that won't make any sense right.you can specify these customistaions in your springConfig class

# NOTE
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(customizer -> customizer.disable());
        httpSecurity.authorizeHttpRequests(request -> request
                                                    .requestMatchers("register","login")
                                                    .permitAll()
                                                    .anyRequest()
                                                    .authenticated());
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return httpSecurity.build();
    }

# Implementing JWT filter and what is OncePerRequestFilter
    To authenticate the user spring by default uses a usernamePassword filter before sending the request to the servlet to authenticate the user, but what is i want to implement a custom Jwt based filter for authetication using the jet token that i got after the successfull loging request
    as we know that security chain filter can be multiple filter before sending the request to the servlet
    client -> servlet dispatcher -> filter1 -> filter1 -> filter3.....-> servlet
    To implement custom JWT filter i would like to implement it just before the default UsernamePasswordAuthenticationFilter used by spring and to do this we need to specify it in our securityFilterChain in our SecurityConfigClass.

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(customizer -> customizer.disable());
        httpSecurity.authorizeHttpRequests(request -> request
                                                        .requestMatchers("register","login")
                                                        .permitAll()
                                                        .anyRequest()
                                                        .authenticated());
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //here we mentioning it, i hope the method name already explains itself.
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); 
    }
    Now we need to create this JwtFilter class that will do the actual filteration for incoming request, for it we will extend the OncePerRequestFilter interface and override it's methods.

    @Autowired
        private JwtService jwtService;  //we need a object of it,cuz we want to do the logic part in service layer of jwt
    public class JwtFilter extends OncePerRequestFilter {
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String authHeader = request.getHeader("Authorization");
            String token = null;
            String userName = null;

            //checking if authHeader is not empty and starts with bearer coz that'ss what a jwt token starts with.
            if(authHeader != null && authHeader.startsWith("Bearer ")){
                token = authHeader.substring(7);
                userName = jwtService.extractUserName(token);
            }

            if(userName != null && SecurityContextHolder.getContext().getAuthentication()==null){

                UserDetails userDetails = applicationContext.getBean(CustomUserDetailsService.class).loadUserByUsername(userName);

                if(jwtService.validateToken(token, userDetails)){
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        }
    }

    Now what is OncePerRequestFilterEvery filter implements the Filter class but due to this 2 or more servlets that might have the same filter will execute the filter more than 1 time which is totally redundant and can make application slower, to avoid this we have OncePerRequest filter which would be executed once per each request.
    This Ensures that a specific filter is executed only once per request, even if multiple filters or dispatches might trigger it.
    Prevents redundant operations and potential issues that could arise from executing a filter multiple times within the same request cycle.
    securityContextHolder gives us authentication object if it already exists and null if not, this will let us ensure that we don't need to perfrom the validation if we already have the Authentication object.In case of null we will do the authentication with jwtService.authentication() and create a new authentication token and set it in SecurityContext through 
    SecurityContextHolder.getContext().setAuthentication(authToken).


# SecurityContextHolder and SecurityContext
    The SecurityContextHolder utilizes a SecurityContext to hold the Authentication object, representing the currently authenticated user. The Authentication object contains the principal, credentials, and granted authorities.
    When a user authenticates, Spring Security updates the SecurityContextHolder with the authentication details. Throughout the request lifecycle, the application can access the SecurityContextHolder to retrieve the user's authentication details and make security-related decisions.
    For more details
    https://www.javacodegeeks.com/2018/02/securitycontext-securitycontextholder-spring-security.html

# Circular dependency
    A circular dependency occurs when a bean A depends on another bean B, and the bean B depends on bean A as well:
    Bean A → Bean B → Bean A
    Let’s say we don’t have a circular dependency. We instead have something like this:
    Bean A → Bean B → Bean C
    Spring will create bean C, then create bean B (and inject bean C into it), then create bean A (and inject bean B into it).
    But with a circular dependency, Spring cannot decide which of the beans should be created first since they depend on one another. In these cases, Spring will raise a BeanCurrentlyInCreationException while loading context.
    It can happen in Spring when using constructor injection. If we use other types of injections, we shouldn’t have this problem since the dependencies will be injected when they are needed and not on the context loading.

# @ManyToMany
    https://www.baeldung.com/jpa-many-to-many
    
# Abstract vs Interface
    The main difference between abstract classes and interfaces is that abstract classes can have state (instance variables) and non-abstract methods, 
    whereas interfaces cannot have any state and can only contain abstract methods and default methods. Another difference is that a class can implement
    multiple interfaces but can only inherit from one abstract class.
    Use abstract classes when you want to share code between related classes with common logic.
    Use interfaces when you want to enforce specific behaviors across possibly unrelated classes.

# Microservices 
    Suppose you have set up a monolithic application, let's take example of a quiz service that i have created and you can clone it from the given provided link.

    Initially this was a monolithic application with primarly 2 tasks with 2 controllers, one was to deal with the requests regarding questions(creating, fetching, deleting) (QuestionController) and other for the requests regarding  quiz(creating,fetching) quizes and their scoring (quizController), the primary task of quizController is to create a quiz with a unique id which contains different questions and returning a quiz when asked by the client,now for the simplicity of this project we are not dealing with delete and updates(Hope you would implement it by yourself) 

    So why do we shift from a monolithic architecture to a microservice?, let's suppose you have created the questionService and quizService, here the use of questionService is just to create and fetch the questions for the questions service and probably we won't get much requests for it but quizService might get a lot of requests to deal with cuz it is responsible for delivering the quiz to the client, now to deal with so many requests for quiz you might need to scale the application for quiz service by scaling we mean having multiple instances of it working actively, so now in a monolithic application you are forced to scale the whole application as a single service which would require to scale the question service too, now a sharp developer will instantly get that this is a waste of resources by scaling the question service too for no reason.It might be possible that you need 10 instances of quiz and just 2 or 3 of question service for your application in this case you'll have to have 10 instance of the single application which means 10 instances of both quiz and question, so what to do now? here comes the Microservices to help you, Microservices says that don't worry just seperate your services and scale up whichever you want according to your use-case.So we are divinding our whole application(which is a single service) to multiple services which has multiple benfits like:-
    -> you can scale individual services independently based on their needs
    -> Each microservice can be deployed independently, which means updates to a single service don’t require redeploying the entire application
    -> Each service can be built using the best technology for its specific task. For example, you might use Python for data processing services, Java for core business logic, and Node.js for real-time services.
    -> Since services are independent, a failure in one microservice (e.g., the payment service) won’t necessarily impact other services (e.g., user authentication), improving fault tolerance and system reliability.
    -> Microservices allow for parallel development by different teams. Each team can work on a service, develop it, and release it independently, leading to faster delivery of new features and updates.

    There are much more benfits of it but that would make our blog more legnthy and that's not the scope of it.

    First question that would arise is if we seperate the services then how the hell quiz service will request the question service to create a quiz or to fetch the questions that are in a quiz and more to that how is it going to know where is that question service residing in this world to even make a request to it?   

    Here is the time to know about service registry and service discovery.A service registry is like a smart map that keeps track of all the services registered to it, so your services must register to this service registry.A client(question and quiz) retrieves a list of all connected peers in a service registry, and makes all further requests to other services through a load-balancing algorithm.Service discovery is term which defines the services that needs to discover location of other services to perform requests/response.Here in our project the quiz service needs the question service, so in short the quesiton service will register itself in service registry and the quiz service shall be able to discover this question service using service discovery.
    So, in simple terms, Service Registry helps services announce their presence, and Service Discovery helps clients to find the services they need in the microservices project.

    Implementing service registry:
        Eureka by netfix is one which provides this service registry and to create a server reistery were services can register themselves, we need to create a new spring project with following dependencies on it.
        spring-cloud-starter-netflix-eureka-server
        After downloading the project in main Spring Boot Application class file, we need to add @EnableEurekaServer annotation. The @EnableEurekaServer annotation is used to make your Spring Boot application acts as a Eureka Server.

    application.properties
        server.port=8761 It is specified on which port the application will serve. The default port number for Eureka Server is 8761.
        eureka.client.registerWithEureka=false We set this property to false so that it does not register itself in the list. Because; Here it acts as a server, not a client.
        eureka.instance.hostname=localhost
        eureka.client.fetchRegistry=false This value is set to false because it will not retrieve the registered microservices list from anywhere. It will create and maintain this list itself.
        on running the project we will see "No instances available" under application section, this means we need our micro services to register here so that they'll be show here.

    Implementing service discovery:
        Now to implement service discovery on our quiz and question service we need to install some dependecies that are mentioned here in your micro service(question and quiz).
        spring-cloud-starter-netflix-eureka-client 
        This is a eureka client that register itself on eureka server and through this eureka clients will be able to discover each other.
        
        application.properties
            eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/ we just have to pass the url where it needs to register itself.
    
    After implemting service discovery, relaoding the service registry will show you the registered instances, now our sevice knows the location of other service but how will it make a request, for that we use another dependency which is know as feign.

# Feign
    When a microservice wants to call another service’s API, developers often use HTTP clients or REST templates to make those calls. Although these are functional methods, they entail a lot of boilerplate code, making the codebase harder to maintain and understand.
    The @FeignClient annotation streamlines this process by abstracting the HTTP client layer, allowing developers to focus more on business logic and less on infrastructural concerns.
    The @FeignClient annotation, when used in conjunction with Spring Cloud and a service registry like Eureka, offers built-in client-side load balancing. This means that requests are automatically routed to different service instances, providing both redundancy and efficient resource utilization.

    working of feing
    By adding @EnableFeignClients to your springApplicaiton file, you're telling Spring to scan for interfaces that are annotated with @FeignClient and to generate proxy implementations for them.
    The @FeignClient annotation works by dynamically creating a proxy of the annotated interface at runtime. Each method in this interface corresponds to an HTTP request to the service specified in the annotation. When a method of the interface is called, Spring intercepts this call and translates it into an HTTP request, including URL mapping, request and response body conversion, and header setting. It then sends this request to the target service, processes the response, and returns it back as the return value of the method.

# Note
    The spring-boot-starter-web already has the embedded tomcat server package so that we don't have to configure a server manually to run the project plus the jar 
    file also has this server, we can run the spring boot project through it's jar file
    //command to run jar
    java -jar yourJarFile.jar

# CommandLineRunner
    The CommandLineRunner is an interface in Spring Boot. When a class implements this interface, Spring Boot will automatically run its run method after loading the application context. 
    Usually, we use this CommandLineRunner to perform startup tasks like user or database initialization, seeding, or other startup activities.
    Suppose you have url of your s3 buckets and aws or other services and you want to display them after your application startup, so you can override the run method of
    commandLineRunner and log them.
    -> Application starts.
    -> Spring Boot initializes and configures beans, properties, and the application context.
    -> CommandLineRunner (or ApplicationRunner) methods are executed.
    -> The application is now ready to serve connections or requests.

    Application can have multiple classes that implement CommandLineRunner, the order of execution can be specified using @Order Annotation.
    Note — Don’t forget to annotate the class with @Component. this class must be registered in Spring context so that Spring can find this class and execute overridden run()

# AutoConfiguration
    https://medium.com/@AlexanderObregon/understanding-spring-boot-auto-configuration-and-customization-aee35fa2eef8
    set your logging level to debug to see the Auto-configuration report

# @Transaction
    https://www.scaler.com/topics/spring-boot/transaction-management-in-spring-boot/
    This annotation is used to make your database transactions more robust and safe.It achieves the atomicity and isolation

    important
    https://www.marcobehler.com/guides/spring-transaction-management-transactional-in-depth#transactional-pitfalls

# Scope of a trasnaction
    Using @Transactional on the method will increase the scope of to jpaTest1,which means the whole code inside the method would be either successfully exucte or will get
    rolled back in case of any error.This annotaion creates a persistence context that acts as a first level cache and stores and manages all the entities in the method,
    if there is no persistence context then no entity is managed and stored.
    If we comment this for once and try to run this test then every line using entity manager will be in a seperate transaction and the 
    changes won't get rolled back if any issue happens event at the last line of the code, as a developer we don't want our code to be so unreliable.
    One more thing to note on commenting transactional annotation is that this code will throw an lazyIntitalizationException because  on calling student.getIdentity()
    there is no session(persistentContext) currently going on, because it is not inside a transaction or getting executed under entity manger.
    @Test
    //@Transactional
	public void jpaTest1(){
		logger.info(String.valueOf(System.identityHashCode(entityManager)));
		logger.info("test for one-to-one eager and lazy loading");
		Student student = entityManager.find(Student.class,4);
		logger.info("find by id response -> {}",student);
		logger.info("getIdentity response -> {}",student.getIdentity());
		logger.info("getIdentity response2 -> {}",student.getIdentity());
	}

# Persistence context
    PersistenceContext is the main concept in jpa that is used to manage the lifecycle of different entities abd their interaction with database.We can think of it as a
    cache or a simple hashmap also called as a "first-level cache".It has the following roles.

    ->Tracking Entities: It keeps track of entity instances and their changes, ensuring consistency between the in-memory state and the database.
    ->Synchronization: Any changes made to entities within the persistence context are automatically synchronized with the database at the appropriate time (e.g., during transaction commit).
    ->Caching: It minimizes redundant database queries by caching entities already loaded in the persistence context.

    Just like any other object, persistanceContext is also initialised at the starting of your spring application in the heap memory.
    Why Do We Need a Persistence Context?

    ->Automatic Change Tracking
        The persistence context tracks changes made to entities automatically.
        For example, if you update an entity's property, you don't need to explicitly call an update() method. The persistence context detects the change and ensures it's propagated to the database during the transaction commit.
        java
        Copy code
        @Transactional
        public void updateEntity(Long id) {
            MyEntity entity = entityManager.find(MyEntity.class, id);
            entity.setName("Updated Name"); // Change is tracked
            // No explicit save/update call needed
        }

    ->Transactional Consistency
        Entities managed by the persistence context remain consistent throughout a transaction. If the transaction rolls back, the changes are discarded.
        This ensures data integrity and avoids partial updates.

    ->Lazy Loading
        The persistence context enables lazy loading of associations (e.g., @OneToMany, @ManyToOne) by deferring database queries until the related data is accessed.
        Without the persistence context, lazy loading would not work seamlessly.
        MyEntity entity = entityManager.find(MyEntity.class, 1L);
        // Related data (e.g., a collection) is loaded lazily when accessed
        List<RelatedEntity> related = entity.getRelatedEntities();

    ->Caching
        The persistence context acts as a first-level cache. If an entity is already loaded in the persistence context, subsequent queries for the same entity avoid hitting the database, improving performance.
        MyEntity entity1 = entityManager.find(MyEntity.class, 1L); // Database query
        MyEntity entity2 = entityManager.find(MyEntity.class, 1L); // Cache hit

    ->Detached and Merged Entities
        The persistence context provides mechanisms to detach entities (remove them from tracking) and merge them back (reattach them for updates).
        // Detach
        entityManager.detach(entity);

        // Merge
        MyEntity mergedEntity = entityManager.merge(entity);

    ->Efficient Batching
        The persistence context groups multiple changes into a single transaction and writes them to the database in one batch, improving performance.
    
    ->Declarative Transaction Management
        The persistence context integrates seamlessly with Spring Boot's transaction management. When a method is annotated with @Transactional, the persistence context is automatically propagated or created, making it easy to manage transactions declaratively.

# @PersistenceContext annotation
    @PersistenceContext annotation takes care to create a unique EntityManager for every transaction (communication with the database). In a production application you can have multiple clients, calling your application in the same time. For each call, the application will create a new thread, will open/create a new transaction with the database and will assign a separate persistence context. Each thread, in this case, must use its own EntityManager.The persistence context is created when a transaction starts (via @Transactional)
    and is flushed/closed when the transaction ends.Changes made to entities are automatically synchronized with the database at the end of the transaction.

    @PersistenceContext
    public EntityManager entityManager;
    using @Autowired instead of @PersistenceContext for entity manager will create a single instance of it for the whole application and we don't want this.

    Without persistence context it becomes your duty to manage the lifecycle of entity manager, you need to get an object of it using the EntityManagerFactory class and have to take care of opening and closing it.Refernce of the code is given below.

    public class MyRepository {

        private EntityManagerFactory entityManagerFactory;

        public MyRepository() {
            this.entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        }

        public void saveEntity(MyEntity entity) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin(); // Begin transaction
            entityManager.persist(entity);
            entityManager.getTransaction().commit(); // Commit transaction
            entityManager.close(); // Close EntityManager manually
        }
    }

# Entity Manager
    EntityManager is responsible for putting entity in persistence context, using persist() method, which is then tracked.Transaction is also associated with entity manager and entity is bound to this transaction.
    Once the trasnaction is committed, changes are pushed to data source.
    This class keeps a track of different entites, it makes the use of persistent context to track them it has a 1:1 relationship with persistance context, for every transaction if a new entity manager is created then a new persistent context is also created.
    Any changes done to entity will reflect directly in our database and sometimes we don't even need to use the save method.Take this for example

    @Repository
    @Transactional
    public class PersonJpaRepository {

        @Autowired
        public EntityManager entityManager;

        public void playWithEntityManager(){
            Person person = new Person("jaitly","faridabad",Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());
            entityManager.persist(person);//now entity manager will keep track of this object
            person.setLocation("Nit");//this will automatically detect changes and will reflect it in database
        }
    }

    All the changes that we do in our object during the transaction are executed at the last, so if we use detach() or clear() methods before manually using flush() method
    manually then the changes won't reflect in DB.

    Person person = new Person("jaitly","faridabad",Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());

    //now entity manager will keep track of this object
    entityManager.persist(person);

    //this will automatically detect changes and will reflect it in database at the end of transaction
    person.setLocation("Nit");

    person.setName("himanshu");
    //Now changes to person will not reflect in database as entity manager is not keeping track of this object, so name and location will not change.
    entityManager.detach(person);
    //this will manually save the changes in database, but as person is detached it will not reflect in db.
    entityManager.flush();

    Person person1 = new Person("bhuwan","faridabad", Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());
    entityManager.persist(person1);
    person1.setLocation("gurgaon");

    //entity manager will not keep track of any object in this transaction and changes to person1 location will not reflect in DB.
    entityManager.clear();

    //now EM will start tracking the objects again and will reflect the changes in the DB.Using persist intead of merge will throw an error
    //because persist() is designed for new entities, not for re-attaching existing entities.
    person = entityManager.merge(person);
    person1 = entityManager.merge(person1);

    //This will take the value of person1 from database(primarykey) and set them back to person1, any changes to person1 will be backtracked.
    entityManager.refresh(person1)

# Lazy vs Eager loading
    Suppose you have a one to one relationship between two entities Student and Identity
    Now on executing a find_by_id query on student the student data is retrieved inluding a left join with Identity table(this is eager loading), now many times we might
    need to get entites only when required in this case we need a lazy loading, this can be attained by using @OneToOne(fetch = FetchType.LAZY)

    Eager:
        select
            s1_0.id,
            s1_0.birth_date,
            s1_0.creation_date,
            i1_0.id,
            i1_0.create_date_time,
            i1_0.identity_type,
            i1_0.number,
            i1_0.update_date_time,
            s1_0.location,
            s1_0.name,
            s1_0.updated_date 
        from
            student s1_0 
        left join
            identity i1_0 
                on i1_0.id=s1_0.identity_id 
        where
            s1_0.id=?

    Lazy:
        select
            s1_0.id,
            s1_0.birth_date,
            s1_0.creation_date,
            s1_0.identity_id,
            s1_0.location,
            s1_0.name,
            s1_0.updated_date 
        from
            student s1_0 
        where
            s1_0.id=?

# Bidirectional mapping 
    So far we have fetched records of Student having uni-directional OneToOne with Identity, this means the student table contains a column of identity(primary key), so
    we can easily fetch identity from students, but what if we want to fetch the students from identity, as the relationship is OneToOne we might think of having a student 
    column in Identity class but that is not a good practice of having both the tables having each other's primary key which could result in a circular dependency.
    To avoid this we have a solution that is to use mappedby keyword in @OneToOne mapping on the Identity class.

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "identity")
    private Student student;

    Using mappedby keyword on identity class tells the spring that the owner side is student class and it contains an object of identity with the name "identity", this
    mappedby name should match with your defined object name in the owner class that is important.
    This mapping will not generate a student column inside the Identity table, but it gives a way for fetching the students from identity object.

    @Test
	public void jpaTest2(){
		logger.info("checking Bidirectional mapping by fetching student(owner) from identity");
		Identity identity = entityManager.find(Identity.class,1);
		logger.info("Identity response -> {}",identity.toString());
		//now fetching the student record, if everything is correct then a student sql query must be executed
		logger.info("student response -> {}",identity.getStudent().toString());
	}

    Sql:
        select
            s1_0.id,
            s1_0.birth_date,
            s1_0.creation_date,
            s1_0.identity_id,
            s1_0.location,
            s1_0.name,
            s1_0.updated_date 
        from
            student s1_0 
        where
            s1_0.identity_id=?
    Hence we got a query for student without having a column defined for student inside identity.
    Kindly note the following default fetching strategies of mappings.
    OneToOne -> Eager
    ManyToOne -> Eager
    OneToMany -> Lazy
    ManyToMany -> Lazy

# Isolation
    https://ssudan16.medium.com/database-isolation-levels-explained-61429c4b1e31

# Cascading in spring
    https://medium.com/@himani.prasad016/jpa-hibernate-cascade-types-0490be19cc1f

    CascadeType.PERSIST: Propagates the persist operation from parent to child.
    CascadeType.MERGE: Propagates the merge operation from parent to child.
    CascadeType.REMOVE: Propagates the remove operation from parent to child.
    CascadeType.REFRESH: Propagates the refresh operation from parent to child.
    CascadeType.DETACH: Propagates the detach operation from parent to child.
    CascadeType.ALL: Applies all the above cascade operations.

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    CascadeType.ALL is a shortcut for applying all cascade operations, but it should be used cautiously, especially with REMOVE, as it can accidentally delete child
    entities, you can use multiple cascading operations like this (cascade = {CascadeType.PERSIST,CascadeType.MERGE}).

    The orphanRemoval = true attribute ensures that if a child entity is removed from the relationship, it is deleted from the database, take this example.

        //department and employee have one to many relationship with orphanRemoval set to true.
        Department department = new Department();
        department.setName("IT");

        Employee emp1 = new Employee();
        emp1.setName("Alice");
        emp1.setDepartment(department);

        Employee emp2 = new Employee();
        emp2.setName("Bob");
        emp2.setDepartment(department);

        department.getEmployees().add(emp1);
        department.getEmployees().add(emp2);

        departmentRepository.save(department);

        //now removing a row from the list of employee in department will make no sense of having that orphan employee object in database.
        department.getEmployees().remove(emp1); // Removes Alice from the employees list
        departmentRepository.save(department); //save operation will also delete alice from Employee table.

# Implementing serializable interface on entities while using caching 
    When using Ehcache, objects stored in the cache may be serialized, depending on the configuration (e.g., if off-heap storage, disk persistence, or clustered caching
    is enabled). Serialization ensures that the object can be safely written to and read from the cache.
    If Ehcache is configured for distributed or clustered caching, the cache data might need to be transferred between different JVMs. Serialization allows the object 
    to be transmitted as a byte stream and reconstructed on the other side.
    In Ehcache:
    Heap Storage Only: Serialization is not strictly required for heap-only caching, but it’s a good practice for future-proofing, especially if you plan to enable
                        off-heap or disk storage later.
    Disk Storage: If data is spilled to disk (e.g., when the heap cache is full), serialization is mandatory.
    Distributed Cache: Serialization is essential when caching data across multiple JVMs in a distributed environment.
    Other Scenarios
    Persistence: Serializable objects can be saved to a file or database for later retrieval.

# Circular dependencies bwtween entities and how to avoid them
    https://medium.com/@AlexanderObregon/understanding-springs-jsonbackreference-and-jsonmanagedreference-annotations-783090468572

# BeanDefinitionOverrideException
    https://www.baeldung.com/spring-boot-bean-definition-override-exception

# failed to lazily initialize a collection of role: com.spring.springJPA.entity.Course.reviewList: could not initialize proxy - no Session
    This error occurs when you try to serialize an entity with a lazy-loaded collection into JSON, but the Hibernate session is already closed. Here's a detailed 
    explanation and how you can resolve it:

    Root Cause:-
    Lazy Initialization: Hibernate delays the loading of @OneToMany, @ManyToMany, or @ElementCollection associations by default when FetchType.LAZY is used.
    Session Issue: When the controller or service tries to return the entity (or its nested objects) as a JSON response, the collection hasn't been initialized 
                    because the Hibernate session has already been closed.
    Serialization Problem: Spring's Jackson JSON serializer attempts to serialize the lazy-loaded collection, but since it hasn't been initialized, it throws the error.4

    Solution:-
    1. Use @Transactional annotation on service layer method to keep the object under persistence context.
    2. Set fetchType to Eager on @OneToMany annotaion to fetch the list of objects eagerly.
    3. User DTOs.

# Second level caching
    V.V.V Important:-
    https://freedium.cfd/https://dip-mazumder.medium.com/how-i-improved-database-performance-hibernate-caching-in-spring-boot-c1fcc83d0945

    A second-level cache in Hibernate is a shared cache that operates at the session factory level, making it accessible across multiple sessions. It is used to store 
    and manage entity data so that it can be efficiently retrieved without having to hit the database repeatedly.
    The second-level cache stores entity data at a global level, making it accessible to all sessions.
    Unlike 1st level cache we have to manually configure the second level cache, because 2nd level cache has no idea about what data needs to be cached we have to specify
    it, we will implement a simple caching using EhCache.

    Steps for implementation:
    1-> Enable second level caching.
    2-> Secify the caching framework - (EhCache in this case)
    3-> Only cache what i tell you to cache.
    4-> Specify the data to cache.

    Reference site for our project, as we are using Jcache and by default Jcache uses ConcurrentHashMap as underlying cache store, but here we are using ehcache as the
    underlying implementation.
    https://refactorfirst.com/spring-boot-spring-cache-with-ehcache-3

# Criteria API
    To create advanced search operations, where user might provide multiple fields with or without combination we need to dynamically genreate the sql queries.
    https://medium.com/@danaprata/jpa-criteria-api-quick-intro-6f2ce2462ee1

# Note
    setting fetch type to lazy doesn't means that the entites won't be shown in the response, i have two entites (Course and Review) and a one-to-many relationship 
    between them, so as we know one-to-many has it's default type as lazy that means when we ask for all the courses in our getCourses api only the courses will be 
    fetched because we are not explicilty mentioning any course.getReviews() method so it's obvious that no review will be shown in the response.
    //screenshot
    But here we see that reviews are still getting fetched, this is happening because if you're returning a List<Course> directly from the controller, tools like 
    Jackson (used for JSON serialization) will try to access all fields of the Course entity which happens when serializing course object, including the reviews.
    Since the getter for reviews is accessed, the lazy loading is triggered, fetching the reviews(course->getReviews()).

# N+1 problem
    Scenario:
        Suppose you have two entities, Author and Book, where an Author can have multiple Books (one-to-many relationship).
        You want to fetch all Authors along with their Books.
        How the Problem Happens:

    When you fetch the list of Authors, Hibernate executes one query to fetch all Authors.
    Then, for each Author in the list, Hibernate executes one additional query to fetch the associated Books.
    So, for N Authors, Hibernate will execute:
        1 query to fetch all authors.
        N queries to fetch books for each author.
        Total = N+1 queries.

    Why is this a Problem?
        It is highly inefficient, especially with large datasets, as it results in excessive database queries and increased latency.
        Each query adds network round-trips and processing overhead, which slows down the application.
            
    Suppose you have a AuthorController and inside this you have getAll method which fetches all the author from database,in this case hibernate will also fetch all 
    the books written by all the authors even if the fetchType is LAZY because by default jackson library de-serializes the author object to send the API response 
    and calls the getBooks() method which in turn triggers a sql query for all the authors and results in N+1 problem.

    How can we solve this issue:

        Use entity graphs:
            https://medium.com/geekculture/resolve-hibernate-n-1-problem-f0e049e689ab
        Use Eager Fetching (JOIN FETCH):
            Modify the query to fetch the associated Books in a single query using a JOIN FETCH.
                Example:
                    List<Author> authors = session.createQuery(
                        "SELECT a FROM Author a JOIN FETCH a.books", Author.class
                    ).getResultList();
                This generates 1 query that fetches both Authors and their Books together.
                Configure @OneToMany or @ManyToOne Relationships:

            Change the fetch type from LAZY (default) to EAGER.
                Example:
                    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
                    private List<Book> books;
                Hibernate will automatically fetch associated Books when fetching Authors.
                Use Hibernate's Batch Fetching:

        Batch multiple queries into fewer queries.
            Configure batch size using @BatchSize or Hibernate settings.
            Example:
                @BatchSize(size = 5)
                private List<Book> books;
            Instead of querying for each author individually, Hibernate fetches data in batches.

        Second-Level Cache:
            If data does not change frequently, consider using Hibernate's second-level cache to avoid unnecessary queries.
            Use Criteria or HQL for Custom Queries:

        Write optimized queries using Criteria API or HQL to fetch related data in one go.

# LazyLoadingException in @Cacheable
    Recently i was working with spring data jpa and caching, so before telling you about the exception and how to deal with it i'll make you go through my code

# Jpa entity graph
    Until JPA 2.0, to load an entity association, we usually used FetchType.LAZY and FetchType.EAGER as fetching strategies. This instructs the JPA provider to 
    additionally fetch the related association or not. Unfortunately, this meta configuration is static and doesn’t allow switching between these two strategies at 
    runtime.
    The main goal of the JPA Entity Graph is to improve the runtime performance when loading the entity’s related associations and basic fields, it provide ways to 
    dynamically configure your fetching strategy at the run time.

    Why Use EntityGraph?
        To solve the N+1 problem by fetching related entities in a single query.
        To override the default fetch strategies defined in the entity mappings (@OneToMany, @ManyToOne, etc.).
        To optimize queries by fetching only the data you need, reducing the amount of data fetched from the database.

    Types of EntityGraph
    There are two types of EntityGraph in JPA:
        Named EntityGraph: Defined using annotations on the entity.
        Dynamic EntityGraph: Defined programmatically at runtime.
        https://www.baeldung.com/spring-data-jpa-named-entity-graphs

# Jackson library
    https://medium.com/@AlexanderObregon/serializing-and-deserializing-objects-in-spring-boot-a-comprehensive-guide-e58b3e321b92

# @Modifying annotation
    https://www.baeldung.com/spring-data-jpa-modifying-annotation

# Input validation spring-data-validataion
    implement or learn how to do input validation, it's quite easy so skipping
    it contains annotations like.

    CustomerDto class:-
        @NotEmpty(message = "Name can not be a null or empty")
        @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
        private String name;

        @Schema(
                description = "Email address of the customer", example = "tutor@eazybytes.com"
        )
        @NotEmpty(message = "Email address can not be a null or empty")
        @Email(message = "Email address should be a valid value")
        private String email;

        @Schema(
                description = "Mobile Number of the customer", example = "9345432123"
        )
        @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
        private String mobileNumber;

    use @Validated annotation on the controller(CustomerController) class so that your spring knows that it has to perform validation on CustomerDto, but doing this
    will only let the spring know to do validation of the input fields, to actually perform this validation we need to use @Valid on the method where CoustomerDto is 
    being passed as a parameter, something like this
    public ResponseEntity<CustomerDto> saveCoustomer(@RequestBody @Valid CustomerDto)

# Note
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
    This will not set password field in response when serializing the User object.

# Docker image

    Docker commands:
        🔹 Working with Containers
            Command	Description
            docker run <image>	Run a container from an image
            docker run -d <image>	Run a container in the background (detached mode)
            docker run -it <image> bash	Run a container and interact with it
            docker ps	Show running containers
            docker ps -a	Show all containers (including stopped ones)
            docker stop <container_id>	Stop a running container
            docker start <container_id>	Start a stopped container
            docker restart <container_id>	Restart a container
            docker rm <container_id>	Remove a container
            docker logs <container_id>	Show container logs
            docker exec -it <container_id> bash	Access a running container’s shell
            docker inspect <container_id>	Get detailed info about a container
            
        🔹 Working with Images
            Command	Description
            docker images	List all available Docker images
            docker pull <image>	Download an image from Docker Hub
            docker push <image>	Push an image to Docker Hub
            docker rmi <image_id>	Remove an image
            docker tag <image> <repo>:<tag>	Tag an image
            docker build -t <image_name> .	Build a Docker image from a Dockerfile


    Using Dockerfile:
        create a file named Dockerfile with no extension and add the following script commands.
            # A JRE (Java Runtime Environment) image is required because it provides the necessary components to run Java applications,
            FROM openjdk:21-jdk-slim

            # Add metadata (Recommended over MAINTAINER)
            LABEL maintainer="piyushkumar9818@gmail.com"

            # copies the JAR file from the first stage to the second stage.
            COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

            # This is necessary because containers don’t "boot" like a regular OS—they only run a single main process.
            # Without this, the container would start and immediately exit, doing nothing.
            ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]

            Build image
                docker build {path of Dockerfile} -t docker_user/image_name:image_version
                eg:- docker build . -t piyushrawat147/accounts:v1
    
    Using buildpacks(follows the standards enables caching and other optimization to create a light weight file):
        Edit pom.xml plugin part and add this:
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <image>
                        <name>
                            piyushrawat147/${project.artifactId}:v1
                        </name>
                        <builder>paketo-buildpacks/java</builder>
                    </image>
                </configuration>
            </plugin>
        
        Generate docker image:
            Run this command from the place where pom.xml file is present
            mvn spring-boot:build-image
    
     Using Jib:
        Edit pom.xml plugin part and add this:
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                            <version>${lombok.version}</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
        
        Generate docker image:
            Run this command from the place where pom.xml file is present
            mvn compile jib:dockerBuild

    Create a docker container:
        docker run -p 8081:8080 piyushrawat147/accounts:v1

    Push docker image to docker hub:
        docker image push docker.io/{account}/{image_name}:{image_version}

# Docker compose 
    Suppose you have 100 microservices, so it will be a leangthy and tiring task to build all images and run them, that's where docker comes into play, you just have to
    generate a docker-compose.yml file on the root folder where all of your microservices folder resides and use a command from terminal to run the file and let it 
    genereate containers and images for you, there's much more you can do with it we'll see it more.

    docker-compose.yml

    To run the file use this command:
        docker compose up -d
    To remove and stop all the containers:
        docker compose down
        docker compose stop

# docker container command
    create rabbitmq container:
        docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management
            -it → Runs the container in interactive mode with a terminal (-i for interactive, -t for TTY).
            --rm → Automatically removes the container once it stops.
            --name rabbitmq → Assigns the name rabbitmq to the container.
            -p 5672:5672 → Maps port 5672 (used for RabbitMQ messaging) from the container to the host machine.
            -p 15672:15672 → Maps port 15672 (RabbitMQ Management UI) from the container to the host machine.
            rabbitmq:3.12-management → Uses the RabbitMQ v3.12 image with the management plugin enabled.
    create mysql container:
        docker run -p 3306:3306 --name accountsdb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=accountsdb -d mysql
            p for port, e for environment variable and image name = mysql
    create redis container
        docker run -p 6379:6379 --name redis -d redis
    create keycloack container
        docker run -p 7080:8080 -e KC_BOOTSTRAP_ADMIN_USERNAME=admin -e KC_BOOTSTRAP_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:26.2.0 start-dev
        -e KC_BOOTSTRAP_ADMIN_USERNAME=admin	Sets the initial Keycloak admin username.
        -e KC_BOOTSTRAP_ADMIN_PASSWORD=admin	Sets the initial admin password.
        quay.io/keycloak/keycloak:26.2.0	    Specifies the Keycloak image and version.
        start-dev	                            Starts Keycloak in developer mode (simplified config, no HTTPS, no database setup).


# open feign client
    This service is used in a microservice architecture with which several microservices can easily communicate with each other, unlike resttemplate fiengclient provides auto load balancing and it has a declarative way of using it.
    https://medium.com/@AlexanderObregon/navigating-client-server-communication-with-springs-feignclient-annotation-70376157cefd

# API gateway
    https://medium.com/@roopa.kushtagi/the-role-of-api-gateway-in-microservices-architecture-5eca7ab0a2e4

    Implement API gateway using spring cloud gateway:
        https://medium.com/@ksaquib/exploring-api-gateways-with-spring-boot-ba00e8a08f50
    
    Implement dynaminc routing:
        Using yml or programmatic approach will lead to re run of the applicaiton in case of a code change, instead we can store the route in database and fetch it on run time.
        https://anjireddy-kata.medium.com/spring-cloud-gateway-dynamic-route-configuration-and-loading-from-the-datastore-a0637e6bd9b4

# Global Filters
    A Global Filter runs for every request going through the gateway — no matter what route or path it’s targeting. It’s perfect for logging, auth checks, metrics, etc.
    To create a custom global filter for your api gateway you'll have to implement GlobalFilter interface and override two methods
    -> filter(ServerWebExchange exchange,GatewayFilterChain chain)() method: Logic that runs before and after the route's processing.
    -> getOrder(): Controls the order of execution among multiple filters. Lower number = higher priority.


    https://wankhedeshubham.medium.com/spring-cloud-gateway-pre-and-post-global-filter-global-request-and-response-filter-1378bac33c0e

# why spring cloud gateways is reactive in nature, what does "Reactive" mean?
    Reactive programming is a programming style where, You react to data or events as they come.
        -> You process things asynchronously, using streams (like a conveyor belt).
        -> You don’t block threads while waiting for results (like HTTP calls or DB queries).
        -> Think of it like working with a stream of requests — you don’t wait around for one to finish, you just keep reacting to events.
        In Spring, this is powered by Project Reactor, using types like:
            Mono<T> → async stream with 0 or 1 item
            Flux<T> → async stream with 0 or many items

    What does "Non-blocking" mean?
        -> The system doesn’t pause or wait for slow operations (like HTTP calls, database queries, etc.)
        -> Instead of using a thread that waits for the result (which blocks resources), it frees up that thread and reacts when the result is ready.
        So, in a reactive, non-blocking system:
            -> While Service A waits for a response from Service B…
            -> The server thread can handle other requests in the meantime
            -> When Service B responds, it resumes processing Service A’s request

    Why does Spring Cloud Gateway use it?
        High-concurrency traffic
        Microservices communication
        Fast, efficient request routing
        Using reactive, non-blocking IO helps handle thousands of requests with fewer threads and lower memory usage.
        Compare this to traditional (blocking) Spring MVC, where:
        Each request occupies one thread
        If it’s waiting for something (like a DB call), the thread just sits there
        With 1000 slow requests, you need 1000 threads = heavy!

    Example: Reactive vs Blocking
    Blocking Style (Spring MVC):
        String data = restTemplate.getForObject("http://slow-service", String.class);
        // waits here until response returns

    Reactive Style (Spring WebFlux):
        Mono<String> data = webClient.get()
            .uri("http://slow-service")
            .retrieve()
            .bodyToMono(String.class);
        // doesn’t wait, returns immediately


# Resiliency 
    Implementing fault-tolerance and resiliency is important in our spring application, if a service becomes slow or unresponsive it becomes a bottleneck for other microservices dependednt on it, here we need to implement some fault tolerant mechanisms, one such library that implement these patterns is resillency4j.
    https://medium.com/@alxkm/building-resilient-spring-boot-applications-with-resilience4j-cf8e5e7c2700

    Suppose you are sending request to your microservice which is dependent on some other ms, now if that ms becomes unresponsive and no response is there for 10 seconds then for 10 seconds thread processing, memory and CPU utilization is being done which will be a waste of resources, 100 such requests will result in more waiting time, this would severly affect your edge server which will be getting more requests from client, in such case you might need to stop that microservice or return a default response or you might return a cached response, to perform this we need the Circuit-breaker pattern.

# Circuit breaker
    The microservices that faces downtime is generally for a temporary period of time, after that it might start working correctly but for that period of time there might be 1000s of request coming to it, implementing a circuit breaker will detect the impotency of that ms in the initial stage of failure and will help other requests to get a fast response(error,default or cache) which will eventually optimize and imporve the sevice by not letting the requests go to the failing ms.

    Not only this circuit breaker will send some partial data to check if the affected ms is now up or not, if it's up then circuit breaker will go down or elese it will continue working. Circuit breaker pattern ensures fast response, fail gracefully, recover seamlessly.
    
    https://medium.com/spring-boot/exploring-resilience4j-enhancing-circuit-breaker-patterns-for-robust-applications-6cb8093d0b9

# open feing service discovery and request creation.
    Service Discovery (Finding the Location)
    If you're using Spring Cloud with Eureka or any service registry (like Consul):    
        -> You register your microservices with the registry.
        -> Each service registers itself with a name (e.g., payment-service) and host:port.
        -> Feign uses the service name you provide in @FeignClient(name = "payment-service").
        -> Behind the scenes, it uses Ribbon (or Spring Cloud LoadBalancer) to discover the service location:
        -> payment-service → [http://10.0.0.5:8082, http://10.0.0.6:8082]
        -> If you're not using Eureka, Feign uses the url parameter directly (e.g., url="http://localhost:8082").

    Feign Builds the Request
        When you call a method like this paymentClient.getPaymentStatus(), Here's what happens:
            a. Feign generates a dynamic proxy at runtime.
            b. That proxy:
            Builds the HTTP request using method + path (@GetMapping("/status"))
            Adds parameters, headers, body (based on annotations like @RequestParam, @RequestBody, etc.)
            c. Uses an HTTP client (like Apache HttpClient or OkHttp) under the hood to send the request.
            d. Applies interceptors, circuit breakers, retries, and other filters if configured.

    Circuit Breaker & Load Balancer Integration
        If you’re using Spring Cloud LoadBalancer → It picks a healthy instance
        Spring Cloud LoadBalancer (new default):
            -> Uses round-robin by default
            -> Pluggable strategies: random, weighted, zone-aware, etc.

        Resilience4j → Wraps the call in a circuit breaker.
            High-Level Flow (Feign + Eureka + Resilience4j)

            Feign Client
            │
            └─> Service Name (e.g., "payment-service")
                    │
                    └─> Eureka/Discovery Client → Get instances
                            │
                            └─> LoadBalancer → Choose one instance
                                    │
                                    └─> Resilience4j CircuitBreaker wraps the call
                                            │
                                            └─> HTTP request via WebClient/RestTemplate
                                                    │
                                                    └─> Actual downstream service

# Note
    implementing CB on api gateway will override you CB inside your services.

# Retry 
    Retry enables you to handle transient errors efficiently. Transient errors are temporary and usually, the operation is likely to succeed if retried.
    retry on service level:
        Pros: Service has more context; retries can be fine-tuned (e.g., retry only on timeouts, not on validation errors).
        Cons: No retries if the service is down/unreachable from the gateway.
        https://anjireddy-kata.medium.com/architecture-and-design-101-resiliency-patterns-retry-mechanism-67eb7da51318
    retry on gateway level:
        Pros: Simpler service logic; centralized retry logic.
        Cons: Less context — gateway doesn’t know if a failure is transient or fatal.
        .route(p -> p
						.path("/banking/cards/**")
						.filters(filter ->
								filter.rewritePath("banking/cards/?(?<remaining>.*)","/${remaining}")
										.retry(retryConfig -> retryConfig
												.setMethods(HttpMethod.GET)
												.setRetries(3)
												.setBackoff(Duration.ofMillis(100),Duration.ofMillis(1000),2,true))
						)
						.uri("lb://CARDS")
				)
    try to put retry operation on only on of these layers, not on both else for total retry operations will increase to (API retry x service retry).

# Rate Limmiter
    One of the imperative architectural concerns is to protect APIs and service endpoints from harmful effects, such as denial of service, cascading failure. or overuse of resources. Rate limiting is a technique to control the rate by which an API or a service is consumed. In a distributed system, no better option exists than to centralize configuring and managing the rate at which consumers can interact with APIs. Only those requests within a defined rate would make it to the API. Any more would raise an HTTP “Many requests” error.

# Centralized logging

# Distributed tracing

# OAuth grant types
    OAuth 2.0 defines grant types as ways for clients to obtain access tokens to access resources on behalf of a user or themselves.
    Think of a grant type as a "login flow" — a protocol that explains who is trying to get access, and how.
    
    There are different flows (grant types) depending on:
        Who the client is (user, app, machine)
        How secure the environment is (browser, backend, mobile)
    
    Let's discuss some of these flow(Grant Types)
    https://athiththan11.medium.com/oauth-2-grant-types-a-story-guide-582580a3c4c2

    In our microservices architecture we are using the client credentials grant type which means that this is service-to-service flow, meaning there is no UI from where the resource owner can grant the access or permission for the client. 
    Here the registered client will send the authentication request to the auth server which in this case we're using Keyclock, this auth server will have the registered clients with their roles and on the basis of it, it will grant the access token to the client, this access token will be used by the client(you can refer client as as external API/server) to query the resource server for resources.
    Resource server is the server which actually contain the resources or services that the client needs, in our case we're using our gateway server as the resource server, this resource server will fetch the access token from the client request and the it asks the auth server to verify that token, based on it our request will be approved.

# Microservice security using keyclock(auth server)
    Let’s walk through how to implement microservices security using Keycloak step by step. This setup is widely used and integrates well with Spring Boot, Spring Security, or any service needing OAuth2/OIDC-based authentication and authorization.

    Overview of What We’ll Build
        Microservices secured by Keycloak, where:
        Keycloak handles login, token issuance (JWT), user roles
        Each microservice validates incoming requests using Keycloak tokens
        Gateway (like Spring Cloud Gateway or API Gateway) authenticates and optionally authorizes requests

    Step-by-Step Implementation
    1. Set Up Keycloak
        Run Keycloak with Docker:
            docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:24.0.2 start-dev

    2. Create Realm, Client & Roles in Keycloak
        Create a Realm (e.g., myrealm)
        Create a Client (e.g., my-microservice)
        Set access type to confidential (or public for frontend)
        Set valid redirect URIs if needed (for web apps)
        Create Roles (e.g., USER, ADMIN)
        Create Users and assign roles

    3. Secure Spring Boot Microservice with Keycloak
        Add dependencies:
            <!-- pom.xml -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-security</artifactId>
            </dependency>
        application.yml:
            spring:
            security:
                oauth2:
                resourceserver:
                    jwt:
                    issuer-uri: http://localhost:8080/realms/myrealm
        SecurityConfig.java:
            @EnableWebSecurity
            public class SecurityConfig {

                @Bean
                public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                    http
                        .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/admin/**").hasRole("ADMIN")
                            .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                            .anyRequest().authenticated()
                        )
                        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
                    return http.build();
                }
            }
    4. Use Tokens in Requests
        Get a token using Keycloak:
            curl -X POST http://localhost:8080/realms/myrealm/protocol/openid-connect/token \
            -H "Content-Type: application/x-www-form-urlencoded" \
            -d "client_id=my-microservice" \
            -d "username=myuser" \
            -d "password=mypassword" \
            -d "grant_type=password"
        Call your service with the token:
            curl http://localhost:8081/user/hello \
            -H "Authorization: Bearer <access_token>"

    Optional Additions
    Spring Cloud Gateway to centralize auth
    Use Keycloak adapters or OPA for fine-grained auth
    Refresh tokens and login flow for frontends (Angular/React)

# Resource server internal flow
    First: What is a Resource Server?
        In OAuth2, a Resource Server is any backend service that:
        Receives access tokens (like JWTs) from clients.
        Validates those tokens.
        Authorizes access based on claims like roles or scopes.
    In your case, your Gateway becomes a Resource Server when it:
        Receives requests with an Authorization: Bearer <JWT> header.
        Validates the token against Keycloak's realm (issuer).
        Authorizes routes/endpoints based on the JWT's claims.

    Internal Flow in Spring Security (Simplified)
        Let’s assume this config exists:
            spring:
                security:
                    oauth2:
                    resourceserver:
                        jwt:
                        issuer-uri: http://localhost:8080/realms/demo-realm

    Here's what happens step-by-step when the Gateway starts:

    1️. Spring Boot Autoconfiguration kicks in
        The class OAuth2ResourceServerAutoConfiguration is triggered.
        This detects that you want a Resource Server, because:
            The classpath has spring-security-oauth2-resource-server
            The spring.security.oauth2.resourceserver.jwt config is present
        This yaml configuration:
            Registers the BearerTokenAuthenticationFilter into the SecurityFilterChain
            Configures it to:
                Look for Authorization: Bearer <token> header
                Extract and pass the token to AuthenticationManager

            When the BearerTokenAuthenticationFilter sees:
                Authorization: Bearer eyJhbGciOiJIUz...
                It Extracts the token and wraps it into a BearerTokenAuthenticationToken
                    String token = request.getHeader("Authorization").substring(7);
                    Authentication authRequest = new BearerTokenAuthenticationToken(token);
                Delegates to AuthenticationManager.authenticate(...):
                    Authentication authResult = authenticationManager.authenticate(authRequest);

            When AuthenticationManager hits the authenticate method.
                Spring registers a specialized AuthenticationManager that knows how to handle BearerTokenAuthenticationToken.
                It does so by creating a JwtAuthenticationProvider and wiring it into the AuthenticationManager
                Spring knows to register JwtAuthenticationProvider with the AuthenticationManager because of the .oauth2ResourceServer().jwt() configuration in your HttpSecurity, here refered as our yml config.
                AuthenticationManager delegates to JwtAuthenticationProvider:
                    This is where Spring’s provider-based architecture comes into play.
                    The manager loops through a list of AuthenticationProviders.
                    Finds one that supports BearerTokenAuthenticationToken, that would be JwtAuthenticationProvider.
                    public boolean supports(Class<?> authentication) {
                        return BearerTokenAuthenticationToken.class.isAssignableFrom(authentication);
                    }

            when JwtAuthenticationProvider.authenticate(...) is called
                Jwt jwt = this.jwtDecoder.decode(token);
                Calls the JwtDecoder to validate and parse the JWT:
                Verifies the signature using Keycloak's public key (from JWK set)
                Checks expiration, issuer, etc.
            Create Authentication object and stores it in SecurityContextHolder to verify further requests.
                JwtAuthenticationToken authToken = new JwtAuthenticationToken(jwt, authorities);
                Converts claims (like realm_access.roles) to GrantedAuthoritys
                Creates a fully authenticated object
                The result is returned back through:
                    SecurityContextHolder.getContext().setAuthentication(authResult);
        SecurityFilterChain:
            → [ CORS Filter ]
            → [ CSRF Filter ]
            → [ BearerTokenAuthenticationFilter ] ← This kicks in when `Authorization: Bearer ...` is found
            → [ Authorization Filter ]

    2️. JWT Decoder is Created
        Spring boot creates a bean of type JwtDecoder. Internally:
            //This url is generally read from application.yml file when you spring internally creates it.
            JwtDecoder decoder = JwtDecoders.fromIssuerLocation("http://localhost:8080/realms/demo-realm");
            This calls Keycloak’s /.well-known/openid-configuration endpoint.
            It retrieves Keycloak’s public keys (JWK Set).
            These keys are used to validate signed JWTs.

    3️. SecurityFilterChain is Configured:
        Spring configures a default SecurityFilterChain that includes:
            An AuthenticationManager that knows how to parse and validate JWT tokens

        A BearerTokenAuthenticationFilter which:
            Reads the Authorization: Bearer <token> header
            Extracts the token
            Passes it to the JWT decoder
            Converts the token into an Authentication object (JwtAuthenticationToken)
            Stores it in the SecurityContextHolder

    4️. Request Handling at Runtime
        At runtime, when a request hits your Gateway:
            The BearerTokenAuthenticationFilter detects the Authorization header.
            It extracts the JWT token.
            Passes it to the JwtDecoder (backed by Keycloak's JWK set).
            If valid, it creates an Authentication object.
            The request proceeds if the endpoint allows that role/authority.
            If invalid (expired token, wrong signature), it returns 401 Unauthorized.

    Summary: What Makes Gateway a Resource Server?
        You included spring-boot-starter-oauth2-resource-server
        You configured spring.security.oauth2.resourceserver.jwt.issuer-uri

        Spring Security:
            Automatically loads Keycloak's public key set
            Adds a filter to validate JWTs
            Converts JWT claims into Spring Authentication and GrantedAuthority
            Enforces access rules via annotations or route rules

# @EnableWebSecurityFlux
    @EnableWebFluxSecurity activates Spring Security for reactive applications using Spring WebFlux instead of traditional (blocking) Spring MVC.
    It does a few key things:
        Registers a SecurityWebFilterChain bean
        Enables reactive security filters (non-blocking)
        Allows you to configure access control rules using a ServerHttpSecurity bean

    Without @EnableWebFluxSecurity, Spring won’t know to apply security to your WebFlux routes. Your routes will:
        Not be protected
        Ignore any SecurityWebFilterChain config you write
        won't inject ServerHttpSecurity bean for you to use

# why spring won't automatically extract keycloak roles from access token for role based authorization
    That’s a common (and great!) question — the short answer is:
    Spring Security doesn't automatically know how to extract Keycloak roles, because Keycloak puts roles in a custom location in the JWT, not where Spring expects by default.
    As we know that for role based access authorization of any jwt token spring looks for roles inside "authorization" column inside jwt.
    Spring’s Default Expectation

    Spring Security expects authorities (roles) in the authorities claim, like:
        {
        "authorities": ["ROLE_admin", "ROLE_user"]
        }
    But Keycloak puts roles under:
        {
            "realm_access": {
                "roles": ["admin", "user"]
            }
        }
    That’s not standard according to Spring's default config — so unless you customize it, Spring won’t look there.
    Here's the internal flow:
        Spring Security Default Flow (with JWT)
            Spring Security with oauth2ResourceServer.jwt works like this:
                HTTP Request Comes In with Authorization: Bearer <JWT> header.
                BearerTokenAuthenticationFilter intercepts the request and pulls out the token.
                The token is passed to a JwtDecoder, which:
                    -> Verifies the signature using public keys from the issuer.
                    -> Parses the JWT and builds a Jwt object containing claims.
                    -> The JwtAuthenticationProvider takes that Jwt object and:
                    -> Uses a JwtGrantedAuthoritiesConverter to extract roles.
                    -> Wraps the result into a JwtAuthenticationToken.

            By default, this converter looks for scope or scp or authorities claims to extract roles.
            {
                "authorities": ["ROLE_user"],
                "scope": "read write"
            }
            So if you don’t have a claim named authorities, Spring doesn't find any roles = Access Denied.

        Problem with Keycloak
            Keycloak puts roles in a nested claim:
            {
                "realm_access": {
                    "roles": ["admin", "user"]
                }
            }
            Spring's default JwtGrantedAuthoritiesConverter doesn't know to look inside realm_access.roles, so:
            No roles are extracted.
            Access checks like hasRole("admin") will fail, because user has no authorities.

        Enter KeycloakRoleConverter
            You write your own Converter<Jwt, Collection<GrantedAuthority>>, like:
                jwt.getClaim("realm_access").get("roles")
                This converter now:
                    -> Reads the realm_access.roles list from the JWT.
                    -> Prefixes each with ROLE_ (Spring expects roles like ROLE_admin).
                    -> Returns it as a collection of GrantedAuthority objects.

    Modified Internal Flow with KeycloakRoleConverter
        -> HTTP Request Comes In with Authorization: Bearer <JWT>.
        -> BearerTokenAuthenticationFilter extracts token.
        -> Token goes to JwtDecoder → verifies + parses → produces Jwt.
        -> JwtAuthenticationProvider gets the Jwt.
        -> It calls your custom converter: KeycloakRoleConverter.convert(jwt)
        -> This reads realm_access.roles = ["admin", "user"]
        -> Converts them to: [ROLE_admin, ROLE_user]
        -> Those are used to create the Authentication object (typically JwtAuthenticationToken).
        -> Now SecurityContext holds the authenticated user with authorities.
        -> When your SecurityFilterChain checks .hasRole("admin"), it finds ROLE_admin → ✅ access granted.

    How to Fix It
        You need to tell Spring how to map Keycloak’s realm_access.roles into Spring authorities. you can do it in two ways.
            1. Configure Spring to use our Keycloak converter
            2. Define which roles are required to access the endpoints
        we will continue with the 1st approach.
        Step-by-step Role Mapping
            1. Create a Role Converter
                import org.springframework.core.convert.converter.Converter;
                import org.springframework.security.core.GrantedAuthority;
                import org.springframework.security.core.authority.SimpleGrantedAuthority;
                import org.springframework.security.oauth2.jwt.Jwt;

                import java.util.Collection;
                import java.util.List;
                import java.util.Map;
                import java.util.stream.Collectors;

                public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

                    @Override
                    public Collection<GrantedAuthority> convert(Jwt jwt) {
                        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
                        if (realmAccess == null || realmAccess.isEmpty()) {
                            return List.of();
                        }

                        List<String> roles = (List<String>) realmAccess.get("roles");

                        return roles.stream()
                                .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // Spring needs "ROLE_" prefix
                                .collect(Collectors.toList());
                    }
                }
            2. Apply it to the JWT Decoder
                import org.springframework.context.annotation.Bean;
                import org.springframework.context.annotation.Configuration;
                import org.springframework.security.oauth2.jwt.JwtDecoder;
                import org.springframework.security.oauth2.jwt.JwtDecoders;
                import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

                @Configuration
                public class JwtSecurityConfig {

                    @Bean
                    public JwtDecoder jwtDecoder() {
                        NimbusJwtDecoder jwtDecoder = JwtDecoders.fromIssuerLocation("http://localhost:7080/realms/spring-microservices");
                        jwtDecoder.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
                        return jwtDecoder;
                    }
                }
        Result
            Now when you use:
            .antMatchers("/admin/**").hasRole("admin")
            Spring will correctly detect ROLE_admin from the realm_access.roles in the token.

# Event driven microservices
    So what is an event? It is an incident that happens inside your microservices, which signifies a state transition or an update inside your system. Whenever an event takes place, we need to alert the concerned parties.
    For example, take an e-commerce application inside Amazon website, whenever you place an order, the order microservice has to notify the delivery microservice, which is deployed inside the Amazon network. The communication between order microservice and the delivery microservice dosn't have to be an synchronous communication.
    Instead, the order microservice as soon as the end user made a payment and order is confirmed, it is going to trigger an event which will act as a notification to the delivery microservice. 
    In this scenario, the order microservice is just going to generate an event or trigger an notification. Apart from that, the order microservice is not going to wait for that delivery process to complete and the delivery microservice to give a successful response. The order microservice responsibility is to only send a notification to the delivery microservice. So this is a classic example of event driven microservice.
    So if you see here, the communication is not going to happen synchronously. Instead the communication is going to happen with the help of asynchronous communication. Because the order microservice is not waiting for the successful response from the delivery microservice.Instead, it simply send a notification or alert and its job is completed.
    In order to build the event driven microservices, we need to follow event driven architecture, producing and consuming events with the help of asynchronous communication event brokers and in the same process, we can leverage to fascinating projects available inside the spring cloud ecosystem, which are spring cloud function and spring cloud stream.

# RabbitMq
    RabbitMQ is a message broker: it accepts and forwards messages. You can think about it as a post office: when you put the mail that you want posting in a post box, you can be sure that the letter carrier will eventually deliver the mail to your recipient. In this analogy, RabbitMQ is a post box, a post office, and a letter carrier.
    https://medium.com/cwan-engineering/rabbitmq-concepts-and-best-practices-aa3c699d6f08
    
    example: 
        https://www.cloudamqp.com/blog/part1-rabbitmq-for-beginners-what-is-rabbitmq.html

    Core Concepts in RabbitMQ
        1. Queue
            A queue is a buffer that stores messages until a consumer retrieves them.
            It’s the endpoint where messages reside until someone reads them.

            Consumers listen to queues, not exchanges.

            A queue has a name and can be durable, exclusive, and auto-delete.

        Publisher --> Exchange --> Queue --> Consumer
        2. Exchange
            An exchange is like a message router 📡.
            It receives messages from producers and routes them to queues based on rules defined by bindings.
            Exchanges don't store messages; they just pass them along.

        Types of exchanges:
            Direct: Routes to queues with exact matching routing key
            Topic: Routes using wildcard routing keys (*, #)
            Fanout: Broadcasts to all bound queues, ignores routing keys
            Headers: Routes based on message headers instead of routing keys

        3. Binding
            A binding is the link between an exchange and a queue, with optional routing criteria.

            Think of it like:
                “Queue A wants to receive messages from Exchange X with routing key = foo.bar”
                Exchange (X) --binding key: "foo.bar"--> Queue A

# How does my other services know from which queue of which exchange should their methods listen to?
    You define the destination (exchange) and group (which maps to a queue) in application.yml
    This acts as a binding contract between producer and consumers

    Service A – producer:
        streamBridge.send("outputA", message)

        YAML:
            spring.cloud.stream.bindings.outputA.destination=order.events
            spring.cloud.stream.rabbit.bindings.outputA.producer.routing-key-expression='''order.created'''
        If exchange have multiple queues the to route to a specific queue will push 

    Service B – consumer:
        @Bean
        public Consumer<OrderCreatedEvent> orderListener() {
            return event -> System.out.println("Received: " + event);
        }

        YAML:
            spring.cloud.stream.bindings.orderListener.destination=order.events
            spring.cloud.stream.bindings.orderListener.group=service-b
            spring.cloud.stream.rabbit.bindings.orderListener.consumer.routing-key='order.created'

        Spring Cloud Stream does this at runtime:
            Declares an exchange named order.events (if not already there)
            Declares a queue named: order.events.service-b 
        Binds the queue to the exchange (with optional routing key)
        Sets up a listener on that queue

        Group keyword:
            If you omit the group, Spring treats the consumer as anonymous and ephemeral:
                A new, non-durable queue(auto-delete) is created each time.
                Messages are broadcast to all anonymous consumers
                The consumer receives messages only while it’s running
                No message replay or durability
                Good for:
                    Stateless event listeners
                    Fire-and-forget event-driven flows
            If you use the this then:
                The consumer is durable (will survive restarts)
                RabbitMQ creates a durable queue named after the group
                Messages are load-balanced across consumers in that group
            
                    With group | Without group
            Durable consumer   |   Ephemeral consumer
            Shared queue       | Individual temp queues
            Load-balanced msgs | Broadcast to all
            Good for scale-out | Good for dev/testing

    Here's How the Binding Works:
        Both services are pointed to the same exchange: order.events
        The consumer uses a group (creates a durable, named queue):
        → Queue name: order.events.service-b
        The queue is automatically bound to the exchange using routing-key=order.created

    So:
    [ exchange: order.events ]
        → binding → queue: order.events.service-b
            → consumer: Service B

# Service order a have two rest methods A and B that sends notifiaction to two queues queueA and queueB, from these two queues my service notifiaction reads messages   from these two queues with method readQueueA and readQueueB
    Project 1: order-service
        application.yml:
            spring:
                application:
                    name: order-service

            cloud:
                stream:
                    function:
                        definition: sendToA;sendToB
                    bindings:
                        sendToA-out-0:
                            destination: queueA
                        sendToB-out-0:
                            destination: queueB
                    rabbit:
                        bindings:
                            sendToA-out-0:
                                producer:
                                    routing-key-expression: '''queueA'''
                            sendToB-out-0:
                                producer:
                                    routing-key-expression: '''queueB'''

            spring.cloud.stream.bindings.*
                This is generic to Spring Cloud Stream — used to:
                -> Bind functions (in-0, out-0) to destinations (topics, queues, exchanges)
                -> Define basic stream-level info like group, destination, and contentType
                -> This tells Spring Cloud Stream: "The output of sendToA() function goes to queueA".

            spring.cloud.stream.rabbit.bindings.*
                This is broker-specific configuration for RabbitMQ — used to:
                -> Fine-tune Rabbit-specific behavior
                -> Define routing keys, exchange types, dead-letter queues, etc.
                routing-key-expression: '''queueA''' tells Spring: "When sending to Rabbit, use the routing key queueA for this binding."
            
            Why both are needed?
                Spring Cloud Stream is designed to be broker-agnostic — Kafka, RabbitMQ, Pulsar, etc.
                -> bindings.* configures how functions are wired
                -> rabbit.bindings.* configures how messages behave in Rabbit
            They're merged at runtime to produce the final message routing behavior

    MessageProducer.java:
        @RestController
        @RequiredArgsConstructor
        public class MessageProducer {

            private final Function<String, String> sendToA;
            private final Function<String, String> sendToB;

            @PostMapping("/sendToA")
            public ResponseEntity<String> sendToQueueA(@RequestBody String message) {
                sendToA.apply(message);
                return ResponseEntity.ok("Message sent to Queue A");
            }

            @PostMapping("/sendToB")
            public ResponseEntity<String> sendToQueueB(@RequestBody String message) {
                sendToB.apply(message);
                return ResponseEntity.ok("Message sent to Queue B");
            }
        }

    Functions.java:
        @Configuration
        public class Functions {

            @Bean
            public Function<String, String> sendToA() {
                return message -> {
                    System.out.println("Sending to Queue A: " + message);
                    return message;
                };
            }

            @Bean
            public Function<String, String> sendToB() {
                return message -> {
                    System.out.println("Sending to Queue B: " + message);
                    return message;
                };
            }
        }

    Project 2: notification-service
        application.yml:
        spring:
            application:
                name: notification-service

            cloud:
                stream:
                function:
                    definition: readQueueA;readQueueB
                bindings:
                    readQueueA-in-0:
                    destination: queueA
                    group: notificationGroupA
                    readQueueB-in-0:
                    destination: queueB
                    group: notificationGroupB

            Group keyword:
                If you omit the group, Spring treats the consumer as anonymous and ephemeral:
                    A new, non-durable queue(auto-delete) is created each time.
                    Messages are broadcast to all anonymous consumers
                    The consumer receives messages only while it’s running
                    No message replay or durability
                    Good for:
                        Stateless event listeners
                        Fire-and-forget event-driven flows
                If you use the this then:
                    The consumer is durable (will survive restarts)
                    RabbitMQ creates a durable queue named after the group
                    Messages are load-balanced across consumers in that group
                
                        With group | Without group
                Durable consumer   |   Ephemeral consumer
                Shared queue       | Individual temp queues
                Load-balanced msgs | Broadcast to all
                Good for scale-out | Good for dev/testing'

                Why do we assign group in input bindings?
                    Because only consumers (input bindings) care about "grouping".
                    OUTPUT (Producers) don't care about group
                    -> When you send a message, you’re just dropping it into an exchange or topic — you don't care who receives it, or how many.
                    So for output, no group is needed.

                    INPUT (Consumers) need group to:
                        1. Ensure message delivery is shared across instances
                            If you scale notification-service horizontally (e.g., 3 pods), and you want to avoid duplicate processing, the group makes all instances part of a single group, so each message is only consumed once, by one of the instances.
                            readQueueA-in-0:
                                destination: queueA
                                group: notificationGroupA

                            This means:
                                “All instances of notification-service that share this group will consume from the same RabbitMQ queue: queueA.notificationGroupA.”
                                Without the group, each instance would get all messages, resulting in duplicates"

                        2. Enable durability
                            With group, Spring Cloud Stream:
                                -> Creates a durable queue
                                -> Binds it to the configured destination
                                -> Ensures that even if the consumer goes down, messages are persisted in the queue
                                -> No group = non-durable, auto-deleted queue → messages may be lost if the consumer is down

    NotificationServiceApplication.java
        @SpringBootApplication
        public class NotificationServiceApplication {
            public static void main(String[] args) {
                SpringApplication.run(NotificationServiceApplication.class, args);
            }
        }

    MessageConsumer.java:
        @Configuration
        public class MessageConsumer {

            @Bean
            public Consumer<String> readQueueA() {
                return message -> {
                    System.out.println("Received from Queue A: " + message);
                };
            }

            @Bean
            public Consumer<String> readQueueB() {
                return message -> {
                    System.out.println("Received from Queue B: " + message);
                };
            }
        }
    flow diagram:
               ┌──────────────────────────────┐
               │        order-service         │
               │     (Spring Cloud Stream)    │
               └──────────────────────────────┘
                        ▲            ▲
                        │            │
         Function: sendToA()     Function: sendToB()
                        │            │
                        ▼            ▼
         ┌────────────────────┐ ┌────────────────────┐
         │ Binding: sendToA-  │ │ Binding: sendToB-  │
         │ out-0              │ │ out-0              │
         └────────┬───────────┘ └────────┬───────────┘
                  │ Rabbit Binding        │
   routing-key: 'queueA'       routing-key: 'queueB'
                  │                         │
                  ▼                         ▼
           ┌────────────┐           ┌────────────┐
           │ RabbitMQ   │           │ RabbitMQ   │
           │ Exchange   │           │ Exchange   │
           └────┬───────┘           └────┬───────┘
                │                        │
           routingKey=queueA       routingKey=queueB
                ▼                        ▼
   ┌─────────────────────────┐ ┌─────────────────────────┐
   │ Queue: queueA.notificationGroupA │ │ Queue: queueB.notificationGroupB │
   └────────────┬────────────┘ └────────────┬────────────┘
                │                           │
     Function: readQueueA()       Function: readQueueB()
                │                           │
                ▼                           ▼
  ┌──────────────────────────────┐ ┌──────────────────────────────┐
  │     notification-service     │ │     notification-service     │
  │     (Spring Cloud Stream)    │ │     (Spring Cloud Stream)    │
  │     Group: notificationGroupA│ │     Group: notificationGroupB│
  └──────────────────────────────┘ └──────────────────────────────┘


# Spring cloud function
    Without Spring Cloud Function — The Traditional Way
        Let’s say you want to:
        -> Expose logic via HTTP
        -> Expose the same logic as a Kafka consumer
        -> Deploy it to AWS Lambda

        Problem: You need to duplicate the logic!, Example: Capitalizing a string input you'd typically write:
            A. For HTTP (Spring Web):
                @RestController
                public class UppercaseController {
                    @PostMapping("/uppercase")
                    public String toUppercase(@RequestBody String input) {
                        return input.toUpperCase();
                    }
                }
            B. For Kafka (Spring Cloud Stream):
                @EnableBinding(MyProcessor.class)
                public class KafkaListener {
                    @StreamListener("input")
                    public void process(String input) {
                        String output = input.toUpperCase();
                        // send to output channel
                    }
                }
            C. For AWS Lambda:
                public class UppercaseLambda implements RequestHandler<String, String> {
                    @Override
                    public String handleRequest(String input, Context context) {
                        return input.toUpperCase();
                    }
                }
        Issue: Ypu duplicated your logic input.toUpperCase() across 3 different technologies. That’s not DRY or flexible.

    With Spring Cloud Function — The Elegant Way
        You write the logic once — as a function. Spring handles the rest.
            @Bean
            public Function<String, String> uppercase() {
                return value -> value.toUpperCase();
            }

            A. Use It via HTTP:
            Add spring-cloud-starter-function-web
                curl http://localhost:8080/uppercase -H "Content-Type:text/plain" -d "hello"
            B. Kafka
                Add spring-cloud-starter-stream-kafka
                    Configuration:
                        spring:
                            cloud:
                                function:
                                definition: uppercase
                                stream:
                                bindings:
                                    uppercase-in-0:
                                    destination: input-topic
                                    uppercase-out-0:
                                    destination: output-topic
                It listens to input-topic, applies the function, and publishes to output-topic.
            C. AWS Lambda
                Add spring-cloud-function-adapter-aws
                It automatically wires the function as a handler.
                You can deploy the same Function as an AWS Lambda using the provided adapter class.

    With Spring Cloud Function, you write logic once, and deploy it everywhere — HTTP, Kafka, RabbitMQ, AWS Lambda — without any tech-specific code.

# Apache kafka
    https://medium.com/@sutanu3011/kafka-a-peek-into-internals-b47b9dc6fd0f
    This has a part-2 read that too.

    Partitions in kafka:
    You're diving into the heart of Kafka parallelism — awesome! Let's break down partitioning in detail, how consumers interact with it, and how Kafka manages load distribution and ordering.

    What Is a Partition (Quick Recap)?
        A partition is a sub-log of a Kafka topic:
            Each partition is a sequential, append-only log
            It is the unit of parallelism and ordering
            Kafka topics can have one or more partitions

    How Partitioning Works (Producer Side)
        When a producer sends a message to a topic, Kafka decides which partition it should go to.

    Partition Assignment Strategies:
        Key-based	Kafka hashes the message key, then assigns it to a specific partition: partition = hash(key) % numPartitions
        Round-robin	If no key is provided, Kafka round-robins the message across partitions
        Custom partitioner	Developers can write custom logic to determine partitioning
        Same key → same partition → ordered delivery per key

    Partition Storage
        Each partition is:
            Physically stored on a single Kafka broker
            Replicated to other brokers (for fault tolerance)
            Contains log segments + index files

    How Consumers Read from a Partition
        A consumer reads messages sequentially from a partition
        Tracks its own offset — the position of the last read message
        Uses poll() to fetch data from the broker

        records = consumer.poll(Duration.ofMillis(100));
        Kafka sends:
            A batch of messages (from the offset the consumer left off)
            Messages in the exact order they were written in the partition

    Consumer Groups and Partition Assignment
        Key Concepts:
            Kafka uses consumer groups to coordinate load sharing
            Each partition is consumed by at most one consumer in a group at a time
            This guarantees no duplicate processing within a group

        Example:
            Topic: orders with 3 partitions
            Scenario -> Partition Assignment
                1 consumer in group	Gets all 3 partitions
                2 consumers in group	One gets 2, the other gets 1
                3 consumers in group	Each gets 1 partition
                4+ consumers in group	Extra consumers stay idle (only 3 partitions)
                🔁 When consumers join/leave, Kafka rebalances partition assignments.

    Ordering Guarantee
        Kafka guarantees message order:
            Within a partition
            Not across partitions 
        If order matters (e.g., all events of a single user), use a consistent key so those messages go to the same partition.

# Kubernetes
    Kubernetes (often abbreviated as K8s) is an open-source platform for automating the deployment, scaling, and management of containerized applications.
    Think of it like this:
        If Docker is like putting your application in a shipping container, Kubernetes is the shipping company that:
        Figures out where containers should go
        Makes sure they're running properly
        Restarts them if they crash
        Scales them up or down depending on demand
        Handles network communication between them

        The Problem Before Kubernetes:
            Let’s say you're using Docker to run your app in a container. That’s awesome... for one container.
            But real-world apps are more complex. You’ll quickly run into problems like:
                1. Manual deployment & scaling
                How do you deploy 10 copies of your app across multiple servers?
                How do you scale from 3 to 30 containers during peak hours?

                2. Fault tolerance
                What if one container crashes? Who restarts it?
                What if the whole server dies.

                3. Networking
                How do services find each other when containers have dynamic IPs?
                How do you expose your app to the outside world?

                4. Rolling updates
                How do you deploy a new version of your app without downtime?
                And if something breaks, how do you rollback?

                5. Configuration management
                How do you manage different environments (dev, test, prod)?
                How do you handle secrets and app configs cleanly?

            NOTE -> Kubernetes doesn't replace Docker — it builds on top of Docker (or any container runtime) to fix the problems you run into when managing containers at scale.

        How Kubernetes Fixes This
            Here’s how Kubernetes makes Docker production-ready:
                1. Deployments & ReplicaSets
                    You tell Kubernetes how many copies (replicas) you want. It ensures that number is running — always. If one crashes, it auto-restarts.
                2. Services & Ingress
                    Services give your app a fixed name and IP, even if the pod changes. Ingress lets you expose services with rules, TLS, etc.
                3. Autoscaling
                    K8s can automatically scale your app based on CPU or memory usage (with Horizontal Pod Autoscaler).
                4. Health Checks
                    You define readiness and liveness probes. If your app stops responding, K8s restarts it automatically.
                5. Secrets & ConfigMaps
                    Securely store and manage config data or secrets (like API keys or passwords) — separated from your code.
                6. Rolling Updates
                    Update your app with zero downtime. If something breaks, roll back in seconds.

        
# Client side vs server side load balancing

# ClusterIP vs LoadBalancer 


