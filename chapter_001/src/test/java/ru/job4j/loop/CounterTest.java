package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *  Test sums of even numbers.
 *
 *  @author apermyakov
 *  @since 11.10.2017
 *  @version 1.0
 */
public class CounterTest {

    /**
     * Test when sum even numbers.
     *
     * @author apermyakov
     * @since 10.10.2017
     */
    @Test
    public void whenSumEvenNumbersFromOneToTenThenTakeThirty() {
        Counter count = new Counter();
        int result = count.add(1, 10);
        assertThat(result, is(30));
    }
}
