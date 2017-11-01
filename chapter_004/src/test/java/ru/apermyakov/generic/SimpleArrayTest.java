package ru.apermyakov.generic;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test simple array.
 *
 * @author apermyakov
 * @version 1.0
 * @since 31.10.2017
 */
public class SimpleArrayTest {

    /**
     * Test when design string container then container of string.
     */
    @Test
    public void whenDesignStringContainerThenContainerOfString() {
        SimpleArray<String> container = new SimpleArray<>();
        container.add("Hi!");
        container.add("Ivan");
        container.add("Vasilii");
        container.delete("Ivan");
        container.update("Vasilii", "Petr");
        assertThat(container.get(1), is("Petr"));
    }

    /**
     * Test when design integer container then container of integers.
     */
    @Test
    public void whenDesignIntegerContainerThenContainerOfIntegers() {
        SimpleArray<Integer> container = new SimpleArray<>();
        container.add(30000);
        container.add(35000);
        container.add(50000);
        container.delete(30000);
        container.update(50000, 70000);
        assertThat(container.get(1), is(70000));
    }

    /**
     * Test when update date non of container then exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenUpdateDateNonOfContainerThenException() {
        SimpleArray<Integer> container = new SimpleArray<>();
        container.add(30000);
        container.add(35000);
        container.delete(30000);
        container.update(30000, 80000);
    }

    /**
     * Test when delete date out of container then exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenDeleteDateOutOfContainerThenException() {
        SimpleArray<Integer> container = new SimpleArray<>();
        container.add(30000);
        container.add(35000);
        container.add(40000);
        container.delete(30000);
        container.update(40000, 70000);
        container.delete(2);
    }

    /**
     * Test when get data out of container then exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenGetDataOutOfContainerThenException() {
        SimpleArray<Integer> container = new SimpleArray<>();
        container.add(35000);
        container.add(50000);
        container.update(50000, 70000);
        assertThat(container.get(1), is(70000));
        container.get(2);
    }

    /**
     * Test when add more then 100 data and delete 101 data then container grow to 50 and delete last item.
     */
    @Test
    public void whenAddMoreThen100DataAndDelete101DataThenContainerGrowTo50AndDeleteLastItem() {
        SimpleArray<String> container = new SimpleArray<>();
        for (int count = 0; count != 102; count++) {
            container.add("Hi!");
        }
        container.add("Petr");
        assertThat(container.get(101), is("Hi!"));
        container.delete("Hi!");
        assertThat(container.get(101), is("Petr"));
    }

    /**
     * Test when check iterator's methods then assert and exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenCheckIteratorsMethodsThenAssertAndException() {
        SimpleArray<String> container = new SimpleArray<>();
        container.add("Hi!");
        container.add("Ivan");
        container.add("Vasilii");
        container.iterator().next();
        container.iterator().next();
        assertThat(container.iterator().next(), is("Vasilii"));
        for (int count = 0; count < 97; count++) {
            container.add("Hi!");
        }
        for (int index = 4; index <= 99; index++) {
            container.iterator().next();
        }
        assertThat(container.iterator().next(), is("Hi!"));
        assertThat(container.iterator().hasNext(), is(false));
        container.iterator().next();
    }
}