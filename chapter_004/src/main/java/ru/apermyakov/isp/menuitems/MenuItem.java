package ru.apermyakov.isp.menuitems;

/**
 * Class for modulate menu item by item's interfaces.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 10.01.2018.
 */
public class MenuItem implements Item {

    /**
     * Field for item's name.
     */
    private String name;

    /**
     * Field for item's key.
     */
    private String key;

    /**
     * Constructor for menu item.
     *
     * @param key key.
     * @param name name.
     */
    public MenuItem(String key, String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * Method for do item's action.
     */
    @Override
    public void doAction() {
        System.out.format("The action of the %s %s menu item is executed%s", key, name, System.lineSeparator());
    }

    /**
     * Method for get item's key.
     *
     * @return item's key.
     */
    @Override
    public String getKey() {
        return this.key;
    }

    /**
     * Method for get item's name.
     *
     * @return item's name.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Method for check item's key by another key.
     *
     * @param key another key.
     * @return check result.
     */
    @Override
    public boolean checkKey(String key) {
        return key.equals(this.key);
    }
}
