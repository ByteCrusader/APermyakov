package ru.apermyakov.isp.menuwork;

import ru.apermyakov.isp.menuitems.Item;
import ru.apermyakov.isp.menuitems.ItemKey;

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
     * Method for check parent.
     *
     * @param item item.
     * @return true if have parent.
     */
    private boolean checkParent(Item item) {
        return item.isSubItem();
    }

    /**
     * Method for build indents by menu item key.
     *
     * @param menuItem menu item.
     * @return string of indents.
     */
    @Override
    public String buildIndent(Item menuItem) {
        StringBuilder resultIndent = new StringBuilder();
        if (this.checkParent(menuItem)) {
            resultIndent.append("\t");
            resultIndent.append(this.buildIndent(menuItem.getParent()));
        }
        return resultIndent.toString();
    }

    /**
     * Method for build menu root.
     *
     * @param menuItem menu item.
     * @return result string.
     */
    private String buildMenuRoot(Item menuItem) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s %s %s %s", buildIndent(menuItem), menuItem.getKey(), menuItem.getName(), System.lineSeparator()));
        if (menuItem.isRootItem()) {
            menuItem.getChildren().forEach(i -> builder.append(this.buildMenuRoot(i)));
        }
        return builder.toString();
    }

    /**
     * Method for show menu.
     */
    @Override
    public void showMenu() {
        this.sortMenu();
        this.menuItems.forEach(i -> System.out.print(buildMenuRoot(i)));
    }

    /**
     * Method for do menu action.
     *
     * @param menuItem menu item.
     * @param itemName item name.
     */
    private void doMenuAction(Item menuItem, String itemName) {
        if (menuItem.checkKey(itemName)) {
            menuItem.doAction();
        } else if (menuItem.isRootItem()) {
            menuItem.getChildren().forEach(i -> doMenuAction(i, itemName));
        }
    }

    /**
     * Method for work with menu item.
     *
     * @param itemName item's name.
     */
    @Override
    public void workItem(String itemName) {
        this.menuItems.forEach(i -> doMenuAction(i, itemName));
    }

    /**
     * Method for sort menu.
     */
    @Override
    public void sortMenu() {
        this.menuItems.sort(Comparator.comparing(ItemKey::getKey));
    }
}
