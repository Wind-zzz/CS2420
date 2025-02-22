package assign04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class handles operations on integer values represented as strings.
 * This class provides methods to sort, compare, and group such strings, as well as
 * to find maximum elements based on different criteria.
 *
 * @author Tien Phong Le & Quang Khai Huynh
 * @version September 19, 2024
 */

public class IntegerStringUtility {

    /**
     * This nested class defines the comparison of positive integer values
     * represented as strings, numerically. The behavior of this comparator is
     * undefined if one or both of the strings being compared do not represent a
     * positive integer value.
     */
    public static class StringNumericalValueComparator implements Comparator<String> {
        public int compare(String str1, String str2) {

            //Initiate the ending index of leading zeros substring
            int endIndex1 = 0;
            int endIndex2 = 0;

            for (int i = 0; i < str1.length(); i++) {
                if (str1.charAt(i) == '0') {
                    endIndex1++;
                }
                if (str1.charAt(i) != '0') {
                    break;
                }
            }
            for (int i = 0; i < str2.length(); i++) {
                if (str2.charAt(i) == '0') {
                    endIndex2++;
                }
                if (str2.charAt(i) != '0') {
                    break;
                }
            }
            str1 = str1.replace(str1.substring(0, endIndex1), "");
            str2 = str2.replace(str2.substring(0, endIndex2), "");
            if (str1.length() < str2.length())
                return -1;
            if (str1.length() > str2.length())
                return 1;
            return str1.compareTo(str2);
        }
    }

    /**
     * This nested class defines the comparison of strings, by similarity. When two
     * strings have different lengths, they are not similar and the shorter string
     * comes before the longer string. When two strings have the same length but are
     * not similar, the sorted order of the characters are compared
     * lexicographically.
     */
    public static class StringSimilarityComparator implements Comparator<String> {
        public int compare(String str1, String str2) {
            if (str1.length() < str2.length())
                return -1;
            if (str1.length() > str2.length())
                return 1;

            Character[] chars1 = toCharacterArray(str1);
            Character[] chars2 = toCharacterArray(str2);

            insertionSort(chars1, (Character char1, Character char2) -> char1.compareTo(char2));
            insertionSort(chars2, (Character char1, Character char2) -> char1.compareTo(char2));

            for (int i = 0; i < chars1.length; i++) {
                int comp = chars1[i].compareTo(chars2[i]);
                if (comp != 0) {
                    return comp;
                }
            }
            return 0;
        }

        /**
         * This private helper method helps to convert String to Character array
         *
         * @param str input String
         * @return Character array
         */
        private Character[] toCharacterArray(String str) {
            Character[] chars = new Character[str.length()];
            for (int i = 0; i < str.length(); i++) {
                chars[i] = str.charAt(i);
            }
            return chars;
        }
    }

    /**
     * This nested class defines the comparison of similarity groups by group size
     * (i.e., array length). If two groups have the same size, the group with the
     * largest integer value (represented as a string) is deemed the largest group.
     * If both groups are empty, they are deemed equal.
     */
    public static class StringSimilarityGroupComparator implements Comparator<String[]> {
        public int compare(String[] group1, String[] group2) {
            StringNumericalValueComparator cmp = new StringNumericalValueComparator();

            if (group1.length == 0 && group2.length == 0) {
                return 0;
            }
            if (group1.length != group2.length) {
                return group1.length - group2.length;
            }
            String max1 = findMax(group1, cmp);
            String max2 = findMax(group2, cmp);

            return cmp.compare(max1, max2);
        }
    }

    /**
     * This generic method sorts the input array using an insertion sort and the
     * input Comparator object.
     *
     * @param array existing array
     * @param cmp   input Comparator object
     */
    public static <E> void insertionSort(E[] array, Comparator<? super E> cmp) {
        for (int i = 0; i < array.length; i++) {
            E temp = array[i];
            int target = i - 1;
            for (int j = i - 1; j >= 0; j--) {
                if (cmp.compare(temp, array[j]) < 0) {
                    array[j + 1] = array[j];
                    target--;
                }
            }
            array[target + 1] = temp;
        }
    }

    /**
     * This generic method returns the largest element in the input array, according
     * to the input Comparator object. This method must not alter the given array
     * and must call your insertionSort method.
     *
     * @param array input array
     * @param cmp   input Comparator object
     * @return the largest element in the input array
     * @throws NoSuchElementException when there is no such element
     */
    public static <E> E findMax(E[] array, Comparator<? super E> cmp) throws NoSuchElementException {
        if (array.length == 0) {
            throw new NoSuchElementException();
        }

        E[] copy = Arrays.<E>copyOf(array, array.length);
        insertionSort(copy, cmp);
        return copy[copy.length - 1];
    }

    /**
     * This method returns the similarity groups in the input array. Each row in the
     * two-dimensional array returned is a single similarity group, and each string
     * in a row is similar to every other string in the same row.
     *
     * @param array input array
     * @return the similarity groups in the input array
     */
    public static String[][] getSimilarityGroups(String[] array) {
        if (array.length == 0) {
            return new String[0][0];
        }

        String[] copy = Arrays.copyOf(array, array.length);

        Comparator<String> cmp = new IntegerStringUtility.StringSimilarityComparator();
        insertionSort(copy, cmp);

        List<List<String>> grps = getGroups(copy, cmp);
        String[][] result = new String[grps.size()][];

        for (int j = 0; j < grps.size(); j++) {
            result[j] = grps.get(j).toArray(new String[0]);
        }
        return result;
    }

    /**
     * This private helper method returns the similarity groups
     *
     * @param copy copy of input array
     * @param cmp  input Comparator
     * @return List of similarity groups
     */
    private static List<List<String>> getGroups(String[] copy, Comparator<String> cmp) {
        List<List<String>> grps = new ArrayList<>();
        List<String> newGrp = new ArrayList<String>();
        String current = copy[0];

        for (String element : copy) {
            if (cmp.compare(current, element) == 0) {
                newGrp.add(element);
            } else {
                grps.add(new ArrayList<>(newGrp));
                newGrp.clear();
                current = element;
                newGrp.add(current);
            }
        }
        grps.add(newGrp);
        return grps;
    }

    /**
     * This method returns the largest similarity group in the input array.
     *
     * @param array input array
     * @return largest similarity group
     * @throws NoSuchElementException when there is no similar group or no element exist
     */
    public static String[] findMaximumSimilarityGroup(int[] array) throws NoSuchElementException {
        if (array.length == 0) {
            throw new NoSuchElementException();
        }
        String[] copy = new String[array.length];
        StringSimilarityGroupComparator cmp = new StringSimilarityGroupComparator();

        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i] + "";
        }

        String[][] grps = getSimilarityGroups(copy);
        if (grps.length == 0) {
            throw new NoSuchElementException();
        }
        return findMax(grps, cmp);
    }
}
