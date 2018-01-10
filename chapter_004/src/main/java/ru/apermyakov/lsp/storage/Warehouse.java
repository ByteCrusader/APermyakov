package ru.apermyakov.lsp.storage;

import ru.apermyakov.lsp.food.Products;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for modulate warehouse activity.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 10.01.2018.
 */
public class Warehouse implements Storage {

    /**
     * Field for products.
     */
    private List<Products> products = new ArrayList<>();

    /**
     * Method for get name of storage.
     *
     * @return storage's name.
     */
    @Override
    public String getName() {
        return "Warehouse";
    }

    /**
     * Method for get all products of storage.
     *
     * @return list of products.
     */
    @Override
    public List<Products> getProducts() {
        return this.products;
    }

    /**
     * method for check suitable of product.
     *
     * @param product product.
     * @return suitable or not.
     */
    @Override
    public boolean isSuitable(Products product) {
        return product.checkQuality() < 25;
    }
}
