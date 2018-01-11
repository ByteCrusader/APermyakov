package ru.apermyakov.isp.menuwork;

import ru.apermyakov.isp.menuitems.Item;

/**
 * Interface for modulate menu initialize.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 10.01.2018.
 */
public interface MenuInitialize {

    /**
     * Method for add items to menu list.
     *
     * @param menuItem menu item.
     */
    void addToMenuList(Item menuItem);
}
