package ru.apermyakov.generic;

/**
 * Class for design data which will store in simple linked array.
 *
 * @author apermyakov
 * @version 1.2
 * @since 02.11.2017
 * @param <T> storing data type
 */
public class Item<T> {

    /**
     * Field for save link to previous item.
     */
    private Item<T> previous;

    /**
     * Field for save link to next item.
     */
    private Item<T> next;

    /**
     * Field for save storing data.
     */
    private T object;

    /**
     * Design item.
     *
     * @param object storing data
     */
    public Item(T object) {
        this.object = object;
    }

    /**
     * Method for get link for previous item.
     *
     * @return previous item
     */
    public Item<T> getPrevious() {
        return this.previous;
    }

    /**
     * Method for set link for previous item.
     *
     * @param previous link for previous item
     */
    public void setPrevious(Item<T> previous) {
        this.previous = previous;
    }

    /**
     * Method for get link for next item.
     *
     * @return next item
     */
    public Item<T> getNext() {
        return this.next;
    }

    /**
     * Method for set link for next item.
     *
     * @param next link for next item
     */
    public void setNext(Item<T> next) {
        this.next = next;
    }

    /**
     * Method for get storing object.
     *
     * @return storing object
     */
    public T getObject() {
        return object;
    }

    /**
     * Method for eliminate item to garbage collector.
     */
    protected void eliminateItem() {
        this.next = null;
        this.previous = null;
        this.object = null;
    }
}
