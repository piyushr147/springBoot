# what is stream?

# Different ways of creating a stream

# Intermediate opereations

# Terminal operations

# why can't we reuse a stream after terminal operation?

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

# Sequence of stream operations

# stream vs parallelStream
