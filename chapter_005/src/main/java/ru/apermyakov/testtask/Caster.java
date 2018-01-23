package ru.apermyakov.testtask;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Class for cast check orders by price.
 *
 * @author apermyakov
 * @version 1.0
 * @since 14.11.2017
 */
public class Caster {

    /**
     * Field for order.
     */
    private Order order;

    /**
     * Initialize caster.
     *
     * @param order insert order
     */
    public Caster(Order order) {
        this.order = order;
    }

    /**
     * Method for delete orders from map.
     *
     * @param removeOrders remove orders
     * @param supplier sell or buy map
     */
    private void removeAction(List<Double> removeOrders, Supplier<Map<Double, Order>> supplier) {
        removeOrders.forEach(supplier.get()::remove);
    }

    /**
     * Method for check order and change values of orders.
     *
     * @param removeOrders remove orders
     * @param sellOrBuy sell or buy map
     * @param supplier sell or buy map
     */
    private void castAction(List<Double> removeOrders, boolean sellOrBuy, Supplier<Map<Double, Order>> supplier) {
        for (Order buyOrder : supplier.get().values()) {
            if (this.order != null && sellOrBuy ? this.order.getPrice() <= buyOrder.getPrice() : this.order.getPrice() >= buyOrder.getPrice()) {
                if (this.order.getVolume() < buyOrder.getVolume()) {
                    supplier.get().put(buyOrder.getPrice(), new Order(buyOrder.getBook(), buyOrder.getOperation(),
                            buyOrder.getPrice(), buyOrder.getVolume() - this.order.getVolume(), buyOrder.getOrderId()));
                    this.order = null;
                    break;
                } else if (this.order.getVolume() == buyOrder.getVolume()) {
                    supplier.get().remove(buyOrder.getPrice());
                    this.order = null;
                    break;
                } else {
                    this.order.setVolume(this.order.getVolume() - buyOrder.getVolume());
                    removeOrders.add(buyOrder.getPrice());
                }
            }
        }
    }

    /**
     * Method for initialize cast actions.
     *
     * @param sellOrBuy sell or buy action
     * @param supplier sell or buy map
     * @return result order
     */
    public Order cast(boolean sellOrBuy, Supplier<Map<Double, Order>> supplier) {
        List<Double> removeOrders = new LinkedList<>();

        castAction(removeOrders, sellOrBuy, supplier);
        removeAction(removeOrders, supplier);
        return order;
    }
}
