package assign08;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortedSetTest {

	SortedSet<Integer> set;
	SortedSet<Integer> biSet;
	ArrayList<Integer> list;
	@BeforeEach
	void setUp() {
		set = new ArraySortedSet<>();
		biSet = new BinarySearchTree<>();
		list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
	}
	@Test
	void testEmpty() {
		assertTrue(set.isEmpty());
		assertEquals(0, set.size());
		assertFalse(set.contains(1));
		assertFalse(set.containsAll(list));
		assertThrows(UnsupportedOperationException.class, () -> set.remove(1));
		assertThrows(UnsupportedOperationException.class, () -> set.removeAll(list));
		assertThrows(NoSuchElementException.class, () -> set.min());
		assertThrows(NoSuchElementException.class, () -> set.max());
		assertTrue(set.add(1));
		assertFalse(set.add(1));
		ArrayList<Integer> expected = new ArrayList<>();
		expected.add(1);
		assertEquals(expected, set.toArrayList());
		
	}
	
	@Test
	void testNormal() {
		ArrayList<Integer> secondList = new ArrayList<>();
		secondList.add(10);
		secondList.add(20);
		secondList.add(30);
		secondList.add(-10);
		secondList.add(5);
		assertTrue(set.add(10));
		assertTrue(set.add(20));
		assertTrue(set.add(30));
		assertTrue(set.add(-10));
		assertTrue(set.add(5));
	
		assertFalse(set.isEmpty());
		assertEquals(5, set.size());
		assertFalse(set.contains(1));
		assertFalse(set.containsAll(list));
		assertTrue(set.containsAll(secondList));
		assertThrows(UnsupportedOperationException.class, () -> set.remove(1));
		assertThrows(UnsupportedOperationException.class, () -> set.removeAll(list));
		assertEquals(-10 , set.min());
		assertEquals(30, set.max());
		assertTrue(set.add(1));
		assertFalse(set.add(1));
		ArrayList<Integer> expected = new ArrayList<>();
		expected.add(-10);
		expected.add(1);
		expected.add(5);
		expected.add(10);
		expected.add(20);
		expected.add(30);
		assertEquals(expected, set.toArrayList());
	}
	
	@Test
	void testRemove() {
		SortedSet<Integer> empty = new BinarySearchTree<>();
		assertFalse(empty.remove(1));
		assertFalse(empty.removeAll(list));
		
		empty.add(1);
		assertTrue(empty.remove(1));
		assertTrue(empty.isEmpty());
		assertFalse(empty.contains(1));
		
		assertTrue(empty.add(1));
		assertTrue(empty.add(2));
		assertTrue(empty.add(3));
		assertTrue(empty.add(4));
		assertEquals(4, empty.max());
		assertTrue(empty.remove(4));
		assertEquals(3, empty.max());
		assertTrue(empty.contains(1));
		assertTrue(empty.contains(2));
		assertTrue(empty.contains(3));
		
		
		empty.clear();
		assertTrue(empty.add(1));
		assertTrue(empty.add(2));
		assertTrue(empty.add(3));
		assertTrue(empty.add(4));
		assertTrue(empty.remove(2));
		assertFalse(empty.contains(2));
		assertEquals(empty.size(), 3);
		assertTrue(empty.contains(1));
		assertTrue(empty.contains(3));
		assertTrue(empty.contains(4));
		
		empty.clear();
		assertTrue(empty.add(1));
		assertTrue(empty.add(2));
		assertTrue(empty.add(4));
		assertTrue(empty.add(5));
		assertTrue(empty.add(3));
		assertTrue(empty.remove(4));
		assertFalse(empty.contains(4));
		assertTrue(empty.contains(1));
		assertTrue(empty.contains(2));
		assertTrue(empty.contains(3));
		assertTrue(empty.contains(5));
	}
	
	@Test
	void testToArray() {
		ArrayList<Integer> expected = new ArrayList<>();
		expected.add(1);
		expected.add(5);
		expected.add(6);
		biSet.add(5);
		biSet.add(1);
		biSet.add(6);
		assertEquals(expected, biSet.toArrayList());
	}
	
	@Test
	void testIsEmpty() {
		biSet.add(2);
		biSet.add(1);
		assertFalse(biSet.isEmpty());
		biSet.remove(1);
		biSet.remove(2);
		assertTrue(biSet.isEmpty());
	}
	
	@Test
    void test() {
        SortedSet<String> set = new BinarySearchTree<>();
        set.add("1");
        set.add("2");
        set.remove("1");
        set.remove("2");
    }
}
