package ru.apermyakov.io.socket.manager;

import java.io.*;
import java.net.Socket;

/**
 * Class for file handle.
 *
 * @author apermyakov
 * @version 1.0
 * @since 28.12.2017
 */
public class FileHandler {

    /**
     * Method for convert file to input stream for download file.
     *
     * @param file file
     * @param socket socket
     * @throws IOException e
     */
    protected void convertFileToInputStream(File file, Socket socket) throws IOException {
        try (RandomAccessFile targetFile = new RandomAccessFile(file, "rw")) {
            FileInputStream inputFile = (FileInputStream) socket.getInputStream();
            int fileByte;
            while ((byte) (fileByte = inputFile.read()) != -1) {
                targetFile.writeByte(fileByte);
            }
        }
    }

    /**
     * Method for convert file to output stream for upload file.
     *
     * @param file file
     * @param socket socket
     * @throws IOException e
     */
    protected void convertFileToOutputStream(File file, Socket socket) throws IOException {
        try (RandomAccessFile fileAccess = new RandomAccessFile(file, "r")) {
            FileOutputStream fileOutput = (FileOutputStream) socket.getOutputStream();
            int fileByte;
            while ((fileByte = fileAccess.read()) != -1) {
                fileOutput.write(fileByte);
            }
            fileOutput.write(fileByte);
        }
    }

    /**
     * Method for build file by message.
     *
     * @param message message
     * @param messageConst message const
     * @param targetCatalog target catalog
     * @return created file
     */
    protected File buildFileByMessage(String message, String messageConst, String targetCatalog) {
        message = message.replace(messageConst, "");
        message = message.replaceAll(" ", "");
        String[] splittedMessage = message.split("\\\\");
        String resultFilePath = String.format("%s\\%s", targetCatalog, splittedMessage[splittedMessage.length - 1]);
        return new File(resultFilePath);
    }
}
