package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test factorial calculation.
 *
 * @author apermyakov
 * @since 11.10.2017
 * @version 1.0
 */
public class FactorialTest {

    /**
     * Test when calculate factorial from five then take one hundred twenty.
     *
     * @author apermyakov
     * @since 11.10.2017
     */
    @Test
    public void whenCalculateFactorialFromFiveThenTakeOneHundredTwenty() {
        Factorial fact = new Factorial();
        int result = fact.calc(5);
        assertThat(result, is(120));
    }

    /**
     * Test when calculate factorial from null then take one.
     *
     * @author apermyakov
     * @since 11.10.2017
     */
    @Test
    public void whenCalculateFactorialFromNullThenTakeOne() {
        Factorial fact = new Factorial();
        int result = fact.calc(0);
        assertThat(result, is(1));
    }
}
