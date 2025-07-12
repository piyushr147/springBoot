# Why Were Generics needed?
    -> Type Safety
        Before generics, collections could store any Object, and you had to cast manually.
            List list = new ArrayList();  // Pre-generics
            list.add("Hello");
            String s = (String) list.get(0);  // Must cast manually ✅
        If the wrong type was added, you'd get a runtime ClassCastException.

    -> Generics remove the need for explicit casting, reducing boilerplate and errors.

    -> You can write generic algorithms or data structures that work for any type.
        public class Pair<K, V> {
            private K key;
            private V value;
            // constructors, getters, setters
        }

        Pair<String, Integer> user = new Pair<>("Alice", 101);

    -> Errors are caught during compilation, not at runtime.

    -> Generics avoids the duplication of code 
        Suppose we have a box class that stores a single value(integer, float, boolean, string) now for creating a box object that conatins all these types but only one at a time i need to create 4 box classes.
        To avoid this generic helps us.
            public class Box<T> {

                private T value;

                public T getValue() {
                    return value;
                }

                public void setValue(T value) {
                    this.value = value;
                }
            }
# Extension in generics
    If you are extending a generic class with specifying the diamond type then you need to define the specifi type of the class
    For example:
        public class Print<T>{}
        Now if ColorPrint needs to extend this but not making it generic, it has to define the class Type
            public ColorPrint extends Print<String>{}

# Raw type in generics
    In Java, a raw type refers to using a generic class or interface without specifying a type parameter.
    Example:
        List list = new ArrayList(); // raw type
        list.add("Hello");
        list.add(123); // no type safety!
    Compare that to a parameterized (generic) type:
        List<String> list = new ArrayList<>();
        list.add("Hello");
        // list.add(123); // compile-time error

# Bounded type parameters in generics
    we can also set our generic type to be bounded to a particaular type, suppose we want our class to be genric for numbers(int,float,long,double) but not for strings, we can simply make our class to be generic to Number class.
    code:
        public class NumberContainer<T extends Number> implements Container<T> {
    
            private T value;
            
            @Override
            public T getValue() {
                return value;
            }

            @Override
            public void setValue(T value) {
                this.value = value;
            }
        }
    Here NumberContainer implements an interface for container, now if we try to create a NumberContainer<String> this will throw a compile time exception.
    Same boundation can be done on class level
        interface Printable{}
        class MyNumber extends Number implements Printable{}
        class Boxx<T extends Number & Printable>{}

        Boxx can have only those class object as T, which extends Number and implements Printable, which is MyNumber in this case.
            MyNumber myNumber = new MyNumber(10);
            Boxx<MyNumber> boxx = new Boxx<MyNumber>();
    
# Multi bound in generics
    Suppose i have a class that extends another class and implements multiple interfaces like
        Public class Human extends Species implements Walk, Swim{}
    To have this condiditon in generics we should use something likt this
        Public class Print<T extends Species & Walk & Swim>
    The first class should be the exteneded class and rest could be the interfaces.
        Print<Human> human = new Print<>();

# Generic constructors
    Here the class is not generic but the constructor is, we can pass value of any type, the syntax is to use the type <T> syntax before the constructor name to tell that the type of parameter will be generic, and then use it inside parameters, 
        public class Box2 {
        
            public <T> Box2(T value){
                System.out.println(value);
            }
            
            public static void main(String[] args) {
                Box2 box = new Box2(1);
                Box2 box2 = new Box2("string");
            } 
        }

# Generic methods 
    Methods can also be made generic like this:
        use type <T> before the return type of the method.
        public class Test {

            public static void main(String[] args) {
                int[] array = {1,2,3,4,5,6,7,8,9};
                String[] strings = {"hello","world"};
                printArray(strings);
            }

            public static <T> void printArray(T[] array){
                for(T element: array){
                    System.out.println(element);
                }
            }
        }

