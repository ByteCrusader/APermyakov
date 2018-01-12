package ru.apermyakov.testtask.requests;

import ru.apermyakov.testtask.user.User;

import java.util.*;

/**
 * Class for modulate computer requests.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public class ComputerRequest implements Request {

    /**
     * Field for board height.
     */
    private int height;

    /**
     * Field for board width.
     */
    private int width;

    /**
     * Constructor.
     *
     * @param height board height.
     * @param width board width.
     */
    public ComputerRequest(int height, int width) {
        this.height = height;
        this.width = width;
    }

    /**
     * Method for request coordinates.
     *
     * @return map of coordinates.
     */
    @Override
    public Map<String, Integer> requestCoordinates() {
        Random random = new Random();
        Map<String, Integer> result = new HashMap<>();
        result.put("Height", random.nextInt(this.height) + 1);
        result.put("Width", random.nextInt(this.width) + 1);
        return result;
    }

    /**
     * Method for request complexity.
     *
     * @return complexity.
     */
    @Override
    public int requestComplexity() {
        return 0;
    }

    /**
     * Method for request priority.
     *
     * @param users users.
     */
    @Override
    public void requestPriority(List<User> users) {
    }

    /**
     * Method for request size.
     *
     * @return map of sizes.
     */
    @Override
    public Map<String, Integer> requestSize() {
        return null;
    }

    /**
     * Method for request value.
     *
     * @param users list of users.
     */
    @Override
    public void requestValue(List<User> users) {
    }

    /**
     * Method for request continue or exit.
     *
     * @return true if continue.
     */
    @Override
    public boolean continueGame() {
        return true;
    }
}
