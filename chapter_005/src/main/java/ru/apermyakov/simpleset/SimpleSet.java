package ru.apermyakov.simpleset;

/**
 * Interface for design actions of simple sets.
 *
 * @author apermyakov
 * @version 1.0
 * @since 07.11.2017
 * @param <T> items type
 */
public interface SimpleSet<T> extends Iterable<T> {

    /**
     * Method for add items to container.
     *
     * @param insert insert item
     */
    void add(T insert);
}
