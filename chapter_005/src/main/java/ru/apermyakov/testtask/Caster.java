package ru.apermyakov.testtask;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * Class for cast check orders by price.
 *
 * @author apermyakov
 * @version 1.0
 * @since 14.11.2017
 */
public class Caster {

    /**
     * Field for sell map.
     */
    TreeMap<Double, Order> sell;

    /**
     * Field for buy map.
     */
    TreeMap<Double, Order> buy;

    /**
     * Field for order.
     */
    Order order;

    /**
     * Initialize caster.
     *
     * @param sell sell map
     * @param buy buy map
     * @param order insert order
     */
    public Caster(TreeMap<Double, Order> sell, TreeMap<Double, Order> buy, Order order) {
        this.sell = sell;
        this.buy = buy;
        this.order = order;
    }

    /**
     * Method for delete orders from map.
     *
     * @param removeOrders remove orders
     * @param sellOrBuy sell or buy map
     */
    private void removeAction(List<Double> removeOrders, boolean sellOrBuy) {
        for (Double price : removeOrders) {
            Order remove = sellOrBuy ? buy.remove(price) : sell.remove(price);
        }
    }

    /**
     * Method for check order and change values of orders.
     *
     * @param removeOrders remove orders
     * @param sellOrBuy sell or buy map
     */
    private void castAction(List<Double> removeOrders, boolean sellOrBuy) {
        TreeMap<Double, Order> targetMap;
        targetMap = sellOrBuy ? this.buy : this.sell;
        for (Order buyOrder : targetMap.values()) {
            if (order != null && sellOrBuy ? order.price <= buyOrder.price : order.price >= buyOrder.price) {
                if (order.volume < buyOrder.volume) {
                    targetMap.put(buyOrder.price, new Order(buyOrder.book, buyOrder.operation,
                            buyOrder.price, buyOrder.volume - order.volume, buyOrder.orderId));
                    order = null;
                    break;
                } else if (order.volume == buyOrder.volume) {
                    targetMap.remove(buyOrder.price);
                    order = null;
                    break;
                } else {
                    order.volume -= buyOrder.volume;
                    removeOrders.add(buyOrder.price);
                }
            }
        }
    }

    /**
     * Method for initialize cast actions.
     *
     * @param sellOrBuy sell or buy action
     * @return result order
     */
    public Order cast(boolean sellOrBuy) {
        List<Double> removeOrders = new LinkedList<>();

        castAction(removeOrders, sellOrBuy);
        removeAction(removeOrders, sellOrBuy);
        return order;
    }
}
