package hashMap;

import com.sun.jdi.Value;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class HashMapExample {
    public static class Person{
        String name;
        int age;
        public Person(String name,int age){}
    }

    public static void main(String[] args){
        SimpleHashMap<Integer,Integer> simpleHashMap = new SimpleHashMap();
        simpleHashMap.put(1,2);
        simpleHashMap.put(2,3);
        simpleHashMap.put(3,4);
        simpleHashMap.put(4,5);
        simpleHashMap.put(5,6);
        simpleHashMap.put(6,7);

        System.out.println(simpleHashMap.get(1));
        System.out.println(simpleHashMap.get(2));

        //linkedhashmap
        LinkedHashMap<Integer,String> linkedMap = new LinkedHashMap<>();
        linkedMap.put(1,"one");
        linkedMap.put(2,"two");
        linkedMap.put(3,"three");
        linkedMap.put(4,"four");

        linkedMap.forEach((k,v) -> {
            System.out.println(k + " " + v);
        });
        for(Map.Entry<Integer, String> entry: linkedMap.entrySet()){
            entry.getValue().hashCode();
            entry.getKey().hashCode();
        }
        for(Integer e: linkedMap.keySet()){
            System.out.println(linkedMap.get(e));
        }

        //custom map ordering using treemap
        Map<Person,Integer> map = new TreeMap<>((Person p1, Person p2) -> p1.age - p2.age);
        map.put(new Person("one",10),1);
        map.put(new Person("two",20),1);
        map.put(new Person("three",30),1);


    }
}
