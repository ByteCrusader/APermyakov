package ru.apermyakov.simpleset;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test simple set.
 *
 * @author apermyakov
 * @version 1.2
 * @since 07.11.2017
 */
public class SimpleSetTest {

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

    /**
     * Test when add some linked items and iterator next out of array then exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenAddSomeLinkedItemsAndIteratorNextOutOfArrayThenException() {
        SimpleLinkedSet<String> linkedSet = new SimpleLinkedSet<>();
        linkedSet.add("Ivan");
        linkedSet.add("Petr");
        linkedSet.add("Master");
        linkedSet.add("Master");
        linkedSet.add("Praise the sun");
        assertThat(linkedSet.iterator().previousIndex(), is(-1));
        assertThat(linkedSet.iterator().hasPrevious(), is(false));
        assertThat(linkedSet.iterator().hasNext(), is(true));
        assertThat(linkedSet.iterator().nextIndex(), is(0));
        assertThat(linkedSet.iterator().next(), is("Ivan"));
        assertThat(linkedSet.iterator().next(), is("Petr"));
        assertThat(linkedSet.iterator().next(), is("Master"));
        assertThat(linkedSet.iterator().next(), is("Praise the sun"));
        assertThat(linkedSet.iterator().hasNext(), is(false));
        assertThat(linkedSet.iterator().nextIndex(), is(4));
        assertThat(linkedSet.iterator().hasPrevious(), is(true));
        assertThat(linkedSet.iterator().previousIndex(), is(3));
        assertThat(linkedSet.iterator().previous(), is("Praise the sun"));
        assertThat(linkedSet.iterator().previous(), is("Master"));
        assertThat(linkedSet.iterator().previous(), is("Petr"));
        assertThat(linkedSet.iterator().previous(), is("Ivan"));
        assertThat(linkedSet.iterator().previousIndex(), is(-1));
        assertThat(linkedSet.iterator().hasPrevious(), is(false));
        linkedSet.iterator().previous();
    }

    /**
     * Test when add more then 100 linked items and some null then increase and contain one null.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenAddMoreThen100LinkedItemsAndSomeNullThenIncreaseAndContainOneNull() {
        SimpleSet<String> set = new SimpleLinkedSet<>();
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

    /**
     * Test when add some item to hash set contains and remove then true if not duplicate and exception if contain out of container.
     */
    @Test
    public void whenAddSomeItemToHashSetContainsAndRemoveThenTrueIfNotDuplicateAndExceptionIfContainOutOfContainer() {
        SimpleHashSet<String> set = new SimpleHashSet<>();
        boolean result = false;
        set.add(null);
        assertThat(set.add("Ivan"), is(true));
        assertThat(set.contains("Ivan"), is(true));
        assertThat(set.contains("Petr"), is(result));
        assertThat(set.add("Petr"), is(true));
        assertThat(set.contains("Petr"), is(true));
        assertThat(set.contains("Master"), is(result));
        assertThat(set.add("Master"), is(true));
        assertThat(set.contains("Master"), is(true));
        assertThat(set.add("Master"), is(result));
        assertThat(set.contains("Master"), is(true));
        assertThat(set.remove("Master"), is(true));
        assertThat(set.contains("Master"), is(result));
        assertThat(set.remove("Master"), is(result));
        set.add(null);
        set.add(null);
        set.add(null);
        set.add(null);
        assertThat(set.contains(null), is(true));
        assertThat(set.remove(null), is(false));
        assertThat(set.contains(null), is(true));
        SimpleHashSet<String> nullSet = new SimpleHashSet<>();
        assertThat(nullSet.contains("Ivan"), is(false));
    }
}