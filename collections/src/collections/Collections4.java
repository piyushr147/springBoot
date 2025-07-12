package collections;

import java.util.*;

public class Collections4 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,4,4,4,7,7,9));
        list.addAll(new ArrayList<>(new ArrayList<>(Arrays.asList(19,1,2,1,1))));
        list.set(0,10);
        list.add(0,11);

        ListIterator<Integer> iterator = list.listIterator(7);
        while (iterator.hasPrevious()) {
            System.out.print(iterator.previous());
        }

        System.out.println(list.lastIndexOf(4));
        System.out.println(list);

        List<Integer> subList = list.subList(0,4);
        subList.set(0,99); //changes in sublist have the same referrence to changes on original list

        System.out.println(subList);
        System.out.println(list);

        list.replaceAll(x-> x*x);
        System.out.println(list);
    }
}
