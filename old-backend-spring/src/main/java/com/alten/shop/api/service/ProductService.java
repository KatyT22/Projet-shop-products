package com.alten.shop.api.service;

import com.alten.shop.api.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product createProduct(Product product);
    public Optional<Product> getProductById(long id);
    public Product updateProduct(long id, Product productBody);
    public void deleteProduct(long id);

}
