package ru.apermyakov.io.inputoutput;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Class for work with zip.
 *
 * @author apermyakov
 * @version 1.0
 * @since 25.12.2017
 */
public class WorkerWithZip {

    /**
     * Method to initial convert to zip action.
     *
     * @param directoryPath directory to zip
     * @param keys fail's keys
     */
    public void convertToZip(String directoryPath, List<String> keys) {
        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream("archive.zip"))) {
            {
                File directory = new File(directoryPath);
                convert(directory, zip, keys);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for check files by key.
     *
     * @param file checked file
     * @param keys keys
     * @return exist or not
     */
    private boolean checkByKey(File file, List<String> keys) {
        boolean result = false;
        for (String key : keys) {
            if (file.getAbsolutePath().endsWith(key)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Method for convert files to zip.
     *
     * @param direction file's direction
     * @param zip target zip
     * @param keys file's keys
     * @throws IOException e
     */
    private void convert(File direction, ZipOutputStream zip, List<String> keys) throws IOException {
        for (File file : direction.listFiles()) {
            if (file.isDirectory()) {
                convert(file, zip, keys);
            } else if (checkByKey(file, keys)) {
                try (FileInputStream input = new FileInputStream(file)) {
                    zip.putNextEntry(new ZipEntry(file.getAbsolutePath()));
                    insertIntoZip(input, zip);
                }
            }
        }
    }

    /**
     * Method for insert file input stream to zip.
     *
     * @param input input stream
     * @param output output stream
     * @throws IOException e
     */
    private void insertIntoZip(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[input.available()];
        input.read(buffer);
        output.write(buffer);
    }
}
