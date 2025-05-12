package linkedList;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Code {

    public static void main(String ags[]){
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        LinkedList<Integer> linkedList1 = new LinkedList<>(Arrays.asList(1,2,3));
        LinkedList<Integer> linkedList2 = new LinkedList<>(List.of(1,2,3,4,3));

    }
}
