public class Box2 {

    public <T> Box2(T value, String v){
        System.out.println(value);
    }

    public static void main(String[] args) {
        Box2 box = new Box2(1,"hh");
        Box2 box2 = new Box2("string","ddkd");
    }
}
