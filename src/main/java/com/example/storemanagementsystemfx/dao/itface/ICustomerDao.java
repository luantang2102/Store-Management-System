package com.example.storemanagementsystemfx.dao.itface;

import com.example.storemanagementsystemfx.model.Customer;

import java.util.List;

public interface ICustomerDao {
    List<Customer> getAll();
    Customer getById(String id);
    int save(Customer t);
    int update(Customer t);
    int delete(String customerId);
}
