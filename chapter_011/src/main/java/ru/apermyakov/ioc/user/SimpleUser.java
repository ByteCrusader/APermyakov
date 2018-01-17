package ru.apermyakov.ioc.user;

/**
 * Class for simple user.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 17.01.2018.
 */
public class SimpleUser implements User {

    /**
     * Field for name.
     */
    private String name;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
