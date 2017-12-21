package ru.apermyakov.generic;

import java.util.NoSuchElementException;

/**
 * Abstract class for design queue and stack.
 *
 * @author apermyakov
 * @version 1.0
 * @since 03.11.2017
 * @param <T> type of container
 */
public abstract class AbstractDeque<T> {

    /**
     * Initialize simple linked array container.
     */
    SimpleLinkedArray<T> container = new SimpleLinkedArray<>();

    /**
     * Abstract method for poll item out of container.
     *
     * @return
     */
    abstract T poll() throws NoSuchElementException;

    /**
     * Method for push item to container.
     *
     * @param insert insert item
     */
    public void push(T insert) {
        container.add(insert);
    }
}
