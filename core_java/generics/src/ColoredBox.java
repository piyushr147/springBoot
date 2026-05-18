public class ColoredBox<T> extends Box<T> {
    private String color;

    public ColoredBox(T value,String color) {
        super(value);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public T getValue(){
        return super.getValue();
    }
}