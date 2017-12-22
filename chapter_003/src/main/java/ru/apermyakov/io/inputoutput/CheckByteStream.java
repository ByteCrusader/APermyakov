package ru.apermyakov.io.inputoutput;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Class for modulate check byte stream.
 *
 * @author apermyakov
 * @version 1.0
 * @since 21.12.2017
 */
public class CheckByteStream {

    /**
     * Method for check number.
     *
     * @param in in
     * @return
     */
    public boolean isNumber(InputStream in) {
        boolean result = false;
        try (Scanner scanner = new Scanner(in)) {
            String input = scanner.nextLine();
            if (input.matches("^[a-z]+") || input.contains(",") || input.contains(".")) {
                System.out.println("Wrong input");
            } else {
                result = Integer.parseInt(input) % 2 == 0; /*Integer.valueOf*/
            }
        }
        return result;
    }
}
