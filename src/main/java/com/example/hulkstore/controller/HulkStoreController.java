package com.example.hulkstore.controller;


import com.example.hulkstore.model.Product;
import com.example.hulkstore.model.User;
import com.example.hulkstore.service.ProductService;
import com.example.hulkstore.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@Api(tags= {"hulkStore"})
@RestController
@RequestMapping("/api/v1")
@Component
public class HulkStoreController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @GetMapping(path="/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "getAllProducts", notes = "endpoint to verify products in DB", response = String.class)
    public ResponseEntity <List<Product>> getAllProduct() {
        return ResponseEntity.ok().body(productService.getAllProduct());
    }
    @GetMapping(path="/products/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Product> getProductById(@PathVariable long id){
        return ResponseEntity.ok().body(productService.getProductById(id));
    }
    @PostMapping(path="/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok().body(productService.createProduct(product));
    }
    @PutMapping(path="/products/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity < Product > updateProduct(@PathVariable long id, @RequestBody Product product) {
        product.setId(id);
        return ResponseEntity.ok().body(productService.updateProduct(product));
    }
    @DeleteMapping(path="/products/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return HttpStatus.OK;
    }
    @GetMapping(path="/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "getAllUsers", notes = "endpoint to verify users in DB", response = String.class)
    public ResponseEntity <List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }
    @GetMapping(path="/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <User> getUserById(@PathVariable long id){
        return ResponseEntity.ok().body(userService.getUserById(id));
    }
    @PostMapping(path="/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.createUser(user));
    }
    @PutMapping(path="/users/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity < User > updateUser (@PathVariable long id, @RequestBody User user) {
        user.setId(id);
        return ResponseEntity.ok().body(userService.updateUser(user));
    }
    @DeleteMapping(path="/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return HttpStatus.OK;
    }
}
