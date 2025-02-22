package assign08;



import java.util.*;

/**
 * This experiment measures the performance of adding elements to an ArraySortedSet in random order.
 *
 * @author Tien Phong Le
 * @version 10/31/2024
 */
public class ArraySortedSetAddAllRandomOrderTimingExperiment extends TimingExperiment {
    private static String problemSizeDescription = "setSize";
    private static int problemSizeMin = 500;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 500;
    private static int experimentIterationCount = 100;

    protected ArraySortedSet<Integer> sortedSet;
    protected List<Integer> elementsToAdd;
    private final Random rng = new Random();

    public ArraySortedSetAddAllRandomOrderTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        sortedSet = new ArraySortedSet<>();
        elementsToAdd = new ArrayList<>(problemSize);
        int curr = rng.nextInt(20);

        while (elementsToAdd.size() < problemSize) {
            elementsToAdd.add(curr);
            curr += rng.nextInt(8) + 1;
        }
        Collections.shuffle(elementsToAdd);
    }

    @Override
    protected void runComputation() {
        sortedSet.addAll(elementsToAdd);
    }

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new ArraySortedSetAddAllRandomOrderTimingExperiment();
        timingExperiment.printResults();
    }
}
