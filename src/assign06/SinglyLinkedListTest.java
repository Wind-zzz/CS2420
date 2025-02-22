package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SinglyLinkedListTest {

    SinglyLinkedList<Integer> single;
    SinglyLinkedList<Integer> three;
    SinglyLinkedList<Integer> empty;
    SinglyLinkedList<Integer> five;

    @BeforeEach
    void setup() {
        single = new SinglyLinkedList<>();
        three = new SinglyLinkedList<>();
        empty = new SinglyLinkedList<>();
        five = new SinglyLinkedList<>();

        single.insertFirst(5);

        three.insertLast(1);
        three.insertLast(7);
        three.insertLast(5);

        five.insertLast(5);
        five.insertLast(3);
        five.insertLast(2);
        five.insertLast(1);
        five.insertLast(4);
    }

    @Test
    void testClear() {
        single.clear();
        three.clear();
        empty.clear();
        five.clear();

        assertEquals(0, three.size());
        assertThrows(IndexOutOfBoundsException.class, () -> three.get(0));
        assertArrayEquals(new Object[0], three.toArray());
        assertTrue(three.isEmpty());

        assertEquals(0, single.size());
        assertThrows(IndexOutOfBoundsException.class, () -> single.get(0));
        assertArrayEquals(new Object[0], single.toArray());
        assertTrue(single.isEmpty());

        assertEquals(0, empty.size());
        assertThrows(IndexOutOfBoundsException.class, () -> empty.get(0));
        assertArrayEquals(new Object[0], empty.toArray());
        assertTrue(empty.isEmpty());

        assertEquals(0, five.size());
        assertThrows(IndexOutOfBoundsException.class, () -> five.get(0));
        assertArrayEquals(new Object[0], five.toArray());
        assertTrue(five.isEmpty());
    }

    @Test
    void testSize() {
        assertEquals(single.size(), 1);
        assertEquals(three.size(), 3);
        assertEquals(five.size(), 5);
        assertEquals(empty.size(), 0);
    }
    @Test
    void testDeleteIndexZero() {
        assertEquals(5,single.delete(0));
        assertEquals(1,three.delete(0));
        assertEquals(5,five.delete(0));

        assertThrows(IndexOutOfBoundsException.class, () -> empty.delete(0));

        assertTrue(single.isEmpty());

        assertFalse(three.isEmpty());
        assertFalse(five.isEmpty());

        Object[] expected = new Object[2];
        expected[0] = (Integer)7;
        expected[1] = (Integer)5;

        assertEquals(three.size(), 2);
        assertArrayEquals(three.toArray(), expected);

        expected = new Object[4];
        expected[0] = (Integer)3;
        expected[1] = (Integer)2;
        expected[2] = (Integer)1;
        expected[3] = (Integer)4;

        assertEquals(five.size(), 4);
        assertArrayEquals(five.toArray(), expected);
    }

    @Test
    void testDeleteIndexMid() {
        assertEquals(7, three.delete(1));
        assertEquals(2, five.delete(2));
        assertFalse(three.isEmpty());
        assertFalse(five.isEmpty());

        Object[] expected = new Object[2];
        expected[0] = (Integer)1;
        expected[1] = (Integer)5;

        assertEquals(three.size(), 2);
        assertArrayEquals(three.toArray(), expected);

        expected = new Object[4];
        expected[0] = (Integer)5;
        expected[1] = (Integer)3;
        expected[2] = (Integer)1;
        expected[3] = (Integer)4;

        assertEquals(five.size(), 4);
        assertArrayEquals(five.toArray(), expected);
    }

    @Test
    void testDeleteIndexLast() {
        assertEquals(5,three.delete(2));
        assertEquals(4,five.delete(4));
        assertFalse(three.isEmpty());
        assertFalse(five.isEmpty());

        Object[] expected = new Object[2];
        expected[0] = (Integer)1;
        expected[1] = (Integer)7;

        assertEquals(three.size(), 2);
        assertArrayEquals(three.toArray(), expected);

        expected = new Object[4];
        expected[0] = (Integer)5;
        expected[1] = (Integer)3;
        expected[2] = (Integer)2;
        expected[3] = (Integer)1;

        assertEquals(five.size(), 4);
        assertArrayEquals(five.toArray(), expected);
    }

    @Test
    void testDeleteException() {
        assertThrows(IndexOutOfBoundsException.class, () -> single.delete(1));
        assertThrows(IndexOutOfBoundsException.class, () -> three.delete(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> five.delete(5));
        assertThrows(IndexOutOfBoundsException.class, () -> empty.delete(1));
    }

    @Test
    void testDeleteFirst() {
        assertEquals(5,single.deleteFirst());
        assertEquals(1,three.deleteFirst());
        assertEquals(5,five.deleteFirst());

        assertThrows(NoSuchElementException.class, () -> empty.deleteFirst());

        assertTrue(single.isEmpty());

        assertFalse(three.isEmpty());
        assertFalse(five.isEmpty());

        Object[] expected = new Object[2];
        expected[0] = (Integer)7;
        expected[1] = (Integer)5;

        assertEquals(three.size(), 2);
        assertArrayEquals(three.toArray(), expected);

        expected = new Object[4];
        expected[0] = (Integer)3;
        expected[1] = (Integer)2;
        expected[2] = (Integer)1;
        expected[3] = (Integer)4;

        assertEquals(five.size(), 4);
        assertArrayEquals(five.toArray(), expected);
    }

    @Test
    void testDeleteFirstMultiple() {
        assertEquals(1,three.deleteFirst());
        assertEquals(7,three.deleteFirst());
        assertEquals(5,three.deleteFirst());
        assertEquals(5,five.deleteFirst());
        assertEquals(3,five.deleteFirst());

        assertTrue(three.isEmpty());
        assertFalse(five.isEmpty());

        Object[] expected = new Object[0];

        assertEquals(three.size(), 0);
        assertArrayEquals(three.toArray(), expected);

        expected = new Object[3];
        expected[0] = (Integer)2;
        expected[1] = (Integer)1;
        expected[2] = (Integer)4;

        assertEquals(five.size(), 3);
        assertArrayEquals(five.toArray(), expected);
    }

    @Test
    void testDeleteMultiple() {
        assertEquals(1,three.delete(0));
        assertEquals(7,three.delete(0));
        assertEquals(5,three.delete(0));
        assertEquals(5,five.delete(0));
        assertEquals(3,five.delete(0));

        assertTrue(three.isEmpty());
        assertFalse(five.isEmpty());

        Object[] expected = new Object[0];

        assertEquals(three.size(), 0);
        assertArrayEquals(three.toArray(), expected);

        expected = new Object[3];
        expected[0] = (Integer)2;
        expected[1] = (Integer)1;
        expected[2] = (Integer)4;

        assertEquals(five.size(), 3);
        assertArrayEquals(five.toArray(), expected);
    }

    @Test
    void testDeleteLastMultiple() {
        assertEquals(5,three.delete(2));
        assertEquals(7,three.delete(1));
        assertEquals(1,three.delete(0));
        assertEquals(4,five.delete(4));
        assertEquals(1,five.delete(3));

        assertTrue(three.isEmpty());
        assertFalse(five.isEmpty());

        Object[] expected = new Object[0];

        assertEquals(three.size(), 0);
        assertArrayEquals(three.toArray(), expected);

        expected = new Object[3];
        expected[0] = (Integer)5;
        expected[1] = (Integer)3;
        expected[2] = (Integer)2;

        assertEquals(five.size(), 3);
        assertArrayEquals(five.toArray(), expected);
    }
    @Test
    void testGetIndexZero() {
        assertEquals(5,single.get(0));
        assertEquals(1,three.get(0));
        assertEquals(5,five.get(0));

        assertThrows(IndexOutOfBoundsException.class, () -> empty.get(0));

        assertFalse(single.isEmpty());
        assertFalse(three.isEmpty());
        assertFalse(five.isEmpty());

        Object[] expected = new Object[3];
        expected[0] = (Integer)1;
        expected[1] = (Integer)7;
        expected[2] = (Integer)5;

        assertEquals(three.size(), 3);
        assertArrayEquals(three.toArray(), expected);

        expected = new Object[5];
        expected[0] = (Integer)5;
        expected[1] = (Integer)3;
        expected[2] = (Integer)2;
        expected[3] = (Integer)1;
        expected[4] = (Integer)4;

        assertEquals(five.size(), 5);
        assertArrayEquals(five.toArray(), expected);
    }

    @Test
    void testGetIndexMid() {
        assertEquals(7,three.get(1));
        assertEquals(2,five.get(2));

        assertFalse(three.isEmpty());
        assertFalse(five.isEmpty());

        Object[] expected = new Object[3];
        expected[0] = (Integer)1;
        expected[1] = (Integer)7;
        expected[2] = (Integer)5;

        assertEquals(three.size(), 3);
        assertArrayEquals(three.toArray(), expected);

        expected = new Object[5];
        expected[0] = (Integer)5;
        expected[1] = (Integer)3;
        expected[2] = (Integer)2;
        expected[3] = (Integer)1;
        expected[4] = (Integer)4;

        assertEquals(five.size(), 5);
        assertArrayEquals(five.toArray(), expected);
    }

    @Test
    void testGetIndexLast() {
        assertEquals(5,three.get(2));
        assertEquals(4,five.get(4));

        assertFalse(three.isEmpty());
        assertFalse(five.isEmpty());

        Object[] expected = new Object[3];
        expected[0] = (Integer)1;
        expected[1] = (Integer)7;
        expected[2] = (Integer)5;

        assertEquals(three.size(), 3);
        assertArrayEquals(three.toArray(), expected);

        expected = new Object[5];
        expected[0] = (Integer)5;
        expected[1] = (Integer)3;
        expected[2] = (Integer)2;
        expected[3] = (Integer)1;
        expected[4] = (Integer)4;

        assertEquals(five.size(), 5);
        assertArrayEquals(five.toArray(), expected);
    }

    @Test
    void testGetException() {
        assertThrows(IndexOutOfBoundsException.class, () -> single.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> three.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> five.get(5));
        assertThrows(IndexOutOfBoundsException.class, () -> empty.get(1));
    }

    @Test
    void testGetFirst() {
        assertEquals(5,single.getFirst());
        assertEquals(1,three.getFirst());
        assertEquals(5,five.getFirst());

        assertThrows(NoSuchElementException.class, () -> empty.getFirst());

        assertFalse(single.isEmpty());
        assertFalse(three.isEmpty());
        assertFalse(five.isEmpty());

        Object[] expected = new Object[3];
        expected[0] = (Integer)1;
        expected[1] = (Integer)7;
        expected[2] = (Integer)5;

        assertEquals(three.size(), 3);
        assertArrayEquals(three.toArray(), expected);

        expected = new Object[5];
        expected[0] = (Integer)5;
        expected[1] = (Integer)3;
        expected[2] = (Integer)2;
        expected[3] = (Integer)1;
        expected[4] = (Integer)4;

        assertEquals(five.size(), 5);
        assertArrayEquals(five.toArray(), expected);
    }

    @Test
    void testGetLast() {
        assertEquals(5,single.getLast());
        assertEquals(5,three.getLast());
        assertEquals(4,five.getLast());

        assertThrows(NoSuchElementException.class, () -> empty.getLast());

        assertFalse(single.isEmpty());
        assertFalse(three.isEmpty());
        assertFalse(five.isEmpty());

        Object[] expected = new Object[3];
        expected[0] = (Integer)1;
        expected[1] = (Integer)7;
        expected[2] = (Integer)5;

        assertEquals(three.size(), 3);
        assertArrayEquals(three.toArray(), expected);

        expected = new Object[5];
        expected[0] = (Integer)5;
        expected[1] = (Integer)3;
        expected[2] = (Integer)2;
        expected[3] = (Integer)1;
        expected[4] = (Integer)4;

        assertEquals(five.size(), 5);
        assertArrayEquals(five.toArray(), expected);
    }

    @Test
    void getIndexOfFirst() {
        assertEquals(0, single.indexOf(5));
        assertEquals(0, five.indexOf(5));
        assertEquals(0, three.indexOf(1));
    }

    @Test
    void getIndexOfMid() {
        assertEquals(3, five.indexOf(1));
        assertEquals(1, three.indexOf(7));
    }

    @Test
    void getIndexOfLast() {
        assertEquals(4, five.indexOf(4));
        assertEquals(2, three.indexOf(5));
    }

    @Test
    void getIndexOfNone() {
        assertEquals(-1, five.indexOf(6));
        assertEquals(-1, three.indexOf(4));
        assertEquals(-1, single.indexOf(4));
        assertEquals(-1, empty.indexOf(5));
    }

    @Test
    void getIndexOfWithDuplicates() {
        five.insert(0, 4);
        assertEquals(0, five.indexOf(4));

        three.insert(3, 5);
        assertEquals(2, three.indexOf(5));
    }

    @Test
    void getIndexOfAfterDeletion() {
        five.delete(1);
        three.delete(0);
        assertEquals(1, five.indexOf(2));
        assertEquals(0, three.indexOf(7));
        assertEquals(-1, five.indexOf(3));
        assertEquals(-1, three.indexOf(1));
    }

    @Test
    void testInsertIndexZero() {
        empty.insert(0, 5);
        single.insert(0, 8);
        three.insert(0, 9);
        five.insert(0, 10);

        assertFalse(empty.isEmpty());
        assertFalse(single.isEmpty());
        assertFalse(three.isEmpty());
        assertFalse(five.isEmpty());

        assertEquals(1, empty.size());
        assertEquals(6, five.size());
        assertEquals(4, three.size());
        assertEquals(2, single.size());

        Object[] expected = {5};
        assertArrayEquals(expected, empty.toArray());
        Object[] expected1 = {8,5};
        assertArrayEquals(expected1, single.toArray());
        Object[] expected2 = {9,1,7,5};
        assertArrayEquals(expected2, three.toArray());
        Object[] expected3 = {10,5,3,2,1,4};
        assertArrayEquals(expected3, five.toArray());
    }

    @Test
    void testInsertException() {
        assertThrows(IndexOutOfBoundsException.class, () -> empty.insert(-1, 5));
        assertThrows(IndexOutOfBoundsException.class, () -> single.insert(-1, 5));
        assertThrows(IndexOutOfBoundsException.class, () -> three.insert(-1, 5));
        assertThrows(IndexOutOfBoundsException.class, () -> five.insert(-1, 5));

        assertThrows(IndexOutOfBoundsException.class, () -> empty.insert(1, 5));
        assertThrows(IndexOutOfBoundsException.class, () -> three.insert(4, 5));
        assertThrows(IndexOutOfBoundsException.class, () -> five.insert(6, 5));
        assertThrows(IndexOutOfBoundsException.class, () -> single.insert(2, 5));
    }
    @Test
    void testInsertIndexMid() {
        three.insert(1, 9);
        five.insert(3, 10);

        assertFalse(three.isEmpty());
        assertFalse(five.isEmpty());

        assertEquals(4, three.size());
        assertEquals(6, five.size());

        Object[] expected2 = {1,9,7,5};
        assertArrayEquals(expected2, three.toArray());
        Object[] expected3 = {5,3,2,10,1,4};
        assertArrayEquals(expected3, five.toArray());

        assertEquals(three.getLast(), 5);
        assertEquals(five.getLast(), 4);
    }

    @Test
    void testInsertIndexNearLast() {
        three.insert(2, 9);
        five.insert(4, 10);

        assertFalse(three.isEmpty());
        assertFalse(five.isEmpty());

        assertEquals(4, three.size());
        assertEquals(6, five.size());

        Object[] expected2 = {1,7,9,5};
        assertArrayEquals(expected2, three.toArray());
        Object[] expected3 = {5,3,2,1,10,4};
        assertArrayEquals(expected3, five.toArray());

        assertEquals(three.getLast(), 5);
        assertEquals(five.getLast(), 4);
    }

    @Test
    void testInsertIndexLast() {
        three.insert(3, 9);
        five.insert(5, 10);

        assertFalse(three.isEmpty());
        assertFalse(five.isEmpty());

        assertEquals(4, three.size());
        assertEquals(6, five.size());

        Object[] expected2 = {1,7,5,9};
        assertArrayEquals(expected2, three.toArray());
        Object[] expected3 = {5,3,2,1,4,10};
        assertArrayEquals(expected3, five.toArray());

        assertEquals(three.getLast(), 9);
        assertEquals(five.getLast(), 10);
    }

    @Test
    void testInsertIndexLastMultiple() {
        three.insert(3, 9);
        three.insert(4, 10);
        three.insert(5, 11);
        five.insert(5, 10);
        five.insert(6, 11);

        assertFalse(three.isEmpty());
        assertFalse(five.isEmpty());

        assertEquals(6, three.size());
        assertEquals(7, five.size());

        Object[] expected2 = {1,7,5,9,10,11};
        assertArrayEquals(expected2, three.toArray());
        Object[] expected3 = {5,3,2,1,4,10,11};
        assertArrayEquals(expected3, five.toArray());

        assertEquals(three.getLast(), 11);
        assertEquals(five.getLast(), 11);
    }

    @Test
    void testInsertFirst() {
        empty.insertFirst(5);
        single.insertFirst(8);
        three.insertFirst(9);
        five.insertFirst(10);

        assertFalse(empty.isEmpty());
        assertFalse(single.isEmpty());
        assertFalse(three.isEmpty());
        assertFalse(five.isEmpty());

        assertEquals(1, empty.size());
        assertEquals(6, five.size());
        assertEquals(4, three.size());
        assertEquals(2, single.size());

        Object[] expected = {5};
        assertArrayEquals(expected, empty.toArray());
        Object[] expected1 = {8,5};
        assertArrayEquals(expected1, single.toArray());
        Object[] expected2 = {9,1,7,5};
        assertArrayEquals(expected2, three.toArray());
        Object[] expected3 = {10,5,3,2,1,4};
        assertArrayEquals(expected3, five.toArray());
    }

    @Test
    void testInsertLast() {
        empty.insertLast(5);
        single.insertLast(8);
        three.insertLast(9);
        five.insertLast(10);

        assertFalse(empty.isEmpty());
        assertFalse(single.isEmpty());
        assertFalse(three.isEmpty());
        assertFalse(five.isEmpty());

        assertEquals(1, empty.size());
        assertEquals(6, five.size());
        assertEquals(4, three.size());
        assertEquals(2, single.size());

        Object[] expected = {5};
        assertArrayEquals(expected, empty.toArray());
        Object[] expected1 = {5,8};
        assertArrayEquals(expected1, single.toArray());
        Object[] expected2 = {1,7,5,9};
        assertArrayEquals(expected2, three.toArray());
        Object[] expected3 = {5,3,2,1,4,10};
        assertArrayEquals(expected3, five.toArray());
    }

    @Test
    void testInsertLastMultiple() {
        empty.insertLast(5);
        single.insertLast(8);
        three.insertLast(9);
        five.insertLast(10);

        empty.insertLast(5);
        single.insertLast(8);
        three.insertLast(9);
        five.insertLast(10);

        empty.insertLast(6);
        single.insertLast(9);
        three.insertLast(10);
        five.insertLast(12);

        assertFalse(empty.isEmpty());
        assertFalse(single.isEmpty());
        assertFalse(three.isEmpty());
        assertFalse(five.isEmpty());

        assertEquals(3, empty.size());
        assertEquals(8, five.size());
        assertEquals(6, three.size());
        assertEquals(4, single.size());

        Object[] expected = {5,5,6};
        assertArrayEquals(expected, empty.toArray());
        Object[] expected1 = {5,8,8,9};
        assertArrayEquals(expected1, single.toArray());
        Object[] expected2 = {1,7,5,9,9,10};
        assertArrayEquals(expected2, three.toArray());
        Object[] expected3 = {5,3,2,1,4,10,10,12};
        assertArrayEquals(expected3, five.toArray());

        assertEquals(three.getLast(), 10);
        assertEquals(five.getLast(), 12);
        assertEquals(single.getLast(), 9);
        assertEquals(empty.getLast(), 6);
    }

    @Test
    void testIsEmpty() {
        assertTrue(empty.isEmpty());
        assertFalse(single.isEmpty());
    }

    @Test
    void testIterator() {
        Iterator<Integer> e = empty.iterator();
        Iterator<Integer> s = single.iterator();
        Iterator<Integer> t = three.iterator();
        Iterator<Integer> f = five.iterator();

        assertFalse(e.hasNext());

        assertTrue(s.hasNext());
        assertTrue(t.hasNext());
        assertTrue(f.hasNext());

        assertEquals(5, s.next());
        assertFalse(s.hasNext());
        assertThrows(NoSuchElementException.class, () -> s.next());

        assertEquals(1, t.next());
        assertTrue(t.hasNext());

        assertEquals(7, t.next());
        assertTrue(t.hasNext());

        assertEquals(5, t.next());
        assertFalse(t.hasNext());
        assertThrows(NoSuchElementException.class, () -> t.next());
    }

    @Test
    void testIteratorRemove() {
        Iterator<Integer> e = empty.iterator();
        Iterator<Integer> s = single.iterator();
        Iterator<Integer> t = three.iterator();
        Iterator<Integer> f = five.iterator();
        assertThrows(IllegalStateException.class, () -> s.remove());

        assertEquals(5, s.next());
        s.remove();
        assertThrows(IllegalStateException.class, () -> s.remove());
        assertThrows(IllegalStateException.class, () -> e.remove());

        assertEquals(1, t.next());
        assertTrue(t.hasNext());
        t.remove();
        assertThrows(IllegalStateException.class, () -> t.remove());
        assertEquals(7, three.getFirst());
        assertEquals(7, t.next());
        t.remove();
        assertEquals(5, t.next());
        assertEquals(5, three.getFirst());
        assertEquals(5, three.getLast());
        t.remove();
        assertThrows(NoSuchElementException.class, () -> three.getFirst());
        assertThrows(NoSuchElementException.class, () -> three.getLast());
    }
}
