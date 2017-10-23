package ru.apermyakov;

import java.util.Collection;

/**
 * Class for check time of collections.
 *
 * @author apermyakov
 * @version 1.0
 * @since 23.10.2017
 */
public class Time {

    /**
     * Method for check time of add items.
     *
     * @param collection base collection
     * @param amount amount of items
     * @return time of add items
     */
    public long add(Collection<String> collection, int amount) {
        long start = System.nanoTime();
        for (int i = 0; i < amount; i++) {
            collection.add("There is some text in case " + i);
        }
        long end = System.nanoTime();
        return end - start;
    }

    /**
     * Method for check time of delete items.
     *
     * @param collection base collection
     * @param amount amount of items
     * @return time of delete items
     */
    public long delete(Collection<String> collection, int amount) {
        long start = System.nanoTime();
        for (int i = 0; i < amount; i++) {
            collection.remove("There is some text in case " + i);
        }
        long end = System.nanoTime();
        return end - start;
    }
}
