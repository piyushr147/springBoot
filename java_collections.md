# what is java collections, why we needed it what problem did it solve and how?
    The Java Collections Framework (JCF) is a unified architecture for representing and manipulating groups of objects. It provides data structures (like lists, sets, maps, queues) and algorithms (like sorting and searching) — all within a consistent API.

    Why Was Java Collections Introduced?
        The Problem (Before Collections)
            Before Java 1.2, you had to use arrays or custom data structures like Vector, Hashtable, or manual array management:
            String[] names = new String[10];

        Problems with this approach:
            Arrays have fixed size.
            No consistent way to grow/shrink collections.
            No uniform API — each data structure had its own methods.
            No generics — everything was stored as Object, leading to casting issues.
            Difficult to write reusable algorithms for searching, sorting, etc.

    What Java Collections Solved
        Unified Interfaces
        All collections implement common interfaces like List, Set, Map, Queue, etc.
            List<String> list = new ArrayList<>();
            Set<Integer> set = new HashSet<>();
        Algorithms & Utilities
            The Collections class provides reusable algorithms:
            Collections.sort(list)
            Collections.reverse(list)
            Collections.shuffle(list)

    Core Interfaces in Java Collections:
        Interface   Description	                            Example Implementations
        Collection	Root interface for all collections	    List, Set, Queue
        List	    Ordered collection (allows duplicates)	ArrayList, LinkedList, Vector
        Set	        No duplicates allowed	                HashSet, LinkedHashSet, TreeSet
        Queue	    FIFO (First-In-First-Out) structure	    PriorityQueue, ArrayDeque
        Map	        Key-value pairs (keys are unique)	    HashMap, LinkedHashMap, TreeMap, Hashtable

    Type Safety via Generics
        Collections use generics (from Java 5+), which avoid the need for casting:
            List<String> names = new ArrayList<>();
            names.add("Alice"); // type-safe

# Internal working of Arraylist
    The internal working of ArrayList in Java is based on a resizable array. Here's a breakdown of how it works under the hood:
    1. Data Structure
        Internally, ArrayList uses an array of Object[] to store elements:
            transient Object[] elementData; // non-private to simplify nested class access

    2. Initialization
        When you create an ArrayList, it starts with a default capacity (usually 10 if not specified):
            ArrayList<String> list = new ArrayList<>();
        It does not immediately allocate memory for the array.
        The array is created lazily upon the first element addition (in Java 8+).

    3. Adding Elements
        When you call add(E e), the element is added to the internal array.

        If the array is full, ArrayList resizes itself:
            int newCapacity = oldCapacity + (oldCapacity >> 1); // grows 50%
        This is a 1.5x growth strategy to balance space and performance.
        It then creates a new larger array, copies the old elements, and adds the new one.

        Once the capacity is increased then removing elements won't decrease the size
        you need to use list.trimToSize() which will automatically trim the size according to current elements;

    4. Removing Elements
        .remove(1), removes by indes
        .remove(Integer.valueof(1)), .remove("hello") removes by value.
        When you remove an element:
            It shifts all the elements to fill the gap (cost: O(n)).
            The slot is then set to null to prevent memory leaks.

    5. Accessing Elements
        Uses direct index access, like an array:
        E get(int index) {
            return (E) elementData[index];
        }
        This is why get() and set() are O(1) operations.

    6. Thread Safety
        ArrayList is not synchronized.
        If used in multi-threaded code, you should manually synchronize or use Collections.synchronizedList() or CopyOnWriteArrayList.

# Note
    Map is not a child of iterator or collection hirearchy.
    PriorityQueue and ArrayDeque are not thread safe, Use PriorityBlockingQueue and ConcurrentLinkedDeque  if you need a thread-safe priority queue.
    CopyOnWriteArrayList is thread-safe version of arraylist
    LinkedList implements both queue and deque

# Why map does not extends collections
    Because Map is not a collection of individual elements — it's a collection of key-value pairs, which is conceptually different from what the Collection interface represents.

    Key Differences:
        Collection<E> represents:
            A group of individual elements (like a list, set, queue).
            You can iterate over the elements directly.

        Map<K, V> represents:
            A group of key-value pairs (entries).
            You don’t iterate over keys or values directly — you iterate over .entrySet(), .keySet(), or .values().
            So the data model is fundamentally different.

