package com.example.storemanagementsystemfx.service;

import com.example.storemanagementsystemfx.dao.impl.CustomerDao;
import com.example.storemanagementsystemfx.dao.itface.ICustomerDao;
import com.example.storemanagementsystemfx.model.Customer;

import java.util.List;

public class CustomerService {
    private ICustomerDao customerDao;

    public CustomerService(ICustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAll() {
        return customerDao.getAll();
    }

    public Customer getByCustomerId(String customerId) {
        return customerDao.getById(customerId);
    }

    public int save(Customer customer) {
        return customerDao.save(customer);
    }

    public int update(Customer customer) {
        return customerDao.update(customer);
    }

    public int delete(String customerId) {
        return customerDao.delete(customerId);
    }
}
