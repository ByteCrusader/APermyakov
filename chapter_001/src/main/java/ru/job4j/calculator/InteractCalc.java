package ru.job4j.calculator;

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
        UserMenu menu = new UserMenu();
        menu.initial();
        InteractCalc interactCalc = new InteractCalc(answer, menu, input);
        interactCalc.startCalc();
    }
}
