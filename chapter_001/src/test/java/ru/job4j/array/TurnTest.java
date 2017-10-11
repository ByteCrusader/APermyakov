package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Test turn array.
 *
 * @author apermyakov
 * @since 11.10.2017
 * @version 1.0
 */
public class TurnTest {

    /**
     * Test when array is 12345 then take 54321.
     *
     * @since 11.10.2017
     */
    @Test
    public void whenArrayIs12345ThenTake54321() {
        Turn array = new Turn();
        int[] test = new int[] {1, 2, 3, 4, 5};
        int[] resultArray = array.back(test);
        int[] expectArray = new int[] {5, 4, 3, 2, 1};
        assertThat(resultArray, is(expectArray));
    }

    /**
     * Test when array is 4126 then take 2614.
     *
     * @since 11.10.2017
     */
    @Test
    public void whenArrayIs4162ThenTake2614() {
        Turn array = new Turn();
        int[] test = new int[] {4, 1, 6, 2};
        int[] resultArray = array.back(test);
        int[] expectArray = new int[] {2, 6, 1, 4};
        assertThat(resultArray, is(expectArray));
    }
}
