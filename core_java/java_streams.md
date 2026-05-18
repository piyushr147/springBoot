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
        Stream<Integer> stream = Stream.generate(Math::random).limit(5);
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
    6. anyMatch(), allMatch(), noneMatch() (returns a boolean value verifying your condition)
        boolean hasLongWord = list.stream()
            .anyMatch(s -> s.length() > 10);
    7. findFirst() / findAny()
        Optional<String> first = list.stream()
            .findFirst();


# How does an object of type List calls stream() method
    Java's List (and other collection types like Set, Queue, etc.) can call .stream() because of the Collection interface.
    stream() is defined in the java.util.Collection interface:
        default Stream<E> stream();
    Since List extends Collection, all its implementations (like ArrayList, LinkedList, etc.) inherit this method directly.
        public interface List<E> extends Collection<E> { ... }
    So if you do this:
    List<String> names = new ArrayList<>();
    names.stream(); // Totally valid!
    You’re calling the default method stream() from the Collection interface.

    Under the Hood: How .stream() Works
        Internally, the stream() method uses Spliterator and StreamSupport:
            default Stream<E> stream() {
                return StreamSupport.stream(spliterator(), false);
            }
        What this means:
            spliterator() gives a Spliterator, which knows how to traverse the collection.
            StreamSupport.stream(...) creates a sequential Stream using that spliterator.

# Why intermediate operations are lazy
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
        stream.count();  // Now "Filter: 2", "Filter: 3", etc. will print

    4. Supports Infinite Streams
        Laziness makes it possible to work with infinite streams:

        Stream.iterate(0, n -> n + 1)
            .limit(5)
            .forEach(System.out::println);
        Without laziness, infinite streams would lead to infinite computation!

# Parallel streams
    What Is Parallel Stream?
        A parallel stream divides your data into multiple chunks, processes them concurrently across multiple CPU cores, and then merges the results.
    How It Works Internally
        Step-by-step:
            Data Splitting
            The stream source (e.g., a list) is split into multiple substreams.
            This is usually done using a Spliterator (split + iterator).
            Java collections have built-in spliterators optimized for parallelism.
        Parallel Execution
            Each substream is processed independently in a ForkJoinPool.
            By default, Java uses the common ForkJoinPool (shared thread pool).
            Each element runs in a separate task handled by a different thread.
        Combining Results
            After intermediate and terminal operations finish, results are merged.
            This merging happens using reduce-like algorithms (efficient tree reduction).
        Ordering Behavior
            list.parallelStream().forEach(System.out::println);     // unordered
            list.parallelStream().forEachOrdered(System.out::println); // ordered
            forEachOrdered reduces parallelism.

    Real world Use-cases
        Millions of log entries where we need to count error messages
            CPU-intensive string processing and one single stream(one CPU core) won't be enough.
            long errorCount = logs.parallelStream().filter(log -> log.contains("ERROR")).count();
        
        Bad Use Case: I/O Operations
            files.parallelStream()
                .forEach(file -> writeToDatabase(file)); // ❌
            I/O is blocking so threads sit idle
            Common pool gets exhausted
    
    Things to Watch Out For
        Order sensitivity
            Parallel streams may not preserve order (unless you use forEachOrdered()).
        Shared mutable state
            Never modify shared variables inside stream operations — that breaks thread safety.
        Small data sets
            Splitting/merging overhead can make parallel streams slower than sequential ones.
        locking operations (like I/O)
            Parallel streams are optimized for CPU-bound work, not for blocking I/O.

# You are provided with a stream of trade data for various companies, including the number of trades, prices, and additional information. How do you calculate the profit and loss for each company?
    public class Trade {
        private String company;
        private int quantity;
        private double price;
        private TradeType type;

        public Trade(String company, int quantity, double price, TradeType type) {
            this.company = company;
            this.quantity = quantity;
            this.price = price;
            this.type = type;
        }

        //getters and setters
    }

    public static Map<String, Double> calculatePnL(List<Trade> trades) {

        return trades.stream().collect(
            Collectors.groupingBy(
                Trade::getCompany(),
                Collectors.summingDouble(trade -> 
                    double profitLoss = trade.getPrice() * trade.getQuantity();
                    return trade;
                )
            )
        )
    }

# Map vs FlatMap
    map
        List<String> names = List.of("alice", "bob", "charlie");
        names.stream().map(String::toUpperCase).forEach(System.out::println);
    
    flatMap
        List<String> sentences = List.of(
                "Java is powerful",
                "Streams are useful"
        );
        sentences.stream().flatMap(s -> Arrays.stream(s.split(" "))).forEach(System.out::println);
