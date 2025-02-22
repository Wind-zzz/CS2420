package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

public class TestLinkedListStructures {

    private SinglyLinkedList<Integer> list;
    private LinkedListQueue<Integer> queue;
    private SinglyLinkedList<String> strList;
    private LinkedListQueue<String> strQueue;

    @BeforeEach
    void setUp() {
        list = new SinglyLinkedList<>();
        queue = new LinkedListQueue<>();
        strList = new SinglyLinkedList<>();
        strQueue = new LinkedListQueue<>();
    }

    // Tests for SinglyLinkedList
    @Test
    void testSinglyLinkedList_InsertFirstAndLast() {
        list.insertFirst(1);
        list.insertLast(2);
        assertEquals(1, list.getFirst());
        assertEquals(2, list.getLast());
        assertEquals(2, list.size());
    }

    @Test
    void testSinglyLinkedList_DeleteFirst() {
        list.insertFirst(1);
        list.insertLast(2);
        assertEquals(1, list.deleteFirst());
        assertEquals(2, list.getFirst());
    }

    @Test
    void testSinglyLinkedList_DeleteAtIndex() {
        list.insertFirst(1);
        list.insertLast(2);
        list.insertLast(3);
        assertEquals(2, list.delete(1));
        assertEquals(2, list.size());
        assertEquals(1, list.getFirst());
        assertEquals(3, list.getLast());
    }

    @Test
    void testSinglyLinkedList_Clear() {
        list.insertFirst(1);
        list.insertLast(2);
        list.clear();
        assertTrue(list.isEmpty());
        assertThrows(NoSuchElementException.class, () -> list.getFirst());
    }

    @Test
    void testSinglyLinkedList_IndexOf() {
        list.insertFirst(1);
        list.insertLast(2);
        list.insertLast(3);
        assertEquals(0, list.indexOf(1));
        assertEquals(2, list.indexOf(3));
        assertEquals(-1, list.indexOf(5)); // Element not in list
    }

    @Test
    void testSinglyLinkedList_EmptyList() {
        assertTrue(list.isEmpty());
        assertThrows(NoSuchElementException.class, () -> list.getFirst());
        assertThrows(NoSuchElementException.class, () -> list.getLast());
    }

    @Test
    void testSinglyLinkedList_InsertInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.insert(1, 10)); // Invalid index in empty list
        list.insertFirst(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.insert(2, 10)); // Invalid index greater than size
    }

    @Test
    void testSinglyLinkedList_DeleteInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.delete(0)); // No elements in list
        list.insertFirst(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.delete(1)); // Invalid index
    }

    // Tests for LinkedListQueue
    @Test
    void testLinkedListQueue_OfferAndPeek() {
        queue.offer(1);
        queue.offer(2);
        assertEquals(1, queue.peek());
    }

    @Test
    void testLinkedListQueue_Poll() {
        queue.offer(1);
        queue.offer(2);
        assertEquals(1, queue.poll());
        assertEquals(1, queue.size());
        assertEquals(2, queue.peek());
    }

    @Test
    void testLinkedListQueue_Clear() {
        queue.offer(1);
        queue.offer(2);
        queue.clear();
        assertTrue(queue.isEmpty());
        assertThrows(NoSuchElementException.class, () -> queue.peek());
    }

    @Test
    void testLinkedListQueue_PollOnEmptyQueue() {
        assertThrows(NoSuchElementException.class, () -> queue.poll());
    }

    @Test
    void testLinkedListQueue_PeekOnEmptyQueue() {
        assertThrows(NoSuchElementException.class, () -> queue.peek());
    }

    @Test
    void testLinkedListQueue_IsEmpty() {
        assertTrue(queue.isEmpty());
        queue.offer(10);
        assertFalse(queue.isEmpty());
    }

    @Test
    void testLinkedListQueue_Size() {
        assertEquals(0, queue.size());
        queue.offer(1);
        queue.offer(2);
        assertEquals(2, queue.size());
    }
    @Test
    public void testInsertLastAndGet() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insertLast(-9);
        list.insertLast(-1804238318);
        assertEquals(Integer.valueOf(-9), list.get(0));
        assertEquals(Integer.valueOf(-1804238318), list.get(1)); // Check if the second inserted element is correct
    }

    @Test
    void testSinglyLinkedList_InsertFirstAndLast_String() {
        strList.insertFirst("Hello");
        strList.insertLast("World");
        assertEquals("Hello", strList.getFirst());
        assertEquals("World", strList.getLast());
        assertEquals(2, strList.size());
    }

    @Test
    void testSinglyLinkedList_DeleteFirst_String() {
        strList.insertFirst("Hello");
        strList.insertLast("World");
        assertEquals("Hello", strList.deleteFirst());
        assertEquals("World", strList.getFirst());
    }

    @Test
    void testSinglyLinkedList_DeleteAtIndex_String() {
        strList.insertFirst("One");
        strList.insertLast("Two");
        strList.insertLast("Three");
        assertEquals("Two", strList.delete(1));
        assertEquals(2, strList.size());
        assertEquals("One", strList.getFirst());
        assertEquals("Three", strList.getLast());
    }

    @Test
    void testSinglyLinkedList_Clear_String() {
        strList.insertFirst("Hello");
        strList.insertLast("World");
        strList.clear();
        assertTrue(strList.isEmpty());
        assertThrows(NoSuchElementException.class, () -> strList.getFirst());
    }

    @Test
    void testSinglyLinkedList_IndexOf_String() {
        strList.insertFirst("Alpha");
        strList.insertLast("Beta");
        strList.insertLast("Gamma");
        assertEquals(0, strList.indexOf("Alpha"));
        assertEquals(2, strList.indexOf("Gamma"));
        assertEquals(-1, strList.indexOf("Delta")); // Element not in list
    }

    @Test
    void testSinglyLinkedList_EmptyList_String() {
        assertTrue(strList.isEmpty());
        assertThrows(NoSuchElementException.class, () -> strList.getFirst());
        assertThrows(NoSuchElementException.class, () -> strList.getLast());
    }

    // Tests for LinkedListQueue (String)
    @Test
    void testLinkedListQueue_OfferAndPeek_String() {
        strQueue.offer("Hello");
        strQueue.offer("World");
        assertEquals("Hello", strQueue.peek());
    }

    @Test
    void testLinkedListQueue_Poll_String() {
        strQueue.offer("Hello");
        strQueue.offer("World");
        assertEquals("Hello", strQueue.poll());
        assertEquals(1, strQueue.size());
        assertEquals("World", strQueue.peek());
    }

    @Test
    void testLinkedListQueue_Clear_String() {
        strQueue.offer("First");
        strQueue.offer("Second");
        strQueue.clear();
        assertTrue(strQueue.isEmpty());
        assertThrows(NoSuchElementException.class, () -> strQueue.peek());
    }

    @Test
    void testLinkedListQueue_IsEmpty_String() {
        assertTrue(strQueue.isEmpty());
        strQueue.offer("Item");
        assertFalse(strQueue.isEmpty());
    }
}
