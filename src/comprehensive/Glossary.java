package comprehensive;

import java.util.*;

/**
 * This class constructs the Glossary with methods to organize entries and handle functions such as getting metadata, retrieving sublist,...
 *
 * @author Tien Phong Le & Quang Khai Huynh
 * @version 12/5/2024
 */
public class Glossary {
    private TreeMap<String, TreeSet<WordEntry>> entries;
    private final Set<String> validPos = Set.of("noun", "verb", "adj", "adv", "pron", "prep", "conj", "interj");

    /**
     * This constructor creates a new Glossary
     */
    public Glossary() {
        this.entries = new TreeMap<>(String::compareTo);
    }

    /**
     * This getter retrieves the map for this Glossary
     *
     * @return the map of this glossary
     */
    public TreeMap<String, TreeSet<WordEntry>> getMap() {
        return this.entries;
    }

    /**
     * This method inserts a new entry to the Glossary
     */
    public void addEntry(String word, String partOfSpeech, String definition) {
        TreeSet<WordEntry> wordEntries = entries.get(word);

        // If any element is missing, no entry added to glossary
        if (word.isEmpty() || partOfSpeech.isEmpty()) {
            return;
        }

        // Check validity for part of speech
        if (validPos.contains(partOfSpeech.toLowerCase())) {
            WordEntry wordEntry = new WordEntry(partOfSpeech, definition);
            if (wordEntries == null) {
                //Add new mapping for the new word and its elements
                wordEntries = new TreeSet<>(WordEntry::compareTo);
                entries.put(word, wordEntries);
            }
            //Add new entry if it has not been added to the glossary
            wordEntries.add(wordEntry);
        }
    }

    /**
     * This method handles the option 9 operation
     */
    public void addNewDefinition(String word) {
        if (word.isEmpty()) {
            System.out.println("\nNo word from user");
            return;
        }
        System.out.println("Valid parts of speech: [noun, verb, adj, adv, pron, prep, conj, interj]");

        boolean invalid = true;
        String partOfSpeech = "";

        while (invalid) {
            System.out.print("Type a valid part of speech: ");
            String part = getInput();
            if (validPos.contains(part.toLowerCase())) {
                partOfSpeech = part.toLowerCase();
                invalid = false;
            }
        }

        System.out.print("Type a definition: ");
        String definition = getInput();

        if (definition.isEmpty()) {
            System.out.println("\nNo definition from user");
            return;
        }
        addEntry(word, partOfSpeech, definition);
        System.out.println("\nSuccessfully added!");
    }

    /**
     * This method retrieves the metadata information of the glossary
     */
    public void getMetaData() {
        Set<String> partsOfSpeech = new HashSet<>();
        int defCount = 0;

        for (TreeSet<WordEntry> elements : entries.values()) {
            // Counting definitions
            defCount += elements.size();
            for (WordEntry component : elements) {
                String pOs = component.getPos();
                partsOfSpeech.add(pOs);
                if (partsOfSpeech.size() == 8) {
                    break;
                }
            }
        }

        // Calculating the details
        int wordCount = entries.keySet().size();

        double avgDef = ((double) defCount) / wordCount;
        //Handle division if word count = 0
        if (wordCount == 0) {
            avgDef = 0.000;
        }

        int partOfSpeechCount = partsOfSpeech.size();
        String first = "";
        String last = "";

        if (!entries.keySet().isEmpty()) {
            first = entries.firstKey();
            last = entries.lastKey();
        }
        System.out.println("\nwords- " + wordCount);
        System.out.println("definitions- " + defCount);
        System.out.println("definitions per word- " + String.format("%.3f", avgDef));
        System.out.println("parts of speech- " + partOfSpeechCount);
        System.out.println("first word- " + first);
        System.out.println("last word- " + last);
    }

    /**
     * This method retrieves the ordered list of words within bounds (inclusively)
     *
     * @param start starting word
     * @param end   ending word
     */
    public void wordInRange(String start, String end) {
        System.out.println("\nThe words between " + start + " and " + end + " are:");

        if (start.isEmpty() || end.isEmpty() || start.compareTo(end) > 0) {
            return;
        }

        // Track if start and end were added temporarily
        boolean startAdded = !entries.containsKey(start);
        boolean endAdded = !entries.containsKey(end);

        if (startAdded) {
            entries.put(start, new TreeSet<>());
        }
        if (endAdded) {
            entries.put(end, new TreeSet<>());
        }

        //Get the sub map in range
        Map<String, TreeSet<WordEntry>> temp = entries.subMap(start, true, end, true);

        // Remove the temporary keys
        if (startAdded) {
            temp.remove(start);
            entries.remove(start);
        }
        if (endAdded) {
            temp.remove(end);
            entries.remove(end);
        }

        // Print out words in range
        for (String word : temp.keySet()) {
            System.out.println("\t" + word);
        }
    }