# HashMap internal working
    Internal data structure to store data:
        HashMap uses it’s inner class Node<K,V> for storing map entries.
        HashMap contains an array of Node objects. Each node represents a key-value mapping. This process is defined below:
            static class Node<K,V> implements Map.Entry<K,V> {
                final int hash;
                final K key;
                V value;
                Node<K,V> next;
                //some more code
            }
        HashMap also has a field called table as shown below. It is basically an array of Node objects that are not yet initialized.
            transient Node<K,V>[] table;

    Hashing in HashMap:
        Hashing is the process of converting an object into an integer by using the hashCode() method. 
        It's necessary to write the hashCode() method properly for better performance of the HashMap. 
        HashMap uses the hashCode() method to determine the bucket location for a key.
        Some of the properties of HashCode are:
            If two objects are equal, they should have the same hashcode.
            If two objects have the same hashcode, then it is not necessary for them to be equal.

    Default initial capacity:
        DEFAULT_INITIAL_CAPACITY = 1 << 4;
        HashMap stores entries into multiple singly linked lists, called buckets or bins. Default number of bins is 16 and it’s always power of 2.
     
    Load factor:
        DEFAULT_LOAD_FACTOR = 0.75f;

    Working of put method:
        1. Compute Hash of Key
            int hash = hash(key); // Applies hash(key.hashCode()) and spreads bits

            HashMap uses an internal hash() function to make hashing more uniform:
                static final int hash(Object key) {
                    int h;
                    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
                }

        2. Determine Bucket Index
            int index = (n - 1) & hash; // n is length of the table (array)
            This computes which bucket (index) to put the entry in.

        3. Check If Bucket is Empty
            Node<K,V> e = table[index];
            If empty (null), just insert a new node there.
            If not empty, a collision has occurred — need to handle it.

        4. Handle Collisions
            If the key already exists in that bucket:
                Compare keys using equals().
                If found: 
                    update the existing node's value.
                If not found:
                    Add the new node at the end of the linked list, or
                    If the list is too long (≥ 8), convert to a tree (TreeNode).

        5. Insert New Node
            If no collision: 
                simply create and insert a Node<K,V>.
            If collision and no tree: 
                append to the linked list.
            If tree: 
                insert using tree logic (TreeNode.putTreeVal()).

        6. Resize if Needed
            If the size exceeds the threshold (capacity * load factor):
            HashMap doubles the table size.
            All entries are rehashed and redistributed into new buckets.
            This is a costly operation, so it's deferred until necessary.

    What happens in case of a collision
        In case of collision, it checks if the existing key in the bucket is equal to the key that we are trying to store. If yes, then the value of the key is updated. If the key is different, then it is added at the end of the existing key in the bucket to form a LinkedList.

        This transformation is called treeification.
            Thresholds:
                TREEIFY_THRESHOLD = 8 → convert list to tree
                UNTREEIFY_THRESHOLD = 6 → convert back to list on resize
                MIN_TREEIFY_CAPACITY = 64 → only treeify if table size ≥ 64

    Working of get method:
        First 2 steps are same as put method, getting the hash and the bucket of the key.

        Step 3: Locate Node in the Bucket
            Node<K,V> e = table[index];
            If e == null, the key does not exist:
                return null.
            If e.key.equals(key):
                return e.value.
            If it's not a direct hit, then:
                Traverse Chain (Linked List or Tree)
                while (e != null) {
                    if (e.hash == hash && (e.key == key || e.key.equals(key)))
                        return e.value;
                    e = e.next;
                }
                Uses both == and .equals() to compare keys.
                If it's a tree (TreeNode), it uses a tree lookup instead of a loop.

        Final Outcome:
            Value is returned if key is found.
            null is returned if the key is not present.

    Resizing of HashMap:
        We already know that a HashMap is resized when it is about to get full. 
        When a HashMap will be resized depends upon the load factor.
        If the current capacity is 16, and the load factor is 0.75, then the HashMap will be resized when it has 12 elements (16 * 0.75).
        Now all the elements that are stored in the HashMap will be rearranged amongst these 32 buckets. 

        Why Rehashing Is Expensive
            Each node must be reinserted into the new table.
            For large maps, this can become O(n) operation.
            That's why you should always initialize HashMap with proper capacity if you know it upfront.

    Treeify Threshold:
        TREEIFY_THRESHOLD = 8;
        Searching operation on a LinkedList is O(n) which makes HashMap working slow if collisions increase, therefore we have a limit to it.
        If the size of the LinkedList in a particular bucket becomes more than TREEIFY_THRESHOLD, then the LinkedList is converted to a red-black tree. 
        Search operation on red-black tree is of O(Logn) time complexity.
        TREEIFY_THRESHOLD is a constant with a default value of 8. This value can’t be changed as it is a final variable. 

    Why LinkedList was replaced with Balanced search tree

    points:
        HashMap is not an ordered collection. You can iterate over HashMap entries through keys set but they are not guaranteed to be in the order of their addition to the HashMap.
        HashMap is almost similar to Hashtable except that it’s unsynchronized and allows null key and values.
        HashMap uses hashCode() and equals() methods on keys for get and put operations. So HashMap key object should provide good implementation of these methods. This is the reason immutable classes are better suitable for keys, for example String and Interger.
        Java HashMap is not thread safe, for multithreaded environment you should use ConcurrentHashMap class or get synchronized map using Collections.synchronizedMap() method.

