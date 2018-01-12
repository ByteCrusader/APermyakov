package ru.apermyakov.testtask.user;

/**
 * Class for modulate board user.
 *
 * @author apermyakov .
 * @version 1.0.
 * @since 12.01.2018.
 */
public class BoardUser implements User {

    /**
     * Field for user name.
     */
    private String name;

    /**
     * Field for user value.
     */
    private boolean isCrossValue;

    /**
     * Field for user priority.
     */
    private int priority;

    /**
     * Field for user type.
     */
    private boolean userOrComp;

    /**
     * User constructor.
     *
     * @param name user name.
     * @param userOrComp user type.
     */
    public BoardUser(String name, boolean userOrComp) {
        this.name = name;
        this.userOrComp = userOrComp;
    }

    /**
     * Method for set cross value to user.
     */
    @Override
    public void setValueCross() {
        this.isCrossValue = true;
    }

    /**
     * Method for get user name.
     *
     * @return user name.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Method for get user value.
     *
     * @return is cross or zero.
     */
    @Override
    public boolean isCross() {
        return this.isCrossValue;
    }

    /**
     * Method for set zero value to user.
     */
    @Override
    public void setValueZero() {
        this.isCrossValue = false;
    }

    /**
     * Method for set user priority.
     *
     * @param priority priority.
     */
    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Method for get user priority.
     *
     * @return priority.
     */
    @Override
    public int getPriority() {
        return this.priority;
    }

    /**
     * Method for get user type.
     *
     * @return is user.
     */
    @Override
    public boolean isUser() {
        return this.userOrComp;
    }
}
