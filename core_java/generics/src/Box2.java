import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Box2 {

    public <T> Box2(T value, String v){
        System.out.println(value);
    }

    public <T,K> K printvalues(T[] value,K id){
        for (T v : value){
            System.out.println(v);
        }
        System.out.println(id);
        return id;
    }

    public static List<? extends Number> getNumbers() {
        return List.of(1, 2.122, 3, 4.1F);
    }

    public static void main(String[] args) {
        Box2 box = new Box2(1,"hh");
        Box2 box2 = new Box2("string","ddkd");
        Integer id = box.printvalues(new String[]{"hh","string","ddkd"}, 12);
        List<? extends Number> list = getNumbers();
        System.out.println(list);
        System.out.println(id);

        List<? super Integer> lista = new ArrayList<>();
        lista.add(1);
        Number a = (Integer) lista.getFirst();
    }
}
