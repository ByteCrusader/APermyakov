package ru.apermyakov.generic;

import org.junit.Test;

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
}