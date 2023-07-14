package com.example.storemanagementsystemfx.dao.impl;

import com.example.storemanagementsystemfx.dao.itface.ICustomerDao;
import com.example.storemanagementsystemfx.model.Customer;
import com.example.storemanagementsystemfx.model.holder.UserHolder;
import com.example.storemanagementsystemfx.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerDao implements ICustomerDao {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    @Override
    public List<Customer> getAll() {
        try {
            connection = DBUtils.getConnection();
            String sql = "SELECT * FROM customers where userId = ?";

            List<Customer> customers = new ArrayList<>();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, UserHolder.getInstance().getUser().getUserId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customers.add(new Customer(resultSet.getString(1),//Customer ID
                                        resultSet.getString(2),//Customer Name
                                        resultSet.getString(3),//Customer Email
                                        resultSet.getString(4)));//Customer Phone number
            }
            return customers;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.close(connection, preparedStatement, resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public Customer getById(String customerId) {
        try {
            connection = DBUtils.getConnection();
            String sql = "SELECT * FROM customers WHERE customerId = ? AND userId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customerId);
            preparedStatement.setString(2, UserHolder.getInstance().getUser().getUserId());
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Customer(resultSet.getString(1),//Customer ID
                            resultSet.getString(2),//Customer Name
                            resultSet.getString(3),//Customer Email
                            resultSet.getString(4));//Customer Phone number
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.close(connection, preparedStatement, resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int save(Customer customer) {
        try {
            connection = DBUtils.getConnection();
            String sql = "INSERT INTO customers(customerId, customerName, email, phoneNum, userId) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getCustomerId());
            preparedStatement.setString(2, customer.getCustomerName());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPhoneNum());
            preparedStatement.setString(5, UserHolder.getInstance().getUser().getUserId());
            int i = preparedStatement.executeUpdate();
            if(i>0) {
                System.out.println("Added!");
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.close(connection, preparedStatement, resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Add failed :(");
        return 0;
    }

    @Override
    public int update(Customer customer) {
        try {
            connection = DBUtils.getConnection();
            String sql = "UPDATE customers SET customerName = ?, email = ?, phoneNum = ? WHERE customerId = ? and userId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPhoneNum());
            preparedStatement.setString(4, customer.getCustomerId());
            preparedStatement.setString(5, UserHolder.getInstance().getUser().getUserId());
            int i = preparedStatement.executeUpdate();
            if(i>0) {
                System.out.println("Updated!");
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.close(connection, preparedStatement, resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Update failed :(");
        return 0;
    }

    @Override
    public int delete(String customerId) {
        try {
            connection = DBUtils.getConnection();
            String sql = "DELETE FROM customers WHERE customerId = ? AND userId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customerId);
            preparedStatement.setString(2, UserHolder.getInstance().getUser().getUserId());
            int i = preparedStatement.executeUpdate();
            if(i>0) {
                System.out.println("Deleted!");
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.close(connection, preparedStatement, resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Delete failed :(");
        return 0;
    }
}
