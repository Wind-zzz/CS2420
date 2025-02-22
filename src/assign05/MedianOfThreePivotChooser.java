package assign05;

import java.util.ArrayList;

/**
 * This PivotChooser implementation selects the pivot using the median-of-three strategy.
 * The pivot is chosen as the median of the first, middle, and last elements in the sublist.
 *
 * @author Tien Phong Le, Quang Khai Huynh
 * @version 10/3/2024
 */

public class MedianOfThreePivotChooser<E extends Comparable<? super E>> implements PivotChooser<E> {
    private int pivot;

    /**
     * This constructor defines the MedianOfThreePivotChooser
     */
    public MedianOfThreePivotChooser() {
        this.pivot = 0;
    }

    /**
     * This method returns the median of three elements lower, upper and middle as the pivot
     *
     * @param list  - list containing a portion to be sorted
     * @param lower - position of first item in the sublist to be sorted
     * @param upper - position of the last item in the sublist to be sorted
     * @return pivot index
     */
    @Override
    public int getPivotIndex(ArrayList<E> list, int lower, int upper) {
        int mid = lower + (upper - lower) / 2;
        E l = list.get(lower);
        E m = list.get(mid);
        E u = list.get(upper);
        if (l.compareTo(m) > 0) {
            if (m.compareTo(u) > 0) {
                pivot = mid;
            } else if (l.compareTo(u) > 0) {
                pivot = upper;
            } else {
                pivot = lower;
            }
        } else {
            if (l.compareTo(u) > 0) {
                pivot = lower;
            } else if (m.compareTo(u) > 0) {
                pivot = upper;
            } else {
                pivot = mid;
            }
        }
        return pivot;
    }
}
