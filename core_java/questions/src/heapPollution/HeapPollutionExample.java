package heapPollution;

import java.util.ArrayList;
import java.util.List;

public class HeapPollutionExample {

    static void addUnsafe(List list) { // raw type warning
        list.add(42); // Adds an Integer into a "List<String>"
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        addUnsafe(strings);

        // This will cause a runtime exception
        for (String s : strings) {
            System.out.println(s.toUpperCase());
        }
    }
}