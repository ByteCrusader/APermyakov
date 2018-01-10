package ru.apermyakov.lsp.storage;

import ru.apermyakov.lsp.food.Products;

/**
 * Class for modulate trash activity.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 10.01.2018.
 */
public class Trash extends Warehouse {

    /**
     * Method for get name of storage.
     *
     * @return storage's name.
     */
    @Override
    public String getName() {
        return "Trash";
    }

    /**
     * method for check suitable of product.
     *
     * @param product product.
     * @return suitable or not.
     */
    @Override
    public boolean isSuitable(Products product) {
        return product.checkQuality() >= 100;
    }
}
