package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test from Max.
 *
 * @author apermyakov
 * @version $Id$
 * @since 10.10.2017
 */
public class MaxTest {

    /**
     * Test when first value more than second it's true.
     *
     * @author apermyakov
     * @since 10.10.2017
     */
    @Test
    public void whenFirstMoreThanSecondThenTakeFirst() {
        Max maximum = new Max();
        int result = maximum.max(5, 2);
        assertThat(result, is(5));
    }

    /**
     * Test when first less than second it's true.
     *
     * @author apermyakov
     * @since 10.10.2017
     */
    @Test
    public void whenFirstLessThanSecondThenTakeSecond() {
        Max maximum = new Max();
        int result = maximum.max(2, 5);
        assertThat(result, is(5));
    }

    /**
     * Test when first less than second and third less than second.
     *
     * @author apermyakov
     * @since 11.10.2017
     */
    @Test
    public void whenFirstLessThanSecondAndThirdLessThanSecondThenTakeSecond() {
        Max maximum = new Max();
        int result = maximum.max(3, 5, 4);
        assertThat(result, is(5));
    }

    /**
     * Test when first more than second and third more than second and first more than third.
     *
     * @author apermyakov
     * @since 11.10.2017
     */
    @Test
    public void whenFirstMoreThanSecondAndThirdMoreThanSecondAndFirstMoreThanThirdThenTakeFirst() {
        Max maximum = new Max();
        int result = maximum.max(7, 3, 5);
        assertThat(result, is(7));
    }

    /**
     * Test when first more than second and third less than second.
     *
     * @author apermyakov
     * @since 11.10.2017
     */
    @Test
    public void whenFirstMoreThanSecondAndThirdLessThanSecond() {
        Max maximum = new Max();
        int result = maximum.max(7, 5, 3);
        assertThat(result, is(7));
    }
}
