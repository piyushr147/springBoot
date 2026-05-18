package streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams2 {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("piyush","keshav","sachin","Gauransh","kanika","chetan","aryan","Kanika");

        List<List<Integer>> listOfList = List.of(
                List.of(1,2,3),
                List.of(4,5,6),
                List.of(7,8,9)
        );

        //collect all values in list of list into a single list.
        List<Integer> list = listOfList.stream().flatMap(i -> i.stream()).sorted((Integer i1,Integer i2) -> i2-i1).collect(Collectors.toList());

        //get the sum of all even integers in a list of list.
        int sum = listOfList.stream().flatMap(i -> i.stream().filter(num -> num%2 == 0)).reduce(0,Integer::sum);

        List<String> list2 = Arrays.asList("java is a good language","piyush is a good guy");
        //collect all the unique words in string of list separated by a space
        Set<String> set = list2.stream().flatMap(s -> Arrays.stream(s.split(" "))).collect(Collectors.toSet());

        List<Optional<String>> list3 = Collections.singletonList(stream.map((String s) -> {
            return s.toUpperCase();
        }).limit(1).findAny());

        //list.sort(Comparator.reverseOrder());

        List<Person> personList = new ArrayList<>(
                List.of(
                        new Person(10,10,111),
                        new Person(2,15,136),
                        new Person(19,9,90),
                        new Person(11,15,178),
                        new Person(1,10,100)
                )
        );

        //sort a custom class on the basis of age and height
        Optional<Person> OldestWithLowestHeight = personList.stream().max(Comparator.comparing(Person::getAge).thenComparing(Comparator.comparing(Person::getHeight).reversed()));
        System.out.println(OldestWithLowestHeight.toString());

        //sort on natural order of Id
        Optional<Person> person = personList.stream().max(Person::compareTo);
        System.out.println(person.toString());

        //find the frequency of each character in a string
        String str = "Hi check the frequency of each character queen";
        Map<Character, Long> map = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        List<String> words = List.of(
                "Java", "Python", "Java", "C", "Python", "JavaScript", "C", "Java"
        );
        //find the frequency of each word in a list of string
        Map<String, Long> map1 = words.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
        map1.forEach((k,v)-> System.out.println(k + " " + v));

        //group words by length
        Map<Integer, Set<String>> map2 = words.stream()
                .collect(Collectors.groupingBy(
                        String::length,
                        Collectors.toSet()
                ));

        map2.forEach((k,v)-> System.out.println(k + " " + v));

        //find freq of length of each word.
        Map<String, Long> map3 = words.stream()
                .collect(Collectors.groupingBy(
                        word -> String.valueOf(word.length()),
                        Collectors.counting()
                ));
        map3.forEach((k,v)-> System.out.println(k + " " + v));
    }

}