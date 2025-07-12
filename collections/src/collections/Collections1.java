package collections;

import java.util.*;

public class Collections1 {


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.addAll(Arrays.asList(3, 4, 5, 6));
        Iterator<Integer> iterator = list.iterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        list.forEach(val -> System.out.println(val));

        Collections.reverse(list);
        list.forEach(val -> System.out.println(val));

        System.out.println(Collections.max(list));
        System.out.println(Collections.min(list));
    }
}
