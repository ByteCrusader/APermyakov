package ru.apermyakov.arrayiterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class for convert Iterator of iterators to one iterator.
 *
 * @author apermyakov
 * @version 1.0
 * @since 31.10.2017
 */
public class Converter {
    /**
     * Method for convert Iterator.
     *
     * @param it iterator of iterators
     * @return one iterator
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {

            /**
             * Field for save current iterator.
             */
            private Iterator<Integer> currentIterator = it.hasNext() ? it.next() : null;

            /**
             * Field for save current value of iterator.
             */
            private Integer value = null;

            /**
             * Field for condition of work.
             */
            private boolean work = it.hasNext();

            /**
             * Method for override hasNext method.
             *
             * @return result
             */
            @Override
            public boolean hasNext() {
                return work;
            }
            /**
             * Method for override next method.
             *
             * @return next object count array
             * @throws NoSuchElementException no such element
             */
            @Override
            public Integer next() throws NoSuchElementException {
                if (hasNext()) {
                    if (currentIterator != null) {
                        value = currentIterator.next();
                        if (!currentIterator.hasNext()) {
                            currentIterator = null;
                            work = false;
                        }
                    }
                    if (currentIterator == null && it.hasNext()) {
                        currentIterator = it.next();
                        work = true;
                    }
                } else {
                    throw new NoSuchElementException();
                }
                return value;
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
