import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        ArrayList<String> list = new ArrayList();
        list.add("sdh");

        Object o = list.get(0);
        String s = (String) o;

        Box<Integer> box = new Box(1);
        box.setValue(1);
        Pair<String,Integer> pair = new Pair<>("sdh", 1);
        pair.setKey("kkk");

        NumberContainer<Float> numberContainer = new NumberContainer<>();

        List<? extends Number> numbers = Arrays.asList(1,2,3);
        //numbers.add(1);

        List<? super Integer> numbersList = getNumberList();

        ArrayList<? super Integer> numbers2 = new ArrayList<>();
        numbers2.add(1);  // ✅ Allowed — Integer or subclass
        //numbers2.add(100.43434343);

        ArrayList<? super Integer> numbers3 = new ArrayList<>();
        numbers2.add(2);  // ✅ Allowed — Integer or subclass
        //numbers2.add(100.43434343);

        //ArrayList<? super Integer> combiner = combineArray(numbers2,numbers3);

        Integer sum = sum(List.of(1,2.24,3));
    }

    public static List<? super Number> getNumberList(){
        return List.of(1,2.33,3);
    }
    public static <T> void printArray(ArrayList<T> list){
        for(T t: list){
            System.out.println(t);
        }
    }

    public static <T> ArrayList<T> combineArray(ArrayList<T> source,ArrayList<T> destination){
        for(T t: source){
            destination.add(t);
            System.out.println(t);
        }
        return null;
    }

    public static int sum(List<? extends Number> list){
        int sum = 0;
        for(Number t: list){
            sum += t.intValue();
        }
        return sum;
    }
}
