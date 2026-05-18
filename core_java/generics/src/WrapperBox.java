public class WrapperBox<T extends Number & Container<T>> extends Box<T> {

    private T value;

    public WrapperBox(T value){
        super(value);
        this.value = value;
    }

    public T getValue(){
        return value;
    }

    public void setValue(T value){
        this.value = value;
    }
}
