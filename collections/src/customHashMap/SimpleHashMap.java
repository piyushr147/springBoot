package customHashMap;

import java.util.LinkedList;

class SimpleHashMap<K, V> {

    private static class Entry<K, V> {

        K key;
        V value;

        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

    private final int SIZE = 16;
    private Entry<K, V>[] table;

    public SimpleHashMap() {
        table = new Entry[SIZE];
    }



    public void put(K key, V value) {
        int hash = key.hashCode() % SIZE;
        Entry<K, V> newEntry = new Entry<>(key, value);

        if (table[hash] == null) {
            table[hash] = newEntry;
        } else {

            Entry<K, V> previous = null;
            Entry<K, V> current = table[hash];

            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }

                previous = current;
                current = current.next;
            }

            previous.next = newEntry;
        }

    }



    public V get(K key) {
        int hash = key.hashCode() % SIZE;
        Entry<K, V> entry = table[hash];

        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }

        return null;
    }

    private void resize() {
        Entry<K, V>[] oldTable = table;
        table = new Entry[oldTable.length * 2];

        for (Entry<K, V> entry : oldTable) {
            while (entry != null) {
                put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }


}