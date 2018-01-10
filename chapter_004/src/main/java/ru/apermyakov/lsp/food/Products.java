package ru.apermyakov.lsp.food;

import java.util.Calendar;

/**
 * Interface for modulate products activity.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 10.01.2018.
 */
public interface Products {

    /**
     * Method for set name of product.
     *
     * @param name product name.
     */
    void setName(String name);

    /**
     * Method for get name of product.
     *
     * @return name of product.
     */
    String getName();

    /**
     * Method for set expire data of product.
     *
     * @param expireDate expire data of product.
     */
    void setExpireDate(Calendar expireDate);

    /**
     * Method for get expire data of product.
     *
     * @return expire data of product.
     */
    Calendar getExpireDate();

    /**
     * Method for set create data of product.
     *
     * @param createDate create data of product.
     */
    void setCreateDate(Calendar createDate);

    /**
     * Method for get create date of product.
     *
     * @return create date if product.
     */
    Calendar getCreateDate();

    /**
     * Method for set price of product.
     *
     * @param price price of product.
     */
    void setPrice(int price);

    /**
     * Method for get price of product.
     *
     * @return price of product.
     */
    int getPrice();

    /**
     * Method for set discount of product.
     *
     * @param discount discount of product.
     */
    void setDiscount(int discount);

    /**
     * Method for get discount of product.
     *
     * @return discount of product.
     */
    int getDiscount();

    /**
     * Method for check quality of product.
     *
     * @return quality percent.
     */
    public int checkQuality();

    /**
     * Method for return product price without discount.
     *
     * @return markdown price.
     */
    public int markdown();
}
