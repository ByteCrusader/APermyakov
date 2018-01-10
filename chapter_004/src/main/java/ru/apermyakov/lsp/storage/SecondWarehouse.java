package ru.apermyakov.lsp.storage;

import ru.apermyakov.lsp.food.Products;

import java.util.List;

/**
 * Class for modulate second warehouse.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 10.01.2018.
 */
public class SecondWarehouse extends Warehouse {

    /**
     * Storage field for realise decorator pattern.
     */
    protected Storage storage;

    /**
     * Constrictor for realise decorator pattern.
     *
     * @param storage storage.
     */
    public SecondWarehouse(Storage storage) {
        this.storage = storage;
    }

    /**
     * Method for get name of storage.
     *
     * @return storage's name.
     */
    @Override
    public String getName() {
        return "SecondWarehouse";
    }

    /**
     * Method for get all products of storage.
     *
     * @return list of products.
     */
    @Override
    public List<Products> getProducts() {
        return this.storage.getProducts();
    }

    /**
     * method for check suitable of product.
     *
     * @param product product.
     * @return suitable or not.
     */
    @Override
    public boolean isSuitable(Products product) {
        return this.storage.isSuitable(product);
    }
}
