package ru.apermyakov.testtask.output;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test output.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public class OutputTest {

    /**
     * Field for console output stream.
     */
    private ByteArrayOutputStream console = new ByteArrayOutputStream();

    /**
     * Initial output.
     */
    @Before
    public void initial() {
        System.setOut(new PrintStream(console));
    }

    /**
     * Test when output word check then take check to output stream.
     */
    @Test
    public void whenOutputWordCheckThenTakeCheckToOutputStream() {
        Output output = new UserOutput();
        output.sendMessage("Check");
        assertThat(console.toString(), is("Check\r\n"));
    }
}