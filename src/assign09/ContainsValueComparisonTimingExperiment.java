package assign09;



import java.util.Random;
import java.util.TreeMap;

public class ContainsValueComparisonTimingExperiment extends TimingExperiment {
    private static String problemSizeDescription = "studentSize";
    private static int problemSizeMin = 500;
    private static int problemSizeCount = 50;
    private static int problemSizeStep = 500;
    private static int experimentIterationCount = 100   ;


    private static final Random rng = new Random();

    private HashTable<String, Integer> hashTable;
    private TreeMap<String, Integer> treeMap;
    private Integer targetValue;

    /**
     * Constructor to build a general timing experiment.
     */
    public ContainsValueComparisonTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        hashTable = new HashTable<>();
       // treeMap = new TreeMap<>();

        for (int i = 0; i < problemSize; i++) {
            String key = "Key" + rng.nextInt(problemSize);
            Integer value = rng.nextInt(10000);  // Generate some random integer values
            hashTable.put(key, value);
            //treeMap.put(key, value);
        }

        // Randomly select a value to search for in the containsValue test
        targetValue = rng.nextInt(5000, 10000);
    }

    @Override
    protected void runComputation() {
        hashTable.containsValue(targetValue);
       // treeMap.containsValue(targetValue);
    }

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new ContainsValueComparisonTimingExperiment();
        timingExperiment.printResults();
    }
}
