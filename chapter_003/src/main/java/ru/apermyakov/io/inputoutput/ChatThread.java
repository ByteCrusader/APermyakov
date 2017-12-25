package ru.apermyakov.io.inputoutput;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Class for modulate chat threads.
 *
 * @author apermyakov
 * @version 1.0
 * @since 25.12.2017
 */
public abstract class ChatThread implements Runnable {

    /**
     * Design chat thread.
     *
     * @param log log file
     */
    public ChatThread(File log) {
        this.log = log;
    }

    /**
     * Field for log chat messages.
     */
    final File log;

    /**
     * Method for override run.
     */
    @Override
    public void run() {
    }

    /**
     * Method for log messages to txt file.
     *
     * @param line message
     */
    protected void logToTxt(String line) {
        try (RandomAccessFile access = new RandomAccessFile(log, "rw")) {
            access.seek(access.length());
            access.writeBytes(line + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
