package ru.apermyakov.collections;

import java.util.*;

/**
 *Class for sort hierarchical array.
 *
 *@author apermyakov
 *@version $Id$
 *@since 26.10.2017
 */
public class Sort {

    /**
     * Method for add messed items for array.
     *
     * @param src array with misses
     * @return array without missed items
     */
    public ArrayList<String> addMissed(ArrayList<String> src) {
        Set<String> temp = new TreeSet<>();
        for (String value : src) {
            String fg = value.lastIndexOf('\\') > 0 ? value.substring(0, value.lastIndexOf('\\')) : value;
            boolean missedItem = true;
            for (String item : src) {
                if (fg.equals(item)) {
                    missedItem = false;
                    break;
                }
            }
            if (missedItem) {
                temp.add(fg);
            }
        }
        src.addAll(temp);
        return src;
    }

    /**
     * Method for add miss and sort array ascending.
     *
     * @param source unsorted array
     * @return sorted array
     */
    public String[] sortAscending(String[] source) {
        ArrayList<String> result = new ArrayList<>(Arrays.asList(source));
        addMissed(result);
        result.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1 == null ? 1
                        : o2 == null ? -1
                        : (new Comparing(o1, o2)).checkDepartment();
            }
        });
        return result.toArray(new String[result.size()]);
    }

    /**
     * Method for add miss and sort array decreasing.
     *
     * @param source unsorted array
     * @return sorted array
     */
    public String[] sortDecreasing(String[] source) {
        ArrayList<String> result = new ArrayList<>(Arrays.asList(source));
        addMissed(result);
        result.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1 == null ? 1
                        : o2 == null ? -1
                        : (new Comparing(o1, o2)).decreasingCheckDepartment();
            }
        });
        return result.toArray(new String[result.size()]);
    }
}
