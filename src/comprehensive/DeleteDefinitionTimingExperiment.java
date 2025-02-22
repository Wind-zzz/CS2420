package comprehensive;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeleteDefinitionTimingExperiment extends TimingExperiment {

    private Glossary glossary;
    private Random random;
    private List<String> words; // Store the words for easy access during deletion
    String randomWord = "";
    private String definition;

    public DeleteDefinitionTimingExperiment(String problemSizeDescription, int problemSizeMin,
                                            int problemSizeCount, int problemSizeStep, int experimentIterationCount) {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
        random = new Random();
        words = new ArrayList<>();
    }

    @Override
    protected void setupExperiment(int problemSize) {
        glossary = new Glossary();
        for (int i = 0; i < problemSize; i++) {
            String word = generateWord(i);
            words.add(word); // Store the word
            // Add multiple definitions for each word
            for (int j = 0; j < 5; j++) {
                definition = generateDefinition(i, j);
                glossary.addEntry(word, "noun", definition);
            }
        }
        randomWord = words.get(random.nextInt(words.size()));
    }

    @Override
    protected void runComputation() {
        //glossary.addEntry(randomWord, "adj", definition);
        //glossary.getMap().remove(randomWord);
        glossary.updateDefinition(randomWord, definition);
    }

    private String generateWord(int i) {
        return String.valueOf(i);
    }

    private String generateDefinition(int i, int j) {
        return String.valueOf(i) + " " + String.valueOf(j) + " " + String.valueOf(i + j);
    }

    public static void main(String[] args) {
        TimingExperiment experiment = new DeleteDefinitionTimingExperiment("glossary Size", 1000, 30, 1000, 100);
        experiment.printResults();
    }
}
