package ru.apermyakov.isp.menuwork;

import ru.apermyakov.isp.menuitems.Item;

/**
 * Interface for modulate menu action.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 10.01.2018.
 */
public interface MenuIndentBuild {

    /**
     * Method for build indents by menu item key.
     *
     * @param item menu item.
     * @return string of indents.
     */
    String buildIndent(Item item);
}

