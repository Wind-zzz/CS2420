package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is the test class for SinglyLinkedListIterator.
 */
public class SinglyLinkedListIteratorTest {

    private SinglyLinkedList<String> list;

    @BeforeEach
    public void setUp() {
        list = new SinglyLinkedList<>();
    }

    /**
     * Test iterator on an empty list.
     */
    @Test
    public void testEmptyIterator() {
        Iterator<String> iterator = list.iterator();
        assertFalse(iterator.hasNext());

        // Calling next() on an empty list should throw NoSuchElementException
        try {
            iterator.next();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Pass
        }
    }

    /**
     * Test iterator's hasNext() and next() methods for a list with a single element.
     */
    @Test
    public void testSingleElementIterator() {
        list.insertFirst("A");
        Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next());
        assertFalse(iterator.hasNext());

        // Calling next() after last element should throw NoSuchElementException
        try {
            iterator.next();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Pass
        }
    }

    /**
     * Test iterator on a multi-element list.
     */
    @Test
    public void testMultipleElementsIterator() {
        list.insertLast("A");
        list.insertLast("B");
        list.insertLast("C");

        Iterator<String> iterator = list.iterator();

        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals("B", iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals("C", iterator.next());

        assertFalse(iterator.hasNext());

        // Calling next() after all elements have been traversed should throw NoSuchElementException
        try {
            iterator.next();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Pass
        }
    }

    /**
     * Test iterator's remove() method.
     */
    @Test
    void testIterator_RemoveLastElement() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insertLast(1);
        list.insertLast(2);
        list.insertLast(3);

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();  // Traverse to the last element
        }
        iterator.remove();  // Remove the last element

        // Single assert: Check if the new last element is 2
        assertEquals(2, list.getLast());  // The new last element should be 2
    }

    /**
     * Test calling remove() before next().
     */
    @Test
    public void testRemoveBeforeNext() {
        list.insertLast("A");
        Iterator<String> iterator = list.iterator();

        // Calling remove() before next() should throw IllegalStateException
        try {
            iterator.remove();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            // Pass
        }
    }

    /**
     * Test calling remove() multiple times without next() in between.
     */
    @Test
    public void testRemoveTwiceWithoutNext() {
        list.insertLast("A");
        list.insertLast("B");
        Iterator<String> iterator = list.iterator();

        // Traverse first element
        iterator.next();
        iterator.remove();

        // Calling remove() again without calling next() should throw IllegalStateException
        try {
            iterator.remove();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            // Pass
        }
    }

    /**
     * Test iterator's remove() on the last element in the list.
     */
    @Test
    public void testRemoveLastElement() {
        list.insertLast("A");
        list.insertLast("B");
        list.insertLast("C");

        Iterator<String> iterator = list.iterator();

        // Traverse through the elements
        iterator.next(); // "A"
        iterator.next(); // "B"

        // Remove last element ("C")
        assertEquals("C", iterator.next());
        iterator.remove();
        assertEquals(2, list.size());
        assertEquals("A", list.getFirst());
        assertEquals("B", list.getLast()); // Ensure tail is correctly updated
    }
}
