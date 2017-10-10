package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for simple calculator.
 *
 * @author apermyakov
 * @version $Id$
 * @since 10.10.2017
 */
public class CalculatorTest {

    /**
     * Test when five add to five and take ten.
     *
     * @author apermyakov
     * @since 10.10.2017
     */
    @Test
    public void whenAddFiveToFiveThenTakeTen() {
        Calculator calc = new Calculator();
        calc.add(5D, 5D);
        double result = calc.getResult();
        double expected = 10D;
        assertThat(result, is(expected));
    }

    /**
     * Test when subtract one into five and take four.
     *
     * @author apermyakov
     * @since 10.10.2017
     */
    @Test
    public void whenSubtractOneIntoFiveThenTakeFour() {
        Calculator calc = new Calculator();
        calc.subtract(5D, 1D);
        double result = calc.getResult();
        double expected = 4D;
        assertThat(result, is(expected));
    }

    /**
     * Test when ten div five and take two.
     *
     * @author apermyakov
     * @since 10.10.2017
     */
    @Test
    public void whenDivTenToFiveThenTakeTwo() {
        Calculator calc = new Calculator();
        calc.div(10D, 5D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    /**
     * Test when five multiple two and take ten.
     *
     * @author apermyakov
     * @since 10.10.2017
     */
    @Test
    public  void whenMultipleFiveTwoThenTakeTen() {
        Calculator calc = new Calculator();
        calc.multiple(5D, 2D);
        double result = calc.getResult();
        double expected = 10D;
        assertThat(result, is(expected));
    }
}
