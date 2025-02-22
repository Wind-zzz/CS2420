package assign07;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BreadthFirstTimingExperiment extends TimingExperiment {
    private static String problemSizeDescription = "vertexCount";
    private static int problemSizeMin = 5000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 5000;
    private static int experimentIterationCount = 100;

    private Graph<String> graph;
    private List<String> sources;
    private List<String> destinations;
    private final Random rng = new Random();

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new BreadthFirstTimingExperiment();
        timingExperiment.printResults();
    }

    public BreadthFirstTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int vertexCount) {
        graph = new Graph<>();
        sources = new ArrayList<>(vertexCount);
        destinations = new ArrayList<>(vertexCount);

        // Create vertices
        for (int i = 0; i < vertexCount; i++) {
            String vertex = "v" + i;
            graph.addVertex(vertex);
            if (i % 2 == 0) sources.add(vertex); // Half for sources
            else destinations.add(vertex); // Half for destinations
        }

        // Create edges (sparse graph, e.g., 2*|V| edges)
        int edgeCount = 2 * vertexCount;
        for (int i = 0; i < edgeCount; i++) {
            String src = "v" + rng.nextInt(vertexCount);
            String dst = "v" + rng.nextInt(vertexCount);
            if (!src.equals(dst)) {
                graph.addEdge(src, dst,1);
            }
        }
    }

    @Override
    protected void runComputation() {
        // Select a random source and destination
        String src = sources.get(rng.nextInt(sources.size()));
        String dst = destinations.get(rng.nextInt(destinations.size()));

        // Run the shortestPath computation with try-catch
        try {
            GraphUtility.shortestPath(sources, destinations, src, dst);
        } catch (IllegalArgumentException e) {
            // Exception occurs when there is no path, so handle as part of expected behavior
        }
    }
}
