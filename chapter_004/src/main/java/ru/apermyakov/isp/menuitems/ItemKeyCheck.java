package ru.apermyakov.isp.menuitems;

/**
 * Interface for modulate item's key checker.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 10.01.2018.
 */
public interface ItemKeyCheck {

    /**
     * Method for check item's key by another key.
     *
     * @param key another key.
     * @return check result.
     */
    boolean checkKey(String key);
}
