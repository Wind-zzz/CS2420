package assign10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is to test the BinaryMinHeap in every method and constructor.
 *
 * @author Quang Khai Huynh and Tien Phong Le
 * @version 11/21/2024
 */
public class BinaryMinHeapTest {

    private BinaryMinHeap<Integer> heap;

    /**
     * Sets up the test environment by initializing a new BinaryMinHeap instance
     * before each test.
     */
    @BeforeEach
    void setUp() {
        heap = new BinaryMinHeap<>();
    }

    // Test add method

    @Test
    void testAddSingleElement() {
        heap.add(10);
        assertEquals(10, heap.peek());
        assertEquals(1, heap.size());
    }

    @Test
    void testAddMultipleElements() {
        heap.add(10);
        heap.add(5);
        heap.add(15);
        assertEquals(5, heap.peek());
    }

    @Test
    void testAddDuplicateElements() {
        heap.add(10);
        heap.add(10);
        heap.add(10);
        assertEquals(10, heap.peek());
        assertEquals(3, heap.size());
    }

    @Test
    void testAddNegativeNumbers() {
        heap.add(-5);
        heap.add(-10);
        heap.add(0);
        assertEquals(-10, heap.peek());
    }

    @Test
    void testAddToResizeHeap() {
        for (int i = 0; i < 25; i++) {
            heap.add(i);
        }
        assertEquals(0, heap.peek());
        assertEquals(25, heap.size());
    }

    @Test
    void testAddWithLargeValues() {
        heap.add(Integer.MAX_VALUE);
        heap.add(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, heap.peek());
    }

    // Test peek method

    @Test
    void testPeekSingleElement() {
        heap.add(10);
        assertEquals(10, heap.peek());
    }

    @Test
    void testPeekMultipleElements() {
        heap.add(20);
        heap.add(10);
        heap.add(15);
        assertEquals(10, heap.peek());
    }

    @Test
    void testPeekNegativeNumbers() {
        heap.add(-5);
        heap.add(-20);
        heap.add(0);
        assertEquals(-20, heap.peek());
    }

    @Test
    void testPeekWithComparator() {
        BinaryMinHeap<String> heapStr = new BinaryMinHeap<>(Comparator.reverseOrder());
        heapStr.add("apple");
        heapStr.add("banana");
        heapStr.add("cherry");
        assertEquals("cherry", heapStr.peek());
    }

    @Test
    void testPeekWithoutAddingElements() {
        assertThrows(NoSuchElementException.class, heap::peek);
    }

    @Test
    void testPeekAfterRemovingElement() {
        heap.add(5);
        heap.add(10);
        heap.extract(); // Removes 5
        assertEquals(10, heap.peek());
    }

    @Test
    void testPeekWithDuplicateElements() {
        heap.add(10);
        heap.add(10);
        heap.add(10);
        assertEquals(10, heap.peek());
    }

    @Test
    void testPeekAfterClear() {
        heap.add(20);
        heap.clear();
        assertThrows(NoSuchElementException.class, heap::peek);
    }

    @Test
    void testPeekWithLargeElements() {
        BinaryMinHeap<Long> heapLong = new BinaryMinHeap<>();
        heapLong.add(Long.MAX_VALUE);
        heapLong.add(Long.MIN_VALUE);
        assertEquals(Long.MIN_VALUE, heapLong.peek());
    }

    // Test extract method

    @Test
    void testExtractSingleElement() {
        heap.add(10);
        assertEquals(10, heap.extract());
        assertTrue(heap.isEmpty());
    }

    @Test
    void testExtractMultipleElements() {
        heap.add(20);
        heap.add(10);
        heap.add(15);
        assertEquals(10, heap.extract());
        assertEquals(15, heap.peek());
    }

    @Test
    void testExtractNegativeNumbers() {
        heap.add(-5);
        heap.add(-20);
        heap.add(0);
        assertEquals(-20, heap.extract());
        assertEquals(-5, heap.peek());
    }

    @Test
    void testExtractWithComparator() {
        BinaryMinHeap<String> heap = new BinaryMinHeap<>(Comparator.reverseOrder());
        heap.add("apple");
        heap.add("banana");
        heap.add("cherry");
        assertEquals("cherry", heap.extract());
        assertEquals("banana", heap.peek());
    }

    @Test
    void testExtractEmptyHeap() {
        assertThrows(NoSuchElementException.class, heap::extract);
    }

