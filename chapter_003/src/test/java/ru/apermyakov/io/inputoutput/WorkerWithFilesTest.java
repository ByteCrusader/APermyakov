package ru.apermyakov.io.inputoutput;

import org.junit.Test;

import java.io.File;

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
    public void whenSortTwoFilesThenBuildTwoTempFilesAndSortToResultFile() {
        WorkerWithFiles worker = new WorkerWithFiles();
        File source = new File("C:\\check\\source.txt");
        File distance = new File("C:\\check\\distance.txt");
        worker.sort(source, distance);
    }
}