package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for point on the function.
 *
 * @author apermyakov
 * @version $Id$
 * @since 10.10.2017
 */
public class PointTest {

    /**
     * Test when point on the line and take true.
     *
     * @author apermyakov
     * @since 10.10.2017
     */
    @Test
    public void whenPointOnTheLineThenTakeTrue() {
        Point point = new Point(1, 1);
        boolean result = point.is(0, 1);
        assertThat(result, is(true));
    }

    /**
     * Test when point out of the line and take false.
     *
     * @author apermyakov
     * @since 10.10.2017
     */
    @Test
    public void whenPointOutOfTheLineThenTakeFalse() {
        Point point = new Point(5, 5);
        boolean result = point.is(1, 3);
        assertThat(result, is(false));
    }
}
