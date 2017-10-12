package ru.job4j.test;

/**
 * Class for adds two sorted arrays.
 *
 * @author apermyakov
 * @version 1.0
 * @since 12.10.2017
 */
public class SortedArrays {

    /**
     * Create new array and add arrays into new array ascending.
     *
     * @param first first array
     * @param second second array
     * @return third sorted array
     * @since 12.10.2017
     */
    public int[] add(int[] first, int[] second) {
        int[] result = new int[first.length + second.length];
        int firstIndex = 0;
        int secondIndex = 0;

        for (int out = 0; out < result.length; out++) {

            if (secondIndex > second.length - 1) {
                result[out] = first[firstIndex];
                firstIndex++;
            } else if (firstIndex > first.length - 1) {
                result[out] = second[secondIndex];
                secondIndex++;
            } else if (first[firstIndex] < second[secondIndex]) {
                result[out] = first[firstIndex];
                firstIndex++;
            } else {
                result[out] = second[secondIndex];
                secondIndex++;
            }
        }
        return result;
    }
}
