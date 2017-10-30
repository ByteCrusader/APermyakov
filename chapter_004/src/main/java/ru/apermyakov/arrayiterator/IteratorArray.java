package ru.apermyakov.arrayiterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class for iterate array.
 *
 * @author apermyakov
 * @version 1.0
 * @since 30.10.2017
 */
public class IteratorArray implements Iterator {

    /**
     * Field for source array.
     */
    private int[][] array;

    /**
     * Field for inside array's counter.
     */
    private int in = 0;

    /**
     * Field for outside array's counter.
     */
    private int out = 0;

    /**
     * Field for array's length counter.
     */
    private int sum = 0;

    /**
     * Field for total array's length.
     */
    private int sourceLength;

    /**
     * Design array's iterator.
     *
     * @param array source array
     */
    public IteratorArray(int[][] array) {
        this.array = array;
        arraySum();
    }

    /**
     * Method for calculate total array's length.
     */
    private void arraySum() {
        for (int[] insideArray : this.array) {
            this.sourceLength += insideArray.length;
        }
    }

    /**
     * Method for override hasNext method.
     *
     * @return result
     */
    @Override
    public boolean hasNext() {
        return sourceLength > sum;
    }

    /**
     * Method for override next method.
     *
     * @return next object in array
     * @throws NoSuchElementException no such element
     */
    @Override
    public Object next() throws NoSuchElementException {
        sum++;
        Object res;
        if (in < array[out].length) {
            res = array[out][in++];
        } else {
            in = 0;
            try {
                res = array[++out][in++];
            } catch (Exception ex) {
                throw new NoSuchElementException("No such element");
            }
        }
        return res;
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
}
