package ru.job4j.calculator.doing;

import ru.job4j.calculator.Input;
import ru.job4j.calculator.Result;

import java.util.List;

/**
 * Interface for modulate calculator's srp classes.
 *
 * @author apermyakov
 * @version 1.0
 * @since 09.01.2018
 */
public interface Doing {

    /**
     * Method for get doing's name.
     *
     * @return name
     */
    String getName();

    /**
     * Method for work with user input.
     *
     * @param answer result obj.
     * @param input user input.
     * @return list of result numbers.
     */
    List<Double> handleUserInput(Result answer, Input input);

    /**
     * Method for do class action.
     *
     * @param answer result object.
     * @param input user input.
     */
    void doAction(Result answer, Input input);
}
