package com.project.productservice.service;

import com.project.productservice.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProduct();
    public Product getProductById(int id);
}
