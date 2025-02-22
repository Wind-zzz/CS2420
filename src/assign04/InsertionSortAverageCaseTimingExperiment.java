package assign04;


import java.util.Comparator;
import java.util.Random;

public class InsertionSortAverageCaseTimingExperiment extends ArraySortTimingExperiment {
    private Random rng = new Random();
    private Comparator<String> cmp = new IntegerStringUtility.StringNumericalValueComparator();
    private String[] array;

    /**
     * Constructor to build a sort timing experiment.
     *
     * @param problemSizeDescription   - description of the problem size for the experiment
     * @param problemSizeMin           - minimum array size
     * @param problemSizeCount         - number of array sizes to use in the experiment
     * @param problemSizeStep          - Step size between consecutive array sizes
     * @param experimentIterationCount - Number of times to run computation for a given array size
     */
    public InsertionSortAverageCaseTimingExperiment(String problemSizeDescription, int problemSizeMin, int problemSizeCount, int problemSizeStep, int experimentIterationCount) {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    /**
     * Helper method to populate the array with random integers in random order.
     *
     * @param problemSize - size of the array
     * @implNote method must call populateAscendingArray and then shuffle the contents of the array
     */
    protected void populateRandomArray(int problemSize) {
        array = new String[problemSize];
        for (int i = 0; i < problemSize; i++) {
            int element = rng.nextInt(1, 100000);  // Generate random integers
            array[i] = String.valueOf(element);    // Convert integer to string
        }
    }

    @Override
    protected void setupExperiment(int problemSize) {
    populateRandomArray(problemSize);
    }

    @Override
    protected void runComputation() {
        IntegerStringUtility.insertionSort(array, cmp);
    }

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new InsertionSortAverageCaseTimingExperiment("ElementSize", 100000, 20, 20000, 100);
        timingExperiment.printResults();
    }
}
