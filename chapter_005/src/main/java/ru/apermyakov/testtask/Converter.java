package ru.apermyakov.testtask;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
    Map<Double, Order> sell = new TreeMap<>(Comparator.naturalOrder());

    /**
     * Filed for sorted by price save buy orders.
     */
    Map<Double, Order> buy = new TreeMap<>(Comparator.reverseOrder());

    /**
     * Method for initialize convert action.
     *
     * @param caster caster for check order
     * @param sellOrBuy sell or buy map
     * @param supplier sell or buy map
     */
    private void convertAction(Caster caster, boolean sellOrBuy, Supplier<Map<Double, Order>> supplier) {
        Order castSellOrder = caster.cast(sellOrBuy, supplier);
        if (castSellOrder != null) {
            addToMaps(castSellOrder, supplier);
        }
    }

    /**
     * Method for convert add orders to sell and buy.
     *
     * @param order insert order
     */
    public void convertAdd(Order order) {
        Caster caster = new Caster(order);
        if (order.getOperation().equals("SELL")) {
            convertAction(caster, true, () -> this.sell);
        } else if (order.getOperation().equals("BUY")) {
            convertAction(caster, false, () -> this.buy);
        }
    }

    /**
     * Method for convert delete orders to sell and buy.
     *
     * @param order insert order
     */
    public void convertDelete(Order order) {
        deleteOutOfMaps(order, () -> this.sell);
        deleteOutOfMaps(order, () -> this.buy);
    }

    /**
     * Method for delete order out of target map.
     *
     * @param order order
     * @param supplier sell or buy map
     */
    private void deleteOutOfMaps(Order order, Supplier<Map<Double, Order>> supplier) {
        Map<Double, Order> templateMap = supplier.get().values()
                .stream()
                .filter(i -> order.getOrderId() != i.getOrderId())
                .collect(Collectors.toMap(
                    Order::getPrice,
                    p -> p
        ));
        supplier.get().clear();
        supplier.get().putAll(templateMap);
    }

    /**
     * Method for add order to target map
     *
     * @param order order
     * @param supplier sell or buy map
     */
    private void addToMaps(Order order, Supplier<Map<Double, Order>> supplier) {
        if (supplier.get().computeIfAbsent(order.getPrice(), k -> order) != order) {
            supplier.get().computeIfPresent(order.getPrice(), (k, v) -> v = new Order(order.getBook(), order.getBook(), order.getPrice(),
                    order.getVolume() + supplier.get().get(order.getPrice()).getVolume(), order.getOrderId()));
        }
    }
}
