package ru.apermyakov.generic;

/**
 * Interface for design container actions.
 *
 * @param <T> type of data
 */
public interface SimpleContainer<T> extends Iterable<T> {

    /**
     * Method for add data to container.
     *
     * @param insert added data
     */
    void add(T insert);

    /**
     * Method for get data out of container.
     *
     * @param index data index
     * @return needed data
     */
    T get(int index);
}
