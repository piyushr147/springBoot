# what is java collections, why we needed it what problem did it solve and how?

    The Java Collections Framework (JCF) is a unified architecture for representing and manipulating groups of objects. It provides data structures (like lists, sets, maps, queues) and algorithms (like sorting and searching) — all within a consistent API.

    ✅ Why Was Java Collections Introduced?
        🚫 The Problem (Before Collections)
            Before Java 1.2, you had to use arrays or custom data structures like Vector, Hashtable, or manual array management:
            String[] names = new String[10];

        Problems with this approach:
            Arrays have fixed size.
            No consistent way to grow/shrink collections.
            No uniform API — each data structure had its own methods.
            No generics — everything was stored as Object, leading to casting issues.
            Difficult to write reusable algorithms for searching, sorting, etc.

    ✅ What Java Collections Solved
        🧱 Unified Interfaces
        All collections implement common interfaces like List, Set, Map, Queue, etc.
            List<String> list = new ArrayList<>();
            Set<Integer> set = new HashSet<>();

        ⚙️ Data Structures Included
            List: Ordered collection — ArrayList, LinkedList
            Set: No duplicates — HashSet, TreeSet
            Map: Key-value pairs — HashMap, TreeMap
            Queue: FIFO — LinkedList, PriorityQueue

        📦 Algorithms & Utilities
            The Collections class provides reusable algorithms:
            Collections.sort(list)
            Collections.reverse(list)
            Collections.shuffle(list)

    ✅ Type Safety via Generics
        Collections use generics (from Java 5+), which avoid the need for casting:
            List<String> names = new ArrayList<>();
            names.add("Alice"); // type-safe

# Internal working of Arraylist
    The internal working of ArrayList in Java is based on a resizable array. Here's a breakdown of how it works under the hood:
    🔧 1. Data Structure
        Internally, ArrayList uses an array of Object[] to store elements:
            transient Object[] elementData; // non-private to simplify nested class access

    🚀 2. Initialization
        When you create an ArrayList, it starts with a default capacity (usually 10 if not specified):
            ArrayList<String> list = new ArrayList<>();
        Initially, the elementData array is created with a capacity (e.g., 10 slots).

    ➕ 3. Adding Elements
        When you call add(E e), the element is added to the internal array.

        If the array is full, ArrayList resizes itself:
            int newCapacity = oldCapacity + (oldCapacity >> 1); // grows 50%
        This is a 1.5x growth strategy to balance space and performance.
        It then creates a new larger array, copies the old elements, and adds the new one.

        Once the capacity is increased then removing elements won't decrease the size
        you need to use list.trimToSize() which will automatically trim the size according to current elements;

    📤 4. Removing Elements
        When you remove an element:
            It shifts all the elements to fill the gap (cost: O(n)).
            The slot is then set to null to prevent memory leaks.

    🔁 5. Accessing Elements
        Uses direct index access, like an array:
        E get(int index) {
            return (E) elementData[index];
        }
        This is why get() and set() are O(1) operations.

    🔒 6. Thread Safety
        ArrayList is not synchronized.
        If used in multi-threaded code, you should manually synchronize or use Collections.synchronizedList() or CopyOnWriteArrayList.



