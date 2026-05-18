package hashMap;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapExample {
    //implement tree map main functions like getting all key >= key or <=key, range based queries on key and iterating the map.
    public static void main(String[] args) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>((i1,i2) -> i2-i1);
        treeMap.put(100,1);
        treeMap.put(80,2);
        treeMap.put(190,3);
        treeMap.put(1,5);
        treeMap.put(90,6);
        treeMap.put(53,7);
        treeMap.put(11,8);

        for(Map.Entry<Integer,Integer> entrySet: treeMap.entrySet()){
            System.out.println(entrySet.getKey());
        }
    }
}
