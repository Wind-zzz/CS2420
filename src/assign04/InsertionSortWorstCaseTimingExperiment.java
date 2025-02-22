package assign04;

public class InsertionSortWorstCaseTimingExperiment extends ArraySortTimingExperiment {
    /**
     * Constructor to build a sort timing experiment.
     *
     * @param problemSizeDescription   - description of the problem size for the experiment
     * @param problemSizeMin           - minimum array size
     * @param problemSizeCount         - number of array sizes to use in the experiment
     * @param problemSizeStep          - Step size between consecutive array sizes
     * @param experimentIterationCount - Number of times to run computation for a given array size
     */
    public InsertionSortWorstCaseTimingExperiment(String problemSizeDescription, int problemSizeMin, int problemSizeCount, int problemSizeStep, int experimentIterationCount) {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
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

    @Override
    protected void setupExperiment(int problemSize) {

    }

    @Override
    protected void runComputation() {

    }
}

