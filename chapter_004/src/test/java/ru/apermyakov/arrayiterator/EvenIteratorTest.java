package ru.apermyakov.arrayiterator;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test array iterator by even items.
 *
 * @author apermyakov
 * @version 1.0
 * @since 30.10.2017
 */
public class EvenIteratorTest {

    /**
     * Test when only even number and next after last item then exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenOnlyEvenNumberAndNextAfterLastItemThenException() {
        EvenIterator it = new EvenIterator(new int[] {1, 2, 3, 4, 5, 6, 7});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    /**
     * Test when check all even items then assert.
     */
    @Test
    public void whenCheckAllEvenItemsThenAssert() {
        EvenIterator it = new EvenIterator(new int[] {1, 2, 3, 4, 5, 6, 7});
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(6));
    }

    /**
     * Test when non of even items then false.
     */
    @Test
    public void whenNonOfEvenItemsThenFalse() {
        EvenIterator it = new EvenIterator(new int[]{1});
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Test when iterator remove item then exception.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void whenIteratorRemoveItemThenException() {
        EvenIterator it = new EvenIterator(new int[] {1, 2, 3, 4, 5, 6, 7});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        it.remove();
    }
}