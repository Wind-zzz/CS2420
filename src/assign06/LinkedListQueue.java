package assign06;

import java.util.NoSuchElementException;

/**
 * This class implements the generic LinkedListQueue that implements Queue interface, backed by an instance of your generic SinglyLinkedList class, stored as a private member variable
 *
 * @author Tien Phong Le
 * @version 10/17/2024
 */
public class LinkedListQueue<E> implements Queue<E> {
    private SinglyLinkedList<E> list;

    /**
     * This constructor defines a new linked list queue with no parameter
     */
    public LinkedListQueue() {
        this.list = new SinglyLinkedList<>();
    }

    /**
     * Removes all of the elements from the queue.
     */
    public void clear() {
        this.list.clear();
    }

    /**
     * Indicates whether this queue is empty.
     *
     * @return true if this queue contains no elements; false, otherwise
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * Adds the given element to this queue, putting it at the rear/back of the queue.
     *
     * @param element - the element to add
     */
    public void offer(E element) {
        this.list.insertLast(element);
    }

    /**
     * Gets the element at the front of this queue.
     *
     * @return the element at the front of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public E peek() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.list.getFirst();
    }

    /**
     * Deletes and returns the element at the front of this queue.
     *
     * @return the element at the front of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public E poll() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.list.deleteFirst();
    }

    /**
     * Indicates the number of elements in this queue.
     *
     * @return the number of elements in this queue
     */
    public int size() {
        return list.size();
    }
}
