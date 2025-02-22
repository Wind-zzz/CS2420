package assign09;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the HashTable class. This class contains various test methods to validate the
 * functionality of different operations such as put, remove, clear, size, containsKey, and containsValue,
 * as well as handling of collisions, load factor, and resize functionality.
 *
 * @author Quang Khai Huynh & Tien Phong Le
 * @version November 14th, 2024
 */
class HashTableTest {
    private HashTable<String, Integer> table;
    private HashTable<StudentBadHash, Double> badTable;
    private HashTable<StudentMediumHash, Double> medTable;
    private HashTable<StudentGoodHash, Double> goodTable;

    private StudentBadHash bad1;
    private StudentBadHash bad2;
    private StudentBadHash bad3;

    private StudentMediumHash med1;
    private StudentMediumHash med2;
    private StudentMediumHash med3;

    private StudentGoodHash good1;
    private StudentGoodHash good2;
    private StudentGoodHash good3;

    /**
     * Sets up the test environment by initializing all necessary hash tables and test objects.
     */
    @BeforeEach
    void setUp() {
        table = new HashTable<>();
        badTable = new HashTable<>();
        medTable = new HashTable<>();
        goodTable = new HashTable<>();

        // Initialize students with unique UIDs and names
        bad1 = new StudentBadHash(101, "Alice", "Smith");
        bad2 = new StudentBadHash(102, "Bob", "Brown");
        bad3 = new StudentBadHash(103, "Charlie", "Davis");

        med1 = new StudentMediumHash(201, "David", "Evans");
        med2 = new StudentMediumHash(202, "Eve", "Green");
        med3 = new StudentMediumHash(203, "Frank", "Harris");

        good1 = new StudentGoodHash(301, "Grace", "Lee");
        good2 = new StudentGoodHash(302, "Hank", "Young");
        good3 = new StudentGoodHash(303, "Ivy", "Clark");
    }

    //Tests clear() method

    @Test
    void testClearEmptyTable() {
        table.clear();
        assertTrue(table.isEmpty());
    }

    @Test
    void testClearOneSingleElement() {
        table.put("A", 1);
        table.clear();
        assertTrue(table.isEmpty());
    }

    @Test
    void testClearTableWithMultipleElements() {
        table.put("key1", 1);
        table.put("key2", 2);
        table.put("key3", 3);
        table.clear();
        assertTrue(table.isEmpty());
    }

    @Test
    void testClearTableWithCollisions() {
        badTable.put(bad1, 3.5);
        badTable.put(bad2, 4.5);
        badTable.put(bad3, 5.5);
        badTable.clear();
        assertTrue(badTable.isEmpty());
    }

    @Test
    void testClearWithNullValues() {
        table.put("key1", null);
        table.clear();
        assertTrue(table.isEmpty());
    }

    @Test
    void testClearTableWithManyElements() {
        for (int i = 0; i < 1000; i++) {
            table.put("key" + i, i);
        }
        table.clear();
        assertTrue(table.isEmpty());
    }

    @Test
    void testClearAndReinsert() {
        table.put("key1", 1);
        table.clear();
        table.put("key2", 2);
        assertEquals(2, table.get("key2"));
    }

    // Test isEmpty() method
    @Test
    void testIsEmpty() {
        assertTrue(table.isEmpty());
        table.put("test", 1);
        assertFalse(table.isEmpty());
    }

    //Test put() method
    @Test
    void testPutNewEntry() {
        table.put("apple", 1);
        assertEquals(1, table.get("apple"));
    }

    @Test
    void testPutUpdateExistingEntry() {
        table.put("banana", 2);
        table.put("banana", 3);
        assertEquals(3, table.get("banana"));
    }

    @Test
    void testPutMultipleEntries() {
        table.put("cat", 4);
        table.put("dog", 5);
        table.put("elephant", 6);
        assertEquals(4, table.get("cat"));
        assertEquals(5, table.get("dog"));
        assertEquals(6, table.get("elephant"));
    }

    @Test
    void testPutWithCollisionHandling() {
        badTable.put(bad1, 1.0);
        badTable.put(bad2, 2.0);
        assertEquals(1, badTable.get(bad1));
        assertEquals(2, badTable.get(bad2));
    }

    @Test
    void testPutOverwriteWithCollision() {
        goodTable.put(good1, 1.0);
        goodTable.put(good2, 2.0);
        goodTable.put(good1, 3.0);

        assertEquals(3, goodTable.get(good1));
        assertEquals(2, goodTable.get(good2));
    }

