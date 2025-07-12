package collections;

import java.util.LinkedList;
import java.util.ListIterator;

public class Collections7 {

    public static void main(String[] args) {

        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        ListIterator<Integer> iterator = list.listIterator(list.size());
        while(iterator.hasPrevious()){
            System.out.println(iterator.previous());
        }

    }
}
