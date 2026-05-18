# what is class Class
    To reflect a class we need an object of class Class.
    what is this Class?
        Instance of the class Class represents the classes during runtime.
        Jvm create one class object for each and every class which is loaded during runtime:
            Suppose you have Bird, Car, Human etc 10 classes, a class object is created for each of the during runtime by JVM.
            This class object has Metadata information of your class, you can use this Class object to inspect class details (methods, fields, constructors, etc.) at runtime.
    There are three common ways to get a Class object:
        Using .class
            Class<String> clazz = String.class;

        Using Object.getClass()
            String s = "hello";
            Class<?> clazz = s.getClass();

        Using Class.forName("fully.qualified.ClassName")
            Class<?> clazz = Class.forName("java.lang.String");

# Reflection breaks sigleton
    class Singleton {
        private static final Singleton instance = new Singleton();

        private Singleton() {
            System.out.println("Singleton constructor");
        }

        public static Singleton getInstance() {
            return instance;
        }
    }

    public class Main {
        public static void main(String[] args) throws Exception {
            Singleton s1 = Singleton.getInstance();

            Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            Singleton s2 = constructor.newInstance();  // 🚨 New instance via reflection

            System.out.println(s1 == s2);  // false
        }
    }

    Output:
    Singleton constructor
    Singleton constructor
    false
    So s1 and s2 are two different instances — singleton broken.

    How to Prevent It
        You can defend against reflection by throwing an exception if an instance already exists:
        class Singleton {
            private static final Singleton instance = new Singleton();
            private static boolean instanceCreated = false;

            private Singleton() {
                if (instanceCreated) {
                    throw new RuntimeException("Use getInstance()");
                }
                instanceCreated = true;
            }

            public static Singleton getInstance() {
                return instance;
            }
        }
        This way, even if reflection is used, the constructor will throw an exception on the second call.