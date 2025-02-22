package assign08;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTest {


    private BinarySearchTree<Integer> bst;

    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree<>();
    }

    //Test Add() method

    @Test
    void testAdd() {
        assertTrue(bst.add(10));
        assertTrue(bst.add(5));
        assertTrue(bst.add(15));
        assertFalse(bst.add(10));  // duplicate item
    }

    @Test
    public void testAdd_DuplicateElements() {
        assertTrue(bst.add(5));
        assertFalse(bst.add(5)); // Adding duplicate should return false
        assertEquals(1, bst.size()); // Size should still be 1
    }

    @Test
    public void testAdd_BalancedTree() {
        assertTrue(bst.add(10));
        assertTrue(bst.add(5));
        assertTrue(bst.add(15));
        assertEquals(3, bst.size());
        ArrayList<Integer> expectedOrder = new ArrayList<>();
        expectedOrder.add(5);
        expectedOrder.add(10);
        expectedOrder.add(15);
        assertEquals(expectedOrder, bst.toArrayList());
    }

    @Test
    public void testAdd_LeftHeavyTree() {
        assertTrue(bst.add(10));
        assertTrue(bst.add(8));
        assertTrue(bst.add(6));
        assertTrue(bst.add(4));
        assertEquals(4, bst.size());
        ArrayList<Integer> expectedOrder = new ArrayList<>();
        expectedOrder.add(4);
        expectedOrder.add(6);
        expectedOrder.add(8);
        expectedOrder.add(10);
        assertEquals(expectedOrder, bst.toArrayList());
    }

    @Test
    public void testAdd_RightHeavyTree() {
        assertTrue(bst.add(2));
        assertTrue(bst.add(4));
        assertTrue(bst.add(6));
        assertTrue(bst.add(8));
        assertEquals(4, bst.size());
        ArrayList<Integer> expectedOrder = new ArrayList<>();
        expectedOrder.add(2);
        expectedOrder.add(4);
        expectedOrder.add(6);
        expectedOrder.add(8);
        assertEquals(expectedOrder, bst.toArrayList());
    }

    @Test
    public void testAdd_NegativeZeroPositive() {
        assertTrue(bst.add(-10));
        assertTrue(bst.add(0));
        assertTrue(bst.add(10));
        assertEquals(3, bst.size());
        ArrayList<Integer> expectedOrder = new ArrayList<>();
        expectedOrder.add(-10);
        expectedOrder.add(0);
        expectedOrder.add(10);
        assertEquals(expectedOrder, bst.toArrayList());
    }

    // Test addAll() method

    @Test
    void testAddAll() {
        Collection<Integer> items = Arrays.asList(1, 2, 3, 4, 5);
        assertTrue(bst.addAll(items));  // should return true since all items are new
        assertEquals(5, bst.size());

        Collection<Integer> duplicateItems = Arrays.asList(1, 6, 3);
        assertFalse(bst.addAll(duplicateItems));  // should return false since 1 and 3 are duplicates
        assertEquals(6, bst.size());  // only 6 should be added
    }

    @Test
    public void testAddAll_DistinctElements() {
        List<Integer> elements = Arrays.asList(5, 3, 8, 1, 7);
        assertTrue(bst.addAll(elements));
        assertEquals(5, bst.size());
        ArrayList<Integer> expectedOrder = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 8));
        assertEquals(expectedOrder, bst.toArrayList());
    }

    @Test
    public void testAddAll_WithDuplicates() {
        bst.add(5);
        List<Integer> elements = Arrays.asList(5, 3, 8, 3, 7); // 5 and 3 are duplicates
        assertTrue(bst.addAll(elements));
    }

    @Test
    public void testAddAll_AllElementsAlreadyInTree() {
        bst.addAll(Arrays.asList(1, 3, 5, 7, 9)); // Add initial elements
        List<Integer> elements = Arrays.asList(1, 3, 5, 7, 9); // Same elements
        assertFalse(bst.addAll(elements)); // Adding duplicates should return false
        assertEquals(5, bst.size()); // Size should remain unchanged
        ArrayList<Integer> expectedOrder = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9));
        assertEquals(expectedOrder, bst.toArrayList());
    }

    @Test
    public void testAddAll_LargeCollection() {
        List<Integer> elements = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            elements.add(i);
        }
        assertTrue(bst.addAll(elements));
        assertEquals(1000, bst.size());
        ArrayList<Integer> expectedOrder = new ArrayList<>(elements);
        assertEquals(expectedOrder, bst.toArrayList());
    }

    // Test clear() method

    @Test
    void testClear() {
        bst.add(10);
        bst.add(20);
        bst.add(30);
        assertEquals(3, bst.size());
        bst.clear();
        assertEquals(0, bst.size());
        assertTrue(bst.isEmpty());
    }

    @Test
    public void testClear_EmptyTree() {
        bst.clear();
        assertEquals(0, bst.size());
        assertTrue(bst.isEmpty());
        assertThrows(NoSuchElementException.class, bst::min);
        assertThrows(NoSuchElementException.class, bst::max);
    }

    @Test
    public void testClear_SingleElementTree() {
        bst.add(10);
        bst.clear();
        assertEquals(0, bst.size());
        assertTrue(bst.isEmpty());
        assertThrows(NoSuchElementException.class, bst::min);
        assertThrows(NoSuchElementException.class, bst::max);
    }

    @Test
    public void testClear_MultipleElementsTree() {
        bst.add(10);
        bst.add(5);
        bst.add(15);
        bst.clear();
        assertEquals(0, bst.size());
        assertTrue(bst.isEmpty());
        assertThrows(NoSuchElementException.class, bst::min);
        assertThrows(NoSuchElementException.class, bst::max);
    }

    @Test
    public void testClear_AddAfterClear() {
        bst.add(20);
        bst.add(10);
        bst.add(30);
        bst.clear();
        assertTrue(bst.isEmpty());

        bst.add(40);
        bst.add(25);

        assertEquals(2, bst.size());
        assertFalse(bst.isEmpty());
        assertEquals(25, bst.min());
        assertEquals(40, bst.max());
    }

    @Test
    public void testClear_LargeTree() {
        for (int i = 1; i <= 1000; i++) {
            bst.add(i);
        }
        bst.clear();
        assertEquals(0, bst.size());
        assertTrue(bst.isEmpty());
        assertThrows(NoSuchElementException.class, bst::min);
        assertThrows(NoSuchElementException.class, bst::max);
    }

    // Test contains(0 method

    @Test
    void testContains() {
        bst.add(10);
        bst.add(20);
        bst.add(30);
        assertTrue(bst.contains(10));
        assertTrue(bst.contains(20));
        assertFalse(bst.contains(40));  // item not in tree
    }

    @Test
    public void testContains_EmptyTree() {
        assertFalse(bst.contains(10));
    }

    @Test
    public void testContains_SingleElementPresent() {
        bst.add(10);
        assertTrue(bst.contains(10));
    }

    @Test
    public void testContains_SingleElementNotPresent() {
        bst.add(10);
        assertFalse(bst.contains(5));
    }

    @Test
    public void testContains_MultipleElementsPresent() {
        bst.addAll(Arrays.asList(10, 5, 15));
        assertTrue(bst.contains(10));
        assertTrue(bst.contains(5));
        assertTrue(bst.contains(15));
    }

    @Test
    public void testContains_MultipleElementsNotPresent() {
        bst.addAll(Arrays.asList(10, 5, 15));
        assertFalse(bst.contains(7));
        assertFalse(bst.contains(20));
    }

    //Test containsAll() method

    @Test
    void testContainsAll() {
        bst.add(10);
        bst.add(20);
        bst.add(30);
        Collection<Integer> items = Arrays.asList(10, 20);
        assertTrue(bst.containsAll(items));
        items = Arrays.asList(10, 40);  // 40 not in tree
        assertFalse(bst.containsAll(items));
    }

    @Test
    public void testContainsAll_EmptyTree() {
        assertFalse(bst.containsAll(Arrays.asList(10, 5, 15)));
    }

    @Test
    public void testContainsAll_AllElementsPresent() {
        bst.addAll(Arrays.asList(10, 5, 15));
        assertTrue(bst.containsAll(Arrays.asList(10, 5)));
    }

    @Test
    public void testContainsAll_SomeElementsMissing() {
        bst.addAll(Arrays.asList(10, 5));
        assertFalse(bst.containsAll(Arrays.asList(10, 5, 15)));
    }

    @Test
    public void testContainsAll_SingleElementPresent() {
        bst.add(10);
        assertTrue(bst.containsAll(Arrays.asList(10)));
    }

    @Test
    public void testContainsAll_NoElementsPresent() {
        bst.add(5);
        assertFalse(bst.containsAll(Arrays.asList(10, 15)));
    }

    //Test isEmpty() method

    @Test
    void testIsEmpty() {
        assertTrue(bst.isEmpty());
        bst.add(10);
        assertFalse(bst.isEmpty());
    }

    @Test
    public void testIsEmpty_InitiallyTrue() {
        assertTrue(bst.isEmpty());
    }

    @Test
    public void testIsEmpty_AfterAddingElement() {
        bst.add(10);
        assertFalse(bst.isEmpty());
    }

    @Test
    public void testIsEmpty_AfterRemovingElement() {
        bst.add(10);
        bst.remove(10);
        assertTrue(bst.isEmpty());
    }

    @Test
    public void testIsEmpty_AfterClear() {
        bst.addAll(Arrays.asList(10, 5, 15));
        bst.clear();
        assertTrue(bst.isEmpty());
    }

    @Test
    public void testIsEmpty_WithMultipleElements() {
        bst.addAll(Arrays.asList(10, 5, 15));
        assertFalse(bst.isEmpty());
    }

    //Test min() method

    @Test
    void testMin() {
        assertThrows(NoSuchElementException.class, bst::min);
        bst.add(10);
        bst.add(20);
        bst.add(5);
        assertEquals(5, bst.min());
    }

    @Test
    public void testMin_EmptyTree() {
        assertThrows(NoSuchElementException.class, bst::min);
    }

    @Test
    public void testMin_SingleElement() {
        bst.add(10);
        assertEquals(10, bst.min());
    }

    @Test
    public void testMin_MultipleElements() {
        bst.addAll(Arrays.asList(10, 5, 15));
        assertEquals(5, bst.min());
    }

    @Test
    public void testMin_AfterRemovingMinimum() {
        bst.addAll(Arrays.asList(10, 5, 15));
        bst.remove(5);
        assertEquals(10, bst.min());
    }

    @Test
    public void testMin_WithNegativeElements() {
        bst.addAll(Arrays.asList(-10, -5, -15));
        assertEquals(-15, bst.min());
    }

    //Test max() method

    @Test
    void testMax() {
        assertThrows(NoSuchElementException.class, bst::max);
        bst.add(10);
        bst.add(20);
        bst.add(5);
        assertEquals(20, bst.max());
    }

    @Test
    public void testMax_EmptyTree() {
        assertThrows(NoSuchElementException.class, bst::max);
    }

    @Test
    public void testMax_SingleElement() {
        bst.add(10);
        assertEquals(10, bst.max());
    }

    @Test
    public void testMax_MultipleElements() {
        bst.addAll(Arrays.asList(10, 5, 15));
        assertEquals(15, bst.max());
    }

    @Test
    public void testMax_AfterRemovingMaximum() {
        bst.addAll(Arrays.asList(10, 5, 15));
        bst.remove(15);
        assertEquals(10, bst.max());
    }

    @Test
    public void testMax_WithNegativeElements() {
        bst.addAll(Arrays.asList(-10, -5, -15));
        assertEquals(-5, bst.max());
    }

    //Test remove() method

    @Test
    void testRemove() {
        assertFalse(bst.remove(10));  // nothing to remove from empty tree

        bst.add(10);
        bst.add(5);
        bst.add(20);
        bst.add(15);

        assertTrue(bst.remove(5));  // remove leaf
        assertFalse(bst.contains(5));

        assertTrue(bst.remove(20));  // remove node with one child
        assertFalse(bst.contains(20));

        assertTrue(bst.remove(10));  // remove root node
        assertFalse(bst.contains(10));
    }

    @Test
    public void testRemove_SingleElement() {
        bst.add(10);
        assertTrue(bst.remove(10));
        assertFalse(bst.contains(10));
    }

    @Test
    public void testRemove_ElementNotPresent() {
        bst.add(10);
        assertFalse(bst.remove(5));
    }

    @Test
    public void testRemove_ElementWithOneChild() {
        bst.addAll(Arrays.asList(10, 5, 15));
        assertTrue(bst.remove(5));
        assertFalse(bst.contains(5));
    }

    @Test
    public void testRemove_ElementWithTwoChildren() {
        bst.addAll(Arrays.asList(10, 5, 15));
        assertTrue(bst.remove(10));
        assertFalse(bst.contains(10));
    }

    @Test
    public void testRemove_RootWithTwoChildren() {
        bst.addAll(Arrays.asList(10, 5, 15, 12, 18));
        assertTrue(bst.remove(10));
        assertFalse(bst.contains(10));
    }

    // Test removeAll() method

    @Test
    void testRemoveAll() {
        bst.add(10);
        bst.add(20);
        bst.add(30);
        Collection<Integer> items = Arrays.asList(10, 30);

        assertTrue(bst.removeAll(items));
        assertFalse(bst.contains(10));
        assertFalse(bst.contains(30));
        assertTrue(bst.contains(20));

        items = Arrays.asList(40, 50);  // items not in the tree
        assertFalse(bst.removeAll(items));
    }

    @Test
    public void testRemoveAll_EmptyTree() {
        assertFalse(bst.removeAll(Arrays.asList(10, 5, 15)));
    }

    @Test
    public void testRemoveAll_AllElementsPresent() {
        bst.addAll(Arrays.asList(10, 5, 15));
        bst.removeAll(Arrays.asList(10, 5, 15));
        assertTrue(bst.isEmpty());
    }

    @Test
    public void testRemoveAll_SomeElementsMissing() {
        bst.addAll(Arrays.asList(10, 5));
        assertTrue(bst.removeAll(Arrays.asList(10, 5, 15)));
    }

    @Test
    public void testRemoveAll_SingleElementPresent() {
        bst.add(10);
        assertTrue(bst.removeAll(Arrays.asList(10)));
        assertTrue(bst.isEmpty());
    }

    @Test
    public void testRemoveAll_NoElementsPresent() {
        bst.add(5);
        assertFalse(bst.removeAll(Arrays.asList(10, 15)));
    }

    // Test size() method

    @Test
    void testSize() {
        assertEquals(0, bst.size());
        bst.add(10);
        assertEquals(1, bst.size());
        bst.add(20);
        assertEquals(2, bst.size());
        bst.remove(10);
        assertEquals(1, bst.size());
    }

    @Test
    public void testSize_EmptyTree() {
        assertEquals(0, bst.size());
    }

    @Test
    public void testSize_SingleElement() {
        bst.add(10);
        assertEquals(1, bst.size());
    }

    @Test
    public void testSize_MultipleElements() {
        bst.addAll(Arrays.asList(10, 5, 15));
        assertEquals(3, bst.size());
    }

    @Test
    public void testSize_AfterAddingAndRemoving() {
        bst.addAll(Arrays.asList(10, 5, 15));
        bst.remove(10);
        assertEquals(2, bst.size());
    }

    @Test
    public void testSize_AfterClear() {
        bst.addAll(Arrays.asList(10, 5, 15));
        bst.clear();
        assertEquals(0, bst.size());
    }

    //Test toArrayList() method

    @Test
    void testToArrayList() {
        bst.add(10);
        bst.add(5);
        bst.add(20);
        bst.add(15);

        ArrayList<Integer> expectedList = new ArrayList<>(Arrays.asList(5, 10, 15, 20));
        assertEquals(expectedList, bst.toArrayList());
    }

    @Test
    public void testToArrayList_EmptyTree() {
        assertTrue(bst.toArrayList().isEmpty());
    }

    @Test
    public void testToArrayList_SingleElement() {
        bst.add(10);
        assertEquals(Arrays.asList(10), bst.toArrayList());
    }

    @Test
    public void testToArrayList_MultipleElements() {
        bst.addAll(Arrays.asList(10, 5, 15));
        assertEquals(Arrays.asList(5, 10, 15), bst.toArrayList());
    }

    @Test
    public void testToArrayList_WithDuplicates() {
        bst.addAll(Arrays.asList(10, 5, 15, 10));
        assertEquals(Arrays.asList(5, 10, 15), bst.toArrayList());
    }

    @Test
    public void testToArrayList_LargeTree() {
        for (int i = 1; i <= 1000; i++) {
            bst.add(i);
        }
        ArrayList<Integer> result = bst.toArrayList();
        assertEquals(1000, result.size());
        assertEquals(1, result.get(0));
        assertEquals(1000, result.get(result.size() - 1));
    }
}
