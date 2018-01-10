package ru.apermyakov.lsp.food;

import java.util.Calendar;

/**
 * Class for modulate vegetables.
 *
 * @author apermyakov.
 * @version 1.0.
 */
public class Vegetables extends Food {

    /**
     * Product field for realise decorator pattern.
     */
    protected Products product;

    /**
     * Constrictor for realise decorator pattern.
     *
     * @param product product.
     */
    public Vegetables(Products product) {
        this.product = product;
    }

    /**
     * Method for check belong to vegetables.
     *
     * @return true.
     */
    public boolean isVegetable() {
        return true;
    }

    /**
     * Method for set product name.
     *
     * @param name product name.
     */
    @Override
    public void setName(String name) {
        this.product.setName(name);
    }

    /**
     * Method for get name of product.
     *
     * @return name of product.
     */
    @Override
    public String getName() {
        return this.product.getName();
    }

    /**
     * Method for set expire data of product.
     *
     * @param expireDate expire data of product.
     */
    @Override
    public void setExpireDate(Calendar expireDate) {
        this.product.setExpireDate(expireDate);
    }

    /**
     * Method for get expire data of product.
     *
     * @return expire data of product.
     */
    @Override
    public Calendar getExpireDate() {
        return this.product.getExpireDate();
    }

    /**
     * Method for set create data of product.
     *
     * @param createDate create data of product.
     */
    @Override
    public void setCreateDate(Calendar createDate) {
        this.product.setCreateDate(createDate);
    }

    /**
     * Method for get create date of product.
     *
     * @return create date if product.
     */
    @Override
    public Calendar getCreateDate() {
        return this.product.getCreateDate();
    }

    /**
     * Method for set price of product.
     *
     * @param price price of product.
     */
    @Override
    public void setPrice(int price) {
        this.product.setPrice(price);
    }

    /**
     * Method for get price of product.
     *
     * @return price of product.
     */
    @Override
    public int getPrice() {
        return this.product.getPrice();
    }

    /**
     * Method for set discount of product.
     *
     * @param discount discount of product.
     */
    @Override
    public void setDiscount(int discount) {
        this.product.setDiscount(discount);
    }

    /**
     * Method for get discount of product.
     *
     * @return discount of product.
     */
    @Override
    public int getDiscount() {
        return this.product.getDiscount();
    }

    /**
     * Method for check quality of product.
     *
     * @return quality percent.
     */
    @Override
    public int checkQuality() {
        return this.product.checkQuality();
    }

    /**
     * Method for return product price without discount.
     *
     * @return markdown price.
     */
    @Override
    public int markdown() {
        return this.product.markdown();
    }
}
