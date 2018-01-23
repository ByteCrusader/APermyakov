package ru.job4j.calculator;

import ru.job4j.calculator.doing.Doing;

import java.util.Collections;
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
    private final List<Doing> doings;

    public UserMenu(List<Doing> doings) {
        this.doings = Collections.unmodifiableList(doings);
    }

    /**
     * Method for show calculate doings.
     */
    public void show() {
        this.doings.forEach(System.out::println);
    }

    /**
     * Method for choose calculate doing.
     *
     * @param choice choice doing.
     * @param answer answer of doing.
     * @param input input object of user.
     */
    public void choose(String choice, Result answer, Input input) {
        if (this.doings.stream().anyMatch(i -> choice.equals(i.getName()))) {
            this.doings.stream().filter(i -> choice.equals(i.getName())).findFirst().get().doAction(answer, input);
        } else {
            System.out.println("Wrong input");
        }
    }
}
