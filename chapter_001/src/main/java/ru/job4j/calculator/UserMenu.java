package ru.job4j.calculator;

import ru.job4j.calculator.doing.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for modulate user menu.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 09.01.2018.
 */
public class UserMenu {

    /**
     * List of calculator doings.
     */
    private List<Doing> doings;

    /**
     * Method for initial list of calculator doings.
     */
    public void initial() {
        this.doings = new ArrayList<>();
        this.doings.add(new Add("add"));
        this.doings.add(new Subtract("subtract"));
        this.doings.add(new Div("div"));
        this.doings.add(new Multiple("multiple"));
    }

    /**
     * Method for show calculate doings.
     */
    public void show() {
        int index = 0;
        for (Doing doing : this.doings) {
            ++index;
            System.out.format("%s. %s", index, doing.getName());
            System.out.println();
        }
    }

    /**
     * Method for choose calculate doing.
     *
     * @param choice choice doing.
     * @param answer answer of doing.
     * @param input input object of user.
     */
    public void choose(String choice, Result answer, Input input) {
        int index = 0;
        for (Doing someDoing : this.doings) {
            if (choice.equals(someDoing.getName())) {
                someDoing.doAction(answer, input);
                break;
            }
            index++;
        }
        if (index == this.doings.size()) {
            System.out.println("Wrong input");
        }
    }
}
