package ru.job4j.array;

import java.util.Arrays;

/**
 *  Delete array duplicate.
 *
 *  @author apermyakov
 *  @since 11.10.2017
 *  @version 1.0
 */
public class ArrayDuplicate {

    /**
     * Sort and cut array.
     *
     * @param array base array
     * @return sort and cut array
     * @since 11.10.2017
     */
    public String[] remove(String[] array) {
        int duplicate = 0;

        for (int i = 0; i < array.length - duplicate; i++) {
            for (int j = i + 1; j < array.length - duplicate; j++) {
                if (array[i].equals(array[j])) {
                    array[j] = array[array.length - duplicate - 1];
                    duplicate++;
                    j--;
                }
            }
        }
        return Arrays.copyOf(array, array.length - duplicate);
    }
}
