package assign10;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Timing experiment to collect the running times of BinaryMinHeap methods: add, peek, extract.
 */
public class BinaryMinHeapTimingExperiment extends TimingExperiment {
    private static String problemSizeDescription = "BinaryMinHeapSize";
    private static int problemSizeMin = 10000;
    private static int problemSizeCount = 30;
    private static int problemSizeStep = 10000;
    private static int experimentIterationCount = 100;

    private BinaryMinHeap<Integer> heap;
    private List<Integer> list = new ArrayList<>();
    private final Random rng = new Random();

    /**
     * Constructor to build a general timing experiment.
     */
    public BinaryMinHeapTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    /**
     * This method creates a BinaryMinHeap with problem size
     *
     * @param problemSize - the problem size for one experiment
     */
    @Override
    public void setupExperiment(int problemSize) {
//        populateRandomArrayList(problemSize);
        list.clear();
        for (int i = 0; i < problemSize; i++)
            list.add(rng.nextInt(problemSize));
        Collections.shuffle(list);

        heap = new BinaryMinHeap<>(list);

    }

    /**
     * Helper method to populate the list with random integers in ascending order.
     *
     * @param problemSize - size of the list
     * @implNote integers are bounded between 0 and 20 + 10 * problemSize
     */
    protected void populateAscendingArrayList(int problemSize) {
        this.list = new ArrayList<Integer>(problemSize);
        int currentElement = rng.nextInt(20);
        for (int i = 0; i < problemSize; i++) {
            this.list.add(currentElement);
            currentElement += rng.nextInt(10);
        }
    }

    /**
     * Helper method to populate the list with random integers in random order.
     *
     * @param problemSize - size of the list
     */
    protected void populateRandomArrayList(int problemSize) {
        populateAscendingArrayList(problemSize);
        for (int i = 0; i < problemSize; i++) {
            int j = rng.nextInt(problemSize);
            swapEntries(i, j);
        }
    }

    /**
     * Helper method to swap two entries of the list.
     *
     * @param idx1 - first index to swap
     * @param idx2 - second index to swap
     * @throws IndexOutOfBoundsException if either index is out of bounds
     */
    private void swapEntries(int idx1, int idx2) throws IndexOutOfBoundsException {
        if (idx1 < 0 || idx1 >= list.size() || idx2 < 0 || idx2 >= list.size()) {
            throw new IndexOutOfBoundsException();
        }
        int temp = list.get(idx1);
        list.set(idx1, list.get(idx2));
        list.set(idx2, temp);
    }

    /**
     * This method runs the computation for a given experiment.
     */
    @Override
    public void runComputation() {
//         Run 'add' operation by adding one more element
 //      heap.add(rng.nextInt(heap.size()));
//
//         Run 'peek' operation
//         heap.peek();
//
//         Run 'extract' operation
            heap.extract();

    }

    public static void main(String[] args) {
        BinaryMinHeapTimingExperiment timingExperiment = new BinaryMinHeapTimingExperiment();
        timingExperiment.printResults();

    }
}

