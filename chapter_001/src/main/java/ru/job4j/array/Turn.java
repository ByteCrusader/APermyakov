package ru.job4j.array;

/**
 *  Turn array.
 *
 *  @author apermyakov
 *  @since 11.10.2017
 *  @version 1.0
 */
public class Turn {

    /**
     * Method for turn array.
     *
     * @author apermyakov
     * @param array base array
     * @return result array
     * @since 11.10.2017
     */
    public int[] back(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
        return array;
    }
}
