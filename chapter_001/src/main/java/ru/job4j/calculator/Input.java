package ru.job4j.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class for handle user input.
 *
 * @author apermyakov
 * @version 1.0
 * @since 09.01.2018
 */
public class Input {

    /**
     * Field for system in scanner.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Method for scan user doing by ask.
     *
     * @param ask output ask.
     * @return input doing
     */
    public String scanDoing(String ask) {
        System.out.println(ask);
        return this.scanner.nextLine();
    }

    /**
     * Method for scan denominators for calculate.
     *
     * @return list of denominators.
     */
    public List<String> scanDenominators() {
        List<String> result = new ArrayList<>();
        for (int index = 1; index < 3; index++) {
            System.out.format("Input %s number (\"ans\" for use last answer) : ", index);
            result.add(this.scanner.nextLine());
        }
        return result;
    }
}
