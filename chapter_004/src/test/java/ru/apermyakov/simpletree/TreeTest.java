package ru.apermyakov.simpletree;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test tree.
 *
 * @author apermyakov
 * @version 1.0
 * @since 10.11.2017
 */
public class TreeTest {

    /**
     * Test when add value then build tree without duplicate.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenAddValueThenBuildTreeWithoutDuplicate() {
        Tree<Integer> tree = new Tree<>();
        assertThat(tree.add(10, 5), is(true));
        assertThat(tree.add(10, 8), is(true));
        assertThat(tree.add(10, 8), is(false)); //duplicate
        assertThat(tree.add(10, 3), is(true));
        assertThat(tree.add(5, 4), is(true));
        assertThat(tree.add(5, 3), is(false)); //duplicate
        assertThat(tree.add(5, 4), is(false)); //duplicate
        assertThat(tree.add(4, 1), is(true));
        assertThat(tree.add(1, 1), is(false)); //duplicate
        assertThat(tree.add(9, 3), is(false)); //duplicate
        assertThat(tree.add(15, 10), is(false)); //duplicate
        assertThat(tree.add(15, 11), is(true));
        assertThat(tree.add(11, 2), is(true));
        assertThat(tree.add(10, 7), is(true));
        assertThat(tree.iterator().hasNext(), is(true));
        assertThat(tree.iterator().next(), is(15));
        assertThat(tree.iterator().hasNext(), is(true));
        assertThat(tree.iterator().next(), is(10));
        tree.add(null, null);
    }
}