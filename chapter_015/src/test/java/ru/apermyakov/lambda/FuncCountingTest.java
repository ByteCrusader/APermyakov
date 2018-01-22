package ru.apermyakov.lambda;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for test function counting.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 22.01.2018.
 */
public class FuncCountingTest {

    /**
     * Field for counting.
     */
    private FuncCounting counting = new FuncCounting();

    /**
     * Test when linear function between 1 and 5 then get fourth element of list is 5D.
     */
    @Test
    public  void whenLinearFunctionBetween1And5ThenGetFourthElementOfListIs5D() {
        List<Double> result = counting.diapason(1, 5, x -> (double) ++x);
        assertThat(result.get(3), is(5D));
    }

    /**
     * Test when quadratic function between 1 and 5 then get fourth element of list is 16D.
     */
    @Test
    public  void whenQuadraticFunctionBetween1And5ThenGetFourthElementOfListIs16D() {
        List<Double> result = counting.diapason(1, 5, x -> Math.pow(x, 2));
        assertThat(result.get(3), is(16D));
    }

    /**
     * Test when logarithmic function between 1 and 15 then get fourth element of list is 1D.
     */
    @Test
    public  void whenLogarithmicFunctionBetween1And15ThenGetTenElementOfListIs1D() {
        List<Double> result = counting.diapason(1, 15, Math::log10);
        assertThat(result.get(9), is(1D));
    }
}