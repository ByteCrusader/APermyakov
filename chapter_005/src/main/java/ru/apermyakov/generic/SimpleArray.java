package ru.apermyakov.generic;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class for contain data.
 *
 * @author apermyakov
 * @version 1.1
 * @since 31.10.2017
 * @param <T> type of data.
 */
@ThreadSafe
public class SimpleArray<T> implements SimpleContainer<T> {

    /**
     * Field for list of data.
     */
    @GuardedBy("this")
    private Object[] array = new Object[100];

    /**
     * Field for calculate index of next alive data.
     */
    @GuardedBy("this")
    private int aliveData = 0;

    /**
     * Field for iterator counter.
     */
    @GuardedBy("this")
    private int iteratorCounter = 0;

    /**
     * Method for add data into list.
     *
     * @param insert storing data
     */
    private void addItem(T insert) {
        if (this.aliveData == this.array.length) {
            this.array = Arrays.copyOf(this.array, this.array.length + this.array.length / 2);
        }
        this.array[this.aliveData++] = insert;
    }

    /**
     * Method for override add.
     *
     * @param insert added data
     */
    @Override
    public synchronized void add(T insert) {
        addItem(insert);
    }

    /**
     * Method for update some data.
     *
     * @param old old data
     * @param next new data
     */
    public synchronized void update(T old, T next) {
        int index = 0;
        for (Object value : this.array) {
            if (value != null && value.equals(old)) {
                this.array[index] = next;
                break;
            }
            index++;
        }
        checkIndex(index);
    }

    /**
     * Method for found index of item in array.
     *
     * @param delete needed item
     * @return found index
     */
    private int indexOfItem(T delete) {
        int index = 0;
        for (; index < this.array.length; index++) {
            if (this.array[index] != null && this.array[index].equals(delete)) {
                break;
            }
        }
        return index;
    }

    /**
     * Method for delete data.
     *
     * @param delete deleted data
     */
    public synchronized void delete(T delete) {
        int itemIndex = indexOfItem(delete);
        checkIndex(itemIndex);
        System.arraycopy(this.array, itemIndex + 1, this.array, itemIndex, this.array.length - itemIndex - 1);
    }

    /**
     * Check end of array.
     *
     * @param index index of for
     * @throws IllegalArgumentException container has't such data
     */
    private void checkIndex(int index) throws IllegalArgumentException {
        if (index == this.array.length) {
            throw new IllegalArgumentException("Container has't such data.");
        }
    }

    /**
     * Method for get data by aliveData.
     *
     * @param index aliveData of data
     * @return found data
     */
    @Override
    public synchronized T get(int index) throws IllegalArgumentException {
        if (index >= 0 && index < this.array.length && this.array[index] != null) {
            return (T) this.array[index];
        } else {
            throw new IllegalArgumentException("Container has't data on this index.");
        }
    }

    /**
     * Method for design iterator.
     *
     * @return container's iterator
     */
    @Override
    public synchronized Iterator<T> iterator() {
        return new Iterator<T>() {

            /**
             * Method for override hasnext.
             *
             * @return true if container has next data
             */
            @Override
            public boolean hasNext() {
                return iteratorCounter < array.length;
            }

            /**
             * Method for override next.
             *
             * @return next container's data
             * @throws NoSuchElementException no such element
             */
            @Override
            public T next() throws NoSuchElementException {
                if (hasNext()) {
                    return (T) array[iteratorCounter++];
                } else {
                    throw new NoSuchElementException();
                }
            }

            /**
             * Method for override remove.
             *
             * @throws UnsupportedOperationException operation does't support
             */
            @Override
            public void remove() throws UnsupportedOperationException {
                throw new UnsupportedOperationException("This operation does't support");
            }
        };
    }
}
