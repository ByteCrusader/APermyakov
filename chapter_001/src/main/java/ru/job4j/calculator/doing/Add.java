package ru.job4j.calculator.doing;

import ru.job4j.calculator.Calculator;
import ru.job4j.calculator.Input;
import ru.job4j.calculator.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for modulate add action.
 *
 * @author apermyakov
 * @version 1.0
 * @since 09.01.2018
 */
public class Add implements Doing {

    /**
     * Field for name of action.
     */
    private final String NAME;

    /**
     * Constructor for initial action name.
     *
     * @param name action name
     */
    public Add(String name) {
        this.NAME = name;
    }

    /**
     * Method for get name of action.
     *
     * @return name
     */
    @Override
    public String getName() {
        return this.NAME;
    }

    /**
     * Method for check input, ans or new number.
     *
     * @param input user input.
     * @return true of ans, false if new number.
     */
    private boolean checkInput(String input) {
        final String ans = "ans";
        return ans.equals(input.toLowerCase());
    }

    /**
     * Method for handle user input.
     *
     * @param answer result obj.
     * @param input user input.
     * @return list of numbers.
     */
    @Override
    public List<Double> handleUserInput(Result answer, Input input) {
        List<Double> result = new ArrayList<>();
        for (String userInput : input.scanDenominators()) {
            result.add(checkInput(userInput) ? answer.getLastResult() : Double.valueOf(userInput));
        }
        return result;
    }

    /**
     * Method for do add action.
     *
     * @param answer result object.
     * @param input user input.
     */
    @Override
    public void doAction(Result answer, Input input) {
        Calculator calculator = new Calculator();
        List<Double> numbers = handleUserInput(answer, input);
        calculator.add(numbers.get(0), numbers.get(1));
        answer.setLastResult(calculator.getResult());
    }
}
