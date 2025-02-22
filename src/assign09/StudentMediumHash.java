package assign09;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * This class provides a simple representation for a University of Utah student.
 * Object's hashCode method is overridden with a correct hash function for this
 * object, but one that does a decent job of distributing students in a hash
 * table.
 *
 * @author CS 2420 course staff and Tien Phong Le & Quang Khai Huynh
 * @version 11/14/2024
 */
public class StudentMediumHash {
    private int uid;
    private String firstName;
    private String lastName;
    private final Random rng = new Random();

    /**
     * Creates a new student with the specified uid, firstName, and lastName.
     *
     * @param uid
     * @param firstName
     * @param lastName
     */
    public StudentMediumHash(int uid, String firstName, String lastName) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Getter for this Student's UID.
     *
     * @return the UID for this object
     */
    public int getUid() {
        return this.uid;
    }

    /**
     * Getter for this Student's first name.
     *
     * @return the first name for this object
     */

    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Getter for this Student's last name.
     *
     * @return the last name for this object
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Determines whether the given object is the same as this Student.
     *
     * @return true if both objects have the same UID, first name, and last name; false otherwise
     */
    public boolean equals(Object other) {
        // change to StudentMediumHash and StudentGoodHash for two new classes
        if (!(other instanceof StudentMediumHash))
            return false;

        StudentMediumHash rhs = (StudentMediumHash) other;

        return this.uid == rhs.uid && this.firstName.equals(rhs.firstName) && this.lastName.equals(rhs.lastName);
    }

    /**
     * Generates a textual representation of this Student.
     *
     * @return a textual representation of this object
     */
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("0000000");
        return firstName + " " + lastName + " (u" + formatter.format(uid) + ")";
    }

    /**
     * Generates a hash code based on the ending character of firstName, first character of lastName and uid. This function provides
     * a moderate level of distribution with some collisions due to clustering
     *
     * @return a hash code that slightly derived from the attributes for medium performance
     */
    public int hashCode() {
        return firstName.hashCode() * lastName.hashCode() * uid;
    }
}
