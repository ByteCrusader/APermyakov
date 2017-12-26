package ru.apermyakov.io.inputoutput;

import org.junit.Test;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for text check char stream.
 *
 * @author apermyakov
 * @version 1.0
 * @since 22.12.2017
 */
public class CheckCharStreamTest {

    /**
     * Method for generic test.
     *
     * @param abuse abuse
     * @param check check
     * @param result result
     */
    private void genericTest(String abuse, String check, boolean result) {
        String first = "Hi! I'm here to check you! Check passed fail";
        char[] firstArray = first.toCharArray();
        String[] abuses = new String[]{abuse};
        CheckCharStream checker = new CheckCharStream();
        try (CharArrayReader input = new CharArrayReader(firstArray);
             CharArrayWriter output = new CharArrayWriter()
        ) {
            checker.dropAbuses(input, output, abuses);
            assertThat(output.toString().contains(check), is(result));
        }
    }

    /**
     * Test when drop abuse fail then don't contain fail.
     */
    @Test
    public void whenDropAbuseFailThenDontContainFail() {
        this.genericTest("fail", "fail", false);
    }

    /**
     * Test when drop abuses here then don't contain here.
     */
    @Test
    public void whenDropAbuseHereThenDontContainHere() {
        this.genericTest("here", "here", false);
    }

    /**
     * Test when drop abuses to then don't contain to.
     */
    @Test
    public void whenDropAbuseToThenDontContainTo() {
        this.genericTest("to", "to", false);
    }

    /**
     * Test when drop abuses che then contain check.
     */
    @Test
    public void whenDropAbusesCheThenContainCheck() {
        this.genericTest("che", "check", true);
    }
}