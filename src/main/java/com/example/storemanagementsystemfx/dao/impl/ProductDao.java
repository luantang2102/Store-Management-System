package com.example.storemanagementsystemfx.dao.impl;

import com.example.storemanagementsystemfx.dao.itface.IProductDao;
import com.example.storemanagementsystemfx.model.Product;
import com.example.storemanagementsystemfx.model.holder.UserHolder;
import com.example.storemanagementsystemfx.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductDao implements IProductDao {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public List<Product> getAll() {
        try {
            connection = DBUtils.getConnection();
            String sql = "SELECT * FROM products WHERE userId = ?";
            List<Product> products = new ArrayList<>();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, UserHolder.getInstance().getUser().getUserId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(new Product(resultSet.getString(1),//Product ID
                                        resultSet.getString(2),//Product Name
                                        resultSet.getDouble(3),//Price
                                        resultSet.getString(4),//Type
                                        resultSet.getString(5)));//Status
            }
            return products;
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
    public Product getByProductId(String productId) {
        try {
            connection = DBUtils.getConnection();
            String sql = "SELECT * FROM products WHERE productId = ? AND userId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productId);
            preparedStatement.setString(2, UserHolder.getInstance().getUser().getUserId());
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return new Product(resultSet.getString(1),//Product ID
                        resultSet.getString(2),//Product Name
                        resultSet.getDouble(3),//Price
                        resultSet.getString(4),//Type
                        resultSet.getString(5));//Status
            }
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
    public int save(Product product) {
        try {
            connection = DBUtils.getConnection();
            String sql = "INSERT INTO products(productId, productName, price, type, status, userId) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getProductId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setString(4, product.getType());
            preparedStatement.setString(5, product.getStatus());
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
    public int update(Product product) {
        try {
            connection = DBUtils.getConnection();
            String sql = "UPDATE products SET productId = ?, productName = ?, price = ?, type = ?, status = ? WHERE productId = ? AND userId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setString(4, product.getType());
            preparedStatement.setString(5, product.getStatus());
            preparedStatement.setString(6, product.getProductId());
            preparedStatement.setString(7, UserHolder.getInstance().getUser().getUserId());
            preparedStatement.setString(1, product.getProductId());
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
    public int delete(String productId) {
        try {
            connection = DBUtils.getConnection();
            String sql = "DELETE FROM products WHERE productId = ? AND userId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productId);
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
