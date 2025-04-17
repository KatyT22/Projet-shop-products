package com.project.shop.api.service;

import com.project.shop.api.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product createProduct(Product product);
    public Optional<Product> getProductById(Long id);
    public Product updateProduct(Long id, Product productBody);
    public void deleteProduct(Long id);

}
