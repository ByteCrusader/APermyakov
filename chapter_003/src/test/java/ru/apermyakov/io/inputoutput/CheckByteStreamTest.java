package ru.apermyakov.io.inputoutput;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test check byte stream.
 *
 * @author apermyakov
 * @version 1.0
 * @since 21.12.2017
 */
public class CheckByteStreamTest {

    /**
     * Method for build generic test.
     *
     * @param input input
     * @param result result
     * @throws IOException e
     */
    private void genericTest(String input, boolean result) throws IOException {
        byte[] inputBytes = input.getBytes();
        CheckByteStream checker = new CheckByteStream();
        try (ByteArrayInputStream stream = new ByteArrayInputStream(inputBytes)) {
            assertThat(checker.isNumber(stream), is(result));
        }
    }

    /**
     * Test when check number two then take true.
     */
    @Test
    public void whenCheckNumberTwoThenTakeTrue() throws IOException {
        this.genericTest("2", true);
    }

    /**
     * Test when check number three then take false.
     */
    @Test
    public void whenCheckNumberThreeThenTakeFalse() throws IOException {
        this.genericTest("3", false);
    }

    /**
     * Test when check word fourth then take false.
     */
    @Test
    public void whenCheckWordFourthThenTakeFalse() throws IOException {
        this.genericTest("fourth", false);
    }

    /**
     * Test when check number with point then take false.
     */
    @Test
    public void whenCheckNumberWithPointThenTakeFalse() throws IOException {
        this.genericTest("2.5", false);
    }
}