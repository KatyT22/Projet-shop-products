package com.alten.shop.api.controller;
import com.alten.shop.api.model.Product;
import com.alten.shop.api.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @MockBean
    private ProductService productService;
    @Autowired
    private MockMvc mvc;

    /*@InjectMocks
    private ProductController productController;*/

    private List<Product> products;
    private Product product;
    @BeforeEach
    public void startTest(){
        product = new Product(152, "code_152", "product_1", "Description_product_1", 10f, 10, "INSTOCK", "Stuff", "product1.jpg", 5);
        products = new ArrayList<Product>();
        products.add(product);
    }
    @Test
    public void getAllProductsAPITest() throws Exception {
        //Product product = null;
        when(productService.getAllProducts()).thenReturn(products);
        mvc.perform(MockMvcRequestBuilders.get("/api/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());


        //verify(productService).getAllProducts();
    }
    @Test
    public void getProductByIdTest() throws Exception {

        when(productService.getProductById(152)).thenReturn(Optional.of(product));
        mvc.perform(MockMvcRequestBuilders.get("/api/products/152")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("product_1"));

        //verify(productService).getProductById(152);
    }

}