    @Test
    void testExtractAllElements() {
        heap.add(5);
        heap.add(10);
        heap.add(1);
        assertEquals(1, heap.extract());
        assertEquals(5, heap.extract());
        assertEquals(10, heap.extract());
        assertTrue(heap.isEmpty());
    }

    @Test
    void testExtractAfterClear() {
        heap.add(20);
        heap.add(15);
        heap.clear();
        assertThrows(NoSuchElementException.class, heap::extract); // Empty heap after clear
    }

    @Test
    void testExtractWithDuplicateElements() {
        heap.add(10);
        heap.add(10);
        heap.add(10);
        assertEquals(10, heap.extract());
        assertEquals(10, heap.extract());
        assertEquals(10, heap.extract());
        assertTrue(heap.isEmpty());
    }

    @Test
    void testExtractWithLargeValues() {
        BinaryMinHeap<Long> heapLong = new BinaryMinHeap<>();
        heapLong.add(Long.MAX_VALUE);
        heapLong.add(Long.MIN_VALUE);
        assertEquals(Long.MIN_VALUE, heapLong.extract());
    }

    // Test isEmpty method

    @Test
    void testIsEmptyForEmptyHeap() {
        assertTrue(heap.isEmpty());
    }

    @Test
    void testNotEmptyAfterAdd() {
        heap.add(10);
        assertFalse(heap.isEmpty());
    }

    @Test
    void testEmptyAfterSingleExtract() {
        heap.add(5);
        heap.extract();
        assertTrue(heap.isEmpty());
    }

    @Test
    void testNotEmptyAfterMultipleAdds() {
        heap.add(20);
        heap.add(30);
        assertFalse(heap.isEmpty());
    }

    @Test
    void testEmptyAfterExtractingAll() {
        heap.add(15);
        heap.add(10);
        heap.add(20);
        heap.extract();
        heap.extract();
        heap.extract();
        assertTrue(heap.isEmpty());
    }

    @Test
    void testEmptyAfterClear() {
        heap.add(25);
        heap.add(35);
        heap.clear();
        assertTrue(heap.isEmpty());
    }

    @Test
    void testNotEmptyAfterReAdding() {
        heap.add(40);
        heap.extract();
        heap.add(50);
        assertFalse(heap.isEmpty());
    }

    @Test
    void testEmptyAfterBuildHeapFromList() {
        BinaryMinHeap<Integer> heap = new BinaryMinHeap<>(List.of());
        assertTrue(heap.isEmpty());
    }

    @Test
    void testNotEmptyAfterBuildHeapFromList() {
        BinaryMinHeap<Integer> heap = new BinaryMinHeap<>(List.of(10, 20, 30));
        assertFalse(heap.isEmpty());
    }

    // Test clear method

    @Test
    void testClearEmptyHeap() {
        heap.clear();
        assertTrue(heap.isEmpty());
    }

    @Test
    void testClearSingleElementHeap() {
        heap.add(10);
        heap.clear();
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
    }

    @Test
    void testClearMultipleElementsHeap() {
        heap.add(15);
        heap.add(10);
        heap.add(20);
        heap.clear();
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
    }

    @Test
    void testClearWithComparator() {
        BinaryMinHeap<String> heap = new BinaryMinHeap<>(String::compareTo);
        heap.add("apple");
        heap.add("banana");
        heap.clear();
        assertTrue(heap.isEmpty());
    }

    @Test
    void testClearAfterHeapifyFromList() {
        BinaryMinHeap<Integer> heap = new BinaryMinHeap<>(List.of(10, 20, 5));
        heap.clear();
        assertTrue(heap.isEmpty());
    }

    @Test
    void testClearMultipleTimes() {
        heap.add(10);
        heap.add(15);
        heap.clear();
        heap.clear();
        assertTrue(heap.isEmpty());
    }

    @Test
    void testClearAndRebuildHeapFromList() {
        heap.add(50);
        heap.add(30);
        heap.clear();
        heap = new BinaryMinHeap<>(List.of(20, 10, 40));
        assertFalse(heap.isEmpty());
        assertEquals(3, heap.size());
        assertEquals(10, heap.peek());
    }

    // Test size method
    @Test
    void testSizeEmptyHeap() {
        assertEquals(0, heap.size());
    }

    @Test
    void testSizeAfterAddSingleElement() {
        heap.add(10);
        assertEquals(1, heap.size());
    }

    @Test
    void testSizeAfterAddMultipleElements() {
        heap.add(10);
        heap.add(20);
        heap.add(5);
        assertEquals(3, heap.size());
    }

