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

        int value;
        public StaticClass(int value){
            staticLevel = value;
            this.value = value+100;
        }
        public void print(){
            System.out.println("from StaticClass level: " + staticLevel + " value: " + value);
        }
    }
}
