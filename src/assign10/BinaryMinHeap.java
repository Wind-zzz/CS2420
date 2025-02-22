package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A generic implementation of a binary min-heap backed by an array.
 * This data structure maintains the heap property where the value of each node
 * is less than or equal to the values of its children. The smallest element
 * is always at the root.
 *
 * @author Tien Phong Le & Quang Khai Huynh
 * @version 11/21/2024
 */

public class BinaryMinHeap<E> implements PriorityQueue<E> {
    private Object[] heap;
    private int size;
    private Comparator<? super E> cmp;


    public BinaryMinHeap() {
        this.heap = new Object[20];
        this.size = 0;
        this.cmp = null;
    }

    public BinaryMinHeap(Comparator<? super E> cmp) {
        this.heap = new Object[20];
        this.size = 0;
        this.cmp = cmp;
    }

    public BinaryMinHeap(List<? extends E> list) {
        this.heap = new Object[list.size()];
        this.size = list.size();
        this.cmp = null;
        for (int i = 0; i < list.size(); i++) {
            heap[i] = list.get(i);
        }
        buildHeap();
    }

    public BinaryMinHeap(List<? extends E> list, Comparator<? super E> cmp) {
        this.heap = new Object[list.size()];
        this.size = list.size();
        this.cmp = cmp;
        for (int i = 0; i < list.size(); i++) {
            heap[i] = list.get(i);
        }
        buildHeap();
    }

    /**
     * Adds the given element to this priority queue.
     * O(1) in the average case, O(log N) in the worst case
     *
     * @param element - element to be added to this priority queue
     */
    @Override
    public void add(E element) {
        if (this.size == heap.length) {
            resizeHeap();
        }
        heap[size] = element;
        percolateUp(size);
        size++;
    }

    /**
     * Gets the highest-priority element this priority queue.
     * O(1)
     *
     * @return highest-priority element in this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    @Override
    public E peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return getElement(0);
    }

    /**
     * Gets and removes the highest-priority element this priority queue.
     * O(log N)
     *
     * @return highest-priority element in this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    @Override
    public E extract() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E min = peek();
        heap[0] = getElement(size - 1);
        size--;
        percolateDown(0);
        return min;
    }

    /**
     * Gets the number of elements in this priority queue.
     * O(1)
     *
     * @return number of elements in this priority queue
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Determines whether this priority queue is empty.
     * O(1)
     *
     * @return true if this priority queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Empties this priority queue of elements.
     * O(1)
     */
    @Override
    public void clear() {
        this.heap = new Object[20];
        this.size = 0;
        this.cmp = null;
    }

    /**
     * Generates an array of the elements in this priority queue,
     * in the same order they appear in the backing array.
     * O(N)
     * <p>
     * (NOTE: This method is used for grading. The root element
     * must be stored at index 0 in the returned array, regardless of
     * whether it is in stored there in the backing array.)
     */
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(heap, 0, result, 0, size);
        return result;
    }

    /**
     * This private method build a new heap from input elements
     */
    private void buildHeap() {
        for (int i = (size - 1) / 2; i >= 0; i--) {
            percolateDown(i);
        }
    }

    /**
     * This private method does the upHeap operation by swapping with parent until the parent is smaller
     *
     * @param index index of the element
     */
    private void percolateUp(int index) {
        int parIndex = (index - 1) / 2;

        while (index > 0 && innerCompare(getElement(index), getElement(parIndex)) < 0) {
            E curr = getElement(index);
            E parent = getElement(parIndex);

            // Swap with parent
            heap[index] = parent;
            heap[parIndex] = curr;

            //Update current and parent index
            index = parIndex;
            parIndex = (index - 1) / 2;
        }
    }

    /**
     * This private method does the downHeap operation by swapping with one of the children until both are larger
     *
     * @param index index of the element
     */
    private void percolateDown(int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int smaller = index;

        if (left < size && innerCompare(getElement(left), getElement(smaller)) < 0) {
            smaller = left;
        }

        if (right < size && innerCompare(getElement(right), getElement(smaller)) < 0) {
            smaller = right;
        }

        if (smaller != index) {
            E temp = getElement(index);
            E small = getElement(smaller);
            heap[index] = small;
            heap[smaller] = temp;
            percolateDown(smaller);
        }
    }

    /**
     * This private method resizes the backing array when it capacity if full
     */
    private void resizeHeap() {
        Object[] newHeap = new Object[heap.length * 2];
        System.arraycopy(heap, 0, newHeap, 0, heap.length);
        heap = newHeap;
    }


    /**
     * Compares two elements using the comparator provided or natural ordering if not
     *
     * @param first  the first element to compare
     * @param second the second element to compare
     * @return a negative integer if the first element is less than the second,
     * zero if they are equal, or a positive integer if the first element
     * is greater than the second
     */
    @SuppressWarnings("unchecked")
    private int innerCompare(E first, E second) {
        if (first == null && second == null) {
            return 0;
        } else if (first == null) {
            return -1;
        } else if (second == null) {
            return 1;
        } else if (cmp != null) {
            return cmp.compare(first, second);
        } else {
            Comparable<? super E> cmpFirst = (Comparable<? super E>) first;
            return cmpFirst.compareTo(second);
        }
    }

    /**
     * This method retrieves an element from the Object backing array
     */
    @SuppressWarnings("unchecked")
    private E getElement(int index) {
        return (E) heap[index];
    }

}