    @Test
    void testSizeAfterExtractSingleElement() {
        heap.add(15);
        heap.extract();
        assertEquals(0, heap.size());
    }

    @Test
    void testSizeAfterClear() {
        heap.add(10);
        heap.add(20);
        heap.clear();
        assertEquals(0, heap.size());
    }

    @Test
    void testSizeAfterHeapifyFromList() {
        BinaryMinHeap<Integer> heap = new BinaryMinHeap<>(List.of(10, 20, 5));
        assertEquals(3, heap.size());
    }

    @Test
    void testSizeWithDuplicateElements() {
        heap.add(10);
        heap.add(10);
        assertEquals(2, heap.size());
    }

    @Test
    void testSizeAfterResize() {
        for (int i = 1; i <= 25; i++) {
            heap.add(i);
        }
        assertEquals(25, heap.size());
    }

    @Test
    void testSizeAfterMixedOperations() {
        heap.add(15);
        heap.add(30);
        heap.extract();
        heap.add(10);
        heap.add(20);
        heap.clear();
        heap.add(5);
        assertEquals(1, heap.size());
    }

    // Test toArray method

    @Test
    void testToArrayEmptyHeap() {
        Object[] array = heap.toArray();
        assertArrayEquals(new Object[]{}, array);
    }


    @Test
    void testToArraySingleElement() {
        heap.add(42);
        Object[] array = heap.toArray();
        assertArrayEquals(new Object[]{42}, array);
    }

    @Test
    void testToArrayMultipleElements() {
        heap.add(10);
        heap.add(5);
        heap.add(15);
        Object[] array = heap.toArray();
        assertEquals(3, array.length);
        assertTrue(Arrays.asList(array).containsAll(List.of(5, 10, 15)));
    }

    @Test
    void testToArrayMaintainsHeapProperty() {
        heap.add(20);
        heap.add(10);
        heap.add(5);
        heap.add(15);
        Object[] array = heap.toArray();
        assertEquals(5, array[0]);
    }

    @Test
    void testToArrayAfterExtract() {
        heap.add(30);
        heap.add(10);
        heap.add(20);
        heap.extract();
        Object[] array = heap.toArray();
        assertEquals(2, array.length);
        assertTrue(Arrays.asList(array).containsAll(List.of(20, 30)));
    }

    @Test
    void testToArrayAfterClear() {
        heap.add(10);
        heap.add(20);
        heap.clear();
        Object[] array = heap.toArray();
        assertEquals(0, array.length);
    }

    @Test
    void testToArrayWithDuplicates() {
        heap.add(10);
        heap.add(10);
        heap.add(5);
        Object[] array = heap.toArray();
        assertEquals(3, array.length);
        assertTrue(Arrays.asList(array).containsAll(List.of(5, 10, 10)));
    }

    @Test
    void testToArrayAfterHeapifyFromList() {
        List<Integer> list = List.of(15, 10, 20, 5);
        BinaryMinHeap<Integer> heap = new BinaryMinHeap<>(list);
        Object[] array = heap.toArray();
        assertEquals(list.size(), array.length);
        assertTrue(Arrays.asList(array).containsAll(list));
    }

    @Test
    void testToArrayAfterMixedOperations() {
        heap.add(50);
        heap.add(40);
        heap.extract();
        heap.add(30);
        heap.add(20);
        heap.clear();
        heap.add(10);
        Object[] array = heap.toArray();
        assertArrayEquals(new Object[]{10}, array);
    }

    // Test constructor with comparator

    @Test
    public void testCustomComparator() {
        Comparator<Integer> reverseComparator = Comparator.reverseOrder();
        BinaryMinHeap<Integer> heap = new BinaryMinHeap<>(reverseComparator);
        heap.add(1);
        heap.add(3);
        heap.add(2);
        assertEquals(3, heap.peek());
    }

    // Test constructor with list

    @Test
    public void testBuildHeapFromList() {
        List<Integer> list = Arrays.asList(5, 3, 8, 1, 2);
        BinaryMinHeap<Integer> heap = new BinaryMinHeap<>(list);
        assertEquals(1, heap.peek());
        assertEquals(5, heap.size());
    }

    // Test constructor with comparator and list

    @Test
    public void testBuildHeapFromListWithComparator() {
        Comparator<Integer> reverseComparator = Comparator.reverseOrder();
        List<Integer> list = Arrays.asList(5, 3, 8, 1, 2);
        BinaryMinHeap<Integer> heap = new BinaryMinHeap<>(list, reverseComparator);

        assertEquals(8, heap.peek());
    }
}