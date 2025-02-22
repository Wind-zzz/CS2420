package assign04;

import java.util.*;

/**
 * Abstract class to perform timing experiments for sorting arrays with varying degrees of "sortedness".
 *
 * @author CS 2420 Course Staff
 * @version 2024-09-12
 */
public abstract class ArraySortTimingExperiment extends TimingExperiment {

    protected Integer[] array;
    protected Random rng = new Random();

    /**
     * Constructor to build a sort timing experiment.
     *
     * @param problemSizeDescription   - description of the problem size for the experiment
     * @param problemSizeMin           - minimum array size
     * @param problemSizeCount         - number of array sizes to use in the experiment
     * @param problemSizeStep          - Step size between consecutive array sizes
     * @param experimentIterationCount - Number of times to run computation for a given array size
     */
    public ArraySortTimingExperiment(
        String problemSizeDescription   ,
        int problemSizeMin,
        int problemSizeCount,
        int problemSizeStep,
        int experimentIterationCount
    ) {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    /**
     * Helper method to populate the array with random integers in ascending order.
     *
     * @implNote integers are bounded between 0 and 20 + 10 * problemSize
     * @param problemSize - size of the array
     */
    protected void populateAscendingArray(int problemSize) {
        array = new Integer[problemSize];
        int currentElement = rng.nextInt(20);
        for (int i = 0; i < problemSize; i++) {
            array[i] = currentElement;
            currentElement += rng.nextInt(10);
        }
    }

    /**
     * Helper method to populate the array with random integers in random order.
     *
     * @implNote method must call populateAscendingArray and then shuffle the contents of the array
     * @param problemSize - size of the array
     */
    protected void populateRandomArray(int problemSize) {
        // TODO: Implement this method
    }

    /**
     * Helper method to populate the array with random integers in descending order.
     *
     * @implNote method must call populateAscendingArray and then reverse the contents of the array
     * @param problemSize - size of the array
     */
    protected void populateDescendingArray(int problemSize) {
        // TODO: Implement this method
    }

    /**
     * Helper method to populate the array with random integers in nearly ascending order.
     *
     * @implNote method must call populateAscending array and then swap a small number of random
     * pairs of nearby elements
     */
    protected void populateNearlyAscendingArray(int problemSize) {
        populateAscendingArray(problemSize);
        // Choose a random number of pairs to swap, between 5 and 19
        int swapCount = 5 + rng.nextInt(15);
        for (int i = 0; i < swapCount; i++) {
            // Choose a random index, excluding the final 11 indices
            int idx1 = rng.nextInt(problemSize - 11);
            // Choose an index between 1 and 10 to the right of idx1
            int idx2 = idx1 + 1 + rng.nextInt(10);
            // Swap the entries at those two indices
            swapEntries(idx1, idx2);
        }
    }

    /**
     * Helper method to swap two entries of the array.
     *
     * @param idx1 - first index to swap
     * @param idx2 - second index to swap
     * @throws IndexOutOfBoundsException if either index is out of bounds
     */
    private void swapEntries(int idx1, int idx2) throws IndexOutOfBoundsException {
        if (idx1 < 0 || idx1 >= array.length || idx2 < 0 || idx2 >= array.length) {
            throw new IndexOutOfBoundsException();
        }
        int temp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = temp;
    }
}