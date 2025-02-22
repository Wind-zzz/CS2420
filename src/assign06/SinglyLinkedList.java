package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements the SinglyLinkedList that implements List interface with an iterator traverse from head to tail
 *
 * @author Tien Phong Le
 * @version 10/17/2024
 */

public class SinglyLinkedList<E> implements List<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * Node class for this character linked list.
     */
    private static class Node<E> {
        public E data;
        public Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * Nested Iterator for singly linked list
     */
    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        private Node<E> curr;
        private Node<E> prev;
        private Node<E> prePrev;
        private SinglyLinkedList<E> list;

        public SinglyLinkedListIterator(SinglyLinkedList<E> list) {
            this.list = list;
            this.curr = list.head;
            this.prev = null;
        }

        /**
         * This method checks if there is another item in the list
         *
         * @return true if there is another item left in the iterator, returns false otherwise
         */
        @Override
        public boolean hasNext() {
            return curr != null;
        }

        /**
         * This method keeps track of the next item in the list
         *
         * @return next item
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E data = curr.data;
            this.prePrev = this.prev;
            this.prev = this.curr;
            this.curr = this.curr.next;
            return data;
        }

        /**
         * This method removes an element from the list as this iterator traverses
         */
        @Override
        public void remove() {
            if (prev == null) {
                throw new IllegalStateException();
            }

            if (prev == list.head) {
                list.head = curr;
            } else {
                if (curr == null) {
                    if (prePrev != null) {
                        prePrev.next = null;
                        list.tail = prePrev;
                    } else {
                        list.head = null;
                        list.tail = null;
                    }
                } else {
                    prev.next = curr;
                }
            }
            prev = null;
            list.size--;
        }
    }

    /**
     * This constructor defines the singly linked list
     */
    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Removes all of the elements from this list.
     * O(1) running-time behavior for a singly-linked list.
     */
    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Deletes and returns the element at a specific position in this list.
     * O(N) running-time behavior for a singly-linked list, in the average case.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
     */
    @Override
    public E delete(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return deleteFirst();
        } else {
            Node<E> prev = getNode(index - 1);
            Node<E> temp = prev.next;
            prev.next = prev.next.next;

            if (temp.equals(this.tail)) {
                this.tail = prev;
            }
            this.size--;
            return temp.data;
        }
    }

    /**
     * Deletes and returns the first element from this list.
     * O(1) running-time behavior for a singly-linked list.
     *
     * @return the first element
     * @throws NoSuchElementException if this list is empty
     */
    @Override
    public E deleteFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node<E> remove = this.head;
        this.head = this.head.next;

        if (this.head == null) {
            this.tail = null;
        }
        this.size--;
        return remove.data;
    }

    /**
     * Gets the element at a specific position in this list.
     * O(N) running-time behavior for a singly-linked list, in the average case.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return getNode(index).data;
    }

    /**
     * Gets the first element in this list.
     * O(1) running-time behavior for a singly-linked list.
     *
     * @return the first element in this list
     * @throws NoSuchElementException if this list is empty
     */
    @Override
    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.head.data;
    }

    /**
     * Gets the last element in this list.
     * O(1) running-time behavior for a singly-linked list with an end/tail reference.
     *
     * @return the last element in this list
     * @throws NoSuchElementException if this list is empty
     */
    @Override
    public E getLast() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.tail.data;
    }

    /**
     * Determines the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     * O(N) running-time behavior for a singly-linked list, in the average case.
     *
     * @param element - the element to search for
     * @return the index of the first occurrence; -1 if the element is not found
     */
    @Override
    public int indexOf(E element) {
        int index = 0;
        Node<E> node = this.head;

        if (element == null || isEmpty()) {
            return -1;
        }

        while (node != null) {
            if (node.data.equals(element)) {
                return index;
            }
            node = node.next;
            index++;
        }
        return -1;
    }

    /**
     * Inserts an element at a specific position in this list.
     * O(N) running-time behavior for a singly-linked list, in the average case.
     *
     * @param index   - the specified position
     * @param element - the element to add
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
     */
    @Override
    public void insert(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            insertFirst(element);
        } else if (index == this.size) {
            insertLast(element);
        } else {
            Node<E> prev = getNode(index - 1);
            Node<E> temp = prev.next;
            prev.next = new Node<>(element, temp);
            this.size++;
        }
    }

    /**
     * Inserts an element at the beginning of this list.
     * O(1) running-time behavior for a singly-linked list.
     *
     * @param element - the element to add
     */
    @Override
    public void insertFirst(E element) {
        Node<E> node = new Node<>(element, head);
        this.head = node;
        if (isEmpty()) {
            this.tail = node;
        }
        this.size++;
    }

    /**
     * Inserts an element at the end of this list.
     * O(1) running-time behavior for a singly-linked list with an end/tail reference.
     *
     * @param element - the element to add
     */
    @Override
    public void insertLast(E element) {
        Node<E> temp = new Node<>(element, null);

        if (isEmpty()) {
            this.head = this.tail = temp;
        } else {
            this.tail.next = temp;
            this.tail = temp;
        }
        this.size++;
    }

    /**
     * Indicates whether this list is empty.
     * O(1) running-time behavior for a singly-linked list.
     *
     * @return true if this list contains no elements; false, otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Creates and returns an iterator for this list.
     *
     * @return an iterator over the elements in this list in proper sequence (from first
     * element to last element)
     */
    @Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<>(this);
    }

    /**
     * Indicates the number of elements in this list.
     * O(1) running-time behavior for a singly-linked list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Generates an array containing all of the elements in this list in proper sequence
     * (from first element to last element).
     * O(N) running-time behavior for a singly-linked list.
     *
     * @return an array containing all of the elements in this list, in order
     */
    @SuppressWarnings("unchecked")
    @Override
    public E[] toArray() {
        Object[] array = new Object[this.size];
        Node<E> node = this.head;
        for (int i = 0; i < this.size; i++) {
            array[i] = node.data;
            node = node.next;
        }
        return (E[]) array;
    }

    /**
     * This private method gets the Node at the given index
     *
     * @return the Node at index
     */
    private Node<E> getNode(int index) {
        Node<E> node = this.head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }
}



