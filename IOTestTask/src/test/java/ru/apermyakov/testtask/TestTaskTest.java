package ru.apermyakov.testtask;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test io test task.
 *
 * @author apermyakov
 * @version 1.0
 * @since 29.12.2017
 */
public class TestTaskTest {

    /**
     * Method for modulate generic test.
     *
     * @param args args
     * @param contain contain
     * @throws IOException e
     */
    private void geneticTest(String[] args, String contain) throws IOException {
        TestTask task = new TestTask();
        boolean result = false;

        task.find(args);
        try (RandomAccessFile file = new RandomAccessFile("log.txt", "r")) {
            String fileLine;
            while ((fileLine = file.readLine()) != null) {
                result = fileLine.contains(contain);
            }
        }

        assertThat(result, is(true));
    }

    /**
     * Test when find by mask then take only files with contain mask.
     *
     * @throws IOException e
     */
    @Test
    public void whenFindByMaskThenTakeOnlyFilesWithContainMask() throws IOException {
        String[] args = new String[] {"-d", "C:\\check", "-n", "*.txt", "-m", "-o", "log.txt"};
        this.geneticTest(args, ".txt");
    }

    /**
     * Test when find by name then take only files with this name.
     *
     * @throws IOException e
     */
    @Test
    public void whenFindByNameThenTakeOnlyFilesWithThisName() throws IOException {
        String[] args = new String[] {"-d", "C:\\check", "-n", "first.txt", "-f", "-o", "log.txt"};
        this.geneticTest(args, "first.txt");
    }

    /**
     * Test when find by regular expression then take only files with contain reg exp.
     *
     * @throws IOException e
     */
    @Test
    public void whenFindByRegxThenTakeOnlyFilesWithContainRegExp() throws IOException {
        String[] args = new String[] {"-d", "C:\\check", "-n", "(.*)rst(.*)", "-r", "-o", "log.txt"};
        this.geneticTest(args, "rst");
    }

    /**
     * Test when need help then take help out to console.
     *
     * @throws IOException e
     */
    @Test
    public void whenNeedHelpThenTakeHelpOutToConsole() throws IOException {
        TestTask task = new TestTask();
        String[] args = new String[] {"-h"};
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        task.find(args);
        assertThat(out.toString().endsWith("-o - write the result of the search to a file.\r\n"), is(true));
    }
}