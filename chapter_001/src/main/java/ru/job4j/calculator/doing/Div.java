package ru.job4j.calculator.doing;

import ru.job4j.calculator.Calculator;
import ru.job4j.calculator.Input;
import ru.job4j.calculator.Result;

import java.util.List;

/**
 * Class for modulate div action.
 *
 * @author apermyakov
 * @version 1.0
 * @since 09.01.2018
 */
public class Div extends Add {

    /**
     * Constructor for initial action name.
     *
     * @param name action name
     */
    public Div(String name) {
        super(name);
    }

    /**
     * Method for do div action.
     *
     * @param answer result object.
     * @param input  user input.
     */
    @Override
    public void doAction(Result answer, Input input) {
        Calculator calculator = new Calculator();
        List<Double> numbers = handleUserInput(answer, input);
        calculator.div(numbers.get(0), numbers.get(1));
        answer.setLastResult(calculator.getResult());
    }
}
