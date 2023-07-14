package com.example.storemanagementsystemfx.model.ui;

public class NOOChart {
    String date;
    int quantity;

    public NOOChart(String date, int quantity) {
        this.date = date;
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
