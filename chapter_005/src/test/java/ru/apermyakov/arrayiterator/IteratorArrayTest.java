package ru.apermyakov.arrayiterator;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test array's iterator.
 *
 * @author apermyakov
 * @version 1.0
 * @since 30.10.2017
 */
public class IteratorArrayTest {

    /**
     * Test when has next after last object then false.
     */
    @Test
    public void whenWhenNextAfterLastObjectThenFalse() {
        IteratorArray iterator = new IteratorArray(new int[][] {{1}, {3, 4}, {6}});
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(6));
        assertThat(iterator.hasNext(), is(false));
    }

    /**
     * Test when next after last object then no such element exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void hasNextNextSequentialInvocation() {
        IteratorArray it = new IteratorArray(new int[][]{{1}, {3, 4}});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    /**
     * Test when next all object then has this objects.
     */
    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation() {
        IteratorArray it = new IteratorArray(new int[][]{{1, 2, 3}, {4, 5, 6}});
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(6));
    }

    /**
     * Test when next after last object then no such element exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        IteratorArray it = new IteratorArray(new int[][]{{1, 2, 3}, {4, 5, 6}});
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(6));
        it.next();
    }
}