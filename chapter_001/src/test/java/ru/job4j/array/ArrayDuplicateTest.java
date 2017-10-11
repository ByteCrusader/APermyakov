package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test delete duplicate test.
 *
 * @author apermyakov
 * @since 11.10.2017
 * @version 1.0
 */
public class ArrayDuplicateTest {

    /**
     * Test when array with duplicate then array without duplicate.
     *
     * @since 11.10.2017
     */
    @Test
    public void whenArrayWithDuplicateThenArrayWithoutDuplicate() {
        ArrayDuplicate array = new ArrayDuplicate();
        String[] baseArray = new String[] {"привет", "мир", "привет", "супер", "мир"};
        String[] resultArray = array.remove(baseArray);
        String[] expectArray = new String[] {"привет", "мир", "супер"};
        assertThat(resultArray, is(expectArray));
    }
}
