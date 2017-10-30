package ru.apermyakov.arrayiterator;

import java.util.ArrayList;
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
     * Field for save all prime items.
     */
    private ArrayList<Integer> primeItems = new ArrayList<>();

    /**
     * Design array iterator.
     *
     * @param array source array
     */
    public PrimesIterator(int[] array) {
        this.array = array;
        checkPrimeItems();
    }

    /**
     * Method for calculate and save index of prime items.
     */
    private void checkPrimeItems() {
        Integer index = 0;
        for (int insideItem : this.array) {
            if (insideItem > 1) {
                if (insideItem == 2 || insideItem == 3) {
                    this.primeItems.add(index);
                } else if (insideItem % 2 != 0) {
                    checkMultiplier(insideItem, index);
                }
            }
            index++;
        }
    }

    /**
     * Method for check whether the item is divided by a multiplier.
     *
     * @param item base item
     * @param index base index of prime item's array
     */
    private void checkMultiplier(int item, int index) {
        for (long i = 3; i <= Math.ceil(Math.sqrt(item)); i += 2) {
            if (item % i == 0) {
                break;
            } else {
                this.primeItems.add(index);
                break;
            }
        }
    }

    /**
     * Method for override hasNext method.
     *
     * @return result
     */
    @Override
    public boolean hasNext() {
        return primeItems.size() > step;
    }

    /**
     * Method for override next method.
     *
     * @return next object count array
     * @throws NoSuchElementException no such element
     */
    @Override
    public Object next() throws NoSuchElementException {
        step++;
        try {
            return array[primeItems.get(count++)];
        } catch (Exception ex) {
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
