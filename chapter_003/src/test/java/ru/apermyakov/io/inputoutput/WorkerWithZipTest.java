package ru.apermyakov.io.inputoutput;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test zip worker.
 *
 * @author apermyakov
 * @version 1.0
 * @since 25.12.2017
 */
public class WorkerWithZipTest {

    /**
     * Test when choose direction and file's keys then build archive.
     */
    @Test
    public void whenChooseDirectionAndFilesKeysThenBuildArchive() throws IOException {
        WorkerWithZip worker = new WorkerWithZip();
        ArrayList<String> keys = new ArrayList<>();
        keys.add(".txt");
        keys.add(".java");
        String result;

        worker.convertToZip("C:\\check", keys);
        try (ZipInputStream zip = new ZipInputStream(new FileInputStream("C:\\Projects\\APermyakov\\chapter_003\\archive.zip"))) {
            result = zip.getNextEntry().getName();

        }

        assertThat(result.contains("C:\\check\\chapter_009"), is(true));
    }
}