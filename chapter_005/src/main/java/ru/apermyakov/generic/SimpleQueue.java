package ru.apermyakov.generic;

import java.util.NoSuchElementException;

/**
 * Class for simple queue.
 *
 * @author apermyakov
 * @version 1.0
 * @since 03.11.2017
 * @param <T> type of data
 */
public class SimpleQueue<T> extends AbstractDeque<T> {

    /**
     * Realize poll method for queue - first in first out.
     *
     * @return poll item
     */
    @Override
    public T poll() {
        try {
            return container.removeItem(container.getFirst().getObject());
        } catch (Exception ex) {
            throw new NoSuchElementException("Container has't such object");
        }
    }
}
