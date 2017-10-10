package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PointTest {

    @Test
    public void whenPointOnTheLineThenTakeTrue() {
        Point point = new Point(1,1);
        boolean result = point.is(0,1);
        assertThat(result, is(true));
    }

    @Test
    public void whenPointOutOfTheLineThenTakeFalse() {
        Point point = new Point(5,5);
        boolean result = point.is(1,3);
        assertThat(result, is(false));
    }
}
