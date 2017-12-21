package ru.apermyakov.generic;

import java.util.Random;

/**
 * Class for design role type data.
 *
 * @author apermyakov
 * @version 1.0
 * @since 01.11.2017
 */
public class Role extends Base {

    /**
     * Field for user's position.
     */
    private String position;

    /**
     * Field for user's age of experience.
     */
    private double experienceAge;

    /**
     * Design role data.
     *
     * @param position user's position
     * @param experienceAge user's age of experience
     */
    public Role(String position, double experienceAge) {
        super.setId(String.valueOf(System.currentTimeMillis() + new Random().nextInt()));
        this.position = position;
        this.experienceAge = experienceAge;
    }

}
