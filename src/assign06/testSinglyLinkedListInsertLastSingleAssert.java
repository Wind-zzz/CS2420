package assign06;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class testSinglyLinkedListInsertLastSingleAssert {

    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

    @Test
    void testInsertLast_EmptyList_FirstElement() {
        list.insertLast(1);
        assertEquals(1, list.getFirst());  // Check the first element is 1
    }

    @Test
    void testInsertLast_EmptyList_LastElement() {
        list.insertLast(1);
        assertEquals(1, list.getLast());   // Check the last element is 1
    }

    @Test
    void testInsertLast_EmptyList_Size() {
        list.insertLast(1);
        assertEquals(1, list.size());      // Check the size is 1
    }

    @Test
    void testInsertLast_OneElementList_FirstElement() {
        list.insertLast(1);
        list.insertLast(2);
        assertEquals(1, list.getFirst());  // First element should still be 1
    }

    @Test
    void testInsertLast_OneElementList_LastElement() {
        list.insertLast(1);
        list.insertLast(2);
        assertEquals(2, list.getLast());   // Last element should be 2
    }

    @Test
    void testInsertLast_OneElementList_Size() {
        list.insertLast(1);
        list.insertLast(2);
        assertEquals(2, list.size());      // Size should be 2
    }

    @Test
    void testInsertLast_MultipleElements_FirstElement() {
        list.insertLast(1);
        list.insertLast(2);
        list.insertLast(3);
        list.insertLast(4);
        assertEquals(1, list.getFirst());  // First element should be 1
    }

    @Test
    void testInsertLast_MultipleElements_LastElement() {
        list.insertLast(1);
        list.insertLast(2);
        list.insertLast(3);
        list.insertLast(4);
        assertEquals(4, list.getLast());   // Last element should be 4
    }

    @Test
    void testInsertLast_MultipleElements_Size() {
        list.insertLast(1);
        list.insertLast(2);
        list.insertLast(3);
        list.insertLast(4);
        assertEquals(4, list.size());      // Size should be 4
    }

    @Test
    void testInsertLast_NullValue_FirstElement() {
        SinglyLinkedList<Integer> listWithNull = new SinglyLinkedList<>();
        listWithNull.insertLast(null);
        assertNull(listWithNull.getFirst());   // First element should be null
    }

    @Test
    void testInsertLast_NullValue_LastElement() {
        SinglyLinkedList<Integer> listWithNull = new SinglyLinkedList<>();
        listWithNull.insertLast(null);
        assertNull(listWithNull.getLast());    // Last element should be null
    }

    @Test
    void testInsertLast_NullValue_Size() {
        SinglyLinkedList<Integer> listWithNull = new SinglyLinkedList<>();
        listWithNull.insertLast(null);
        assertEquals(1, listWithNull.size());  // Size should be 1
    }

    @Test
    void testInsertLast_AfterClearingList_SizeAfterClearing() {
        list.insertLast(1);
        list.insertLast(2);
        list.clear();                         // Clear the list
        assertEquals(0, list.size());         // Size should be 0
    }

    @Test
    void testInsertLast_AfterClearingList_FirstElement() {
        list.insertLast(1);
        list.insertLast(2);
        list.clear();                         // Clear the list
        list.insertLast(3);                   // Insert after clearing
        assertEquals(3, list.getFirst());     // First element should be 3
    }

    @Test
    void testInsertLast_AfterClearingList_LastElement() {
        list.insertLast(1);
        list.insertLast(2);
        // Clear the list
        list.insertLast(3);                   // Insert after clearing
        assertEquals(3, list.getLast());      // Last element should be 3
    }

    @Test
    void testInsertLast_AfterClearingList_SizeAfterInsert() {
        list.insertLast(1);
        list.insertLast(2);
        list.clear();                         // Clear the list
        list.insertLast(3);                   // Insert after clearing
        assertEquals(1, list.size());         // Size should be 1
    }

    @Test
    void testInsertLast_LargeNumberOfElements_FirstElement() {
        for (int i = 0; i < 1000; i++) {
            list.insertLast(i);
        }
        assertEquals(0, list.getFirst());     // First element should be 0
    }

    @Test
    void testInsertLast_LargeNumberOfElements_LastElement() {
        for (int i = 0; i < 1000; i++) {
            list.insertLast(i);
        }
        assertEquals(999, list.getLast());    // Last element should be 999
    }

    @Test
    void testInsertLast_LargeNumberOfElements_Size() {
        for (int i = 0; i < 1000; i++) {
            list.insertLast(i);
        }
        assertEquals(1000, list.size());      // Size should be 1000
    }

    @Test
    void testInsertLast_AlternatingInserts_FirstElement() {
        list.insertFirst(1);                   // Insert 1 at the front
        list.insertLast(2);                    // Insert 2 at the end
        list.insertFirst(0);                   // Insert 0 at the front
        assertEquals(0, list.getFirst());      // First element should be 0
    }

    @Test
    void testInsertLast_AlternatingInserts_LastElement() {
        list.insertFirst(1);                   // Insert 1 at the front
        list.insertLast(2);                    // Insert 2 at the end
        list.insertFirst(0);                   // Insert 0 at the front
        list.insertLast(3);                    // Insert 3 at the end
        assertEquals(3, list.getLast());       // Last element should be 3
    }

    @Test
    void testInsertLast_AlternatingInserts_Size() {
        list.insertFirst(1);                   // Insert 1 at the front
        list.insertLast(2);                    // Insert 2 at the end
        list.insertFirst(0);                   // Insert 0 at the front
        list.insertLast(3);                    // Insert 3 at the end
        assertEquals(4, list.size());          // Size should be 4
    }
}
