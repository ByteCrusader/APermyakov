package ru.job4j.array;

/**
 *  Bubble sort array.
 *
 *  @author apermyakov
 *  @since 11.10.2017
 *  @version 1.0
 */
public class BubbleSort {

    /**
     * Sort array.
     *
     * @author apermyakov
     * @param array base array
     * @return result array
     * @since 11.10.2017
     */
    public int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array [j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }
}
