package ru.apermyakov.generic;

import java.util.Arrays;

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
     * Field for size of array.
     */
    private int size = 100;

    /**
     * Field for list of data.
     */
    private Object[] array = new Object[size];

    /**
     * Field for calculate index of next alive data.
     */
    private int aliveData = 0;

    /**
     * Method for add data into list.
     *
     * @param insert added data
     */
    public void add(T insert) {
        if (this.aliveData == this.size) {
            this.size += this.size / 2;
            this.array = Arrays.copyOf(this.array, this.size);
        }
        this.array[this.aliveData++] = insert;
    }

    /**
     * Method for update some data.
     *
     * @param old old data
     * @param next new data
     */
    public void update(T old, T next) {
        int index = 0;
        for (Object value : this.array) {
            if (value != null && value.equals(old)) {
                this.array[index] = next;
                break;
            }
            index++;
        }
        if (index + 1 == this.size) {
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
        int index = 0;
        for (Object value : this.array) {
            if (value != null && value.equals(insert)) {
                for (int count = index; count < this.size - 1; count++) {
                    Object temp = this.array[count];
                    this.array[count] = this.array[count + 1];
                    this.array[count + 1] = temp;
                }
                this.array = Arrays.copyOf(this.array, this.size - 1);
                this.aliveData--;
                break;
            }
            index++;
        }
        if (index + 1 == this.size) {
            throw new IllegalArgumentException("Container has't such data.");
        }
    }

    /**
     * Method for get data by aliveData.
     *
     * @param index aliveData of data
     * @return found data
     */
    public T get(int index) throws IllegalArgumentException {
        if (this.array[index] != null) {
            return (T) this.array[index];
        } else {
            throw new IllegalArgumentException("Container has't data on this aliveData.");
        }
    }
}
