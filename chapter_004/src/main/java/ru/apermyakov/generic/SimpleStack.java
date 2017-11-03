package ru.apermyakov.generic;

import java.util.NoSuchElementException;

/**
 * Class for simple stack.
 *
 * @author apermyakov
 * @version 1.0
 * @since 03.11.2017
 * @param <T> type of data
 */
public class SimpleStack<T> extends AbstractDeque<T> {

    /**
     * Realize poll method for stack - last in first out.
     *
     * @return poll item
     */
    @Override
    public T poll() {
        try {
            return container.removeItem(container.getLast().getObject());
        } catch (Exception ex) {
            throw new NoSuchElementException("Container has't such object");
        }
    }
}
