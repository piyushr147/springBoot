package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams1 {

    public static void main(String[] args){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Predicate<Integer> isGreaterThan1 = i -> i>1;
        Predicate<Integer> isEven = i -> i%2==0;
        Predicate<Integer> complex = isGreaterThan1.and(isEven);
        int sum = (int) list.stream().filter(complex).count();
        System.out.println(sum);

        Stream<String> integerStream = Stream.of("piyush","keshav","sachin","gauransh","kanika","chetan","aryan");
        Stream<String> nextIntegerStream = integerStream.map((String s) -> s.toUpperCase());
        nextIntegerStream.forEach(System.out::println);
        //long count = nextIntegerStream.count();
        //System.out.println(count);

        List<List<String>> listOfString = Arrays.asList(
                Arrays.asList("a","b","c"),
                Arrays.asList("d","e","f")
        );;
        Stream<String> flatString = listOfString.stream().flatMap((List<String> s) -> s.stream().map((String k) -> k.toUpperCase()));
        flatString.forEach(System.out::println);

        Stream<Integer> streamFromIterate = Stream.iterate(1, (Integer i) -> i+1).limit(10);
        Stream<Integer> nextStreamFromIterate = streamFromIterate.filter(i -> i%2==1).peek(i -> System.out.println(i)).map(i -> i+1);
        Stream<Integer> limitStream = nextStreamFromIterate.limit(5);
        //limitStream.count();
        //limitStream.forEach(System.out::println);
        Optional<Integer> sum2 = limitStream.reduce((a, b)->a+b);
        System.out.println(sum2.get());

        List<Integer> list1 = new ArrayList<>(Arrays.asList(11,2,8,41,77,7,1,99));
        Stream<Integer> stream = list1
                .stream()
                .filter(i -> i%11!=0)
                .peek(i -> System.out.println("after mod11 filter: "+i))
                .map(i -> i+100)
                .peek(i -> System.out.println("after map: "+i))
                .sorted((a,b) -> b-a)
                .peek(i -> System.out.println("after sorting: "+i));
        stream.forEach(System.out::println);
    }
}
