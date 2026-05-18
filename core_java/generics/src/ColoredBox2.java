public class ColoredBox2 extends Box<Integer>{
    private String color;

    public ColoredBox2(Integer value,String color){
        super(value);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public Integer getValue(){
        return super.getValue();
    }
}
