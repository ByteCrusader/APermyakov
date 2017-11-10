package ru.apermyakov.simpletree;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test binary search tree.
 *
 * @author apermyakov
 * @version 1.0
 * @since 10.11.2017
 */
public class BinarySearchTreeTest {

    /**
     * Test when add new item then build tree.
     */
    @Test
    public void whenAddNewItemThenBuildTree() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        assertThat(tree.add(20), is(true));
        assertThat(tree.add(15), is(true));
        assertThat(tree.add(25), is(true));
        assertThat(tree.add(12), is(true));
        assertThat(tree.add(18), is(true));
        assertThat(tree.add(50), is(true));
        assertThat(tree.add(90), is(true));
    }
}