    @Test
    void testPutNullKey() {
        assertThrows(NullPointerException.class, () -> table.put(null, 10));
    }

    @Test
    void testPutNullValue() {
        table.put("keyWithNullValue", null);
        assertNull(table.get("keyWithNullValue"));
    }

    @Test
    void testPutLargeNumberOfEntries() {
        for (int i = 0; i < 1000; i++) {
            table.put("key" + i, i);
        }
        assertEquals(1000, table.size());
        assertEquals(500, table.get("key500"));
    }

    @Test
    void testPutKeyCollisionSameHashDifferentKeys() {
        medTable.put(med1, 1.0);
        medTable.put(med2, 2.0); // Different key with same hash code
        assertEquals(1, medTable.get(med1));
        assertEquals(2, medTable.get(med2));
    }

    @Test
    void testPutResizeAndRehash() {
        for (int i = 0; i < 100; i++) {
            table.put("key" + i, i);
        }
        assertEquals(100, table.size());
        for (int i = 0; i < 100; i++) {
            assertEquals(i, table.get("key" + i));
        }
    }

    @Test
    void testPutNullValueWithCollisionHandling() {
        badTable.put(bad1, null);
        badTable.put(bad2, 5.0); // bad1 and bad2 have the same hash code
        assertNull(badTable.get(bad1));
        assertEquals(5, badTable.get(bad2));
    }

    //Test remove() method
    @Test
    void testRemoveFromEmptyTable() {
        assertNull(table.remove("nonExistentKey"));
        assertEquals(0, table.size());
    }

    @Test
    void testRemoveCollidingKeys() {
        table.put("FB", 1);
        table.put("Ea", 2); //"Ea" and "FB" have the same hashCode() value (=2236)

        assertEquals(1, table.remove("FB"));
        assertEquals(2, table.get("Ea"));
        assertEquals(1, table.size());
    }

    @Test
    void testRemoveNullKeyThrowsException() {
        assertThrows(NullPointerException.class, () -> table.remove(null));
    }

    @Test
    void testRemoveExistingKey() {
        table.put("removeMe", 42);
        assertEquals(42, table.remove("removeMe"));
        assertNull(table.get("removeMe"));
        assertEquals(0, table.size());
    }

    @Test
    void testRemoveNonExistentKey() {
        assertNull(table.remove("nonExistent"));
        assertEquals(0, table.size());
    }

    @Test
    void testRemoveKeyWithNullValue() {
        goodTable.put(good1, null);
        assertEquals(null, goodTable.remove(good1));

        assertFalse(goodTable.containsKey(good1));
        assertEquals(0, goodTable.size());
    }

    //Tests for size

    @Test
    void testSize() {
        assertEquals(0, table.size());
        table.put("A", 1);
        table.put("B", 2);
        assertEquals(2, table.size());

        table.remove("A");
        assertEquals(1, table.size());
    }

    @Test
    void testEmptySize() {
        assertEquals(0, table.size());
    }

    @Test
    void testSizeAfterRehash() {
        for (int i = 0; i < 10; i++) {
            table.put("Key" + i, i);
        }
        assertEquals(10, table.size());
    }

    @Test
    void testSizeAfterRemovingNonExistentKey() {
        table.put("A", 1);
        table.remove("B");
        assertEquals(1, table.size());
    }

    @Test
    void testSizeOnCollisionHandling() {
        table.put("A", 1);
        table.put("A2", 2);
        assertEquals(2, table.size());
    }

    //Tests for containsKey

    @Test
    void testContainsKey() {
        assertFalse(table.containsKey("A"));
        table.put("A", 1);
        assertTrue(table.containsKey("A"));
        table.remove("A");
        assertFalse(table.containsKey("A"));
    }

    @Test
    void testContainsKeyWithNullThrowsException() {
        assertThrows(NullPointerException.class, () -> table.containsKey(null));
    }

    @Test
    void testContainsKeyWithKeysHavingSameHashCode() {
        String key1 = "Aa";
        String key2 = "BB";
        table.put(key1, 1);
        table.put(key2, 2);
        assertTrue(table.containsKey(key1));
        assertTrue(table.containsKey(key2));
    }

    @Test
    void testContainsKeyAfterRehash() {
        for (int i = 0; i < 10; i++) {
            table.put("Key" + i, i);
        }
        assertTrue(table.containsKey("Key5"));
    }

    @Test
    void testContainsKeyAfterUpdatingValue() {
        table.put("A", 1);
        table.put("A", 2);
        assertTrue(table.containsKey("A"));
    }

