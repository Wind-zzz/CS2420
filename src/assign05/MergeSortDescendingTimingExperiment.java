package assign05;


/**
 * This class sets up the experiment for MergeSort with descending list
 *
 * @author Tien Phong Le
 * @version 10/3/2024
 */
public class MergeSortDescendingTimingExperiment extends  ArrayListSortTimingExperiment {
    protected static int threshold;
    private static final String problemSizeDescription = "elementSize";
    private static final int problemSizeMin = 10000;
    private static final int problemSizeCount = 60;
    private static final int problemSizeStep = 10000;
    private static final int experimentIterationCount = 75;

    /**
     * Constructor to build a MergeSortDescendingTimingExperiment experiment.
     */
    public MergeSortDescendingTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    /**
     * This method sets up the experiment
     *
     * @param problemSize - the problem size for one experiment
     */
    @Override
    protected void setupExperiment(int problemSize) {
        populateDescendingArrayList(problemSize);
    }

    /**
     * This method runs the computation
     */
    @Override
    protected void runComputation() {
        MergeSorter<Integer> mergeSorter = new MergeSorter<Integer>(25);
        mergeSorter.sort(list);
    }

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new MergeSortDescendingTimingExperiment();
        timingExperiment.printResults();
    }
}
