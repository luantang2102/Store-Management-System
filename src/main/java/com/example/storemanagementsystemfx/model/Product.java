package com.example.storemanagementsystemfx.model;

import java.util.Random;

public class Product {
    private String productId;
    private String productName;
    private double price;
    private String type;
    private String status;

    public Product() {
    }

    public Product(String name, double price, String type, String status) {
        productId = ("PD" + (new Random().nextInt(99999999) + 100000000));
        this.productName = name;
        this.price = price;
        this.type = type;
        this.status = status;
    }

    public Product(String productId, String productName, double price, String type, String status) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.type = type;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public boolean compare(Product product) {
        return product.getProductId().equals(this.productId)
                && product.getProductName().equals(this.productName)
                && product.getPrice() == this.price
                && product.getType().equals(this.type)
                && product.getStatus().equals(this.status);
    }
}
