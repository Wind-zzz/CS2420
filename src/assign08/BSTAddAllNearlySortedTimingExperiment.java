package assign08;



import java.util.*;

/**
 * This experiment measures the performance of adding elements to a BinarySearchTree in nearly sorted order.
 *
 * @author Tien Phong Le & CS 2420 staff
 * @version 10/31/2024
 */
public class BSTAddAllNearlySortedTimingExperiment extends TimingExperiment {
    private static String problemSizeDescription = "setSize";
    private static int problemSizeMin = 500;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 500;
    private static int experimentIterationCount = 100;

    protected BinarySearchTree<Integer> sortedSet;
    protected List<Integer> elementsToAdd;
    private final Random rng = new Random();

    public BSTAddAllNearlySortedTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    private void populateAscendingArrayList(int problemSize) {
        this.elementsToAdd = new ArrayList<>(problemSize);
        int currentElement = rng.nextInt(20);
        for (int i = 0; i < problemSize; i++) {
            this.elementsToAdd.add(currentElement);
            currentElement += 1 + rng.nextInt(10);
        }
    }

    private void populateNearlyAscendingArrayList(int problemSize) {
        populateAscendingArrayList(problemSize);
        int swapCount = 5 + rng.nextInt(15);
        for (int i = 0; i < swapCount; i++) {
            int idx1 = rng.nextInt(problemSize - 11);
            int idx2 = idx1 + 1 + rng.nextInt(10);
            swapEntries(idx1, idx2);
        }
    }

    private void swapEntries(int idx1, int idx2) throws IndexOutOfBoundsException {
        if (idx1 < 0 || idx1 >= elementsToAdd.size() || idx2 < 0 || idx2 >= elementsToAdd.size()) {
            throw new IndexOutOfBoundsException();
        }
        int temp = elementsToAdd.get(idx1);
        elementsToAdd.set(idx1, elementsToAdd.get(idx2));
        elementsToAdd.set(idx2, temp);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        sortedSet = new BinarySearchTree<>();
        this.populateNearlyAscendingArrayList(problemSize);
    }

    @Override
    protected void runComputation() {
        sortedSet.addAll(elementsToAdd);
    }

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new BSTAddAllNearlySortedTimingExperiment();
        timingExperiment.printResults();
    }
}
