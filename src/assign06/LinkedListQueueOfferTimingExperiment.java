package assign06;

import java.util.Random;

/**
 * Timing experiment to collect the running times of  LinkedList Queue offer method
 *
 * @author Tien Phong Le
 * @version 10/17/2024
 */
public class LinkedListQueueOfferTimingExperiment extends TimingExperiment {
    private static String problemSizeDescription = "linkedListQueueSize";
    private static int problemSizeMin = 10000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 10000;
    private static int experimentIterationCount = 50;

    protected Queue<Integer> queue = new LinkedListQueue<>();
    private final Random rng = new Random();

    /**
     * Constructor to build a general timing experiment.
     */
    public LinkedListQueueOfferTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    /**
     * This method sets up the experiment and create a queue with problemSize
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
     * This method runs computation
     */
    @Override
    protected void runComputation() {
        queue.offer(rng.nextInt());
    }

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new LinkedListQueueOfferTimingExperiment();
        timingExperiment.printResults();
    }
}
