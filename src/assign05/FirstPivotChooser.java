package assign05;

import java.util.ArrayList;

/**
 * A PivotChooser implementation that selects the first element in the list as the pivot.
 *
 * @author Tien Phong Le, Quang Khai Huynh
 * @version 10/3/2024
 */

public class FirstPivotChooser<E extends Comparable<? super E>> implements PivotChooser<E> {
    private int pivot;

    /**
     * This constructor defines the FirstPivotChooser
     */
    public FirstPivotChooser() {
        this.pivot = 0;
    }

    /**
     * This method returns the first element as the pivot
     *
     * @param list  - list containing a portion to be sorted
     * @param lower - position of																																																																															 first item in the sublist to be sorted
     * @param upper - position of the last item in the sublist to be sorted
     * @return pivot index
     */
    @Override
    public int getPivotIndex(ArrayList<E> list, int lower, int upper) {
        this.pivot = lower;
        return this.pivot;
    }
}