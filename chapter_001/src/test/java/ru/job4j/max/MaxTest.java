package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxTest {

    @Test
    public void whenFirstMoreThanSecondThenTakeFirst() {
        Max maximum = new Max();
        int result = maximum.max(5,2);
        assertThat(result, is(5));
    }

    @Test
    public void whenFirstLessThanSecondThenNakeSecond() {
        Max maximum = new Max();
        int result = maximum.max(2,5);
        assertThat(result, is(5));
    }
}
