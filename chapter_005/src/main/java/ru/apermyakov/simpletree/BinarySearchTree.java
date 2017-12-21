package ru.apermyakov.simpletree;

/**
 * Class for realize binary search tree.
 *
 * @author apermyakov
 * @version 1.0
 * @since 10.11.2017
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<T>> {

    /**
     * Class for collect data into tree.
     *
     * @param <T> data type
     */
    class Node<T> {

        /**
         * Field for save smaller item.
         */
        Node<T> left;

        /**
         * Field for save bigger item.
         */
        Node<T> right;

        /**
         * Filed for save parent item.
         */
        Node<T> parent;

        /**
         * Filed for item.
         */
        T item;

        /**
         * Design Node.
         *
         * @param item item
         */
        Node(T item) {
            this.item = item;
        }
    }

    /**
     * Field for root of tree
     */
    private Node<T> root = new Node<>(null);

    /**
     * Method for throw illegal argument exception.
     *
     * @return nothing
     * @throws IllegalArgumentException this tree does't support null objects
     */
    private int throwIAE() throws IllegalArgumentException {
        throw new IllegalArgumentException("This tree does't support null objects");
    }

    /**
     * Method for add bigger or smaller item.
     *
     * @param root root for item
     * @param item insert item
     * @param right bigger or smaller item?
     */
    private void addItem(Node<T> root, T item, boolean right) {
        Node<T> newNode = new Node<>(item);
        if (right) {
            root.right = newNode;
        } else {
            root.left = newNode;
        }
        newNode.parent = root;
    }

    /**
     * Method for compare item with his root.
     *
     * @param root item's root
     * @param item item
     */
    private void compareItem(Node<T> root, T item) {
        int compareResult = item != null ? item.compareTo(root.item) : throwIAE();

        if (compareResult > 0) {
            if (root.right != null) {
                compareItem(root.right, item);
            } else {
                addItem(root, item, true);
            }
        } else {
            if (root.left != null) {
                compareItem(root.left, item);
            } else {
                addItem(root, item, false);
            }
        }
    }

    /**
     * Method for check does root exist?
     *
     * @param item insert item
     * @return true
     */
    private boolean checkRoot(T item) {
        if (this.root.item == null) {
            this.root.item = item;
        } else {
            compareItem(this.root, item);
        }
        return true;
    }

    /**
     * Method for initialize add action.
     *
     * @param item insert item
     * @return insert or not
     */
    public boolean add(T item) {
        return checkRoot(item);
    }
}
