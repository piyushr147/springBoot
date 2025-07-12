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

        Data Structures Included
            List: Ordered collection — ArrayList, LinkedList
            Set: No duplicates — HashSet, TreeSet
            Map: Key-value pairs — HashMap, TreeMap
            Queue: FIFO — LinkedList, PriorityQueue

        Algorithms & Utilities
            The Collections class provides reusable algorithms:
            Collections.sort(list)
            Collections.reverse(list)
            Collections.shuffle(list)

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
        Initially, the elementData array is created with a capacity (e.g., 10 slots).

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
        Either insertion order (default)
        Or access order (when configured)
        It achieves this by maintaining a doubly linked list of all entries.
    
    Internal Data Structures
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
                Adds the new node to the end of the linked list
                Maintains before and after links
            
                Hash Bucket Table      Doubly Linked List
                ┌───────────────┐      ┌─────────┐  ┌─────────┐
                │  table[index] │ -->  │ entry1  │→ │ entry2  │→ ...
                └───────────────┘      └─────────┘  └─────────┘

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

        When to use LinkedHashMap: 
            You need a map that remembers the order of keys inserted	Maintains insertion order via a linked list
            You want a cache with LRU (Least Recently Used) behavior	Use access order + removeEldestEntry() override
            You want fast lookup and ordered iteration	Lookup is O(1); iteration is in a predictable order

# HashMap vs LinkedHashMap
    Core Difference
        Feature	                        HashMap	                                    LinkedHashMap
        Order of elements	            No guarantee – iteration order is random	Predictable – maintains insertion or access order
        Underlying structure	        Hash table	                                Hash table + doubly linked list
        Performance (get/put)	        O(1) average	                            O(1) average (slightly more overhead due to linking)
        Null keys/values	            ✅ One null key, multiple null values	   ✅ Same
        Thread-safe?	                ❌ No   	                                   ❌ No
        Memory usage	                Lower	                                    Higher (because of extra linked list pointers)
        Used in LRU caching?	        ❌ No built-in support  	                  ✅ Yes, via accessOrder and removeEldestEntry()
        When serialized	                Unordered	                                Order preserved

# What is TreeMap?
    A Map implementation that keeps keys sorted according to:
        Their natural ordering (Comparable)
        Or a custom Comparator
    Backed by a Red-Black Tree and not thread-safe
    Does not allow null keys (throws NullPointerException) but allows multiple null values

    Internal Data Structures
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
            Check if key is null → throw NullPointerException.
            Start from the root node.
            Traverse the tree using key comparison (compareTo() or comparator).
            If key exists → update value.
            If key doesn't exist → insert a new node.
            Rebalance tree to maintain Red-Black Tree properties (rotations + recoloring).
        Time complexity: O(log n)

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
        ✅ Use TreeMap for:
        Leaderboard systems where you need rankings (scores in order).
        Calendar apps where events are stored and queried by date/time.
        Interval trees or range searches on sorted data.
        Autocomplete suggestions where prefixes matter (sorted keys help).


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
        Iteration order may be corrupted
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


                

