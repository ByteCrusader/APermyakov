package ru.apermyakov;

/**
 * Class for initial user.
 *
 * @author apermyakov
 * @version 1.0
 * @since 23.10.2017
 */
public class User {

    /**
     * Initial user id.
     */
    private int id;

    /**
     * Initial user name.
     */
    private String name;

    /**
     * Initial user city.
     */
    private String city;

    /**
     * Design User.
     *
     * @param id user id
     * @param name user name
     * @param city user city
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * Method for return user id.
     *
     * @return user id
     */
    public int getId() {
        return this.id;
    }
}
