package ru.apermyakov.generic;

import java.util.*;

/**
 * Class for storing data base by linked array.
 *
 * @author apermyakov
 * @version 1.0
 * @since 02.11.2017
 * @param <T> storing data type
 */
public class SimpleLinkedArray<T> implements SimpleContainer<T> {

    /**
     * Field for save size of linked array.
     */
    private int size = 0;

    /**
     * Filed for save first item in array.
     */
    private Item<T> first;

    /**
     * Field for save last item in array.
     */
    private Item<T> last;

    /**
     * Field for iterator's counter.
     */
    private int iteratorCounter = 0;

    /**
     * Method for add item for array.
     *
     * @param insert storing data
     */
    private void addItem(T insert) {
        Item<T> temporaryItem = this.last;
        Item<T> newItem = new Item<>(insert);
        newItem.setPrevious(temporaryItem);
        this.last = newItem;
        if (temporaryItem == null) {
            this.first = newItem;
        } else {
            temporaryItem.setNext(newItem);
        }
        size++;
    }

    /**
     * Method for override add method for store data.
     *
     * @param insert added data
     */
    @Override
    public void add(T insert) {
        addItem(insert);
    }

    /**
     * Method for get storing data's item by index.
     *
     * @param index index of storing data's item
     * @return storing data's item
     * @throws IndexOutOfBoundsException index out of array
     */
    private Item<T> getItem(int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index < this.size) {
            if (index < size / 2) {
                Item<T> foundItem = this.first;
                for (int count = 0; count < index; count++) {
                    foundItem = foundItem.getNext();
                }
                return foundItem;
            } else {
                Item<T> foundItem = this.last;
                for (int count = this.size - 1; count > index; count--) {
                    foundItem = foundItem.getPrevious();
                }
                return foundItem;
            }
        } else {
            throw new IndexOutOfBoundsException("Container has't data on this index.");
        }
    }

    /**
     * Method for get storing data from found data's item.
     *
     * @param index data index
     * @return storing data from found item
     */
    @Override
    public T get(int index) {
        return getItem(index).getObject();
    }

    /**
     * Method for override list iterator.
     *
     * @return list iterator
     */
    @Override
    public ListIterator<T> iterator() {
        return new ListIterator<T>() {

            /**
             * Field for save current item in iterator.
             */
            private Item<T> next = (iteratorCounter == size) ? null : getItem(iteratorCounter);

            /**
             * Field for save last returned item in iterator.
             */
            private Item<T> returned;

            /**
             * Method for override has next.
             *
             * @return has iterator current item or not
             */
            @Override
            public boolean hasNext() {
                return iteratorCounter < size;
            }

            /**
             * Method for override next.
             *
             * @return current item and go next
             * @throws NoSuchElementException no current element
             */
            @Override
            public T next() throws NoSuchElementException {
                if (hasNext()) {
                    this.returned = this.next;
                    this.next = this.next.getNext();
                    iteratorCounter++;
                    return this.returned.getObject();
                } else {
                    throw new NoSuchElementException();
                }
            }

            /**
             * Method for override has previous method.
             *
             * @return has previous item ot not
             */
            @Override
            public boolean hasPrevious() {
                return iteratorCounter > 0;
            }

            /**
             * Method for override previous method.
             *
             * @return previous item
             */
            @Override
            public T previous() {
                if (hasPrevious()) {
                    this.next = (this.next == null) ? last : next.getPrevious();
                    this.returned = this.next;
                    iteratorCounter--;
                    return this.returned.getObject();
                } else {
                    throw new NoSuchElementException();
                }
            }

            /**
             * Method for return index of next item or size of array.
             *
             * @return index of next item or size of array
             */
            @Override
            public int nextIndex() {
                return (iteratorCounter < size) ? iteratorCounter : size;
            }

            /**
             * Method for return index of previous item or -1.
             *
             * @return index of previous item or -1
             */
            @Override
            public int previousIndex() {
                return (iteratorCounter > 0) ? iteratorCounter - 1 : -1;
            }

            /**
             * Method for override remove, in this realize of iterator this method does't support.
             *
             * @throws UnsupportedOperationException this operation does't support in this realize
             */
            @Override
            public void remove() throws UnsupportedOperationException {
                throw new UnsupportedOperationException("This operation does't support in this realize");
            }

            /**
             * Method for override remove, in this realize of iterator this method does't support.
             *
             * @throws UnsupportedOperationException this operation does't support in this realize
             */
            @Override
            public void set(T t) throws UnsupportedOperationException {
                throw new UnsupportedOperationException("This operation does't support in this realize");
            }

            /**
             * Method for override remove, in this realize of iterator this method does't support.
             *
             * @throws UnsupportedOperationException this operation does't support in this realize
             */
            @Override
            public void add(T t) throws UnsupportedOperationException {
                throw new UnsupportedOperationException("This operation does't support in this realize");
            }
        };
    }
}