# Why the order of elements in a HashMap in Java is not consistent.

# What is LinkedHashMap?
    LinkedHashMap<K, V> is a subclass of HashMap that maintains a predictable iteration order:
        It’s basically a HashMap with a memory — it remembers the order in which entries were inserted (or accessed).
        It maintains insertion order (by default) or access order (if configured).
        It achieves this by maintaining a doubly linked list of all entries.

    Property	                Description
    Order	                    Maintains insertion order (or access order if enabled)
    Duplicates	                Keys not allowed, Values are allowed
    Nulls	                    1 null key, many null values allowed
    Thread Safety	            Not synchronized
    Underlying Data Structure	Hash table + Doubly linked list
    Performance	Slightly slower than HashMap due to link maintenance

    Internal Data Structures:
        Internally, LinkedHashMap extends HashMap and adds a doubly linked list that connects all entries in order.
        LinkedHashMap uses:
            A hash table like HashMap (Node[] table)
            A doubly linked list to maintain order
            It uses a special internal class:
                static class LinkedHashMapEntry<K,V> extends HashMap.Node<K,V> {
                    LinkedHashMapEntry<K,V> before, after;
                }
            before: points to the previous entry
            after: points to the next entry
    
    Internal Working: Key Operations
        1. put(K key, V value)
            Works like in HashMap: computes hash, finds bucket, handles collision
            Additionally:
                Adds the new node to the end of the doubly linked list and maintains before and after links.

        2. get(K key)
            Behaves like HashMap
            If accessOrder is true, the accessed node is moved to the end of the linked list.
            Useful for LRU cache implementations.

        3. remove(Object key)
            Removes from hash table bucket
            Also removes from the linked list by adjusting before and after pointers

        4. Iteration
            Iteration happens over the linked list, not the hash table. So keys are returned in:
                Insertion order (default)
                OR last-accessed-first order (if accessOrder = true)

        5. removeEldestEntry(Map.Entry<K,V> eldest)
            You can override this method to automatically remove the oldest entry, commonly used in LRU cache.
                protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
                    return size() > MAX_ENTRIES;
                }

    Access Order Option
        If you construct it with accessOrder = true:
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>(16, 0.75f, true);
        Now the iteration order changes based on access (get or put).
        Example:
            map.put("A", 1);
            map.put("B", 2);
            map.put("C", 3);

            map.get("A"); // accessed A
            System.out.println(map.keySet());
        Output:
        [B, C, A]
        Because "A" was recently accessed, it moves to the end.
        This is super useful for building LRU caches (Least Recently Used).

        Removing Eldest Entries (LRU Mechanism)
            You can override the method removeEldestEntry() to automatically remove old entries.
            Example:
                LinkedHashMap<Integer, String> cache = new LinkedHashMap<>(3, 0.75f, true) {
                    protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                        return size() > 3; // keep max 3 entries
                    }
                };
                cache.put(1, "A");
                cache.put(2, "B");
                cache.put(3, "C");
                cache.get(1); // access 1
                cache.put(4, "D"); // triggers removal

                System.out.println(cache);
            Output:
            {3=C, 1=A, 4=D}
            Oldest unused (2) was automatically removed!

    When to use LinkedHashMap: 
        You need a map that remembers the order of keys inserted	Maintains insertion order via a linked list
        You want a cache with LRU (Least Recently Used) behavior	Use access order + removeEldestEntry() override
        You want fast lookup and ordered iteration	Lookup is O(1); iteration is in a predictable order

