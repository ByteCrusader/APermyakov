package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test bubble s.
 *
 * @author apermyakov
 * @since 11.10.2017
 * @version 1.0
 */
public class BubbleSortTest {

    /**
     * Test when array is 51273 then take 12357.
     *
     * @since 11.10.2017
     */
    @Test
    public void whenArray51273ThenTake12357() {
        BubbleSort bubble = new BubbleSort();
        int[] baseArray = new int[] {5, 1, 2, 7, 3};
        int[] resultArray = bubble.sort(baseArray);
        int[] expectArray = new int[] {1, 2, 3, 5, 7};
        assertThat(resultArray, is(expectArray));
    }
}
