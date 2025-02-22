package assign05;

import java.util.ArrayList;

/**
 * This class implements the Merge Sort algorithm for sorting an ArrayList.
 * It uses insertion sort for smaller sublists defined by a threshold.
 *
 * @author Tien Phong Le, Quang Khai Huynh
 * @version 10/3/2024
 */

public class MergeSorter<E extends Comparable<? super E>> implements Sorter<E> {
    private final int threshold;

    /**
     * This constructor defines the MergeSorter
     *
     * @throws IllegalArgumentException if the threhold is not positive
     */
    public MergeSorter(int threshold) {
        if (threshold < 0) {
            throw new IllegalArgumentException();
        }
        this.threshold = threshold;
    }


    /**
     * This method sorts the given list using the recursive merge sort algorithm.
     *
     * @param list the list to be sorted
     */
    @Override
    public void sort(ArrayList<E> list) {
        ArrayList<E> temp = new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            temp.add(null);
        }
        if (list.size() <= 1) {
            return;
        }
        mergeSort(list, 0, list.size() - 1, temp);
    }

    /**
     * This recursive private method divides the list and sorts the sublists, then merges them.
     *
     * @param list  the original list
     * @param temp  the temporary list
     * @param lower  the starting index of the sublist to be sorted
     * @param upper the ending index of the sublist to be sorted
     */
    private void mergeSort(ArrayList<E> list, int lower, int upper, ArrayList<E> temp) {
        if (upper - lower + 1 <= threshold) {
            insertionSort(list, lower, upper); // Switch to insertion sort
            return;
        }

        if (lower < upper) {
            int mid = (lower + upper) / 2;
            mergeSort(list, lower, mid, temp);
            mergeSort(list, mid + 1, upper, temp);
            merge(list, lower, mid, upper, temp);
        }
    }

    /**
     * This private methods implements the insertionSort
     * @param list list that need to sort
     * @param lower startIndex of the list
     * @param upper endIndex of the list
     */
    private void insertionSort(ArrayList<E> list, int lower, int upper) {
        for (int i = lower + 1; i <= upper; i++) {
            E temp = list.get(i);
            int j = i - 1;
            while (j >= lower && list.get(j).compareTo(temp) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, temp);
        }
    }

    /**
     * This private method merges two sorted sublists into one sorted list.
     *
     * @param list  the original list containing the sublists
     * @param temp  the temporary list
     * @param lower the starting index of the first sublist
     * @param mid   the ending index of the first sublist
     * @param upper the ending index of the second sublist
     */
    private void merge(ArrayList<E> list, int lower, int mid, int upper, ArrayList<E> temp) {
        int l1 = lower;
        int l2 = mid + 1;
        int index = lower;

        // Merge the two sublists
        while (l1 <= mid && l2 <= upper) {
            if (list.get(l1).compareTo(list.get(l2)) > 0) {
                temp.set(index, list.get(l2));
                l2++;
            } else {
                temp.set(index, list.get(l1));
                l1++;
            }
            index++;
        }

        // Copy remaining elements from the left half, if any
        while (l1 <= mid) {
            temp.set(index, list.get(l1));
            l1++;
            index++;
        }
        while (l2 <= upper) {
            temp.set(index, list.get(l2));
            l2++;
            index++;
        }

        // Copy temp back into the original list
        for (int i = lower; i <= upper; i++) {
            list.set(i, temp.get(i));
        }
    }
}

