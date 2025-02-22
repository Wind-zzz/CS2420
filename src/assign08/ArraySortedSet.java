package assign08;

import java.util.*;

/**
 * An implementation of the SortedSet interface using an array as the underlying data structure.
 * This implementation provides efficient insertion, removal, and search operations for sorted data.
 *
 * @author Tien Phong Le & Quang Khai Huynh
 * @version 10/31/2024
 */
public class ArraySortedSet<Type extends Comparable<? super Type>> implements SortedSet<Type> {
    private Object[] array;
    private int size;

    /**
     * This constructor defines an ArraySortedSet
     */
    public ArraySortedSet() {
        this.array = new Object[20];
        this.size = 0;
    }

    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually inserted); otherwise, returns false
     */
    @Override
    public boolean add(Type item) {
        if (this.size == array.length) {
            this.array = copyElements(array);
        }

        if (isEmpty()) {
            this.array[0] = item;
            this.size++;
            return true;
        } else {
            int index = binarySearch(item);

            if (index > 0) {
                return false;
            } else {
                if (this.getElement(0).equals(item)) {
                    return false;
                }
                index = -index;

                for (int i = this.size; i > index; i--) {
                    this.array[i] = this.array[i - 1];
                }
                this.array[index] = item;
                this.size++;
                return true;
            }
        }
    }

    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually inserted); otherwise,
     * returns false
     */
    @Override
    public boolean addAll(Collection<? extends Type> items) {
        if (this.size == array.length) {
            this.array = copyElements(array);
        }

        boolean result = false;
        for (Type item : items) {
            if (this.add(item)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @Override
    public void clear() {
        this.array = new Object[20];
        this.size = 0;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     * otherwise, returns false
     */
    @Override
    public boolean contains(Type item) {
        return binarySearch(item) > 0 || this.getElement(0).compareTo(item) == 0;
    }

    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     * in this set that is equal to it; otherwise, returns false
     */
    @Override
    public boolean containsAll(Collection<? extends Type> items) {
        for (Type item : items) {
            if (!contains(item)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the smallest item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type min() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.getElement(0);
    }

    /**
     * Returns the largest item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type max() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.getElement(this.size - 1);
    }

    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns an ArrayList containing all the items in this set, in sorted
     * order.
     */
    @Override
    public ArrayList<Type> toArrayList() {
        ArrayList<Type> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(getElement(i));
        }
        return list;
    }

    /**
     * This method retrieves an element from the Object backing array
     */
    @SuppressWarnings("unchecked")
    private Type getElement(int index) {
        return (Type) array[index];
    }

    /**
     * This private helper method implements the binary search for the sorted list
     *
     * @return indexes of the target
     */
    private int binarySearch(Type target) {
        int r = this.size - 1;
        int l = 0;

        while (l <= r) {
            int mid = (l + r) / 2;

            if (this.getElement(mid).compareTo(target) == 0) {
                return mid;
            } else if (this.getElement(mid).compareTo(target) < 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        //Negative index holder to add
        return -l;
    }

    /**
     * This method is to deliver elements in the original array to a larger array.
     *
     * @param array the original array
     * @return largerArray
     */
    @SuppressWarnings("unchecked")
    private Object[] copyElements(Object[] array) {
        Object[] temp = new Object[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            temp[i] = (Type) array[i];
        }
        return temp;
    }
}
