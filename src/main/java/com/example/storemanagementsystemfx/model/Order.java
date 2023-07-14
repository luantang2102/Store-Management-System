package com.example.storemanagementsystemfx.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Order {
    private String orderId;
    private Calendar orderDate;
    private String orderDateView;
    private Double amount;
    private int items;
    private String customerId;
    private String customerName;

    public Order() {
        orderId = "OD" + (new Random().nextInt(99999999) + 100000000);
        this.orderDate = Calendar.getInstance();
        orderDateView = new SimpleDateFormat("MMM-dd-yyyy HH:mm:ss").format(orderDate.getTime());
        this.amount = 0.0;
    }

    //4-args constructor (order ID auto-generated)
    public Order(Calendar orderDate, Double amount, int items, String customerId) {
        orderId = "OD" + (new Random().nextInt(99999999) + 100000000);
        this.orderDate = orderDate;
        orderDateView = new SimpleDateFormat("MMM-dd-yyyy HH:mm:ss").format(orderDate.getTime());
        this.amount = amount;
        this.items = items;
        this.customerId = customerId;
    }

    //5-args constructor
    public Order(String orderId, Calendar orderDate, Double amount, int items, String customerId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        orderDateView = new SimpleDateFormat("MMM-dd-yyyy  HH:mm:ss").format(orderDate.getTime());
        this.amount = amount;
        this.items = items;
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderDate=" + orderDate +
                ", orderDateView='" + orderDateView + '\'' +
                ", amount=" + amount +
                ", items=" + items +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Calendar getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Calendar orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderDateView() {
        return orderDateView;
    }

    public void setOrderDateView(String orderDateView) {
        this.orderDateView = orderDateView;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
