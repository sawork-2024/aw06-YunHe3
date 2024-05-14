package com.micropos.products.repository;

import com.micropos.products.model.Product;

import java.util.List;

public interface ProductRepository {
    public List<Product> getProducts();
    public Product getProduct(String pid);
}
