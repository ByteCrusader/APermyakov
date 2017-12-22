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
     * Test when drop abuses then drop abuses and don't touch another words.
     */
    @Test
    public void whenDropAbusesThenDropAbusesAndDOntTouchAnotherWords() {
        String first = "Hi! I'm here to check you! Check passed fail";
        char[] firstArray = first.toCharArray();
        String[] abuse = new String[]{"che", "fail", "here", "to"};
        CheckCharStream checker = new CheckCharStream();
        try (CharArrayReader input = new CharArrayReader(firstArray);
             CharArrayWriter output = new CharArrayWriter()
        ) {
            checker.dropAbuses(input, output, abuse);
            assertThat(output.toString().contains("fail"), is(false));
            assertThat(output.toString().contains("here"), is(false));
            assertThat(output.toString().contains("to"), is(false));
            assertThat(output.toString().contains("check"), is(true));
        }
    }
}