package com.example.storemanagementsystemfx.dao.impl;

import com.example.storemanagementsystemfx.dao.itface.IOrderDetailDao;
import com.example.storemanagementsystemfx.model.OrderDetail;
import com.example.storemanagementsystemfx.model.holder.UserHolder;
import com.example.storemanagementsystemfx.model.ui.BSPChart;
import com.example.storemanagementsystemfx.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderDetailDao implements IOrderDetailDao {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public List<OrderDetail> getByOrderId(String orderId) {
        try {
            connection = DBUtils.getConnection();
            String sql = "SELECT * FROM orderdetails WHERE orderId = ?";
            List<OrderDetail> orderDetailList = new ArrayList<>();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, orderId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderDetailList.add(new OrderDetail(resultSet.getString(1), //Order ID
                                                    resultSet.getString(2),  //Product ID
                                                    resultSet.getInt(3)));    //Quantity
            }
            return orderDetailList;
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
    public List<BSPChart> getProductTotal() {
        try {
            connection = DBUtils.getConnection();
            String sql = "SELECT productId, SUM(quantity) FROM orderdetails WHERE userId = ? GROUP BY productId ORDER BY SUM(quantity) ASC LIMIT 5";
            List<BSPChart> BSPList = new ArrayList<>();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, UserHolder.getInstance().getUser().getUserId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BSPList.add(new BSPChart(resultSet.getString(1), resultSet.getInt(2)));
            }
            return BSPList;
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
    public int save(OrderDetail orderDetail) {
        try {
            connection = DBUtils.getConnection();
            String sql = "INSERT INTO orderdetails(orderId, productId, quantity, userId) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, orderDetail.getOrderId());
            preparedStatement.setString(2, orderDetail.getProductId());
            preparedStatement.setInt(3, orderDetail.getQuantity());
            preparedStatement.setString(4, UserHolder.getInstance().getUser().getUserId());
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
    public int update(OrderDetail orderDetail, String oldProductId) {
        try {
            connection = DBUtils.getConnection();
            String sql = "UPDATE orderdetails SET productId = ?, quantity = ? WHERE orderId = ? AND productId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, orderDetail.getProductId());
            preparedStatement.setInt(2, orderDetail.getQuantity());
            preparedStatement.setString(3, orderDetail.getOrderId());
            preparedStatement.setString(4, oldProductId);
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
    public int delete(String orderId ,String productId) {
        try {
            connection = DBUtils.getConnection();
            String sql = "DELETE FROM orderdetails WHERE orderId = ? AND productId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, orderId);
            preparedStatement.setString(2, productId);
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
