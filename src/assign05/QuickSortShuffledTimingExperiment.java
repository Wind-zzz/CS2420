package assign05;

/**
 * This class sets up the experiment for QuickSort with shuffled list
 *
 * @author Tien Phong Le
 * @version 10/3/2024
 */
public class QuickSortShuffledTimingExperiment extends ArrayListSortTimingExperiment {
    protected static PivotChooser<Integer> pivotChooser;
    private static final String problemSizeDescription = "elementSize";
    private static final int problemSizeMin = 10000;
    private static final int problemSizeCount = 60;
    private static final int problemSizeStep = 10000;
    private static final int experimentIterationCount = 75;

    /**
     * Constructor to build a QuickSortShuffledTimingExperiment experiment.
     */
    public QuickSortShuffledTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    /**
     * This method sets up the experiment
     *
     * @param problemSize - the problem size for one experiment
     */
    @Override
    protected void setupExperiment(int problemSize) {
        populateRandomArrayList(problemSize);
    }

    /**
     * This method runs the computation
     */
    @Override
    protected void runComputation() {
        RandomPivotChooser<Integer> rngPivot = new RandomPivotChooser<>();
        FirstPivotChooser<Integer> firstPivot = new FirstPivotChooser<>();
        MedianOfThreePivotChooser<Integer> medianOfThreePivot = new MedianOfThreePivotChooser<>();

        QuickSorter<Integer> sorter = new QuickSorter<Integer>(medianOfThreePivot);
        sorter.sort(list);
    }

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new QuickSortShuffledTimingExperiment();
        timingExperiment.printResults();
    }
}


