package ru.apermyakov.io.inputoutput;

import java.io.*;

/**
 * Class for check char stream.
 *
 * @author apermyakov
 * @version 1.0
 * @since 22.12.2017
 */
public class CheckCharStream {

    /**
     * Method for check word char by char.
     *
     * @param streamChar char of stream
     * @param bufferedReader input stream
     * @param checkedWord checked word
     * @return true of contain
     * @throws IOException e
     */
    private boolean checkAbuses(BufferedReader bufferedReader, int streamChar, String checkedWord) throws IOException {
        int inside = streamChar;
        boolean record = true;
        for (char symb : checkedWord.toCharArray()) {
            if ((char) inside == symb) {
                inside = bufferedReader.read();
            } else {
                record = false;
            }
        }
        return record && ((char)inside == ' ' || inside == -1);
    }

    /**
     * Method for drop abuses and write to out stream string without abuses.
     *
     * @param in input stream
     * @param out output stream
     * @param abuse array of abuse words
     */
    public void dropAbuses(Reader in, Writer out, String[] abuse) {
        try (BufferedReader bufferedReader = new BufferedReader(in)) {
            int c;
            int position = 0;
            while ((c = bufferedReader.read()) != -1) {
                boolean marked = false;
                for (String string : abuse) {
                    if (string.charAt(0) == (char) c) {
                        bufferedReader.mark(position);
                        marked = checkAbuses(bufferedReader, c, string);
                        if (!marked) {
                            bufferedReader.reset();
                        }
                    }
                }
                if (!marked) {
                    out.append((char) c);
                }
                position++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
