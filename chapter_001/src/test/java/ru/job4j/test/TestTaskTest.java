package ru.job4j.test;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test test task of chapter_001.
 *
 * @author apermyakov
 * @since 12.10.2017
 * @version 1.0
 */
public class TestTaskTest {

    /**
     * Test when check word in base word then return true.
     *
     * @since 12.10.2017
     */
    @Test
    public void whenCheckWordInBaseWordThenTakeTrue() {
        TestTask word = new TestTask();
        String origin = "Привет";
        String sub = "иве";
        boolean result = word.contains(origin, sub);
        assertThat(result, is(true));
    }

    /**
     * Test when check word non in base word then return false.
     *
     * @since 12.10.2017
     */
    @Test
    public void whenCheckWordNonInBaseWordThenTakeFalse() {
        TestTask word = new TestTask();
        String origin = "Привет";
        String sub = "тик";
        boolean result = word.contains(origin, sub);
        assertThat(result, is(false));
    }

    /**
     * Test when check word in base word and have equal length then return true.
     *
     * @since 12.10.2017
     */
    @Test
    public void whenCheckWordInBaseWordAndHaveEqualLengthThenTakeTrue() {
        TestTask word = new TestTask();
        String origin = "Привет";
        String sub = "Привет";
        boolean result = word.contains(origin, sub);
        assertThat(result, is(true));
    }
}
