package ru.apermyakov.generic;

import java.util.*;

/**
 * Class for storing data base by linked array.
 *
 * @author apermyakov
 * @version 1.1
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
     * Method for try get item if container not empty, else exception.
     *
     * @param item item
     * @return item if container not empty
     * @throws NoSuchElementException container is empty
     */
    private T tryGet(Item<T> item) throws NoSuchElementException {
        try {
            return item.getObject();
        } catch (Exception ex) {
            throw new NoSuchElementException("Container is empty");
        }
    }

    /**
     * Method for get first object with time O(1).
     *
     * @return first object
     */
    public T getFirst() throws NoSuchElementException {
        return tryGet(this.first);
    }

    /**
     * Method for get last object with time O(1).
     *
     * @return last object
     */
    public T getLast() throws NoSuchElementException {
        return tryGet(this.last);
    }

    /**
     * Method for get storing data's item by index with time O(n).
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
     * Method for remove first item with time O(1).
     */
    private void removeFirstItemLink() {
        this.first = this.first.getNext();

        if (this.first == null) {
            this.last = null;
        } else {
            this.first.setPrevious(null);
        }
    }

    /**
     * Method for remove last item with time O(1).
     */
    private void removeLastItemLink() {
        this.last = this.last.getPrevious();

        if (this.last == null) {
            this.first = null;
        } else {
            this.last.setNext(null);
        }
    }

    /**
     * Method for remove item inside container with time O(n).
     *
     * @param removable target item
     */
    private void removeInsideItemLink(Item<T> removable) {
        removable.getNext().setPrevious(removable.getPrevious());
        removable.getPrevious().setNext(removable.getNext());
    }

    /**
     * Method for identify place of target item in container.
     *
     * @param removable target item
     * @return object of item
     */
    private T removeLinkItem(Item<T> removable) {
        T temporaryObject = removable.getObject();

        if (removable == this.last) {
            removeLastItemLink();
        } else if (removable == this.first) {
            removeFirstItemLink();
        } else {
            removeInsideItemLink(removable);
        }
        removable.eliminateItem();
        size--;

        return temporaryObject;
    }

    /**
     * Method for remove item by object.
     *
     * @param removableObject object of item
     * @return removed item's object
     */
    public T removeItem(T removableObject) {
        Item<T> removableItem = findItem(removableObject);
        return removeLinkItem(removableItem);
    }

    /**
     * Method for remove item by index.
     *
     * @param index index of item
     * @return object of removed item
     */
    public T removeItem(int index) {
        Item<T> removableItem = findItem(get(index));
        return removeLinkItem(removableItem);
    }

    /**
     * Method for find item by object.
     *
     * @param object item's object
     * @return item
     * @throws NoSuchElementException container has't such object
     */
    private Item<T> findByObject(T object) throws NoSuchElementException {
        for (Item<T> counter = this.first; counter != null; counter = counter.getNext()) {
            if (object.equals(counter.getObject())) {
                return counter;
            }
        }
        throw new NoSuchElementException("Container has't such object");
    }

    /**
     * Method for find item by object and realize O(1) time for first and last item, and O(n) for inside item.
     *
     * @param findObject object of item
     * @return found item
     * @throws IllegalArgumentException incorrect data
     */
    private Item<T> findItem(T findObject) throws  IllegalArgumentException {
        if (findObject != null) {
            return findObject.equals(getFirst()) ? this.first
                    : findObject.equals(getLast()) ? this.last
                    : findByObject(findObject);
        } else {
            throw new IllegalArgumentException("Incorrect data.");
        }
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
