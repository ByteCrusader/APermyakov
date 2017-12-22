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
     * Test check number.
     */
    @Test
    public void checkNumberTakeTrueOrFalse() {
        String first = "2";
        String second = "3";
        String third = "third";
        String fourth = "2.5";
        byte[] firstBytes = first.getBytes();
        byte[] secondBytes = second.getBytes();
        byte[] thirdBytes = third.getBytes();
        byte[] fourthBytes = fourth.getBytes();
        CheckByteStream checker = new CheckByteStream();
        try (ByteArrayInputStream stream = new ByteArrayInputStream(firstBytes)) {
            assertThat(checker.isNumber(stream), is(true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ByteArrayInputStream stream = new ByteArrayInputStream(secondBytes)) {
            assertThat(checker.isNumber(stream), is(false));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ByteArrayInputStream stream = new ByteArrayInputStream(thirdBytes)) {
            assertThat(checker.isNumber(stream), is(false));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ByteArrayInputStream stream = new ByteArrayInputStream(fourthBytes)) {
            assertThat(checker.isNumber(stream), is(false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}