package ru.apermyakov.simpleset;

/**
 * Class for design data to contain in hash set.
 *
 * @author apermyakov
 * @version 1.0
 * @since 07.11.2017
 * @param <T> type of data
 */
public class HashPair<T> {

    /**
     * Field for contain data.
     */
    private T object;

    /**
     * Field for hash code of contain data.
     */
    private int objectHash;

    /**
     * Design hash pair.
     *
     * @param object contain data
     */
    public HashPair(T object) {
        this.object = object;
        this.objectHash = object == null ? 0 : object.hashCode();
    }

    /**
     * Method for get contain data.
     *
     * @return contain data
     */
    public T getObject() {
        return object;
    }

    /**
     * Method for get data hash.
     *
     * @return hash of contain data
     */
    public int getObjectHash() {
        return objectHash;
    }
}
