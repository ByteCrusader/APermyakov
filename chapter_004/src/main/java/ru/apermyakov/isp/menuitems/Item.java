package ru.apermyakov.isp.menuitems;

/**
 * Main interface, extends all another item's interfaces.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 10.01.2018.
 */
public interface Item extends ItemAction, ItemKey, ItemName, ItemKeyCheck, ItemChildren, ItemParent, ItemCheckDependencies {
}
