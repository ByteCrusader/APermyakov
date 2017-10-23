package ru.apermyakov;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for convert list to array and bask.
 *
 * @author apermyakov
 * @version 1.0
 * @since 23.10.2017
 */
public class ConvertList {

    /**
     * Method for convert array to list.
     *
     * @param array base array
     * @return result list
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] insideArray : array) {
            for (int value : insideArray) {
                list.add(value);
            }
        }
        return list;
    }

    /**
     * Method for convert list to array.
     *
     * @param list base array
     * @param rows number of row of array
     * @return result array
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int[][] result = new int[rows][rows];
        int index = 0;
        int out = 0;
        for (int[] insideArray : result) {
            int in = 0;
            for (int value : insideArray) {
                if (index < list.size()) {
                    result[out][in++] = list.get(index);
                } else {
                    result[out][in++] = 0;
                }
                index++;
            }
            out++;
        }
        return result;
    }

    /**
     * Method for convert array to list.
     *
     * @param list base array
     * @return result list
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] insideArray : list) {
            for (int value : insideArray) {
                result.add(value);
            }
        }
        return result;
    }
}
