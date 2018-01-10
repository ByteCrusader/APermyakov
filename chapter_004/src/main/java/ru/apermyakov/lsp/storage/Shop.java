package ru.apermyakov.lsp.storage;

import ru.apermyakov.lsp.food.Products;

/**
 * Class for modulate trash activity.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 10.01.2018.
 */
public class Shop extends Warehouse {

    /**
     * Method for check markdown of product.
     *
     * @param quality quality of product.
     * @param product product.
     */
    private void markdownCheck(int quality, Products product) {
        if (quality > 75 && quality < 100) {
            product.setPrice(product.markdown());
        }
    }

    /**
     * Method for get name of storage.
     *
     * @return storage's name.
     */
    @Override
    public String getName() {
        return "Shop";
    }

    /**
     * method for check suitable of product.
     *
     * @param product product.
     * @return suitable or not.
     */
    @Override
    public boolean isSuitable(Products product) {
        int quality = product.checkQuality();
        markdownCheck(quality, product);
        return quality >= 25 && quality < 100;
    }
}
