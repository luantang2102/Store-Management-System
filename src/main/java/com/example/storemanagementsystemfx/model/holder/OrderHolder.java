package com.example.storemanagementsystemfx.model.holder;

import com.example.storemanagementsystemfx.model.Order;

public class OrderHolder {
    private Order order;
    private final static OrderHolder INSTANCE = new OrderHolder();
    private OrderHolder() {}

    public static OrderHolder getInstance() {
        return INSTANCE;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
