package ru.apermyakov.lsp.food;

import java.util.Calendar;

/**
 * Class for modulate food activity.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 10.01.2018.
 */
public class Food implements Products {

    /**
     * Field for product name.
     */
    private String name;

    /**
     * Field for product expire date.
     */
    private Calendar expireDate;

    /**
     * Field for product create date.
     */
    private Calendar createDate;

    /**
     * Field for product price.
     */
    private int price;

    /**
     * Field for discount price.
     */
    private int discount;

    /**
     * Method for set product name.
     *
     * @param name product name.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method for get name of product.
     *
     * @return name of product.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Method for set expire data of product.
     *
     * @param expireDate expire data of product.
     */
    public void setExpireDate(Calendar expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * Method for get expire data of product.
     *
     * @return expire data of product.
     */
    @Override
    public Calendar getExpireDate() {
        return this.expireDate;
    }

    /**
     * Method for set create data of product.
     *
     * @param createDate create data of product.
     */
    @Override
    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    /**
     * Method for get create date of product.
     *
     * @return create date if product.
     */
    @Override
    public Calendar getCreateDate() {
        return this.createDate;
    }

    /**
     * Method for set price of product.
     *
     * @param price price of product.
     */
    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Method for get price of product.
     *
     * @return price of product.
     */
    @Override
    public int getPrice() {
        return this.price;
    }

    /**
     * Method for set discount of product.
     *
     * @param discount discount of product.
     */
    @Override
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    /**
     * Method for get discount of product.
     *
     * @return discount of product.
     */
    @Override
    public int getDiscount() {
        return this.discount;
    }

    /**
     * Method for check quality of product.
     *
     * @return quality percent.
     */
    @Override
    public int checkQuality() {
        Calendar calendar = Calendar.getInstance();
        long difCurrACreate = calendar.getTimeInMillis() - this.createDate.getTimeInMillis();
        long difCreateAExpire = this.expireDate.getTimeInMillis() - this.createDate.getTimeInMillis();
        double result = ((double) difCurrACreate / difCreateAExpire) * 100;
        return (int) result;
    }

    /**
     * Method for return product price without discount.
     *
     * @return markdown price.
     */
    @Override
    public int markdown() {
        return this.getPrice() - this.discount;
    }
}
