package innerClass;

public class InnerClass {

    int objectLevel = 10;
    static int staticLevel = 20;

    public class NonStaticClass{
        void print(){
            System.out.println("from NonStaticClass" + objectLevel);
            System.out.println("from NonStaticClass" + staticLevel);
        }
    }

    public static class StaticClass{
        public void print(){
            System.out.println("from StaticClass" + staticLevel);
        }
    }
}
