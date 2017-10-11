package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test rotate array.
 *
 * @author apermyakov
 * @since 11.10.2017
 * @version 1.0
 */
public class RotateArrayTest {

    /**
     * Test when rotate three line and three column array then take three three rotate array.
     *
     * @since 11.10.2017
     */
    @Test
    public void whenRotateThreeThreeArrayThenTakeThreeThree() {
        RotateArray array = new RotateArray();
        int[][] baseArray = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] resultArray = array.rotate(baseArray);
        int[][] expectArray = new int[][] {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
        assertThat(resultArray, is(expectArray));
    }

    /**
     * Test when rotate two line and two column array then take two two rotate array.
     *
     * @since 11.10.2017
     */
    @Test
    public void whenRotateTwoTwoArrayThenTakeTwoTwo() {
        RotateArray array = new RotateArray();
        int[][] baseArray = new int[][] {{1, 2}, {3, 4}};
        int[][] resultArray = array.rotate(baseArray);
        int[][] expectArray = new int[][] {{3, 1}, {4, 2}};
        assertThat(resultArray, is(expectArray));
    }
}
