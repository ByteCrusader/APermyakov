package ru.job4j.calculator;

import ru.job4j.calculator.doing.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for modulate interactive calculator action.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 09.01.2018.
 */
public class InteractCalc {

    /**
     * Field for result object to use last result.
     */
    private Result answer;

    /**
     * Field for menu object for work with menu.
     */
    private UserMenu menu;

    /**
     * Field for input object for work with user input.
     */
    private Input input;

    /**
     * Constructor for interactive calculator.
     *
     * @param answer result object.
     * @param menu menu object.
     * @param input input object.
     */
    public InteractCalc(Result answer, UserMenu menu, Input input) {
        this.answer = answer;
        this.menu = menu;
        this.input = input;
    }

    /**
     * Method for start calculator and output information for user.
     */
    public void startCalc() {
        final String exit = "y";
        do {
            this.menu.show();
            this.menu.choose(this.input.scanDoing("Choose calculator action by name: "), this.answer, this.input);
            System.out.format("Result of successful calculate: %s", this.answer.getLastResult());
            System.out.println();
        } while (exit.equals(input.scanDoing("Continue? (Y/N)").toLowerCase()));
    }

    /**
     * Main method for test class.
     *
     * @param args args
     */
    public static void main(String[] args) {
        Result answer = new Result();
        Input input = new Input();
        List<Doing> doings = new ArrayList<>();
        doings.add(new Add("add"));
        doings.add(new Subtract("subtract"));
        doings.add(new Div("div"));
        doings.add(new Multiple("multiple"));
        doings.add(new Hypotenuse("hypotenuse"));
        doings.add(new Sin("sin"));
        UserMenu menu = new UserMenu(doings);
        InteractCalc interactCalc = new InteractCalc(answer, menu, input);
        interactCalc.startCalc();
    }
}
