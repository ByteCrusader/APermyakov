package ru.apermyakov.testtask;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * Class for convert orders to sell and buy.
 *
 * @author apermyakov
 * @version 1.0
 * @since 14.11.2017
 */
public class Converter {

    /**
     * Field for sorted by price save sell orders.
     */
    TreeMap<Double, Order> sell = new TreeMap<>(Comparator.naturalOrder());

    /**
     * Filed for sorted by price save buy orders.
     */
    TreeMap<Double, Order> buy = new TreeMap<>(Comparator.reverseOrder());

    /**
     * Method for initialize convert action.
     *
     * @param caster caster for check order
     * @param sellOrBuy sell or buy map
     */
    private void convertAction(Caster caster, boolean sellOrBuy) {
        Order castSellOrder = caster.cast(sellOrBuy);
        if (castSellOrder != null) {
            addToMaps(castSellOrder, sellOrBuy);
        }
    }

    /**
     * Method for convert add orders to sell and buy.
     *
     * @param order insert order
     */
    public void convertAdd(Order order) {
        Caster caster = new Caster(this.sell, this.buy, order);
        if (order.operation.equals("SELL")) {
            convertAction(caster, true);
        } else if (order.operation.equals("BUY")) {
            convertAction(caster, false);
        }
    }

    /**
     * Method for convert delete orders to sell and buy.
     *
     * @param order insert order
     */
    public void convertDelete(Order order) {
        deleteOutOfMaps(order, true);
        deleteOutOfMaps(order, false);
    }

    /**
     * Method for delete order out of target map.
     *
     * @param order order
     * @param sellOrBuy sell or buy map
     */
    private void deleteOutOfMaps(Order order, boolean sellOrBuy) {
        TreeMap<Double, Order> targetMap = sellOrBuy ? this.sell : this.buy;
        for (Order orders : targetMap.values()) {
            if (orders.orderId == order.orderId) {
                targetMap.remove(orders.price);
                break;
            }
        }
    }

    /**
     * Method for add order to target map
     *
     * @param order order
     * @param sellOrBuy sell or buy map
     */
    private void addToMaps(Order order, boolean sellOrBuy) {
        TreeMap<Double, Order> targetMap = sellOrBuy ? this.sell : this.buy;

        if (targetMap.computeIfAbsent(order.price, k -> order) != order) {
            targetMap.computeIfPresent(order.price, (k, v) -> v = new Order(order.book, order.operation, order.price,
                    order.volume + targetMap.get(order.price).volume, order.orderId));
        }
    }
}
