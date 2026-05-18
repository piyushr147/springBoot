import java.util.ArrayList;
import java.util.List;

public class Wildcard {

    public static void main(String[] args) {
        List<Number> list = new ArrayList<>(List.of(1.2,2,3,4,5));
        lowerBound(list);
        upperBound(list);

    }
    public static void upperBound(List<? extends Number> list){
        list.stream().forEach(System.out::println);
    }

    public static void lowerBound(List<? super Number> list){
        list.add(100);
        list.add(1.222);
        //Integer n = list.get(1);
    }
}
