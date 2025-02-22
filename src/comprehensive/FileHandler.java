package comprehensive;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class provides methods for reading a glossary from a file
 * and saving the current glossary back to a file.
 * It interacts with the glossary class to populate and persist glossary data.
 * This class ensures that glossary data is properly loaded and saved
 *
 * @author Tien Phong Le & Quang Khai Huynh
 * @version 12/5/2024
 */
public class FileHandler {
    /**
     * This static method reads the input text file and store glossaries in Glossary object.
     */
    public static Glossary readFile(String filePath) throws IOException {
        Glossary glossary = new Glossary();

        try (Scanner input = new Scanner(new File(filePath))) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] elements = line.split("::");
                if (elements.length == 3) {
                    glossary.addEntry(elements[0].trim(), elements[1].trim(), elements[2].trim());
                }
            }
        }
        return glossary;
    }

    /**
     * This static updates the text file with file's path and name given by user
     */
    public static void saveFile(Glossary glossary, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String word : glossary.getMap().keySet()) {
                List<WordEntry> entries = new ArrayList<>(glossary.getMap().get(word));
                for (WordEntry entry : entries) {
                    writer.write(word + "::" + entry.getPos() + "::" + entry.getDefinition());
                    writer.newLine();
                }
            }
            System.out.println("\nSuccessfully saved dictionary to " + filePath);
        } catch (IOException e) {
            System.out.println("\nFile not found");
        }
    }
}
