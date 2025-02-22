package assign05;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * This class defines a QuickSorter object, which sorts an ArrayList using the quicksort algorithm, which implements Sorter interface.
 *
 * @author Tien Phong Le, Quang Khai Huynh
 * @version 10/3/2024
 */
public class QuickSorter<E extends Comparable<? super E>> implements Sorter<E> {

    private final PivotChooser<E> chooser;

    /**
     * This constructor defines the QuickSorter object
     *
     * @param chooser the choosing module for the pivot
     */
    public QuickSorter(PivotChooser<E> chooser) {
        this.chooser = chooser;
    }

    /**
     * This method sorts the given list using quicksort
     *
     * @param list the ArrayList to be sorted
     */
    @Override
    public void sort(ArrayList<E> list) {
        if (list.size() <= 1) {
            return;
        }
        sortRecursive(list, 0, list.size() - 1);
    }


    /**
     * This recursive private method performs the quicksort on the specified portion of the list.
     *
     * @param list  the list to sort
     * @param lower the lower index of the sublist to sort
     * @param upper the upper index of the sublist to sort
     */

    private void sortRecursive(ArrayList<E> list, int lower, int upper) {
        if (lower < upper) {
            int pI = chooser.getPivotIndex(list, lower, upper);
            int pivotIndex = partition(list, lower, upper, pI);
            sortRecursive(list, lower, pivotIndex - 1);
            sortRecursive(list, pivotIndex + 1, upper);
        }
    }

    /**
     * This private method divides the list into two halves around the pivot element.
     *
     * @param list  the list to partition
     * @param lower the lower index
     * @param upper the upper index
     * @return the pivot index
     */
    private int partition(ArrayList<E> list, int lower, int upper, int pI) {
        E pivot = list.get(pI);
        swap(list, pI, upper);

        int i = lower - 1;
        for (int j = lower; j < upper; j++) {
            if (list.get(j).compareTo(pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, upper);

        return i + 1;
    }

    /**
     * This method swaps two elements in the list.
     *
     * @param list given list
     * @param j    first element's index
     * @param k    second element's index
     */
    private void swap(ArrayList<E> list, int j, int k) {
        E temp = list.get(j);
        list.set(j, list.get(k));
        list.set(k, temp);
    }
}