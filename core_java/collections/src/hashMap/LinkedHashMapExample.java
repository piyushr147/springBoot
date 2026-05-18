package hashMap;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapExample {
    public static void main(String[] args) {
        LinkedHashMap<String,Integer> map = new LinkedHashMap();
        map.put("Banana",1);
        map.put("Apple",2);
        map.put("Pear",3);

        for(Map.Entry<String,Integer> entry: map.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}
