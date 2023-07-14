package com.example.storemanagementsystemfx.dao.impl;

import com.example.storemanagementsystemfx.dao.itface.IOrderDao;
import com.example.storemanagementsystemfx.model.Order;
import com.example.storemanagementsystemfx.model.holder.UserHolder;
import com.example.storemanagementsystemfx.model.ui.ICChart;
import com.example.storemanagementsystemfx.model.ui.NOOChart;
import com.example.storemanagementsystemfx.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class OrderDao implements IOrderDao {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public List<Order> getAll() {
        try {
            connection = DBUtils.getConnection();
            String sql = "SELECT * FROM orders WHERE userId = ? ";
            List<Order> orders = new ArrayList<>();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, UserHolder.getInstance().getUser().getUserId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Calendar orderDate = Calendar.getInstance();
                orderDate.setTimeInMillis(resultSet.getTimestamp(2).getTime());
                orders.add(new Order(resultSet.getString(1), //Order ID
                                    orderDate,                          //Order Date
                                    resultSet.getDouble(3),  //Amount
                                    resultSet.getInt(4),     //Items
                                    resultSet.getString(5)));//Customer ID
            }
            return orders;
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
    public Order getByOrderId(String orderId) {
        try {
            connection = DBUtils.getConnection();
            String sql = "SELECT * FROM orders WHERE orderId = ? AND userId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, orderId);
            preparedStatement.setString(2, UserHolder.getInstance().getUser().getUserId());
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Calendar orderDate = Calendar.getInstance();
            orderDate.setTimeInMillis(resultSet.getTimestamp(2).getTime());
            return new Order(resultSet.getString(1), //Order ID
                    orderDate,                          //Order Date
                    resultSet.getDouble(3),  //Amount
                    resultSet.getInt(4),     //Items
                    resultSet.getString(5));//Customer ID
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
    public List<ICChart> incomesByDate() {
        try {
            connection = DBUtils.getConnection();
            String sql = "SELECT DATE(orderDate), SUM(amount) FROM orders WHERE userId = ? GROUP BY DATE(orderDate) ORDER BY DATE(orderDate) ASC LIMIT 7";
            List<ICChart> data = new ArrayList<>();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, UserHolder.getInstance().getUser().getUserId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                data.add(new ICChart(resultSet.getString(1), resultSet.getDouble(2)));
            }
            return data;
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
    public List<NOOChart> numbersOfOrdersByDate() {
        try {
            connection = DBUtils.getConnection();
            String sql = "SELECT DATE(orderDate), COUNT(orderId) FROM orders WHERE userId = ? GROUP BY DATE(orderDate) ORDER BY DATE(orderDate) ASC LIMIT 7";
            List<NOOChart> data = new ArrayList<>();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, UserHolder.getInstance().getUser().getUserId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                data.add(new NOOChart(resultSet.getString(1), resultSet.getInt(2)));
            }
            return data;
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
    public int save(Order order) {
        try {
            connection = DBUtils.getConnection();
            String sql = "INSERT INTO orders(orderId, orderDate , amount, items, customerId, userId) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, order.getOrderId());
            preparedStatement.setTimestamp(2, new Timestamp(order.getOrderDate().getTimeInMillis()));
            preparedStatement.setDouble(3, order.getAmount());
            preparedStatement.setInt(4, order.getItems());
            preparedStatement.setString(5, order.getCustomerId());
            preparedStatement.setString(6, UserHolder.getInstance().getUser().getUserId());
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
    public int update(Order order) {
        try {
            connection = DBUtils.getConnection();
            String sql = "UPDATE orders SET orderDate = ?, amount = ?, items = ?, customerId = ? WHERE orderId = ? AND userId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setTimestamp(1, new Timestamp(order.getOrderDate().getTimeInMillis()));
            preparedStatement.setDouble(2, order.getAmount());
            preparedStatement.setInt(3, order.getItems());
            preparedStatement.setString(4, order.getCustomerId());
            preparedStatement.setString(5, order.getOrderId());
            preparedStatement.setString(6, UserHolder.getInstance().getUser().getUserId());
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
    public int delete(String orderId) {
        try {
            connection = DBUtils.getConnection();
            String sql = "DELETE FROM orders WHERE orderId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, orderId);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
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
