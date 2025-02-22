package assign05;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SorterTest {

	ArrayList<Integer> list;
	MergeSorter<Integer> mergeSorter;
	QuickSorter<Integer> quickSorter;
	PivotChooser<Integer> first;
	PivotChooser<Integer> rng;
	PivotChooser<Integer> med;
	
	@BeforeEach
	void setup() {
		list = new ArrayList<Integer>(Arrays.asList(1, 5, 3, 9, 7, 6, 10, 1, 2));
		mergeSorter = new MergeSorter<>(1);
		first = new FirstPivotChooser<>();
		rng = new RandomPivotChooser<>();
		med = new MedianOfThreePivotChooser<>();
		quickSorter = new QuickSorter<>(med);
	}
	
	@Test
	void testMergeSortNormal() {
		mergeSorter.sort(list);
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 1, 2, 3, 5, 6, 7, 9, 10));
		assertEquals(list, expected);
	}

	@Test
	void testMergeSortSingle() {
		list = new ArrayList<Integer>(Arrays.asList(1));
		mergeSorter.sort(list);
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1));
		assertEquals(list, expected);
	}
	
	@Test
	void testMergeSortEmpty() {
		list = new ArrayList<Integer>();
		mergeSorter.sort(list);
		ArrayList<Integer> expected = new ArrayList<Integer>();
		assertEquals(list, expected);
	}
	
	@Test
	void testMergeSortAsc() {
		list = new ArrayList<Integer>(Arrays.asList(1, 1, 2, 3, 5, 6, 7, 9, 10));
		mergeSorter.sort(list);
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 1, 2, 3, 5, 6, 7, 9, 10));
		assertEquals(list, expected);
		
	}
	@Test
	void testMergeSortDesc() {
		new ArrayList<Integer>(Arrays.asList(10, 9, 7, 6, 5, 3, 2, 1, 1));
		mergeSorter.sort(list);
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 1, 2, 3, 5, 6, 7, 9, 10));
		assertEquals(expected, list);
	}
	
	@Test
	void testQuickSortNormal() {
		quickSorter.sort(list);
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 1, 2, 3, 5, 6, 7, 9, 10));
		assertEquals(list, expected);
	}

	@Test
	void testQuickSortSingle() {
		list = new ArrayList<Integer>(Arrays.asList(1));
		quickSorter.sort(list);
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1));
		assertEquals(list, expected);
	}
	
	@Test
	void testQuickSortEmpty() {
		list = new ArrayList<Integer>();
		quickSorter.sort(list);
		ArrayList<Integer> expected = new ArrayList<Integer>();
		assertEquals(list, expected);
	}
	
	@Test
	void testQuickSortAsc() {
		list = new ArrayList<Integer>(Arrays.asList(1, 1, 2, 3, 5, 6, 7, 9, 10));
		quickSorter.sort(list);
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 1, 2, 3, 5, 6, 7, 9, 10));
		assertEquals(list, expected);
	}
	@Test
	void testQuickSortDesc() {
		new ArrayList<Integer>(Arrays.asList(10, 9, 7, 6, 5, 3, 2, 1, 1));
		quickSorter.sort(list);
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 1, 2, 3, 5, 6, 7, 9, 10));
		assertEquals(list, expected);
	}
}
