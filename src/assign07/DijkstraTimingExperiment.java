package assign07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DijkstraTimingExperiment extends TimingExperiment {
    private static final String PROBLEM_SIZE_DESCRIPTION = "vertexCount";
    private static final int PROBLEM_SIZE_MIN = 1000;
    private static final int PROBLEM_SIZE_COUNT = 10;
    private static final int PROBLEM_SIZE_STEP = 1000;
    private static final int EXPERIMENT_ITERATION_COUNT = 75;

    private List<String> sources;
    private List<String> destinations;
    private List<Double> weights; // List to store edge weights
    private String srcData;
    private String dstData;

    public DijkstraTimingExperiment() {
        super(PROBLEM_SIZE_DESCRIPTION, PROBLEM_SIZE_MIN, PROBLEM_SIZE_COUNT,
                PROBLEM_SIZE_STEP, EXPERIMENT_ITERATION_COUNT);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        sources = new ArrayList<>();
        destinations = new ArrayList<>();
        weights = new ArrayList<>(); // Initialize the weights list
        Random rng = new Random();

        // Generate source, destination, and weight lists for a random graph
        // where |E| = 2 * |V|
        for (int i = 0; i < 2 * problemSize; i++) {
            String src = "v" + rng.nextInt(problemSize);
            String dest = "v" + rng.nextInt(problemSize);
            double weight = rng.nextDouble(100) + 1; // Generate random weight between 1 and 100
            sources.add(src);
            destinations.add(dest);
            weights.add(weight);
        }

        // Randomly select source and destination vertices for Dijkstra's
        srcData = "v" + rng.nextInt(problemSize);
        dstData = "v" + rng.nextInt(problemSize);
    }

    @Override
    protected void runComputation() {
        try {
            GraphUtility.shortestWeightedPath(sources, destinations, weights, srcData, dstData);
        } catch (IllegalArgumentException e) {
            // Catch the exception when there is no path from source to destination
        }
    }

    public static void main(String[] args) {
        TimingExperiment experiment = new DijkstraTimingExperiment();
        experiment.printResults();
    }
}
