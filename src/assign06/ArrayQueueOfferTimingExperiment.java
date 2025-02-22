package assign06;

import java.util.Random;

/**
 * Timing experiment to collect the running times of  Array Queue offer method
 *
 * @author Tien Phong Le
 * @version 10/17/2024
 */
public class ArrayQueueOfferTimingExperiment extends TimingExperiment {
    private static String problemSizeDescription = "ArrayQueueSize";
    private static int problemSizeMin = 10000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 10000;
    private static int experimentIterationCount = 50;

    protected Queue<Integer> queue = new ArrayQueue<>();
    private final Random rng = new Random();

    /**
     * Constructor to build a general timing experiment.
     */
    public ArrayQueueOfferTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    /**
     * This method creates an ArrayQueue with problem size
     *
     * @param problemSize - the problem size for one experiment
     */
    @Override
    protected void setupExperiment(int problemSize) {
        queue.clear();
        for (int i = 0; i < problemSize; i++) {
            queue.offer(rng.nextInt());
        }
    }

    /**
     * This method runs the computation
     */
    @Override
    protected void runComputation() {
        queue.offer(rng.nextInt());
    }

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new ArrayQueueOfferTimingExperiment();
        timingExperiment.printResults();
    }
}
