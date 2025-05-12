

enum Operation{
    ADD, SUBTRACT, MULTIPLY, DIVIDE;

    public <T extends Number> double apply(T a,T b){
        switch (this){
            case ADD:
                return a.doubleValue() + b.doubleValue();
            case SUBTRACT:
                return a.doubleValue() - b.doubleValue();
            case MULTIPLY:
                return a.doubleValue() * b.doubleValue();
            case DIVIDE:
                return a.doubleValue() / b.doubleValue();
            default:
                return 0.0;
        }
    }
}
public class Test2 {
    public static void main(String[] args){
        Double a = Operation.ADD.apply(1.0,2);
        System.out.println(a);
    }
}
