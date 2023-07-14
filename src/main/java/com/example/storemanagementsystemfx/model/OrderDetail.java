package com.example.storemanagementsystemfx.model;

public class OrderDetail {
    private String orderId;
    private String productId;
    private int quantity;
    private String productName;
    private String productType;
    private Double productPrice;
    public OrderDetail(String orderId, String productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public boolean compare(OrderDetail orderDetail) {
        return orderDetail.getOrderId().equals(this.orderId)
                && orderDetail.getProductId().equals(this.productId)
                && orderDetail.getQuantity() == this.quantity;
    }
}
