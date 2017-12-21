package ru.apermyakov.arrayiterator;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test array iterator by prime items.
 *
 * @author apermyakov
 * @version 1.0
 * @since 30.10.2017
 */
public class PrimesIteratorTest {

    /**
     * Test when next after last prime item then exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenNextAfterLastPrimeItemThenException() {
        PrimesIterator it = new PrimesIterator(new int[]{1, 2, 3, 4, 5, 6, 7, 3571});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3571));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    /**
     * Test when hasnext hasnext next then adequate behavior.
     */
    @Test
    public void whenHasnextHasnextNextThenAdequateBehavior() {
        PrimesIterator it = new PrimesIterator(new int[]{1, 2, 3, 4, 5, 6, 7, 3571});
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(7));
        assertThat(it.next(), is(3571));
    }

    /**
     * Test when set of non prime numbers then false.
     */
    @Test
    public void whenSetOfNonPrimeNumbersThenFalse() {
        PrimesIterator it = new PrimesIterator(new int[]{4, 6, 78, 234, 14, 698, 16, 32, 48, 56, 99});
        assertThat("should return false, cause there is no any prime number", it.hasNext(), is(false));
    }

}