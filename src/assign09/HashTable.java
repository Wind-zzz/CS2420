package assign09;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a hashtable that implements the Map interface using quadratic probing
 * strategy to resolve collisions. It uses lazy deletion to mark deleted entries, maintaining the
 * performance of probing without creating gaps in the probing sequence.
 * The hash table dynamically resizes when the load factor exceeds 0.5.
 *
 * @param <K> - placeholder for key type
 * @param <V> - placeholder for value type
 * @author Tien Phong Le & Quang Khai Huynh
 * @version 11/14/2024
 */
public class HashTable<K, V> implements Map<K, V> {
    private Object[] table;
    private int size;
    private double load;
    private boolean[] deleted;
    private int collisions = 0;

    /**
     * This constructor creates default Hashtable object
     */
    public HashTable() {
        this.table = new Object[50];
        this.deleted = new boolean[50];
        this.size = 0;
        this.load = 0;
        this.collisions = 0;
    }

    /**
     * Construct new hash table with table of a given capacity.
     *
     * @param capacity - backing table capacity
     */
    public HashTable(int capacity) {
        this.table = new Object[capacity];
        this.deleted = new boolean[capacity];
        this.size = 0;
        this.load = 0;
        this.collisions = 0;
    }

    /**
     * Getter for the number of collisions
     */
    public int getCollisions() {
        return this.collisions;
    }

    /**
     * Removes all mappings from this map.
     * O(table length) for a quadratic-probing hash table
     */
    @Override
    public void clear() {
        this.table = new Object[50];
        this.deleted = new boolean[50];
        this.size = 0;
        this.load = 0;
        this.collisions = 0;
    }

    /**
     * Determines whether this map contains the specified key.
     * O(1) for a quadratic-probing hash table
     *
     * @param key - the key being searched for
     * @return true if this map contains the key, false otherwise
     */
    @Override
    public boolean containsKey(K key) {
        int temp = searchKey(key);
        return getElement(temp) != null && !deleted[temp];
    }

    /**
     * Determines whether this map contains the specified value.
     * O(table length) for a quadratic-probing hash table
     *
     * @param value - the value being searched for
     * @return true if this map contains one or more keys to the specified value,
     * false otherwise
     */
    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < table.length; i++) {
            if (getElement(i) != null && !deleted[i]) {
                if (getElement(i).getValue() != null && getElement(i).getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns a list view of the mappings contained in this map, where the ordering of
     * mapping in the list is insignificant.
     * O(table length) for a quadratic-probing hash table
     *
     * @return a List object containing all mapping (i.e., entries) in this map
     */
    @Override
    public List<MapEntry<K, V>> entries() {
        List<MapEntry<K, V>> entries = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            if (getElement(i) != null && !deleted[i]) {
                entries.add(getElement(i));
            }
        }
        return entries;
    }

    /**
     * Gets the value to which the specified key is mapped.
     * O(1) for a quadratic-probing hash table
     *
     * @param key - the key for which to get the mapped value
     * @return the value to which the specified key is mapped, or null if this map
     * contains no mapping for the key
     */
    @Override
    public V get(K key) {
        int temp = searchKey(key);
        if (getElement(temp) != null && !deleted[temp]) {
            return getElement(temp).getValue();
        }
        return null;
    }

    /**
     * Determines whether this map contains any mappings.
     * O(1) for a quadratic-probing hash table
     *
     * @return true if this map contains no mappings, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * (I.e., if the key already exists in this map, resets the value;
     * otherwise adds the specified key-value pair.)
     * O(1) for a quadratic-probing hash table
     *
     * @param key   - the key for which to update the value (if exists)
     *              or to be added to the table
     * @param value - the value to be mapped to the key
     * @return the previous value associated with key, or null if there was no
     * mapping for key
     */
    @Override
    public V put(K key, V value) {
        if (load > 0.5) {
            resize();
        }

        int index = searchKey(key);

        if (getElement(index) != null && !deleted[index]) {
            V oldValue = getElement(index).getValue();
            getElement(index).setValue(value);
            return oldValue;
        } else {
            table[index] = new MapEntry<>(key, value);
            deleted[index] = false;
            this.size++;
            load();
            return null;
        }
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * O(1) for a quadratic-probing hash table
     *
     * @param key - the key to be removed
     * @return the previous value associated with key, or null if there was no
     * mapping for key
     */
    @Override
    public V remove(K key) {
        int index = searchKey(key);
        if (getElement(index) != null && !deleted[index]) {
            V value = getElement(index).getValue();
            deleted[index] = true;
            this.size--;
            load();
            return value;
        }
        return null;
    }

    /**
     * Determines the number of mappings in this map.
     * O(1) for a quadratic-probing hash table
     *
     * @return the number of mappings in this map
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * This private method resize when the load factor exceed 0.5
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        int capacity = table.length * 2;
        while (!isPrime(capacity)) {
            capacity++;
        }

        Object[] oldTable = table;
        boolean[] oldDeleted = deleted;

        size = 0;
        table = new Object[capacity];
        deleted = new boolean[capacity];

        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null && !oldDeleted[i]) {
                MapEntry<K, V> entry = (MapEntry<K, V>) oldTable[i];
                put(entry.getKey(), entry.getValue());
            }
        }
        load();
    }

    /**
     * This private method determines if the given number is a prime number or not
     *
     * @param number the given number
     */
    private boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * This private method searches for a key
     *
     * @param key the given key
     */
    private int searchKey(K key) {
        int initial = Math.abs(key.hashCode()) % table.length;
        int index = initial;
        int temp = 1;
        int firstDeleted = -1;

        while (table[index] != null) {
            if (!deleted[index]) {
                this.collisions++;
                if (getElement(index).getKey().equals(key)) {
                    return index; //return the index if the key is found
                }
            } else {
                if (firstDeleted == -1) {
                    firstDeleted = index;
                }
            }
            index = (initial + temp * temp) % table.length;
            temp++;

            if (temp > table.length) {
                resize();
                return searchKey(key);
            }
        }
        //Placeholder to put entry of the specified key
        if (firstDeleted == -1) {
            return index;
        } else {
            return firstDeleted;
        }
    }

    /**
     * This private method modifies the load factor of this map
     */
    private void load() {
        load = ((double) size) / table.length;
    }

    /**
     * This method retrieves the entry from the map
     */
    @SuppressWarnings("unchecked")
    private MapEntry<K, V> getElement(int index) {
        return (MapEntry<K, V>) table[index];
    }
}
