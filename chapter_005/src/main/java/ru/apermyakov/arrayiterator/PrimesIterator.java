package ru.apermyakov.arrayiterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class for iterate array by prime items.
 *
 * @author apermyakov
 * @version 1.0
 * @since 30.10.2017
 */
public class PrimesIterator implements Iterator {

    /**
     * Field for source array.
     */
    private int[] array;

    /**
     * Field for array counter.
     */
    private int count = 0;

    /**
     * Field for step by all prime items.
     */
    private int step = 0;

    /**
     * Design array iterator.
     *
     * @param array source array
     */
    public PrimesIterator(int[] array) {
        this.array = array;
    }

    /**
     * Method for check item if item is prime.
     *
     * @return prime or not
     */
    private boolean checkPrimeItem(int item) {
        boolean prime = false;
        if (item > 1) {
            if (item == 2 || item == 3) {
                prime = true;
            } else if (item % 2 != 0) {
                prime = checkMultiplierOfItem(item);
            }
        }
        return prime;
    }

    /**
     * Method for check whether the item is divided by a multiplier.
     *
     * @param item divided or not
     */
    private boolean checkMultiplierOfItem(int item) {
        for (long i = 3; i <= Math.ceil(Math.sqrt(item)); i += 2) {
            if (item % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method for calculate number of primes.
     *
     * @return number of primes
     */
    private int numberOfPrimes() {
        return (int) Arrays.stream(this.array).filter(this::checkPrimeItem).count();
    }

    /**
     * Method for override hasNext method.
     *
     * @return result
     */
    @Override
    public boolean hasNext() {
        return numberOfPrimes() > count;
    }

    /**
     * Method for override next method.
     *
     * @return next object count array
     * @throws NoSuchElementException no such element
     */
    @Override
    public Object next() throws NoSuchElementException {
        if (hasNext()) {
            for (; step < this.array.length; step++) {
                if (checkPrimeItem(this.array[step])) {
                    count++;
                    break;
                }
            }
            return this.array[step++];
        } else {
            throw new NoSuchElementException("No such element");
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
}
