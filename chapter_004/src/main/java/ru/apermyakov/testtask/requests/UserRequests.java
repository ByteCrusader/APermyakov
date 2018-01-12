package ru.apermyakov.testtask.requests;

import ru.apermyakov.testtask.input.Input;
import ru.apermyakov.testtask.input.UserInput;
import ru.apermyakov.testtask.user.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for modulate user requests.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public class UserRequests implements Request {

    /**
     * Field for user input.
     */
    private Input input = new UserInput();

    /**
     * Method for request complexity.
     *
     * @return complexity.
     */
    @Override
    public int requestComplexity() {
        String answer = this.input.ask("Insert number of items to win game : ");
        return Integer.parseInt(answer);
    }

    /**
     * Method for request priority.
     *
     * @param users users.
     */
    @Override
    public void requestPriority(List<User> users) {
        String me = "m";
        String answer = this.input.ask("Choose who start - you or computer (M/C) : ");
        users.get(0).setPriority(me.equals(answer.toLowerCase()) ? 2 : 1);
        users.get(1).setPriority(me.equals(answer.toLowerCase()) ? 1 : 2);

    }

    /**
     * Method for parsing input to map of results.
     *
     * @param message input message.
     * @return map of results.
     */
    protected Map<String, Integer> parsingAnswer(String message) {
        Map<String, Integer> result = new HashMap<>();
        String answer = this.input.ask(message);
        String[] splitedAnswer = answer.split(",");
        result.put("Height", Integer.parseInt(splitedAnswer[0]));
        result.put("Width", Integer.parseInt(splitedAnswer[1]));
        return result;
    }

    /**
     * Method for request size.
     *
     * @return map of sizes.
     */
    @Override
    public Map<String, Integer> requestSize() {
        return parsingAnswer("Insert size of board (height,width) : ");
    }

    /**
     * Method for request coordinates.
     *
     * @return map of coordinates.
     */
    @Override
    public Map<String, Integer> requestCoordinates() {
        return parsingAnswer("Insert coordinate of sell (height,width) : ");
    }

    /**
     * Method for request value.
     *
     * @param users list of users.
     */
    @Override
    public void requestValue(List<User> users) {
        String value = "x";
        String answer = this.input.ask("Choose cross or zero (X/O) : ");
        if (value.equals(answer.toLowerCase())) {
            users.get(0).setValueCross();
            users.get(1).setValueZero();
        } else {
            users.get(0).setValueZero();
            users.get(1).setValueCross();
        }
    }

    /**
     * Method for request continue or exit.
     *
     * @return true if continue.
     */
    @Override
    public boolean continueGame() {
        final String cont = "r";
        return cont.equals(this.input.ask("Restart game or exit? (R/E) : ").toLowerCase());
    }
}
