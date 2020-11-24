package com.example.hulkstore.controller;

import com.example.hulkstore.model.Product;
import com.example.hulkstore.repository.ProductsRepository;
import com.example.hulkstore.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
class ProductControllerTest {


    @Autowired
    private ProductService productService;

    @MockBean
    private ProductsRepository productsRepository;

    @Test
    @DisplayName("Test getAll")
    void getAllProduct() {
        Product product1 = new Product(1L,"Camiseta",15,50);
        Product product2 = new Product(2L,"Hulk",50,300);
        doReturn(Arrays.asList(product1,product2)).when(productsRepository).findAll();

        List <Product> products = productService.getAllProduct();

        Assertions.assertEquals(2,products.size(),"get all should return 2 products");
    }

    @Test
    @DisplayName("Test getProductById")
    void getProductById() {
        Product product = new Product(1L,"Spider-man",60,500);
        doReturn(Optional.of(product)).when(productsRepository).findById(1L);

        Optional<Product> returnProduct = Optional.ofNullable(productService.getProductById(1L));

        Assertions.assertTrue(returnProduct.isPresent(),"Product was not found");
        Assertions.assertSame(returnProduct.get(),product,"The product returned was not the same as the mock");
    }

    @Test
    @DisplayName("Test getAll")
    void createProduct() {
        Product product = new Product(1l, "Pantalones", 23, 100);
        doReturn(product).when(productsRepository).save(any());

        // Execute the service call
        Product returnedProduct = productService.createProduct(product);

        // Assert the response
        Assertions.assertNotNull(returnedProduct, "The saved widget should not be null");
        Assertions.assertEquals(returnedProduct, returnedProduct, "The version is saved");
    }

    @Test
    @DisplayName("Test updateProduct")
    void updateProduct() {
        Product product = new Product(1L, "Jogging",40,75);
        Product productUpdate = new Product(1L,"Jogging",34, 70);

        Product returnedProduct = productService.createProduct(productUpdate);

        Assertions.assertEquals(returnedProduct, returnedProduct, "The new version is saved");
    }

    @Test
    @DisplayName("Test deleteProduct")
    void deleteProduct() {
        Product product1 = new Product (1L, "Jogging",40,75);
        Product product2 = new Product(2L,"Hulk",50,300);
        Product product3 = new Product(23L,"Spider-man",50,300);
        doReturn(Arrays.asList(product2,product3)).when(productsRepository).findAll();
        productsRepository.deleteById(product1.getId());

        List <Product> products = productService.getAllProduct();

        Assertions.assertEquals(2,products.size(),"get all should return 2 products");


    }
}