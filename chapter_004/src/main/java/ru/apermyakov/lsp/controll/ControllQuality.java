package ru.apermyakov.lsp.controll;

import ru.apermyakov.lsp.food.Products;
import ru.apermyakov.lsp.storage.Storage;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for modulate quality controller.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 10.01.2018.
 */
public class ControllQuality implements Controller {

    /**
     * Field for contain storages.
     */
    private List<Storage> storages = new ArrayList<>();

    /**
     * Method for add storage to list.
     *
     * @param storage new storage.
     */
    @Override
    public void addStorageToList(Storage storage) {
        this.storages.add(storage);
    }

    /**
     * Method for get all storages.
     *
     * @return list of storages.
     */
    @Override
    public List<Storage> getAllStorages() {
        return this.storages;
    }

    /**
     * Method for get storage's products.
     *
     * @param storageName storage name.
     * @return list of products.
     */
    @Override
    public List<Products> getStorageProducts(String storageName) {
        List<Products> result = new ArrayList<>();
        for (Storage storage : this.storages) {
            if (storageName.equals(storage.getName())) {
                result.addAll(storage.getProducts());
            }
        }
        return result;
    }

    /**
     * Method for distribute product to storage.
     *
     * @param product product.
     * @return name of storage.
     */
    @Override
    public String distributeProduct(Products product) {
        String resultStorage = "";
        for (Storage storage : this.storages) {
            if (storage.isSuitable(product)) {
                storage.getProducts().add(product);
                resultStorage = storage.getName();
            }
        }
        return resultStorage;
    }
}
