package ru.apermyakov.io.inputoutput;

import java.io.*;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Class for work with files.
 *
 * @author apermyakov
 * @version 1.0
 * @since 22.12.2017
 */
public class WorkerWithFiles{

    /**
     * Method for merge result of outer sort.
     *
     * @param distance target file
     * @param seriesLength length of series
     * @param firstTemp first temporary file
     * @param secondTemp second temporary file
     * @throws IOException e
     */
    private void merge (File distance, int seriesLength, File firstTemp, File secondTemp) throws IOException {
        try ( RandomAccessFile firstTempAccess = new RandomAccessFile(firstTemp, "rw");
              RandomAccessFile secondTempAccess = new RandomAccessFile(secondTemp, "rw");
              RandomAccessFile dist = new RandomAccessFile(distance, "rw")) {
            String firstFileLine = firstTempAccess.readLine();
            String secondFileLine = secondTempAccess.readLine();
            LinkedList<String> result = new LinkedList<>();
            clear(distance);
            while (firstFileLine != null || secondFileLine != null) {
                firstFileLine = write(firstFileLine, firstTempAccess, result, seriesLength);
                secondFileLine = write(secondFileLine, secondTempAccess, result, seriesLength);
                result.sort(Comparator.comparingInt(String::length));
                for (String string : result) {
                    dist.writeBytes(string + "\r\n");
                }
                result.clear();
            }
        }
    }

    /**
     * Method for clear file.
     *
     * @param file target file
     * @throws FileNotFoundException e
     */
    private void clear (File file) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print("");
        }
    }

    /**
     * Method for write line from file to list.
     *
     * @param line first line
     * @param source source file
     * @param target target list
     * @param seriesLength length of series
     * @return string after last added
     * @throws IOException e
     */
    private String write(String line, RandomAccessFile source, LinkedList<String> target, int seriesLength) throws IOException {
        int counter = 0;
        while (counter < seriesLength) {
            if (line != null) {
                target.add(line);
                line = source.readLine();
                counter++;
            } else {
                break;
            }
        }
        return line;
    }

    /**
     * Overloaded method for write line from file to file.
     *
     * @param line first line
     * @param source source file
     * @param target target file
     * @param seriesLength length of series
     * @return string after last written
     * @throws IOException e
     */
    private String write(String line, RandomAccessFile source, RandomAccessFile target, int seriesLength) throws IOException {
        int counter = 0;
        while (counter < seriesLength) {
            if (line != null) {
                target.writeBytes(line + "\r\n");
                line = source.readLine();
                counter++;
            } else {
                break;
            }
        }
        return line;
    }

    /**
     * Method for modulate pass of outside sort
     *
     * @param source source file
     * @param distance target file
     * @param passCounter number of pass
     * @return all phase or not
     * @throws IOException e
     */
    private boolean pass (File source, File distance, int passCounter) throws IOException {
        boolean result = true;
        File firstTempFile = new File("first.txt");
        File secondTempFile = new File("second.txt");
        try ( RandomAccessFile firstTemp = new RandomAccessFile(firstTempFile, "rw");
              RandomAccessFile secondTemp = new RandomAccessFile(secondTempFile, "rw");
              RandomAccessFile sour = new RandomAccessFile(source, "r")
        ) {
            String line = sour.readLine();
            clear(firstTempFile);
            clear(secondTempFile);
            int counter = 0;
            while (line != null) {
                if ((line = write(line, sour, firstTemp, passCounter)) == null && counter == 0) {
                    result = false;
                }
                line = write(line, sour, secondTemp, passCounter);
                counter++;
            }
            if (result) {
                merge(distance, passCounter, firstTempFile, secondTempFile);
            }
        }
        return result;
    }

    /**
     * Method for initial file sort.
     *
     * @param source source file
     * @param distance target file
     */
    public void sort(File source, File distance) {
        int iterator = 0;
        boolean iteration;
        try {
            iteration = pass(source, distance, (int)Math.pow(2, iterator++));
            while (iteration) {
                iteration = pass(distance, distance, (int)Math.pow(2, iterator++));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
