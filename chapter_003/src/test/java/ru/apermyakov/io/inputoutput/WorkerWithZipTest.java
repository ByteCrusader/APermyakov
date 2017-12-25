package ru.apermyakov.io.inputoutput;

import org.junit.Test;

import java.util.ArrayList;

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
    public void whenChooseDirectionAndFilesKeysThenBuildArchive() {
        WorkerWithZip worker = new WorkerWithZip();
        ArrayList<String> keys = new ArrayList<>();
        keys.add(".txt");
        keys.add(".java");
        worker.convertToZip("C:\\check", keys);
    }
}