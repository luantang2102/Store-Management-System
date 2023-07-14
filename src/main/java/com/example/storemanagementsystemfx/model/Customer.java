package com.example.storemanagementsystemfx.model;

import java.util.Random;

public class Customer {
    private String customerId;
    private String customerName;
    private String email;
    private String phoneNum;

    public Customer(String customerName, String email, String phoneNum) {
        customerId = "CS" + (new Random().nextInt(99999999) + 100000000);
        this.customerName = customerName;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public Customer(String customerId, String customerName, String email, String phoneNum) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }

    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public boolean compare(Customer customer) {
        return customer.getCustomerId().equals(this.customerId)
                && customer.getCustomerName().equals(this.customerName)
                && customer.getEmail().equals(this.email)
                && customer.getPhoneNum().equals(this.phoneNum);
    }
}
