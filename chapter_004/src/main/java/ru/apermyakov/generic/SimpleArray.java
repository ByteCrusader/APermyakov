package ru.apermyakov.generic;

import java.util.LinkedList;

/**
 * Class for contain data.
 *
 * @author apermyakov
 * @version 1.0
 * @since 31.10.2017
 * @param <T> type of data.
 */
public class SimpleArray<T> {

    /**
     * Field for list of data.
     */
    private LinkedList<T> array = new LinkedList<>();

    /**
     * Method for add data into list.
     *
     * @param insert added data
     */
    public void add(T insert) {
        this.array.add(insert);
    }

    /**
     * Method for update some data.
     *
     * @param old old data
     * @param next new data
     */
    public void update(T old, T next) {
        int index = 0;
        for (T value : this.array) {
            if (value.equals(old)) {
                this.array.set(index, next);
                break;
            }
            index++;
        }
        if (index == this.array.size()) {
            throw new IllegalArgumentException("Container has't data, that need to be update.");
        }
    }

    /**
     * Method for delete data.
     *
     * @param insert deleted data
     * @throws IllegalArgumentException container has't such data
     */
    public void delete(T insert) throws IllegalArgumentException {
        if (this.array.contains(insert)) {
            this.array.remove(insert);
        } else {
            throw new IllegalArgumentException("Container has't such data.");
        }
    }

    /**
     * Method for get data by index.
     *
     * @param index index of data
     * @return found data
     */
    public T get(int index) throws IllegalArgumentException {
        try {
            return this.array.get(index);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Container has't data on this index.");
        }
    }
}
