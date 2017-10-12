package ru.job4j.test;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test add sorted arrays.
 *
 * @author apermyakov
 * @since 12.10.2017
 * @version 1.0
 */
public class SortedArraysTest {

    /**
     * Test when first array add second array with equal length then take third sorted array.
     *
     * @since 12.10.2017
     */
    @Test
    public void whenFirstArrayAddSecondArrayThenTakeThirdArrayOne() {
        SortedArrays arrays = new SortedArrays();
        int[] first = new int[] {1, 2, 3, 4, 5};
        int[] second = new int[] {6, 7, 8, 9, 10};
        int[] result = arrays.add(first, second);
        int[] expect = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertThat(result, is(expect));
    }

    /**
     * Test when first array add second array with equal length then take third sorted array.
     *
     * @since 12.10.2017
     */
    @Test
    public void whenFirstArrayAddSecondArrayThenTakeThirdArrayTwo() {
        SortedArrays arrays = new SortedArrays();
        int[] first = new int[] {1, 3, 5, 7, 9};
        int[] second = new int[] {2, 4, 6, 8, 10};
        int[] result = arrays.add(first, second);
        int[] expect = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertThat(result, is(expect));
    }

    /**
     * Test when first array add second array with different length then take third sorted array.
     *
     * @since 12.10.2017
     */
    @Test
    public void whenFirstArrayAddSecondArrayHaveDifferentLengthThenTakeThirdArrayTwo() {
        SortedArrays arrays = new SortedArrays();
        int[] first = new int[] {1, 3, 5, 7, 9};
        int[] second = new int[] {2, 4, 6};
        int[] result = arrays.add(first, second);
        int[] expect = new int[] {1, 2, 3, 4, 5, 6, 7, 9};
        assertThat(result, is(expect));
    }
}
