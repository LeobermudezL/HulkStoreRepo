package com.example.hulkstore.repository;


import com.example.hulkstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository <Product, Long> {

}
