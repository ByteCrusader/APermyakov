package ru.apermyakov.generic;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test simple linked array.
 *
 * @author apermyakov
 * @version 1.1
 * @since 02.11.2017
 */
public class SimpleLinkedArrayTest {

    /**
     * Test when check all method of linked array and list iterator and if previous out of array then exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenCheckAllMethodOfLinkedArrayAndListIteratorAndIfPreviousOutOfArrayThenException() {
        SimpleLinkedArray<String> array = new SimpleLinkedArray<>();
        array.add("Ivan");
        array.add("Kirk");
        array.add("Petr");
        assertThat(array.get(1), is("Kirk"));
        assertThat(array.get(0), is("Ivan"));
        assertThat(array.get(2), is("Petr"));
        assertThat(array.iterator().hasPrevious(), is(false));
        assertThat(array.iterator().previousIndex(), is(-1));
        assertThat(array.iterator().hasNext(), is(true));
        assertThat(array.iterator().nextIndex(), is(0));
        assertThat(array.iterator().next(), is("Ivan"));
        assertThat(array.iterator().hasNext(), is(true));
        assertThat(array.iterator().nextIndex(), is(1));
        assertThat(array.iterator().next(), is("Kirk"));
        assertThat(array.iterator().hasNext(), is(true));
        assertThat(array.iterator().nextIndex(), is(2));
        assertThat(array.iterator().next(), is("Petr"));
        assertThat(array.iterator().hasNext(), is(false));
        assertThat(array.iterator().nextIndex(), is(3));
        assertThat(array.iterator().hasPrevious(), is(true));
        assertThat(array.iterator().previousIndex(), is(2));
        assertThat(array.iterator().previous(), is("Petr"));
        assertThat(array.iterator().hasPrevious(), is(true));
        assertThat(array.iterator().previousIndex(), is(1));
        assertThat(array.iterator().previous(), is("Kirk"));
        assertThat(array.iterator().hasPrevious(), is(true));
        assertThat(array.iterator().previousIndex(), is(0));
        assertThat(array.iterator().previous(), is("Ivan"));
        assertThat(array.iterator().hasPrevious(), is(false));
        assertThat(array.iterator().previousIndex(), is(-1));
        array.iterator().previous();
    }

    /**
     * Test when iterator next out of array then exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenIteratorNextOutOfArrayThenException() {
        SimpleLinkedArray<String> array = new SimpleLinkedArray<>();
        array.add("Petr");
        assertThat(array.iterator().hasNext(), is(true));
        assertThat(array.iterator().nextIndex(), is(0));
        assertThat(array.iterator().next(), is("Petr"));
        assertThat(array.iterator().hasNext(), is(false));
        assertThat(array.iterator().nextIndex(), is(1));
        array.iterator().next();
    }

    /**
     * Test when iterator remove item then exception.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void whenIteratorRemoveItemThenException() {
        SimpleLinkedArray<String> array = new SimpleLinkedArray<>();
        array.add("Petr");
        array.iterator().remove();
    }

    /**
     * Test when iterator add item then exception.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void whenIteratorAddItemThenException() {
        SimpleLinkedArray<String> array = new SimpleLinkedArray<>();
        array.add("Petr");
        array.iterator().add("Ivan");
    }

    /**
     * Test when iterator set item then exception.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void whenIteratorSetItemThenException() {
        SimpleLinkedArray<String> array = new SimpleLinkedArray<>();
        array.add("Petr");
        array.iterator().set("Ivan");
    }

    /**
     * Test when get item out of array then exception.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetItemOutOfArrayThenException() {
        SimpleLinkedArray<String> array = new SimpleLinkedArray<>();
        array.add("Petr");
        array.get(1);
    }

    /**
     * Test when add four items and delete two in the middle then get first is first get last is last.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenAddFourItemsAndDeleteTwoInTheMiddleThenGetFirstIsFirstGetLastIsLast() {
        SimpleLinkedArray<String> array = new SimpleLinkedArray<>();
        array.add("Petr");
        array.add("Vasilii");
        array.add("Ivan");
        array.add("Kirk");
        assertThat(array.removeItem("Vasilii"), is("Vasilii"));
        assertThat(array.removeItem(1), is("Ivan"));
        assertThat(array.getFirst().getObject(), is("Petr"));
        assertThat(array.get(0), is("Petr"));
        assertThat(array.getLast().getObject(), is("Kirk"));
        assertThat(array.get(1), is("Kirk"));
        array.removeItem(null);
    }

    /**
     * Test when remove unexpected item then exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenRemoveUnexpectedItemThenException() {
        SimpleLinkedArray<String> array = new SimpleLinkedArray<>();
        array.add("Petr");
        array.removeItem("Ivan");
    }
}