# Wild cards in generics
    In generics, a wildcard is represented by the question mark ?. It is used to specify unknown types when defining or using generic classes, interfaces, or methods.
    Suppose we write a generic method to print array, with return type as void
        public <T> void printArray(ArrayList<T> list){
            for(T t: list){
                System.out.println(t);
            }
        }
    So here we need not to use type T, we can use ? wild card as a method parameter, this will work the same.
        public void printArray(ArrayList<?> list){
            for(Object t: list){
                System.out.println(t);
            }
        }
    But in case we need to return something then we have to use genric type only, returning Object class type will again cause run time error.

    Types of Wildcards
        Unbounded Wildcard: <?>
            Accepts any type.
            Typically used when the logic doesn't depend on the type parameter.
                public void printList(List<?> list) {
                    for (Object obj : list) {
                        System.out.println(obj);
                    }
                }

        Upper Bounded Wildcard: <? extends T>
            Accepts T or any subclass of T.
            You can safely return values only when you're using upper-bounded wildcards (<? extends T>), because the compiler knows everything is at least of type T, Number class in this case.
                public double sum(List<? extends Number> numbers) {
                    double total = 0;
                    for (Number n : numbers) {
                        total += n.doubleValue();
                    }
                    return total;
                }
        
        Lower Bounded Wildcard: <? super T>
            Accepts T or any superclass of T.
            Used when you want to write to a generic structure.
                public void addIntegers(List<? super Integer> list) {
                    list.add(1);
                    list.add(2);
                }
    Note: 
        You cannot use ? as a return type, that’s not allowed by the compiler because ? is a wildcard, not a real type that you can work with. It’s only allowed in type arguments, not as a standalone return type.
        What you can do is use a bounded wildcard inside a generic wrapper
            public List<? extends Number> getNumbers() {
                return List.of(1, 2, 3);
            }

    Why this gives a compile time error, when we saw that we can use a wild card in generic wrapper.
        List<? extends Number> numbers = Arrays.asList(1,2,3);
        Number n = numbers.get(0); // ✅ You can read safely
        numbers.add(1);            // ❌ You can't add — type is unknown
    Because you're using ? extends Number, which is an upper-bounded wildcard. That tells the compiler:
    "This list holds some subtype of Number — could be Integer, Double, Long, etc. I don’t know which."
    And since the compiler doesn’t know the exact type, it can’t allow you to add anything to the list — even if it seems safe like 1 (an Integer).

    What’s the fix?
    If you want to add values to a generic list, you need a lower-bounded wildcard:
        List<? super Integer> numbers = new ArrayList<>();
        numbers.add(1);  // ✅ Allowed — Integer or subclass
        numbers.add("sde");

    Why does "sde" fail?
        You declared the list as List<? super Integer>, which means:
        "This list can hold Integer or any superclass of Integer (like Number, Object)."
        BUT...
        The compiler still needs to ensure type safety, and it only allows you to add things that are guaranteed to be at least an Integer, not even float.
        1 is an Integer ✅
        1.0 is an Float ❌
        "sde" is a String ❌ — not allowed because it could violate the list’s type guarantee

# super vs extends in generics
    Understanding super vs extends in Java generics is key to using collections safely and effectively. Here's a clear breakdown:
        ? extends T — Upper Bounded Wildcard, this is “Producer” — You can read from it.
            Declaration:
                List<? extends Number> list;
                Means:
                "This list holds some unknown subtype of Number — could be Integer, Double, etc."

                ✅ You can:
                    Read items safely as Number
                    Example:
                        Number num = list.get(0); // Safe

                ❌ You cannot:
                    Add any type of object (not even Number)
                    Example:
                        list.add(123); // Compile error
                    Why? Because the actual type might be List<Double> — adding an Integer would break type safety.

        ? super T — Lower Bounded Wildcard, this is a “Consumer” — You can write to it.
            Declaration:
                List<? super Integer> list;
                Means:
                "This list holds some unknown supertype of Integer — could be Integer, Number, or Object."

                ✅ You can:
                    Add Integer or its subtypes
                        Example:
                            list.add(123); // Safe
                    
                ❌ You cannot:
                    Safely read elements as an Integer
                        Example:
                            Integer i = list.get(0); // Compile error
                            Object o = list.get(0);  // OK
                    Why? Because the actual type might be Object, and Java won’t let you assume it's an Integer.

# Type eraser
    Type erasure in Java is the process by which generic type information is removed at compile time, making generics a compile-time only feature. At runtime, the JVM has no knowledge of generic type parameters.

    🔍 Why Does Type Erasure Exist?
    Java generics were added in Java 5, but to maintain backward compatibility with older Java code, the compiler erases generic types before compiling the bytecode. This means generic code can run on JVMs that predate generics.

    ✅ What It Looks Like
        List<String> list = new ArrayList<>();
        list.add("Hello");
        String s = list.get(0);

    At runtime, this is equivalent to:
        List list = new ArrayList();  // raw type
        list.add("Hello");
        String s = (String) list.get(0);  // type cast added by compiler

    🧹 How Type Erasure Works:
        Generic types are replaced with their bounds (or Object if no bounds).
        Type casts are inserted to preserve type safety.
        Bridge methods are generated when needed (for inheritance with generics).
    Examples
        1. Erased type
            public class Box<T> {
                public T value;
            }
        Becomes:
            public class Box {
                public Object value;
            }

        2. Bounded type
            public class Box<T extends Number> {
                T value;
            }
        Becomes:
            public class Box {
                Number value;
            }

# what is instanceof and why can't we use it in generics
    instanceof is a Java operator used to check whether an object is an instance of a specific class or implements an interface, at runtime.

    ✅ Basic Usage
        if (obj instanceof String) {
            System.out.println("It's a String!");
        }
    This returns true if obj is a String, otherwise false.

    🧱 Why instanceof Doesn’t Work with Generics
        Because of type erasure, the generic type information is removed at runtime. So something like this is not allowed:
        if (obj instanceof List<String>) {  // ❌ Compile-time error
            ...
        }
    This fails because at runtime, List<String> becomes just List. There's no way for the JVM to distinguish between List<String> and List<Integer>.

    Workaround: Use Class Tokens
        public <T> boolean isInstanceOf(Object obj, Class<T> clazz) {
            return clazz.isInstance(obj);
        }
    You can call:   
        isInstanceOf("hello", String.class);  // true
    This allows generic-like type checking while keeping it runtime-safe.

# why can't i create a generic array
    You can’t directly create a generic array in Java because of type erasure — the compiler removes the generic type information at runtime, so the JVM doesn't actually know what type the array should hold. This leads to type safety issues.

     Why It's Disallowed
        Let's say you try this:
            T[] arr = new T[10]; // ❌ Compile-time error
        At runtime, due to type erasure, the JVM doesn’t know what T is — it just sees Object[]. But generic arrays are meant to enforce type safety, and this would break that contract.
