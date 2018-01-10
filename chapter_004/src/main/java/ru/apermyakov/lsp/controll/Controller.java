package ru.apermyakov.lsp.controll;

import ru.apermyakov.lsp.food.Products;
import ru.apermyakov.lsp.storage.Storage;

import java.util.List;

/**
 * Interface for modulate controller's actions.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 10.01.2018.
 */
public interface Controller {

    /**
     * Method for add storage to list.
     *
     * @param storage new storage.
     */
    void addStorageToList(Storage storage);

    /**
     * Method for get all storages.
     *
     * @return all storages.
     */
    List<Storage> getAllStorages();

    /**
     * Method for get storage's products.
     *
     * @param storageName storage name.
     * @return list of products.
     */
    List<Products> getStorageProducts(String storageName);

    /**
     * Method for distribute product to storages.
     *
     * @param product product.
     * @return name of storage.
     */
    String distributeProduct(Products product);
}
