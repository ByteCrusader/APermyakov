package ru.apermyakov.arrayiterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class for iterate array by even items.
 *
 * @author apermyakov
 * @version 1.0
 * @since 30.10.2017
 */
public class EvenIterator implements Iterator {

    /**
     * Field for source array.
     */
    private int[] array;

    /**
     * Field for array counter.
     */
    private int count = 0;

    /**
     * Field for step by all even items.
     */
    private int step = 0;

    /**
     * Field for save all even items.
     */
    private  ArrayList<Integer> evenItems = new ArrayList<>();

    /**
     * Design array iterator.
     *
     * @param array source array
     */
    public EvenIterator(int[] array) {
        this.array = array;
        checkEvenItems();
    }

    /**
     * Method for calculate and save index of even items.
     */
    private void checkEvenItems() {
        Integer index = 0;
        for (int insideItem : this.array) {
            if (insideItem % 2 == 0) {
                this.evenItems.add(index);
            }
            index++;
        }
    }

    /**
     * Method for override hasNext method.
     *
     * @return result
     */
    @Override
    public boolean hasNext() {
        return evenItems.size() > step;
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
            return array[evenItems.get(count++)];
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