    /**
     * This method shows all entries for a word with corresponding parts of speech and definitions
     *
     * @param word the given word
     */
    public void getEntry(String word) {
        TreeSet<WordEntry> wordEntries = entries.get(word);

        if (wordEntries == null) {
            System.out.println("\n" + word + " not found"); // No such word exists
        } else {
            System.out.println("\n" + word);
            for (WordEntry entry : wordEntries) {
                System.out.println("\t" + entry.display());
            }
        }
    }

    /**
     * This method shows all entries of the first word
     */
    public void getFirstWord() {
        if (entries.isEmpty()) {
            System.out.println("\nGlossary is empty");
            return;
        }
        String first = entries.firstKey();
        getEntry(first);
    }

    /**
     * This method shows all entries of the last word
     */
    public void getLastWord() {
        if (entries.isEmpty()) {
            System.out.println("\nGlossary is empty");
            return;
        }
        String last = entries.lastKey();
        getEntry(last);
    }

    /**
     * This method retrieves all the parts of speech of a given word
     *
     * @param word the given word
     */
    public void getAllPos(String word) {
        TreeSet<String> allPos = new TreeSet<>();
        TreeSet<WordEntry> wordEntries = entries.get(word);

        if (wordEntries == null) {
            System.out.println("\n" + word + " not found\n"); // No such word exists
            return;
        }

        //Collect parts of speech in order
        System.out.println("\n" + word);

        // Search for existing parts of speech for given word
        for (WordEntry component : wordEntries) {
            allPos.add(component.getPos());
            if (allPos.size() == 8) {
                break;
            }
        }

        // Print out all the parts of speech for the given word
        for (String pos : allPos) {
            System.out.println("\t" + pos);
        }
    }

    /**
     * This method updates a definition for the given word (selecting from all definitions for the word)
     */
    public void updateDefinition(String word) {
        TreeSet<WordEntry> wordEntries = entries.get(word);

        if (wordEntries == null) {
            System.out.println("\n" + word + " not found, cannot update definition");
            return;
        }

        List<WordEntry> entryList = new ArrayList<>(wordEntries);
        int choice = selectDefinition(word, entryList, "update");

        if (choice == -1) {
            return; // Go back to the main menu
        }

        System.out.print("Type a new definition: ");
        String newDefinition = getInput();

        //No definition from user, remain the same
        if(newDefinition.isEmpty()){
            System.out.println("\nNo definition from user");
            return;
        }

        WordEntry deleting = entryList.get(choice - 1);
        wordEntries.add(new WordEntry(deleting.getPos(), newDefinition)); // Add the updated definition
        wordEntries.remove(deleting); // Remove the old definition

        System.out.println("\nDefinition updated");
    }

    /**
     * This method deletes a definition for the given word (selecting from all definitions for the word)
     */
    public void deleteDefinition(String word) {
        TreeSet<WordEntry> wordEntries = entries.get(word);

        if (wordEntries == null) {
            System.out.println("\n" + word + " not found, cannot delete definition");
            return;
        }

        List<WordEntry> entryList = new ArrayList<>(wordEntries);
        int choice = selectDefinition(word, entryList, "remove");

        if (choice == -1) {
            return; // Go back to the main menu
        }

        WordEntry deleting = entryList.get(choice - 1);
        wordEntries.remove(deleting); // Remove the selected definition

        System.out.println("\nDefinition removed");

        // If no definitions remain, remove the word
        if (wordEntries.isEmpty()) {
            entries.remove(word);
            System.out.println(word + " removed");
        }
    }

    /**
     * This private method displays the list of definitions for a word and prompts for a selection.
     *
     * @param word      The word whose definitions are to be displayed.
     * @param entryList The list of definitions for the word.
     * @param action    The action being performed
     * @return The selected definition index (1-based) or -1 as back to main menu
     */
    private int selectDefinition(String word, List<WordEntry> entryList, String action) {
        int choice;
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nDefinitions for " + word);
            for (int i = 0; i < entryList.size(); i++) {
                System.out.println((i + 1) + ". " + entryList.get(i).display());
            }
            System.out.println((entryList.size() + 1) + ". Back to main menu\n");
            System.out.print("Select a definition to " + action + ": ");

            try {
                choice = Integer.parseInt(getInput());
                if (choice >= 1 && choice <= entryList.size()) {
                    return choice; // Valid choice
                } else if (choice == entryList.size() + 1) {
                    return -1; // Back to main menu
                } else {
                    System.out.println("Invalid selection");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection");
            }
        }
    }

    /**
     * This private method gets user input by creating a new Scanner instance.
     *
     * @return The user input as a String.
     */
    private String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }
}