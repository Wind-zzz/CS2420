package assign08;

/**
 * This class defines the Node for the BinarySearchTree
 *
 * @author Tien Phong Le & Quang Khai Huynh & CS 2420 course staff
 * @version 10/31/2024
 */

public class Node<Type> {
    private Type data;
    private Node<Type> right;
    private Node<Type> left;

    /**
     * Creates a binary node with the given data and child references.
     *
     * @param data  - data to be contained in this node
     * @param left  - reference to this node's left child
     * @param right - reference to this node's right child
     */
    public Node(Type data, Node<Type> left, Node<Type>
            right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }


    /**
     * Gets this binary node's data.
     *
     * @return this node's data
     */
    public Type getData() {
        return this.data;
    }

    /**
     * (Re)sets this binary node's data.
     *
     * @param data - data to be contained in this node
     */
    public void setData(Type data) {
        this.data = data;
    }

    /**
     * Gets the reference to this binary node's left child.
     *
     * @return reference to this node's left child
     */
    public Node<Type> getLeft() {
        return this.left;
    }

    /**
     * Gets the reference to this binary node's right child.
     *
     * @return reference to this node's right child
     */
    public Node<Type> getRight() {
        return this.right;
    }

    /**
     * Sets this binary node's left child reference.
     *
     * @param left - reference for setting this node's left child
     */
    public void setLeft(Node<Type> left) {
        this.left = left;
    }

    /**
     * Sets this binary node's right child reference.
     *
     * @param right - reference for setting this node's right child
     */
    public void setRight(Node<Type> right) {
        this.right = right;
    }

    /**
     * Gets the reference to the leftmost node in the binary tree rooted at this
     * binary node.
     *
     * @return reference to the leftmost node in the binary tree rooted at this
     * node
     */
    public Node<Type> getLeftmostNode() {
        Node<Type> curr = this;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    /**
     * Gets the reference to the rightmost node in the binary tree rooted at this
     * binary node.
     *
     * @return reference to the rightmost node in the binary tree rooted at this
     * node
     */
    public Node<Type> getRightmostNode() {
        Node<Type> curr = this;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr;
    }
}
