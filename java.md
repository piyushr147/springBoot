# SERVELTS
    Servlet is a class that extends the capabilities of the servers and responds to the incoming requests. It can respond to any requests.Servlet is a web component that is deployed on the server to create a dynamic web page.

    CGI technology enables the web server to call an external program and pass HTTP request information to the external program to process the request. For each request, it starts a new process and the creation is limited plus if the no of clients increases it takes more time for response, instead of this servlets use mutithreading for multiple request handling at the same time. Threads have many benefits over the Processes such as they share a common memory area, lightweight, cost of communication between the threads are low.
    public static void main( String[] args ) throws LifecycleException {
        System.out.println( "Hello World!" );
        Tomcat tomcat = new Tomcat();
        tomcat.start();
        tomcat.getServer().await();//gets the server and awaits it to process request response until you want to close the server
    }
    Now that dynamic(html,css) content can be send through servlet but we don't prefer this because it becomes bulky which would make it more time taking, instead we use JSPs (java servlet pages), servlets do their processing part and send the data to jsp where data is populated inside html pages and rendered to the client side.

# NOTE
    Normally to run a spring project we need a war file which has to be kept inside out externally downloaded tomcat, which is a server that runs out project but spring boot comes an embedded tomcat which creates the server and runs our jar file which starts our project.

# MVC
    model,view,controller
    so what happens is for eg a client asks for a page with dynamic content in our web application, so this request goes to the controller which is a servlet, this servlet fetched data from the model(database) and sends the data(object) to the view which might be react,angular or a jsp, this data gets populated inside the view which is sended as a response to the client where the html page is shown.

# Controller
    The @Controller annotation used in spring boot is used to tell the spring framework that this is a servlet.

# NOTE
    Tomcat server does not work on jsp directly instead what it does is it converts the jsp into a servlet so that it can work with it, to convert it we use a dependency called tomcat jasper.

# Model
    Model is an Interface in the spring core package under com.springframework.ui is used for transferring the data or attributes from our business logic to the rendering view pages. Its primary use is to add attributes to the model and can be simply viewed and accessed similar to the java.util.Map Interface.

# @ModelAttribute
    The @ModelAttribute annotation in Spring MVC serves multiple roles, providing a robust solution for data mapping between a client's request and the server's model object. This annotation can be applied to method parameters, method return types, or even methods themselves
    At its core, @ModelAttribute is designed for binding form data, query parameters, or even attributes in the session to Java objects. In simple terms, it binds an HTML form's input fields to the properties of a Java object. This is highly beneficial when dealing with forms that contain a large number of fields, as it eliminates the need to manually extract each form parameter.

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
    ORM stands for object relational mapping. suppose you have want to create a student table which contains a rollNo, name ans marks, what you can do is you can create a student class with the same properties in your spring boot project and let any ORM tool map your class to a table in a relational database, this mapping will make a developers work easier.
    one of such tools is Hibernate.
    Every object of that class will map to a row in database, creating and saving a new object will create a new row in the table.

# Spring data jpa
    There are different tools for ORM like hibernate, and different projects might use another different tools, so now if you want to shift to some other ORM tool from hibernate, you might have to change the whole code, so can't there be any standardized specification on which all these ORM tools can agree on so that you don't have to change a lot of code in your project.
    Here comes JPA(java persistence API) which is an implementation on which all the other ORM tools work.

# JPA vs JDBC
    JPA is simpler, cleaner, and less labor-intensive than JDBC, SQL, and hand-written mapping. JPA is suitable for non-performance oriented complex applications. The main advantage of JPA over JDBC is that, in JPA, data is represented by objects and classes while in JDBC data is represented by tables and records. It uses POJO to represent persistent data that simplifies database programming. There are some other advantages of JPA
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
    whenever you configure spring security it will provide you with a custom authentication provider, now the role of authentication provider is to authenticate your user.
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
    Now this UserDetails is an interface that spring tells it will work with for authentication, so we need need object of a class extending this interface.
    Here we need to create a new class called UserPrincipal that implements UserDetails and overrides all the methods.
    But before that we need to create a UserRepository class as we are working with spring-data-jpa and declare a method to findByUsername which finds the user on the basis of username.Remember to autowire object of UserRepository in CustomUserDetailsService so that we can use this object to query the database.

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

   