# What is TreeMap?
    A TreeMap in Java is a Red-Black Tree–based implementation of the NavigableMap interface.
    It stores key–value pairs in sorted (ascending) order of keys — unlike HashMap, which is unordered.

    Property	                Description
    Ordering	                Keys are always sorted (natural or custom order)
    Underlying Data Structure	Red-Black Tree (self-balancing binary search tree)
    Null Keys	                Not allowed (throws NullPointerException)
    Null Values	                Allowed
    Duplicates	                Keys unique, Values may repeat
    Performance	                O(log n) for all operations
    Thread Safety	            Not synchronized

    A Map implementation that keeps keys sorted according to:
        Their natural ordering (Comparable) Or a custom Comparator
    Does not allow null keys (throws NullPointerException) but allows multiple null values

    Internal Data Structures:
        TreeMap uses a nested static class called Entry<K, V>:
            static final class Entry<K,V> implements Map.Entry<K,V> {
                K key;
                V value;
                Entry<K,V> left;
                Entry<K,V> right;
                Entry<K,V> parent;
                boolean color; // RED or BLACK
            }

    1. put(K key, V value)
        Steps:
            If the tree is empty → key becomes the root.
            Otherwise, compare with existing keys:
                Smaller → go left
                Larger → go right
            Insert in correct position.
            Balance the tree using Red-Black Tree rules to maintain O(log n) operations.
        Maintains sorted order of keys automatically.

    2. get(Object key)
        Traverse the tree based on key comparison.
            If key found, return value.
            If not found, return null.
        Time complexity: O(log n)

    3. remove(Object key)
        Traverse the tree to find the node.
        Remove the node using BST rules.
        Rebalance the tree to maintain Red-Black Tree properties.
        Time complexity: O(log n)

    4. firstKey() / lastKey()
        Returns the smallest / largest key by traversing:
        firstKey: farthest left node
        lastKey: farthest right node

    5. ceilingKey(K key) / floorKey(K key) / higherKey(K key) / lowerKey(K key)
        These methods use tree traversal to find keys that are:
            Ceiling: ≥ key
            Floor: ≤ key
            Higher: > key
            Lower: < key

    If you want custom order → pass a Comparator to constructor:
        TreeMap<String, Integer> map = new TreeMap<>(Comparator.reverseOrder());

    Red-Black Tree Basics
        TreeMap uses a Red-Black Tree to keep operations efficient:
            Each node is either red or black.
            The root is always black.
            Red nodes cannot have red children (no two reds in a row).
            Every path from a node to a leaf must have the same number of black nodes.
        ➡ This guarantees a balanced tree with O(log n) height.

    When to Use TreeMap
        Use Case	Why TreeMap Works Well
        Sorted map	Keys are always sorted (natural or comparator)
        Range queries (e.g., keys between A and M)	Methods like subMap(), headMap(), tailMap() make this easy
        Ceiling/floor lookup (e.g., closest ≥ or ≤ key)	ceilingKey(), floorKey(), higherKey(), etc.
        Navigable operations (like reverse order, partial views)	Implements NavigableMap

    Real-world Scenarios
        Leaderboard systems where you need rankings (scores in order).
        Calendar apps where events are stored and queried by date/time.
        Interval trees or range searches on sorted data.
        Autocomplete suggestions where prefixes matter (sorted keys help).

# HashTable and it's working
    A Hashtable in Java is a key–value data structure that stores elements using hashing.
    It’s very similar to a HashMap, but with one major difference — it is synchronized (thread-safe).

    Property	        Description
    Duplicates	        Keys not allowed, Values are allowed
    Nulls	            Neither null keys nor null values are allowed
    Order	            Unordered
    Thread Safety	    Synchronized (all methods are thread-safe)
    Performance 	    Slower than HashMap due to synchronization

    Synchronization Mechanism:
        All major methods (get, put, remove, etc.) are synchronized, e.g.:
            public synchronized V get(Object key) { ... }
            public synchronized V put(K key, V value) { ... }
        This means:
            Only one thread can access the map at a time.
            Prevents race conditions.
            But introduces performance overhead in multithreaded scenarios.

