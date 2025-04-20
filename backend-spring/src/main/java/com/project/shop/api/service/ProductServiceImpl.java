package com.project.shop.api.service;

import com.project.shop.api.model.Product;
import com.project.shop.api.repository.ProductRepository;
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
                .save(new Product(null, product.getCode(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(), product.getInventoryStatus(), product.getCategory(), product.getImage(), product.getRating()));
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product updateProduct(Long id, Product productBody) {
        Optional<Product> p = productRepository.findById(id);

        return p.map(product -> productRepository.save(editProduct(productBody, product))).orElse(null);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }


    private Product editProduct(Product newProductData, Product oldProduct) {
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


    public List<Product> sortProducts(List<Product> products, String column, int direction){

        switch (column){
            case "Name" :
                products.sort((productA, productB) -> direction * productA.getName().compareTo(productB.getName()));
                break;
            case "Price" :
                products.sort((productA, productB) -> direction * Float.compare(productA.getPrice(), productB.getPrice()));
                break;
            case "Rating" :
                products.sort((productA, productB) -> direction * Float.compare(productA.getRating(), productB.getRating()));
                break;
            case "Quantity":
                products.sort((productA, productB) -> direction * Float.compare(productA.getQuantity(), productB.getQuantity()));
                break;
        }
        return products;
    }

    public List<Product> filterProductsByInventoryStatus(List<Product> products, String inventoryStatus){
        return products.stream().filter(product -> product.getInventoryStatus().equals(inventoryStatus)).toList();
    }

    public List<Product> filterProductsByCategory(List<Product> products, String categoryType){
        return products.stream().filter(product -> product.getCategory().equals(categoryType)).toList();
    }

    public List<Product> filterProductsWhithinPriceRange(List<Product> products, float minPrice, float maxPrice){
        return products.stream().filter(product -> product.getPrice()<= maxPrice).filter(product -> product.getPrice()>=minPrice).toList();
    }

    public List<Product> filterProductsWithinQuantityRange(List<Product> products, int minQuantity, int maxQuantity){
        return products.stream().filter(product -> product.getQuantity()<= maxQuantity).filter(product -> product.getQuantity()>=minQuantity).toList();
    }

    public List<Product> filterProductsWithinRatingRange(List<Product> products, int minRating, int maxRating){
        return products.stream().filter(product -> product.getRating()<= maxRating).filter(product -> product.getRating()>=minRating).toList();
    }

    public double calculateRatingAverage(List<Product> products){
        return products.stream().mapToLong(Product::getRating).average().orElse(-1);
    }

    public List<Product> filterProductsByName(List<Product> products, String words){
        return products.stream().filter(product -> product.getName().contains(words)).toList();
    }

}
