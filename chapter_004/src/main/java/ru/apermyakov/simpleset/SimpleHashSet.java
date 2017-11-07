package ru.apermyakov.simpleset;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Class for realize simple set base by hash table.
 *
 * @author apermyakov
 * @version 1.0
 * @since 07.11.2017
 * @param <T> data type
 */
public class SimpleHashSet<T> {

    /**
     * Field for array of hash pairs.
     */
    private HashPair[] array = new HashPair[100];

    /**
     * Field for calculate alive items into array.
     */
    private int aliveItems = 0;

    /**
     * Field for save index of duplicate item.
     */
    private int duplicateIndex = -1;

    /**
     * Method for search duplicate.
     *
     * @param object comparing object
     * @return found or not
     */
    private boolean searchDuplicate(T object) {
        boolean duplicate;
        int index = 0;
        do {
            duplicate = object == null ? this.array[index].getObjectHash() == 0
                    : object.hashCode() == this.array[index].getObjectHash();
            duplicateIndex = duplicate ? index : -1;
            index++;
        } while (index < aliveItems && !duplicate);
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
     * @param object added item
     * @return true
     */
    private boolean addItem(T object) {
        if (this.aliveItems == this.array.length) {
            increaseArray();
        }
        this.array[aliveItems++] = new HashPair(object);
        return true;
    }

    /**
     * Method for check and add item if not duplicate.
     *
     * @param insert insert item
     * @return added or not
     */
    public boolean add(T insert) {
        return (aliveItems == 0 || !searchDuplicate(insert)) && addItem(insert);
    }

    /**
     * Method for throw no such element exception.
     *
     * @return exception
     * @throws NoSuchElementException set is empty
     */
    private boolean throwNoSuchElExc() throws NoSuchElementException {
        throw new NoSuchElementException("Set is empty");
    }

    /**
     * Method for check contains item into array.
     *
     * @param comparable comparable item
     * @return contain or not
     */
    public boolean contains(T comparable) {
        return aliveItems != 0 ? searchDuplicate(comparable) : throwNoSuchElExc();
    }

    /**
     * Method for remove item.
     *
     * @return true
     */
    private boolean removeItem() {
        System.arraycopy(this.array, duplicateIndex + 1, this.array, duplicateIndex, this.array.length - duplicateIndex - 1);
        duplicateIndex = -1;
        aliveItems--;
        return true;
    }

    /**
     * Method for check item and if contain then remove.
     *
     * @param checkItem check item
     * @return remove or not
     */
    private boolean checkRemove(T checkItem) {
        return searchDuplicate(checkItem) && removeItem();
    }

    /**
     * Method for check item and remove.
     *
     * @param removable remove item
     * @return remove or not
     */
    public boolean remove(T removable) {
        return aliveItems != 0 ? checkRemove(removable) : throwNoSuchElExc();
    }
}
