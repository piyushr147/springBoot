package nonStaticMain;

//This approach works but from java 25, if static main is not found when class is loaded the jvm create an object with default constructor of the class.
//This constructor is mandatory to be declared and using the object created it looks for function main().
public class NonStaticMain {

    //Starting from Java 21, the Java launcher does this:
    //If a class has no static main method, Java will try to:
    //new YourClass();
    //yourClassInstance.main();
    //so our non-static main program needs to declare a 0-argument constructor as a fallback
    public NonStaticMain() {
    }

    NonStaticMain(int value) {
        System.out.println("This is a program which can run without running static main method by making it non-static");
        System.out.println("Non-static method executed");
        this.value = value;
    }

    //Static block Runs because class loading triggers static blocks.
//    static {
//        System.out.println("Static method started");
//        NonStaticMain obj = new NonStaticMain(10);
//        obj.main();
//        //Kills JVM before it looks for a main class.
//        System.exit(0);
//    }

    private int value;

    public void showValue(){
        System.out.println("value: " + value);
    }

    public void main(){
        NonStaticMain obj = new NonStaticMain(10);
        obj.showValue();
    }
}
