package ru.apermyakov.references;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Class for modulate file uploader.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 15.01.2018.
 */
public class FileUploader {

    /**
     * Method for upload
     *
     * @param filePath file path.
     * @return file text.
     * @throws IOException exception.
     */
    public String upload (String filePath) throws IOException {
        StringBuilder buffer = new StringBuilder();
        RandomAccessFile file = new RandomAccessFile(filePath, "r");
        String fileLine;
        while ((fileLine = file.readLine()) != null) {
            buffer.append(fileLine);
        }
        return buffer.toString();
    }
}
