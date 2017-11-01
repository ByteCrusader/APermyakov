package ru.apermyakov.generic;

import java.util.Random;

/**
 * Class for design user data type.
 *
 * @author apermyakov
 * @version 1.0
 * @since 01.11.2017
 */
public class User extends Base {

    /**
     * Field for user's name.
     */
    private String name;

    /**
     * Field for user's gender.
     */
    private String gender;

    /**
     * Field for user's age.
     */
    private int age;

    /**
     * Design user.
     *
     * @param name user's name
     * @param gender user's gender
     * @param age user's age
     */
    public User(String name, String gender, int age) {
        super.setId(String.valueOf(System.currentTimeMillis() + new Random().nextInt()));
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
}
