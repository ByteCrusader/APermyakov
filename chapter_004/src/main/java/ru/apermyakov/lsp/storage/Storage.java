package ru.apermyakov.lsp.storage;

import ru.apermyakov.lsp.food.Products;

import java.util.List;

/**
 * Interface for modulate main storage activity.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 10.01.2018.
 */
public interface Storage {

    /**
     * Method for get name of storage.
     *
     * @return storage's name.
     */
    String getName();

    /**
     * Method for get all products of storage.
     *
     * @return list of products.
     */
    List<Products> getProducts();

    /**
     * method for check suitable of product.
     *
     * @param product product.
     * @return suitable or not.
     */
    boolean isSuitable(Products product);
}