    //Tests for containsValue

    @Test
    void testContainsValue() {
        assertFalse(table.containsValue(1));
        table.put("A", 1);
        table.put("B", 2);
        assertTrue(table.containsValue(1));
        assertTrue(table.containsValue(2));

        table.remove("A");
        assertFalse(table.containsValue(1));
    }

    @Test
    void testContainsValueOnEmptyTable() {
        assertFalse(table.containsValue(1));
    }

    @Test
    void testContainsValueWithSingleExistingValue() {
        table.put("A", 1);
        assertTrue(table.containsValue(1));
    }

    @Test
    void testContainsValueWithSingleNonExistentValue() {
        table.put("A", 1);
        assertFalse(table.containsValue(2));
    }

    @Test
    void testContainsValueWithNullValue() {
        table.put("A", null);
        assertNull(table.get("A"));
    }

    @Test
    void testContainsValueAfterUpdatingValue() {
        table.put("A", 1);
        table.put("A", 2);
        assertFalse(table.containsValue(1));
        assertTrue(table.containsValue(2));
    }

    //Test load() method

    @Test
    void testLoadFactorLimitAndResize() {
        for (int i = 0; i < 30; i++) {
            table.put("Key" + i, i);
        }
        assertEquals(30, table.size());
    }

    @Test
    void testLoadFactorResize() {
        for (int i = 0; i < 30; i++) {
            table.put("key" + i, i);
        }
        assertTrue(table.size() > 25);
    }

    @Test
    void testLazyDeletionEffectiveness() {
        HashTable<String, Integer> lazyTable = new HashTable<>();
        lazyTable.put("A", 1);
        lazyTable.put("B", 2);
        lazyTable.remove("A");
        assertFalse(lazyTable.containsKey("A"));
        lazyTable.put("A", 3);
        assertTrue(lazyTable.containsKey("A"));
        assertEquals(3, lazyTable.get("A"));
    }

    //Tests for entries

    @Test
    void testEntries() {
        table.put("A", 1);
        table.put("B", 2);
        table.put("C", 3);

        List<MapEntry<String, Integer>> entries = table.entries();
        List<String> keys = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        for (MapEntry<String, Integer> entry : entries) {
            keys.add(entry.getKey());
            values.add(entry.getValue());
        }

        assertTrue(keys.contains("A"));
        assertTrue(keys.contains("B"));
        assertTrue(keys.contains("C"));
        assertTrue(values.contains(1));
        assertTrue(values.contains(2));
        assertTrue(values.contains(3));
    }

    //Tests for bad hash key type

    @Test
    void testPutAndGetWithBadHashKey() {
        badTable.put(bad1, 2.5);
        badTable.put(bad2, 3.0);

        assertEquals(2.5, badTable.get(bad1));
        assertEquals(3.0, badTable.get(bad2));
    }

    @Test
    void testCollisionsWithBadHashKey() {
        badTable.put(bad1, 2.5);
        badTable.put(bad2, 3.0);
        badTable.put(bad3, 3.5);

        assertEquals(2.5, badTable.get(bad1));
        assertEquals(3.0, badTable.get(bad2));
        assertEquals(3.5, badTable.get(bad3));
    }

    //Tests for medium hash key type

    @Test
    void testPutAndGetWithMediumHashKey() {
        medTable.put(med1, 3.4);
        medTable.put(med2, 3.6);

        assertEquals(3.4, medTable.get(med1));
        assertEquals(3.6, medTable.get(med2));
    }

    @Test
    void testCollisionsWithMediumHashKey() {
        medTable.put(med1, 3.4);
        medTable.put(med2, 3.6);
        medTable.put(med3, 3.8);

        assertEquals(3.4, medTable.get(med1));
        assertEquals(3.6, medTable.get(med2));
        assertEquals(3.8, medTable.get(med3));
    }

    //Tests for good hash key type

    @Test
    void testPutAndGetWithGoodHashKey() {
        goodTable.put(good1, 3.9);
        goodTable.put(good2, 3.7);

        assertEquals(3.9, goodTable.get(good1));
        assertEquals(3.7, goodTable.get(good2));
    }

    @Test
    void testCollisionsWithGoodHashKey() {
        goodTable.put(good1, 3.9);
        goodTable.put(good2, 3.7);
        goodTable.put(good3, 3.5);

        assertEquals(3.9, goodTable.get(good1));
        assertEquals(3.7, goodTable.get(good2));
        assertEquals(3.5, goodTable.get(good3));
    }
}
