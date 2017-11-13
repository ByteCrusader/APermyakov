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
    private Object[] array = new Object[2];

    /**
     * Field for calculate alive items into array.
     */
    private int aliveItems = 0;

    /**
     * Field for save index of duplicate item.
     */
    private int duplicateIndex = -1;


    /**
     * Method for calculate hash of key.
     *
     * @param key key
     * @return key's hash
     */
    private int calculateHash(T key) {
        int hash = key == null ? 0 : key.hashCode();
		/*
		* HashCodes that differ only by constant multiples at each bit position
		* have a bounded number of collisions (approximately 8 at default load factor).
		*/
        hash ^= (hash >>> 20) ^ (hash >>> 12);
        return hash ^ (hash >>> 7) ^ (hash >>> 4);
    }

    /**
     *Method for build array's index base on key's hash.
     *
     * @param key key
     * @return array's index
     */
    private int buildIndex(T key) {
        if (aliveItems == array.length) {
            increaseArray();
        }
        return calculateHash(key) & array.length - 1;
    }

    /**
     * Method for search duplicate.
     *
     * @param object comparing object
     * @return found or not
     */
    private boolean searchDuplicate(T object) {
        boolean duplicate;
        int index = object == null ? 0 : buildIndex(object);
        int arrayIndexHash = this.array[index] == null ? 0 : calculateHash((T) this.array[index]);
        duplicate = object == null ? arrayIndexHash == 0
                :  calculateHash(object) ==  arrayIndexHash;
        duplicateIndex = duplicate ? index : -1;
        return duplicate;
    }

    /**
     * Method for increase array if add item more then array length.
     */
    private void increaseArray() {
        Object[] oldArray = this.array;
        int oldLength = oldArray == null ? 0 : oldArray.length;
        Object[] newArray = new Object[oldLength * 2];
        for (int index = 0; index < oldLength; index++) {
            Object temporaryObject = oldArray[index];
            if (temporaryObject != null) {
                oldArray[index] = null;
                newArray[calculateHash((T) temporaryObject) & (oldLength * 2 - 1)] = temporaryObject;
            }
        }
        this.array = newArray;
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
        this.array[buildIndex(object)] = object;
        aliveItems++;
        return true;
    }

    /**
     * Method for check and add item if not duplicate.
     *
     * @param insert insert item
     * @return added or not
     */
    public boolean add(T insert) {
        return !searchDuplicate(insert) && addItem(insert);
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
        this.array[duplicateIndex] = null;
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
        return checkItem != null && searchDuplicate(checkItem) && removeItem();
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
