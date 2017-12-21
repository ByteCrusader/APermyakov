package ru.apermyakov.simpletree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for realize our oun tree.
 *
 * @author apermyakov
 * @version 1.0
 * @since 09.11.2017
 * @param <T> type of data
 */
public class Tree<T extends Comparable<T>> implements SimpleTree<T> {

    /**
     * Class for collect data into tree.
     *
     * @param <T> data type
     */
    class Node<T> {

        /**
         * Filed for children.
         */
        List<Node<T>> children;

        /**
         * Filed for value.
         */
        T value;

        /**
         * Design Node.
         *
         * @param value value
         */
        Node(T value) {
            this.value = value;
            this.children = new LinkedList<>();
        }
    }

    /**
     * Field for iterator counter.
     */
    private int iteratorCounter = 0;

    /**
     * Field for root of tree
     */
    private Node<T> root = new Node<>(null);


    /**
     * Method for rebase root to new parent.
     *
     * @param parent new parent
     */
    private void addParent(T parent) {
        T transportValue = this.root.value;
        List<Node<T>> transportList = this.root.children;

        this.root.value = parent;
        this.root.children = new LinkedList<>();

        Node<T> transportNode = new Node<>(transportValue);
        transportNode.children = transportList;
        this.root.children.add(transportNode);
    }

    /**
     * Method for add child to root.
     *
     * @param child child
     */
    private void addChild(T child) {
        if (checkChildren(this.root, child)) {
            this.root.children.add(new Node<>(child));
        }
    }

    /**
     * Method for check parents in children.
     *
     * @param children children
     * @param parent parent
     * @return found parent or null
     */
    private Node<T> checkParents(List<Node<T>> children, T parent) {
        Node<T> result = null;
        for (Node<T> steps : children) {
            if (parent.compareTo(steps.value) == 0) {
                result = steps;
                break;
            }
        }
        return result;
    }

    /**
     * Method for check child in parent.
     *
     * @param parent parent
     * @param child child
     * @return exist or not
     */
    private boolean checkChildren(Node<T> parent, T child) {
        boolean result = true;
        for (Node<T> steps : parent.children) {
            if (child.compareTo(steps.value) == 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Recursive method for find parent inside other parents.
     *
     * @param children children
     * @param parent parent
     * @return found parent or null
     */
    private Node<T> recursiveParentCheck(List<Node<T>> children, T parent) {
        Node<T> result = null;
        for (Node<T> steps : children) {
            if (steps.children.size() != 0) {
                result = recursiveParentCheck(steps.children, parent);
            } else {
                if (parent.compareTo(steps.value) == 0) {
                    result = steps;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Method for check child by all tree.
     *
     * @param parent parent
     * @param child child
     */
    private void checkChild(T parent, T child) {

        Node<T> checkedParent = checkParents(this.root.children, parent);
        if (checkedParent == null) {
            Node<T> transportNode = recursiveParentCheck(this.root.children, parent);
            if (transportNode != null) {
                transportNode.children.add(new Node<>(child));
            } else {
                Node<T> newNode = new Node<>(parent);
                newNode.children.add(new Node<>(child));
                this.root.children.add(newNode);
            }
        } else {
            if (checkChildren(checkedParent, child)) {
                checkedParent.children.add(new Node<>(child));
            }
        }
    }

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
     * Method for compare root with parent and choose action.
     *
     * @param parent parent
     * @param child child
     * @return true
     */
    private boolean compareRoot(T parent, T child) {
        int compareResult = parent != null ? this.root.value.compareTo(parent) : throwIAE();

        if (compareResult < 0) {
            addParent(parent);
        } else if (compareResult == 0 || this.root.children.size() == 0) {
            addChild(child);
        } else {
            checkChild(parent, child);
        }
        return true;
    }

    /**
     * Method for check insert child for unique.
     *
     * @param child insert child
     * @return unique or not
     */
    private boolean uniqueChild(T child) {
        return !toList().contains(child);
    }

    /**
     * Method for check unique child and add item.
     *
     * @param parent parent
     * @param child child
     * @return add or not
     */
    private boolean checkAndAdd(T parent, T child) {
        if (this.root.value == null) {
            this.root.value = parent;
        }

        return uniqueChild(child) && compareRoot(parent, child);
    }

    /**
     * Method for initialize add actions.
     *
     * @param parent parent
     * @param child  child
     * @return add or not
     */
    @Override
    public boolean add(T parent, T child) {
        return checkAndAdd(parent, child);
    }

    /**
     * Method for recursive search children size.
     *
     * @param children children
     * @return binary or not
     */
    private boolean recursiveIsBinary(List<Node<T>> children) {
        boolean result = true;
        for (Node<T> steps : children) {
            if (steps.children.size() > 2) {
                result = false;
                break;
            }
            if (steps.children.size() != 0) {
                recursiveIsBinary(steps.children);
            }
        }
        return result;
    }

    /**
     * Method for build check binary action.
     *
     * @return binary or not
     */
    public boolean isBinary() {
        return this.root.children.size() <= 2 && recursiveIsBinary(this.root.children);
    }

    /**
     * Recursive method for save tree's items to list.
     *
     * @param children tree of children
     * @return list of children
     */
    private LinkedList<T> recursiveToList(List<Node<T>> children) {
        LinkedList<T> result = new LinkedList<>();
        for (Node<T> steps : children) {
            result.add(steps.value);
            if (steps.children.size() != 0) {
                result.addAll(recursiveToList(steps.children));
            }
        }
        return result;
    }

    /**
     * Method for convert tree to list.
     *
     * @return list
     */
    private LinkedList<T> toList() {
        LinkedList<T> result = new LinkedList<>();
        result.add(this.root.value);
        result.addAll(recursiveToList(this.root.children));
        return result;
    }

    /**
     * Override iterator.
     *
     * @return new iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            /**
             * Field for
             */
            LinkedList<T> list = toList();

            /**
             * @return
             */
            @Override
            public boolean hasNext() {
                return iteratorCounter < list.size();
            }

            /**
             * @return
             */
            @Override
            public T next() {
                return list.get(iteratorCounter++);
            }
        };
    }
}