# ConcurrentHashMap (The modern alternative of HashTable),
    A ConcurrentHashMap is a thread-safe, high-performance, hash-based implementation of the Map interface — designed for multi-threaded environments, it's more fast then HashTable because hashtable locks the whole table while doing a get or put operation but this only puts a lock on specific bucket which a thread is trying to access. This makes multiple threads to work on different buckets simultaneously.

    Property	        Description
    Thread Safety	    Yes — multiple threads can read/write concurrently
    Null Keys/Values	Not allowed (throws NullPointerException)
    Locking Mechanism	Fine-grained locking (segments or per-bin locks)
    Iteration	        Weakly consistent (no ConcurrentModificationException)
    Performance	        Much faster than Hashtable under high concurrency

    Internal Working of ConcurrentHashMap
        Data Structure:
            In Java 8+, a ConcurrentHashMap is built using:
                An array of buckets (like a HashMap)
                Each bucket is a chain or tree of nodes
                Locking happens per bucket, not the entire map
            
            static class Node<K,V> implements Map.Entry<K,V> {
                final int hash;
                final K key;
                volatile V value;
                volatile Node<K,V> next;
            }
            Notice volatile — ensures visibility across threads.

        Concurrency Mechanism
            Before Java 8:
                The map was divided into segments (each with a lock).
                Example: 16 segments → 16 threads could operate concurrently.
            After Java 8:
                No segments.
                Uses fine-grained synchronization via:
                    CAS (Compare-And-Swap) operations
                    synchronized blocks on individual bins
                    volatile fields for visibility
            So, multiple threads can update different buckets at the same time safely.

        put() Internal Flow:
            When you call:
                map.put("Alice", 25);
            Here’s what happens internally:
                Compute hash of the key.
                Find target bucket index.
                    If bucket is empty:
                        Use CAS to insert new node atomically.
                    If bucket is non-empty:
                        Lock only that specific bin (not the entire map).
                        Traverse the list/tree.
                        Update or insert the node.
                Release the lock.
            Result: Multiple threads can safely modify different bins simultaneously.
        
        get() Operation
            No locking:
                Uses volatile reads to ensure visibility.
                Traverses only the relevant bucket.
            Extremely fast for read-heavy workloads.

# why can't we add in between iteration of HashMap/HashSet
    The reason we can’t safely add to a HashMap during iteration (i.e., in between iteration steps) is due to how Java's fail-fast iterators work, and how they protect consistency of the data structure during traversal.

    TL;DR:
        You can't add to a HashMap during iteration because modifying its structure invalidates the iterator, and Java throws a ConcurrentModificationException to prevent unpredictable behavior.

    Let's Dive In
    1. Fail-Fast Iterators
        Java collections like HashMap, ArrayList, etc., use fail-fast iterators.
        When you create an iterator, it remembers the collection's internal modCount (modification count).
            Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
            It stores the modCount as the expectedModCount which should not be changed.
        Each time next() is called:
            It checks whether modCount == expectedModCount
            If not, it throws:
                throw new ConcurrentModificationException();
        what if you do a put() operation in between:
            It will increase the modCount which will never be equal to the expectedModCount in next next() call
            So it will throw the error
        This prevents bugs that would happen if you changed the map mid-iteration (adding or removing elements).

    2. Why Is It a Problem?
        When you add a new element to the map:
            The internal hash table may resize
            The bucket structure may change
            so, the Iteration order may get corrupted
        The new entry may be inserted into a bucket that the iterator has already passed, or one it hasn't reached yet
        This causes the iterator to behave unpredictably — so Java fails fast rather than continue with corrupted behavior.

    3. How to solve it
        ConcurrentHashMap allows safe concurrent reads and writes — even during iteration — by using:
            Segmented locking (Java 7) or bucket-level locking (Java 8+)
            Weakly consistent iterators that do not fail-fast
        How It Works Internally (Java 8+)
            In Java 8 and beyond, ConcurrentHashMap uses:
                1. Array of buckets:
                    Like HashMap, it uses an array of buckets (nodes) for storing entries.
                2. Fine-Grained Synchronization:
                    Instead of locking the whole map:
                        It synchronizes only the bucket (bin) being modified.
                        Uses synchronized blocks on individual nodes or bins to allow more concurrency.
                            Node<K,V>[] table;
                3. No modCount:
                    ConcurrentHashMap does not have a modCount.
                    So there's no expectedModCount for the iterator to check against.
                    As a result, no ConcurrentModificationException is thrown.


                

