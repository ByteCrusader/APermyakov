package ru.apermyakov.isp.menuwork;

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
     * @param menuKey menu item key.
     * @return string of indents.
     */
    String buildIndent(String menuKey);
}

