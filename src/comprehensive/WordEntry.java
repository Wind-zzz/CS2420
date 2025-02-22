package comprehensive;

/**
 * This class handles the components linked with a word, which are part of speech and definition
 *
 * @author Tien Phong Le & Quang Khai Huynh
 * @version 12/5/2024
 */

public class WordEntry implements Comparable<WordEntry>{
    private String pOs;
    private String definition;

    public WordEntry(String pOs, String definition) {
        this.pOs = pOs;
        this.definition = definition;
    }

    /**
     * This method returns the part of speech of the specified word
     *
     * @return the part of speech
     */
    public String getPos() {
        return this.pOs;
    }

    /**
     * This method returns the definition of the specified word
     *
     * @return the definition
     */
    public String getDefinition() {
        return this.definition;
    }

    /**
     * This method displays the part of speech and definition of a word in the same line
     *
     * @return textual representation of part of speech and definition
     */
    public String display() {
        return pOs + ".\t" + definition;
    }

    /**
     * This method overrides the compareTo method from Comparable
     *
     * @param o the other object to be compared.
     * @return 0 if two WordEntry objects are the same, negative if this object is smaller, positive if it's larger.
     */
    @Override
    public int compareTo(WordEntry o) {
        int cmp = this.pOs.compareTo(o.getPos());

        if (cmp != 0) {
            return cmp;
        } else {
            return this.definition.compareTo(o.getDefinition());
        }
    }
}
