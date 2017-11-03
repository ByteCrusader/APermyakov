package ru.apermyakov.generic;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test queue and stack.
 *
 * @author apermyakov
 * @version 1.0
 * @since 03.11.2017
 */
public class AbstractDequeTest {

    /**
     * Test when queue first in first out and poll out of container then exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenQueueFirstInFirstOutAndPollOutOfContainerThenException() {
        AbstractDeque<String> stack = new SimpleQueue<>();
        stack.push("Petr");
        stack.push("Ivan");
        stack.push("Vasilii");
        stack.push("Hi!");
        assertThat(stack.poll(), is("Petr"));
        assertThat(stack.poll(), is("Ivan"));
        assertThat(stack.poll(), is("Vasilii"));
        assertThat(stack.poll(), is("Hi!"));
        stack.poll();
    }

    /**
     * Test when stack first in last out and poll out of container then exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenStackFirstInLastOutAndPollOutOfContainerThenException() {
        AbstractDeque<String> stack = new SimpleStack<>();
        stack.push("Petr");
        stack.push("Ivan");
        stack.push("Vasilii");
        stack.push("Hi!");
        assertThat(stack.poll(), is("Hi!"));
        assertThat(stack.poll(), is("Vasilii"));
        assertThat(stack.poll(), is("Ivan"));
        assertThat(stack.poll(), is("Petr"));
        stack.poll();
    }
}