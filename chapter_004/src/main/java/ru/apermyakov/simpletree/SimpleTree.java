package ru.apermyakov.simpletree;

/**
 * Interface for simple tree actions.
 *
 * @param <T> type of data
 */
public interface SimpleTree<T extends Comparable<T>> extends Iterable<T> {

    /**
     * Add child to parent.
     *
     * @param parent parent
     * @param child child
     * @return add or not
     */
    boolean add(T parent, T child);
}
