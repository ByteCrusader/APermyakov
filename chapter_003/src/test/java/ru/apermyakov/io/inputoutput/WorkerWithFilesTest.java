package ru.apermyakov.io.inputoutput;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


/**
 * Class for test worker with files.
 *
 * @author apermyakov
 * @version 1.0
 * @since 22.12.2017
 */
public class WorkerWithFilesTest {

    /**
     * When sort two files then build two temp files and sort to result file.
     */
    @Test
    public void whenSortTwoFilesThenBuildTwoTempFilesAndSortToResultFile() throws IOException {
        WorkerWithFiles worker = new WorkerWithFiles();
        File source = new File("C:\\check\\source.txt");
        File distance = new File("C:\\check\\distance.txt");
        String fileLine;
        String tempLine ="";
        boolean sorted = true;

        worker.sort(source, distance);
        try (RandomAccessFile file = new RandomAccessFile(distance, "r")) {
            while ((fileLine = file.readLine()) != null) {
                if (tempLine.length() > fileLine.length()) {
                    sorted = false;
                }
                tempLine = fileLine;
            }
        }

        assertThat(sorted, is(true));
    }
}