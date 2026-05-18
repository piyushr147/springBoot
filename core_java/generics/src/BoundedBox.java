public class BoundedBox<T extends Number>{
    private T value;

    public BoundedBox(T value){
        this.value = value;
    }

    public T getValue(){
        return value;
    }

    public void setValue(T value){
        this.value = value;
    }
}
