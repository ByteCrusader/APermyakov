package ru.apermyakov.generic;

/**
 * Class for build base of user and role types.
 *
 * @author apermyakov
 * @version 1.0
 * @since 01.11.2017
 */
public abstract class Base {

    /**
     * Field for id of base data.
     */
    private String id;

    /**
     * Method for get id of base data.
     *
     * @return data's id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Method for set id of base data.
     *
     * @param id set id.
     */
    public void setId(String id) {
        this.id = id;
    }
}
