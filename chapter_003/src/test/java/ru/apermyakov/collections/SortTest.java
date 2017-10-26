package ru.apermyakov.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *Class for test sort hierarchical array.
 *
 *@author apermyakov
 *@version $Id$
 *@since 26.10.2017
 */
public class SortTest {

    /**
     * Test when array in test task ascending sort then add missed and sort.
     */
    @Test
    public void whenArrayInTestTaskAscendingSortThenAddMissedAndSort() {
        String[] test = new String[]{"k1\\sk1", "k1\\sk2", "k1\\sk1\\ssk2", "k1\\sk1\\ssk1", "k2", "k2\\sk1\\ssk2", "k2\\sk1\\ssk1"};
        Sort sort = new Sort();
        String[] expect = new String[]{"k1", "k1\\sk1", "k1\\sk1\\ssk1", "k1\\sk1\\ssk2", "k1\\sk2", "k2", "k2\\sk1", "k2\\sk1\\ssk1", "k2\\sk1\\ssk2"};
        assertThat(sort.sortAscending(test), is(expect));
    }

    /**
     * Test when array in test task decreasing sort then add missed and sort.
     */
    @Test
    public void whenArrayInTestTaskDecreasingSortThenAddMissedAndSort() {
        String[] test = new String[]{"k1\\sk1", "k1\\sk2", "k1\\sk1\\ssk2", "k1\\sk1\\ssk1", "k2", "k2\\sk1\\ssk2", "k2\\sk1\\ssk1"};
        Sort sort = new Sort();
        String[] expect = new String[]{"k2", "k2\\sk1", "k2\\sk1\\ssk2", "k2\\sk1\\ssk1", "k1", "k1\\sk2", "k1\\sk1", "k1\\sk1\\ssk2", "k1\\sk1\\ssk1"};
        assertThat(sort.sortDecreasing(test), is(expect));
    }

    /**
     * Test when array in test task only add missed then misses added into end of array without duplicate.
     */
    @Test
    public void whenArrayInTestTaskOnlyAddMissedThenMissesAddedIntoEndOfArrayWithoutDuplicate() {
        ArrayList<String> test = new ArrayList<>(Arrays.asList("k1\\sk1", "k1\\sk2", "k1\\sk1\\ssk2", "k1\\sk1\\ssk1", "k2", "k2\\sk1\\ssk2", "k2\\sk1\\ssk1"));
        Sort sort = new Sort();
        ArrayList<String> expect = new ArrayList<>(Arrays.asList("k1\\sk1", "k1\\sk2", "k1\\sk1\\ssk2", "k1\\sk1\\ssk1", "k2", "k2\\sk1\\ssk2", "k2\\sk1\\ssk1", "k1", "k2\\sk1"));
        assertThat(sort.addMissed(test), is(expect));
    }

    /**
     * Test when some else array with misses ascending sort.
     */
    @Test
    public void whenSomeElseArrayWithMissesAscendingSort() {
        String[] test = new String[]{"k2", "k2\\sk1\\ssk2", "k2\\sk1\\ssk1"};
        Sort sort = new Sort();
        ArrayList<String> result = new ArrayList<>(Arrays.asList(sort.sortAscending(test)));
        ArrayList<String> expect = new ArrayList<>(Arrays.asList("k2", "k2\\sk1", "k2\\sk1\\ssk1", "k2\\sk1\\ssk2"));
        assertThat(result, is(expect));
    }
}
