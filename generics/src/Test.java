interface Printable {
    void print();
}

class MyNumber extends Number implements Printable {

    Integer value;

    public MyNumber(Integer value) {
        this.value = value;
    }

    @Override
    public void print() {
        System.out.println(intValue());
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public long longValue() {
        return value;
    }

    @Override
    public float floatValue() {
        return value;
    }

    @Override
    public double doubleValue() {
        return value;
    }
}

class Boxx<T extends Number & Printable>{

}

public class Test {

    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber(10);
        Boxx<MyNumber> boxx = new Boxx<MyNumber>();
        int[] array = {1,2,3,4,5,6,7,8,9};
        String[] strings = {"hello","world"};
        printArray(strings);
        display("Ssjdj");
        display(99292);
    }

    public static <T> void printArray(T[] array){
        for(T element: array){
            System.out.println(element);
        }
    }

    public static <T> void display(T value){
        System.out.println("called generic display "+value);
    }

    public static void display(Integer value){
        System.out.println("called integer display "+value);
    }
}
