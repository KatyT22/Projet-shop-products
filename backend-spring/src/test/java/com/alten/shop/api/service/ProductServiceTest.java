package com.alten.shop.api.service;

import com.alten.shop.api.model.Product;
import com.alten.shop.api.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository productRepository;
    private List<Product> products;

    @BeforeEach
    public void startTest(){
        Product product = new Product(153, "code_152", "product_1", "Description_product_1", 10f, 10, "INSTOCK", "Stuff", "product1.jpg", 5);
        products = new ArrayList<Product>();
        products.add(product);
    }

    @Test
    public void getAllProductsTest() throws Exception{
        //Arrange
        when(productRepository.findAll()).thenReturn(products);
        //Act
        List<Product> results = productService.getAllProducts();
        //Assert
        assertEquals(results.getFirst(), products.getFirst());
    }

}
