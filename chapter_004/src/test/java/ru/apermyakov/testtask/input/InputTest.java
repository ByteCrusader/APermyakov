package ru.apermyakov.testtask.input;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test input.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public class InputTest {

    /**
     * Test when input word correct then take result correct.
     */
    @Test
    public void whenInputWordCorrectThenTakeResultCorrect() {
        ByteArrayInputStream console = new ByteArrayInputStream("Correct".getBytes());
        System.setIn(console);
        Input input = new UserInput();

        String result = input.ask("Check");

        assertThat(result, is("Correct"));
    }
}