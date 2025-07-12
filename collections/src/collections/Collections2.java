package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Collections2 {

    public static void main(String[] args) {
        Queue<Integer> pq = new PriorityQueue<>(); //natural order is of min heap
        pq.offer(1);
        pq.remove();
        //pq.remove(); throws an exception with remove.
        pq.poll(); //return false when queue empty.

        //Integer i = pq.element(); throws an exception with remove.
        Integer peek = pq.peek(); //return null if queue is empty
        System.out.println(peek);

        Queue<Integer> pq2 = new PriorityQueue<>((Integer a,Integer b) -> b-a); //max heap using comparator
        pq2.add(11);
        pq2.add(9);
        pq2.add(67);
        pq2.add(15);
        pq2.add(1);

        System.out.println(pq2.peek());
        pq2.poll();
        System.out.println(pq2.peek());

        Integer[] a = {1,2,10,3,1};
        Arrays.sort(a,(a1,a2)->a2-a1);
        for (Integer i : a){
            System.out.println(i);
        }
    }
}
