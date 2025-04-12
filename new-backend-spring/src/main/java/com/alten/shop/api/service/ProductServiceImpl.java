package com.alten.shop.api.service;

import com.alten.shop.api.model.Product;
import com.alten.shop.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public List<Product> getAllProducts () {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository
                .save(new Product(product.getId(), product.getCode(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(), product.getInventoryStatus(), product.getCategory(), product.getImage(), product.getRating()));
    }

    @Override
    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product updateProduct(long id, Product productBody) {
        Optional<Product> p = productRepository.findById(id);

        return p.map(product -> productRepository.save(editProduct(productBody, product))).orElse(null);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }


    private static Product editProduct(Product newProductData, Product oldProduct) {
        oldProduct.setCode(newProductData.getCode());
        oldProduct.setName(newProductData.getName());
        oldProduct.setDescription(newProductData.getDescription());
        oldProduct.setPrice(newProductData.getPrice());
        oldProduct.setId(newProductData.getId());
        oldProduct.setImage(newProductData.getImage());
        oldProduct.setCategory(newProductData.getCategory());
        oldProduct.setInventoryStatus(newProductData.getInventoryStatus());
        oldProduct.setQuantity(newProductData.getQuantity());
        oldProduct.setRating(newProductData.getRating());
        return oldProduct;
    }


}
