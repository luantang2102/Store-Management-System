package com.example.storemanagementsystemfx.service;

import com.example.storemanagementsystemfx.dao.itface.IProductDao;
import com.example.storemanagementsystemfx.model.Product;

import java.util.List;

public class ProductService {
    private IProductDao productDao;

    public ProductService(IProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getAll() {
        return productDao.getAll();
    }
    public Product getByProductId(String productId) {
        return productDao.getByProductId(productId);
    }
    public int save(Product product) {
        return productDao.save(product);
    }
    public int update(Product product) {
        return productDao.update(product);
    }
    public int delete(String productId) {
        return productDao.delete(productId);
    }
}
