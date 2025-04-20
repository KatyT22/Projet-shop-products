package com.project.shop.api.service;

import com.project.shop.api.model.Product;
import com.project.shop.api.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository productRepository;
    //private Product[] products;

    Product productA = new Product(153L, "code_153", "productA", "Description_productA", 10f, 10, "INSTOCK", "Accessories", "productA.jpg", 3);
    Product productB = new Product(154L, "code_154", "productB", "Description_productB", 11f, 11, "LOWSTOCK", "Clothing", "productB.jpg", 4);
    Product productC = new Product(155L, "code_155", "productC", "Description_productC", 12f, 12, "OUTOFSTOCK", "Electronics", "productC.jpg", 5);


    private final List<Product> productsSortedByPriceQuantityRatingNameAsc = new ArrayList<>();
    private final List<Product> productsSortedByPriceQuantityRatingNameDesc = new ArrayList<>();
    private final List<Product> originalNotSortedProducts = new ArrayList<>();
    private final List<Product> expectedProducts = new ArrayList<>();

    @BeforeEach
    public void startTest(){
        productsSortedByPriceQuantityRatingNameAsc.add(productA);
        productsSortedByPriceQuantityRatingNameAsc.add(productB);
        productsSortedByPriceQuantityRatingNameAsc.add(productC);

        productsSortedByPriceQuantityRatingNameDesc.add(productC);
        productsSortedByPriceQuantityRatingNameDesc.add(productB);
        productsSortedByPriceQuantityRatingNameDesc.add(productA);

        originalNotSortedProducts.add(productB);
        originalNotSortedProducts.add(productC);
        originalNotSortedProducts.add(productA);

        expectedProducts.add(productA);
    }

    @Test
    public void givenProductsList_thenReturnAllProducts() throws Exception{
        //Arrange
        when(productRepository.findAll()).thenReturn(originalNotSortedProducts);
        //Act
        List<Product> results = productService.getAllProducts();
        //Assert
        assertEquals(results.getFirst(), originalNotSortedProducts.getFirst());
        assertNotEquals(results.getLast(), originalNotSortedProducts.getFirst());
    }


    @Test
    public void givenProductsNotSortedList_thenReturnSortedProductsByPriceAsc() {
        System.out.println("Not sorted :");
        originalNotSortedProducts.forEach(item -> System.out.println(item.getPrice()));
        List<Product> sortedProducts = productService.sortProducts(originalNotSortedProducts, "Price", 1);
        assertEquals(productsSortedByPriceQuantityRatingNameAsc, sortedProducts);
        assertNotEquals(productsSortedByPriceQuantityRatingNameDesc, sortedProducts);
        System.out.println("\nSorted :");
        sortedProducts.forEach(item -> System.out.println(item.getPrice()));
    }
    @Test
    public void givenProductsNotSortedDescList_thenReturnSortedProductsByPriceDesc() {
        assertEquals(productsSortedByPriceQuantityRatingNameDesc, productService.sortProducts(originalNotSortedProducts, "Price", -1));
        assertNotEquals(productsSortedByPriceQuantityRatingNameAsc, productService.sortProducts(originalNotSortedProducts, "Price", -1));
    }
    @Test
    public void givenProductsNotSortedList_thenReturnSortedProductsByQuantityAsc() {
        assertEquals(productsSortedByPriceQuantityRatingNameAsc, productService.sortProducts(originalNotSortedProducts, "Quantity", 1));
        assertNotEquals(productsSortedByPriceQuantityRatingNameDesc, productService.sortProducts(originalNotSortedProducts, "Quantity", 1));
    }
    @Test
    public void givenProductsNotSortedList_thenReturnSortedProductsByQuantityDesc() {
        assertEquals(productsSortedByPriceQuantityRatingNameDesc, productService.sortProducts(originalNotSortedProducts, "Quantity", -1));
        assertNotEquals(productsSortedByPriceQuantityRatingNameAsc, productService.sortProducts(originalNotSortedProducts, "Quantity", -1));
    }
    @Test
    public void givenProductsNotSortedList_thenReturnSortedProductsByRatingAsc() {
        assertEquals(productsSortedByPriceQuantityRatingNameAsc, productService.sortProducts(originalNotSortedProducts, "Rating", 1));
        assertNotEquals(productsSortedByPriceQuantityRatingNameDesc, productService.sortProducts(originalNotSortedProducts, "Rating", 1));

    }

    @Test
    public void givenProductsNotSortedList_thenReturnSortedProductsByRatingDesc() {
        assertEquals(productsSortedByPriceQuantityRatingNameDesc, productService.sortProducts(originalNotSortedProducts, "Rating", -1));
        assertNotEquals(productsSortedByPriceQuantityRatingNameAsc, productService.sortProducts(originalNotSortedProducts, "Rating", -1));
    }

    @Test
    public void givenProductsNotSortedList_thenReturnSortedProductsByNameAsc() {
        assertEquals(productsSortedByPriceQuantityRatingNameAsc, productService.sortProducts(originalNotSortedProducts, "Name", 1));
        assertNotEquals(productsSortedByPriceQuantityRatingNameDesc, productService.sortProducts(originalNotSortedProducts, "Name", 1));
    }

    @Test
    public void givenProductsNotSortedList_thenReturnSortedProductsByNameDesc() {
        assertEquals(productsSortedByPriceQuantityRatingNameDesc, productService.sortProducts(originalNotSortedProducts, "Name", -1));
        assertNotEquals(productsSortedByPriceQuantityRatingNameAsc, productService.sortProducts(originalNotSortedProducts, "Name", -1));
    }

    @Test
    public void givenProductsList_thenReturnProductsArrayWithInventoryStatusINSTOCK(){
        assertEquals(expectedProducts,productService.filterProductsByInventoryStatus(originalNotSortedProducts, "INSTOCK"));
        assertNotEquals(originalNotSortedProducts,productService.filterProductsByInventoryStatus(originalNotSortedProducts, "INSTOCK"));
    }

    @Test
    public void givenProductsList_thenReturnProductsArrayWithCategoryAccessories(){
        assertEquals(expectedProducts,productService.filterProductsByCategory(originalNotSortedProducts, "Accessories"));
        assertNotEquals(originalNotSortedProducts,productService.filterProductsByCategory(originalNotSortedProducts, "Accessories"));
    }

    @Test
    public void givenProductsList_thenReturnArraysWithinPriceRange(){
        assertEquals(expectedProducts, productService.filterProductsWhithinPriceRange(originalNotSortedProducts, 9.5f, 10.5f));
        assertNotEquals(originalNotSortedProducts, productService.filterProductsWhithinPriceRange(originalNotSortedProducts, 9.5f, 10.5f));
    }

    @Test
    public void givenProductsList_thenReturnArraysWithinQuantityRange(){
        assertEquals(expectedProducts, productService.filterProductsWithinQuantityRange(originalNotSortedProducts, 9, 10));
        assertNotEquals(originalNotSortedProducts, productService.filterProductsWithinQuantityRange(originalNotSortedProducts, 9, 10));
    }

    @Test
    public void givenProductsList_thenReturnArraysWithinRatingRange(){
        assertEquals(expectedProducts, productService.filterProductsWithinRatingRange(originalNotSortedProducts, 2, 3));
        assertNotEquals(originalNotSortedProducts, productService.filterProductsWithinRatingRange(originalNotSortedProducts, 2, 3));
    }

    @Test
    public void givenProductsList_thenReturnRatingAverage(){
        assertEquals(4d, productService.calculateRatingAverage(originalNotSortedProducts));
        assertNotEquals(5d, productService.calculateRatingAverage(originalNotSortedProducts));
    }

    @Test
    public void givenProductsList_thenReturnProductsContainingAInName(){
        assertEquals(expectedProducts, productService.filterProductsByName(originalNotSortedProducts, "tA"));
        assertEquals(originalNotSortedProducts, productService.filterProductsByName(originalNotSortedProducts, "product"));
        assertNotEquals(originalNotSortedProducts, productService.filterProductsByName(originalNotSortedProducts, "tB"));
    }

}
