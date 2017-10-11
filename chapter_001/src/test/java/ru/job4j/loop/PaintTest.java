package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test pyramid.
 *
 * @author apermyakov
 * @since 11.10.2017
 * @version 1.0
 */
public class PaintTest {

    /**
     * Test when draw pyramid with height two then take string height two and width three.
     *
     * @since 11.10.2017
     */
    @Test
    public void whenPyramidWithHeightTwoThenStringWithTwoThree() {
        Paint paint = new Paint();
        String enter = System.getProperty("line.separator");
        String result = paint.piramid(2);
        String expected = String.format(" ^ %s^^^", enter);
        assertThat(result, is(expected));
    }

    /**
     * Test when draw pyramid with height three then take string height three and width five.
     *
     * @since 11.10.2017
     */
    @Test
    public void whenPiramidWithHeightThreeThenStringWithThreeFive() {
        Paint paint = new Paint();
        String enter = System.getProperty("line.separator");
        String result = paint.piramid(3);
        String expected = String.format("  ^  %s ^^^ %s^^^^^", enter, enter);
        assertThat(result, is(expected));
    }
}
