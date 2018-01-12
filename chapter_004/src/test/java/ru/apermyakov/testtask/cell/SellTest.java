package ru.apermyakov.testtask.cell;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for test sell.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public class SellTest {

    /**
     * Field for sell.
     */
    private Sell sell;

    /**
     * Initial sell.
     */
    @Before
    public void initial() {
        this.sell = new BoardSell(2, 2);
        this.sell.setValueZero();
    }

    /**
     * Test when check value set then take true.
     */
    @Test
    public void whenCheckValueSetThenTakeTrue() {
        assertThat(this.sell.isValueSet(), is(true));
    }

    /**
     * Test when check value is cross then take false.
     */
    @Test
    public void whenCheckValueIsCrossThenTakeFalse() {
        assertThat(this.sell.isCross(), is(false));
    }

    /**
     * Test when get height then take two.
     */
    @Test
    public void whenGetHeightThenTakeTwo() {
        assertThat(this.sell.getHeight(), is(2));
    }

    /**
     * Test when get width then take two.
     */
    @Test
    public void whenGetWidthThenTakeTwo() {
        assertThat(this.sell.getWidth(), is(2));
    }

    /**
     * Test when check is coincidence coordinates one and three then take false.
     */
    @Test
    public void whenCheckIsCoincidenceCoordinatesOneAndThreeThenTakeFalse() {
        assertThat(this.sell.isCoincidence(1, 3), is(false));
    }
}