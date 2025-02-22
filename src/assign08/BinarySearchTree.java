package assign08;

import java.util.*;

/**
 * A binary search tree implementation of the SortedSet interface.
 * This data structure provides efficient insertion, removal, and search operations
 * for sorted data.
 *
 * @author Tien Phong Le & Quang Khai Huynh
 * @version 10/31/2024
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {
    private Node<Type> root;
    private int size;

    /**
     * This constructor constructs a new BinarySearchTree
     */
    public BinarySearchTree() {
        this.root = null;
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
        if (root == null) {
            this.root = new Node<>(item, null, null);
            this.size++;
            return true;
        }
        return addRecursive(this.root, item);
    }

    /**
     * Recursive private method for the add method
     *
     * @param parent - current node
     * @param item   - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually inserted); otherwise, returns false
     */

    private boolean addRecursive(Node<Type> parent, Type item) {
        int cmp = item.compareTo(parent.getData());

        if (cmp > 0) {
            if (parent.getRight() == null) {
                parent.setRight(new Node<>(item, null, null));
                this.size++;
                return true;
            }
            return addRecursive(parent.getRight(), item);
        } else if (cmp < 0) {
            if (parent.getLeft() == null) {
                parent.setLeft(new Node<>(item, null, null));
                this.size++;
                return true;
            }
            return addRecursive(parent.getLeft(), item);
        } else {
            return false;
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
        this.root = null;
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
        if (root == null) {
            return false;
        }
        return containsRecursive(this.root, item);
    }

    /**
     * Recursive private method for contains
     *
     * @param parent - current node
     * @param item   - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     * otherwise, returns false
     */
    private boolean containsRecursive(Node<Type> parent, Type item) {
        if (parent == null) {
            return false;
        }

        int cmp = item.compareTo(parent.getData());

        if (cmp > 0) {
            return containsRecursive(parent.getRight(), item);
        } else if (cmp < 0) {
            return containsRecursive(parent.getLeft(), item);
        } else {
            return true;
        }
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
        return this.size == 0;
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
        return this.root.getLeftmostNode().getData();
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
        return this.root.getRightmostNode().getData();
    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is,
     * if the input item was actually removed); otherwise, returns false
     * @throws UnsupportedOperationException if the {@code remove} operation is
     *                                       not supported by the derived class
     */
    @Override
    public boolean remove(Type item) {
        if (this.root == null) {
            return false;
        }
        return removeRecursive(null, this.root, item);
    }

    /**
     * Private recursive method for remove method
     *
     * @param parent - current node
     * @param item   - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is,
     * if the input item was actually removed); otherwise, returns false
     * @throws UnsupportedOperationException if the {@code remove} operation is
     *                                       not supported by the derived class
     */
    public boolean removeRecursive(Node<Type> parent, Node<Type> current, Type item) {
        if (current == null) {
            return false;
        }

        int cmp = item.compareTo(current.getData());

        if (cmp > 0) {
            //Search in right subtree
            return removeRecursive(current, current.getRight(), item);
        } else if (cmp < 0) {
            //Search in left subtree
            return removeRecursive(current, current.getLeft(), item);
        } else {  //Item found

            //Current node is a leaf
            if (current.getLeft() == null && current.getRight() == null) {
                if (parent == null) {
                    this.root = null;
                } else if (parent.getLeft() == current) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }

                //Current node has one child
            } else if (current.getRight() == null) {
                if (parent == null) {
                    this.root = current.getLeft();
                } else if (parent.getLeft() == current) {
                    parent.setLeft(current.getLeft());
                } else {
                    parent.setRight(current.getLeft());
                }
            } else if (current.getLeft() == null) {
                if (parent == null) {
                    this.root = current.getRight();
                } else if (parent.getLeft() == current) {
                    parent.setLeft(current.getRight());
                } else {
                    parent.setRight(current.getRight());
                }

                //Current node has 2 children
            } else {
                Node<Type> successor = current.getRight().getLeftmostNode();
                current.setData(successor.getData());
                removeRecursive(current, current.getRight(), successor.getData());
                this.size++;
            }
            this.size--;
            return true;
        }
    }

    /**
     * Ensures that this set does not contain any of the items in the specified
     * collection.
     *
     * @param items - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is,
     * if any item in the input collection was actually removed); otherwise,
     * returns false
     * @throws UnsupportedOperationException if the {@code removeAll} operation is
     *                                       not supported by the derived class
     */
    @Override
    public boolean removeAll(Collection<? extends Type> items) {
        boolean result = false;
        for (Type item : items) {
            if (remove(item)) {
                result = true;
            }
        }
        return result;
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
        ArrayList<Type> list = new ArrayList<>();
        inOrder(root, list);

        return list;
    }

    /**
     * Recursive private method to perform an in-order traversal of the tree and add each element to the list.
     *
     * @param node - the current node
     * @param list - the result list
     */
    private void inOrder(Node<Type> node, ArrayList<Type> list) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeft(), list);
        list.add(node.getData());
        inOrder(node.getRight(), list);
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(5);
        List<Integer> elements = Arrays.asList(5, 3, 8, 3, 7); // 5 and 3 are duplicates
        bst.addAll(elements);
        ArrayList<Integer> sortedList = bst.toArrayList();

        // Print the sorted list
        System.out.println("Sorted elements in the BST: " + sortedList);
        System.out.println(bst.size());
    }
}
