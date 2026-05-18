public class Box<T> {

    private T value;
    private Number value2;

    public Box(T value){
        this.value = value;
    }

    public <T extends Number> Box(T value){
        this.value2 = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
