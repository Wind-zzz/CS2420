package comprehensive;

import java.io.IOException;
import java.util.Scanner;

/**
 * The Main class is the entry point for running the glossary application.
 * It handles user interaction by displaying a menu of options and performing
 * operations on the glossary, such as adding definitions, updating entries,
 * deleting definitions, and retrieving metadata.
 *
 * @author Tien Phong Le & Quang Khai Huynh
 * @version 12/5/2024
 */
public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Incorrect number of arguments");
            return;
        }
        try {
            Glossary glossary = FileHandler.readFile(args[0]);
            boolean run = true;

            while (run) {

                // Display menu
                System.out.println("Main menu");
                System.out.println("1.  Get metadata");
                System.out.println("2.  Get words in range");
                System.out.println("3.  Get word");
                System.out.println("4.  Get first word");
                System.out.println("5.  Get last word");
                System.out.println("6.  Get parts of speech");
                System.out.println("7.  Update definition");
                System.out.println("8.  Delete definition");
                System.out.println("9.  Add new definition");
                System.out.println("10.  Save dictionary");
                System.out.println("11.  Quit\n");
                System.out.print("Select an option: ");
                String option = getUserInput();

                switch (option) {
                    case "1":
                        glossary.getMetaData();
                        break;
                    case "2":
                        System.out.print("Starting word: ");
                        String start = getUserInput();
                        System.out.print("Ending word: ");
                        String end = getUserInput();
                        glossary.wordInRange(start, end);
                        break;
                    case "3":
                        System.out.print("Select a word: ");
                        String given = getUserInput();
                        glossary.getEntry(given);
                        break;
                    case "4":
                        glossary.getFirstWord();
                        break;
                    case "5":
                        glossary.getLastWord();
                        break;
                    case "6":
                        System.out.print("Select a word: ");
                        String newWord = getUserInput();
                        glossary.getAllPos(newWord);
                        break;
                    case "7":
                        System.out.print("Select a word: ");
                        String updatingWord = getUserInput();
                     //   glossary.updateDefinition(updatingWord);
                        break;
                    case "8":
                        System.out.print("Select a word: ");
                        String deletingWord = getUserInput();
                        glossary.deleteDefinition(deletingWord);
                        break;
                    case "9":
                        System.out.print("Type a word: ");
                        String word = getUserInput();
                        glossary.addNewDefinition(word);
                        break;
                    case "10":
                        System.out.print("Type a filename with path: ");
                        String filename = getUserInput();
                        FileHandler.saveFile(glossary, filename);
                        break;
                    case "11":
                        run = false;
                        break;
                    default:
                        System.out.println("\nInvalid selection\n");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    /**
     * This private method gets user input by creating a new Scanner instance.
     *
     * @return The user input as a String.
     */
    private static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }
}
