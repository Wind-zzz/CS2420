package assign07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TopologicalSortTimingExperiment extends TimingExperiment {

    private static final String PROBLEM_SIZE_DESCRIPTION = "vertexCount";
    private static final int PROBLEM_SIZE_MIN = 5000;
    private static final int PROBLEM_SIZE_COUNT = 30;
    private static final int PROBLEM_SIZE_STEP = 5000;
    private static final int EXPERIMENT_ITERATION_COUNT = 100;

    private List<String> sources;
    private List<String> destinations;

    public TopologicalSortTimingExperiment() {
        super(PROBLEM_SIZE_DESCRIPTION, PROBLEM_SIZE_MIN, PROBLEM_SIZE_COUNT,
                PROBLEM_SIZE_STEP, EXPERIMENT_ITERATION_COUNT);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        sources = new ArrayList<>();
        destinations = new ArrayList<>();
        Random rng = new Random();

        // Generate source and destination lists for a random directed acyclic graph
        for (int i = 0; i < problemSize; i++) {
            int u = rng.nextInt(problemSize - 1); // Select a starting vertex
            int v = rng.nextInt(problemSize - u - 1) + u + 1; // E  nsure v > u to prevent cycles
            String src = "v" + u;
            String des = "v" + v;
            sources.add(src);
            destinations.add(des);
        }    
    }

    @Override
    protected void runComputation() {
        try {
            GraphUtility.sort(sources, destinations);
        } catch (IllegalArgumentException e) {
            // Catch the exception when the graph is not a DAG
            System.err.println("Error: Graph is not a DAG."); 
        }
    }

    public static void main(String[] args) {
        TopologicalSortTimingExperiment experiment = new TopologicalSortTimingExperiment();
        experiment.printResults();
    }
}