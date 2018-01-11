package ru.apermyakov.isp.menuwork;

import ru.apermyakov.isp.menuitems.Item;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class for modulate menu actions by menu's interfaces.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 10.01.2018.
 */
public class MenuAct implements Menu {

    /**
     * Field for list of menu items.
     */
    private List<Item> menuItems = new ArrayList<>();

    /**
     * Method for add items to menu list.
     *
     * @param menuItem menu item.
     */
    @Override
    public void addToMenuList(Item menuItem) {
        this.menuItems.add(menuItem);
    }

    /**
     * Method for build indents by menu item key.
     *
     * @param menuKey menu item key.
     * @return string of indents.
     */
    @Override
    public String buildIndent(String menuKey) {
        String keyWithoutLastSep = menuKey.substring(0, menuKey.lastIndexOf('.'));
        StringBuilder resultIndent = new StringBuilder();
        boolean skipTab = true;
        for (String aSplitKey : keyWithoutLastSep.split("\\.")) {
            if (!skipTab) {
                resultIndent.append("\t");
            }
            skipTab = false;
        }
        return resultIndent.toString();
    }

    /**
     * Method for show menu.
     */
    @Override
    public void showMenu() {
        this.sortMenu();
        for (Item menuItem : menuItems) {
            System.out.format("%s %s %s %s", buildIndent(menuItem.getKey()), menuItem.getKey(), menuItem.getName(), System.lineSeparator());
        }
    }

    /**
     * Method for work with menu item.
     *
     * @param itemName item's name.
     */
    @Override
    public void workItem(String itemName) {
        for (Item menuItem : menuItems) {
            if (menuItem.checkKey(itemName)) {
                menuItem.doAction();
            }
        }
    }

    /**
     * Method for sort menu.
     */
    @Override
    public void sortMenu() {
        this.menuItems.sort(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
    }
}
