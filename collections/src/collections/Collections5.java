package collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Collections5 {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>(8,1);
        Map<String, Integer> map2 = new HashMap<>();

        map.put(1,10);
        map.put(6,62);
        map.put(4,42);
        map.put(2,24);
        map.put(5,56);
        map.put(3,32);

        map.forEach((k,v) -> System.out.print(k+" "));

        map.replace(3,39);
        map.compute(1,(k,v)-> v+10);
        map.replaceAll((k,v)->v+10);

        System.out.println();
        for(Map.Entry<Integer,Integer> mapEntry : map.entrySet()){
            System.out.print(mapEntry.getKey()+" ");
            if(mapEntry.getKey()==6){
                map.put(11,22);
            }
        }

        Iterator<Map.Entry<Integer,Integer>> mapIterator = map.entrySet().iterator();
        System.out.println();
        while(mapIterator.hasNext()){
            Map.Entry<Integer,Integer> next = mapIterator.next();
            System.out.print(next.getKey()+" ");
            if(next.getKey() == 6){
                map.put(11,22);
            }
        }

        map2.put("hey",72);
        map2.put("A",102);
        map2.put("piyush",92);
        map2.put("rawat",82);

        System.out.println();
        for(Map.Entry<String,Integer> mapEntry : map2.entrySet()){
            System.out.print(mapEntry.getKey() + " ");
        }

        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(1,10);
        linkedHashMap.put(9,10);
        linkedHashMap.put(3,10);

        System.out.println();
        linkedHashMap.forEach((k,v) -> System.out.print(k + " "));

        LinkedHashMap<Integer, Integer> linkedHashMap2 = new LinkedHashMap<>(16,0.75f,true);
        linkedHashMap2.put(11,30);
        linkedHashMap2.put(29,50);
        linkedHashMap2.put(3,11);

        System.out.println();
        linkedHashMap2.forEach((k,v) -> System.out.print(k + " "));

        linkedHashMap2.get(11);
        System.out.println();
        linkedHashMap2.forEach((k,v) -> System.out.print(k + " "));

    }
}
