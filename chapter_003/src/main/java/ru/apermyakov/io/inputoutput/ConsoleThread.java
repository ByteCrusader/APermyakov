package ru.apermyakov.io.inputoutput;

import java.io.*;
import java.util.Random;

/**
 * Class for modulate console answers thread.
 *
 * @author apermyakov
 * @version 1.0
 * @since 25.12.2017
 */
public class ConsoleThread extends ChatThread {

    /**
     * Design console thread.
     *
     * @param log log file
     */
    public ConsoleThread(File log) {
        super(log);
    }

    /**
     * Method for get random string from file.
     *
     * @param accessFile file
     * @return random string
     * @throws IOException e
     */
    private String getRandomString(RandomAccessFile accessFile) throws IOException {
        String result = "";
        String temp;
        Random random = new Random();
        while (("").equals(result)) {
            if ((temp = accessFile.readLine()) == null) {
                accessFile.seek(0);
            }
            if (random.nextInt(10) == 0) {
                result = temp;
                break;
            }
        }
        return result;
    }

    /**
     * Method for modulate main activity.
     */
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            try (RandomAccessFile access = new RandomAccessFile("answers.txt", "r")) {
                String answer = getRandomString(access);
                synchronized (log) {
                    log.wait();
                    super.logToTxt(answer);
                }
                System.out.println(answer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
