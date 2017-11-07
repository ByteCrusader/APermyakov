package ru.apermyakov.simpleset;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class for realize simple set base by array.
 *
 * @author apermyakov
 * @version 1.0
 * @since 07.11.2017
 * @param <T> items type
 */
public class SimpleArraySet<T> implements SimpleSet<T> {

    /**
     * Field for initialize array.
     */
    private Object[] array = new Object[100];

    /**
     * Field for calculate alive items into array.
     */
    private int aliveItems = 0;

    /**
     * Field for iterator counter.
     */
    private int iteratorCounter = 0;

    /**
     * Method for compare insert item with items in array.
     *
     * @param item insert item
     * @return found duplicate ot not
     */
    private boolean searchDuplicate(T item) {
        boolean duplicate;
        int index = 0;
        do {
            duplicate = item == null ? this.array[index] == null : item.equals(this.array[index]);
            index++;
        } while (index < this.aliveItems && !duplicate);
        return duplicate;
    }

    /**
     * Method for increase array if add item more then array length.
     */
    private void increaseArray() {
        this.array = Arrays.copyOf(this.array, this.array.length + this.array.length / 2);
    }

    /**
     * Method for add item into array.
     *
     * @param item insert item
     */
    private void addItem(T item) {
        if (this.aliveItems == this.array.length) {
            increaseArray();
        }
        this.array[aliveItems++] = item;
    }

    /**
     * Method for override add method.
     *
     * @param insert insert item
     */
    @Override
    public void add(T insert) {
        if (aliveItems == 0 || !searchDuplicate(insert)) {
            addItem(insert);
        }
    }

    /**
     * Method for override iterator.
     *
     * @return iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            /**
             * Method for override hasnext.
             *
             * @return has next or not
             */
            @Override
            public boolean hasNext() {
                return iteratorCounter < aliveItems;
            }

            /**
             * Method for override next.
             *
             * @return next item into array
             * @throws NoSuchElementException set has't such element
             */
            @Override
            public T next() throws NoSuchElementException {
                if (hasNext()) {
                    return (T) array[iteratorCounter++];
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
