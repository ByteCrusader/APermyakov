package ru.job4j.calculator.doing;

import ru.job4j.calculator.Input;
import ru.job4j.calculator.Result;

import java.util.List;

/**
 * Class for calculate hypotenuse by two cassettes.
 *
 * @author apermyakov
 * @version 1.0
 * @since 09.01.2018
 */
public class Hypotenuse extends Add {
    /**
     * Constructor for initial action name.
     *
     * @param name action name
     */
    public Hypotenuse(String name) {
        super(name);
    }

    /**
     * Method for do hypotenuse calculate action.
     *
     * @param answer result object.
     * @param input  user input.
     */
    @Override
    public void doAction(Result answer, Input input) {
        List<Double> numbers = handleUserInput(answer, input);
        double result =  Math.sqrt(Math.pow(numbers.get(0), 2D) + Math.pow(numbers.get(1), 2D));
        answer.setLastResult(result);
    }
}
