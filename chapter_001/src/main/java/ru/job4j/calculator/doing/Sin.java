package ru.job4j.calculator.doing;

import ru.job4j.calculator.Input;
import ru.job4j.calculator.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for calculate sin of number.
 *
 * @author apermyakov
 * @version 1.0
 * @since 09.01.2018
 */
public class Sin extends Add {

    /**
     * Constructor for initial action name.
     *
     * @param name action name
     */
    public Sin(String name) {
        super(name);
    }

    /**
     * Method for handle user input by one input number.
     *
     * @param answer result obj.
     * @param input  user input.
     * @return input number.
     */
    @Override
    public List<Double> handleUserInput(Result answer, Input input) {
        List<Double> result = new ArrayList<>();
        String userInput = input.scanDoing("Insert number that sin you wont to calculate (\"ans\" for last answer) : ");
        result.add(checkInput(userInput) ? answer.getLastResult() : Double.valueOf(userInput));
        return result;
    }

    /**
     * Method for do calculate sin of number.
     *
     * @param answer result object.
     * @param input  user input.
     */
    @Override
    public void doAction(Result answer, Input input) {
        List<Double> numbers = handleUserInput(answer, input);
        answer.setLastResult(Math.sin(numbers.get(0)));
    }
}
