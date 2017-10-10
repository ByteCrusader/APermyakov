package ru.job4j.condition;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

/**
 * Test for area of triangle.
 *
 * @author apermyakov
 * @version $Id$
 * @since 10.10.2017
 */
public class TriangleTest {

    /**
     * Test when area set three points and calculate area of triangle.
     *
     * @author apermyakov
     * @since 10.10.2017
     */
    @Test
    public void whenAreaSetThreePointsThenCalculateTriangleArea() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        Point c = new Point(2, 0);
        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.area();
        double expected = 2D;
        assertThat(result, closeTo(expected, 0.1));
    }
}
