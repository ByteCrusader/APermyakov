package ru.apermyakov.references;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test cache.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 15.01.2018.
 */
public class SoftRefCacheTest {

    /**
     * Test when upload test file than get test string.
     *
     * @throws IOException
     */
    @Test
    public void whenUploadTestFileThanGetTestString() throws IOException {
        SoftRefCache cache = new Cache();
        assertThat(cache.get("C:\\check\\test.txt"), is("Check soft references cache"));
    }
}