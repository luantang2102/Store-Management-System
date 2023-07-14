package com.example.storemanagementsystemfx.dao.itface;

import com.example.storemanagementsystemfx.model.Product;

import java.util.List;

public interface IProductDao {
    List<Product> getAll();
    Product getByProductId(String productId);
    int save(Product product);
    int update(Product product);
    int delete(String productId);
}
