package ru.apermyakov.simpleset;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test simple set base by array.
 *
 * @author apermyakov
 * @version 1.0
 * @since 07.11.2017
 */
public class SimpleArraySetTest {

    /**
     * Test when add some items and iterator next out of array then exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenAddSomeItemsAndIteratorNextOutOfArrayThenException() {
        SimpleSet<String> set = new SimpleArraySet<>();
        set.add("Ivan");
        set.add("Petr");
        set.add("Master");
        set.add("Master");
        set.add("Praise the sun");
        assertThat(set.iterator().next(), is("Ivan"));
        assertThat(set.iterator().next(), is("Petr"));
        assertThat(set.iterator().next(), is("Master"));
        assertThat(set.iterator().next(), is("Praise the sun"));
        set.iterator().next();
    }

    /**
     * Test when add more then 100 items and some null then increase and contain one null.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenAddMoreThen100ItemsAndSomeNullThenIncreaseAndContainOneNull() {
        SimpleSet<String> set = new SimpleArraySet<>();
        set.add("Ivan");
        set.add("Petr");
        set.add(null);
        set.add(null);
        set.add("Master");
        set.add("Master");
        for (int index = 1; index < 110; index++) {
            set.add("Skipper " + index);
        }
        set.add("Praise the sun");
        assertThat(set.iterator().next(), is("Ivan"));
        assertThat(set.iterator().next(), is("Petr"));
        String result = null;
        assertThat(set.iterator().next(), is(result));
        assertThat(set.iterator().next(), is("Master"));
        for (int index = 1; index < 110; index++) {
            assertThat(set.iterator().next(), is("Skipper " + index));
        }
        assertThat(set.iterator().next(), is("Praise the sun"));
        set.iterator().next();
    }
}