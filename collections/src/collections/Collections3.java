package collections;

import java.util.ArrayDeque;

public class Collections3 {

    public static void main(String[] args) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.addLast(1);
        arrayDeque.addLast(2);
        arrayDeque.addFirst(3);
        System.out.println(arrayDeque);
        arrayDeque.poll();
        System.out.println(arrayDeque);
    }
}
