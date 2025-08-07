# what is stream?

# Different ways of creating a stream
    From list:
        List<String> list = List.of("a","b","c");
        Stream<String> stram = list.stream();
    From arrays:
        String[] stringList = {"a","b","c"};
        Stream<String> stream1 = Arrays.stream(stringList);
        Stream<String> stream2 = Stream.of(stringList);
    From range:
        IntStream stream = IntStream.range(1,5);//1 tot 4
        IntStream stream = IntStream.rangeClosed(1,5) //1 to 5
    From generate:
        Stream<Integer> stream = Strean.generate(Math::random).limit(5);
    From iterate:
        Stream<Integer> stream = Stream.iterate(0,n -> n+2).limit(10);
    From BufferedReader.lines()
        BufferedReader reader = new BufferedReader(new FileReader("myfile.txt"));
        Stream<String> lines = reader.lines();

# Intermediate opereations
    Intermediate operations in Java Streams are the chainable steps that transform a stream and return another stream, allowing you to build powerful data-processing pipelines.
    They are lazy, meaning they don’t do anything until a terminal operation (like collect() or forEach()) is invoked. Think of them like assembling a machine—it only runs when you flip the final switch.
    Common Intermediate Operations
    1. filter(Predicate<T>)
        Filters elements that match a condition.
        list.stream()
            .filter(n -> n > 10)
    2. map(Function<T, R>)
        Transforms each element.
        list.stream()
            .map(String::toUpperCase)
    3. flatMap(Function<T, Stream<R>>)
        Flattens nested structures.
        List<List<String>> data = ...;
        data.stream()
            .flatMap(List::stream)
    4. sorted() or sorted(Comparator)
        Sorts elements.
        list.stream()
            .sorted()  // natural order
        list.stream()
            .sorted(Comparator.reverseOrder())
    5. distinct()
        Removes duplicates.
        list.stream()
            .distinct()
    6. limit(n) and skip(n)
        Controls the number of elements.
        list.stream()
            .limit(5)
            .skip(2)
    7. peek(Consumer<T>)
        For debugging or logging—not a true transformation.
        list.stream()
            .peek(System.out::println)

# Terminal operations
    Terminal operations in Java Streams are the final step in a stream pipeline. They trigger the processing of all previously defined intermediate operations and produce either:
    A result (like a list, number, or optional)
    A side-effect (like printing or modifying external state)
    Once a terminal operation is executed, the stream is consumed and cannot be reused. 🛑
    1. forEach() – perform side-effects
        list.stream()
            .forEach(System.out::println);
    2. collect() – collect into a collection
        List<String> upper = list.stream()
            .map(String::toUpperCase)
            .collect(Collectors.toList());
    3. reduce() – aggregate to a single result
        int sum = List.of(1, 2, 3, 4).stream()
            .reduce(0, Integer::sum);
    4. count() – get total elements
        long total = list.stream().count();
    5. min() / max() – find extreme values
        Optional<String> shortest = list.stream()
            .min(Comparator.comparingInt(String::length));
    6. anyMatch(), allMatch(), noneMatch()
        boolean hasLongWord = list.stream()
            .anyMatch(s -> s.length() > 10);
    7. findFirst() / findAny()
        Optional<String> first = list.stream()
            .findFirst();


# how does an object of type List calls stream() method
    Java's List (and other collection types like Set, Queue, etc.) can call .stream() because of the Collection interface.
    stream() is defined in the java.util.Collection interface:
        default Stream<E> stream();
    Since List extends Collection, all its implementations (like ArrayList, LinkedList, etc.) inherit this method directly.
        public interface List<E> extends Collection<E> { ... }
    So if you do this:
    List<String> names = new ArrayList<>();
    names.stream(); // 💥 Totally valid!
    You’re calling the default method stream() from the Collection interface.

    Under the Hood: How .stream() Works
        Internally, the stream() method uses Spliterator and StreamSupport:
            default Stream<E> stream() {
                return StreamSupport.stream(spliterator(), false);
            }
        What this means:
            spliterator() gives a Spliterator, which knows how to traverse the collection.
            StreamSupport.stream(...) creates a sequential Stream using that spliterator.

# why intermediate operations are lazy
    1. Performance Optimization
        Laziness allows the stream to process only what is necessary. For example:

        Stream.of(1, 2, 3, 4, 5)
            .filter(n -> n > 2)
            .limit(1)
            .forEach(System.out::println);  // Only 1 element is filtered and printed
        Without laziness, all elements would be filtered even though we only need the first match.

    2. Chained Execution Planning
        Intermediate operations don’t trigger any data processing. They just define steps in a pipeline:
        .filter()
        .map()
        .sorted()
        Only when a terminal operation like .collect() or .forEach() is called, the stream engine evaluates the entire chain in a single pass (fused).

    3. Avoid Unnecessary Work
        Since intermediate ops are lazy, they may never run if the terminal operation doesn't require them:

        Stream<Integer> stream = Stream.of(1, 2, 3)
            .filter(n -> {
                System.out.println("Filter: " + n);
                return n > 1;
            });

        // Nothing printed here
        stream.count();  // Now "Filter: 1", "Filter: 2", etc. will print

    4. Supports Infinite Streams
        Laziness makes it possible to work with infinite streams:

        Stream.iterate(0, n -> n + 1)
            .limit(5)
            .forEach(System.out::println);
        Without laziness, infinite streams would lead to infinite computation!

# Create a custom class like MyBox<T> that acts like a List<T> and can return a Stream.

# Sequence of stream operations

# stream vs parallelStream
