package com.example.hulkstore.service;

import com.example.hulkstore.exception.ResourceNotFoundException;
import com.example.hulkstore.model.Product;
import com.example.hulkstore.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    static final String RESOURCE_NOT_FOUND = "Product not found with id";
    @Autowired
    ProductsRepository productsRepository;

    @Override
    public Product createProduct(Product product) {
        return productsRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Optional< Product > productDB = this.productsRepository.findById(product.getId());
        if(productDB.isPresent()){
            Product productUpdate = productDB.get();
            productUpdate.setName(product.getName());
            productUpdate.setPrice(product.getPrice());
            productUpdate.setStock(product.getStock());
            if(productUpdate.getStock() < 0){
                throw new ResourceNotFoundException("Product doesn't have stock");
            } else{
                productsRepository.save(productUpdate);
                return productUpdate;
            }
        } else {
            throw new ResourceNotFoundException(RESOURCE_NOT_FOUND + product.getId());
        }
    }

    @Override
    public List<Product> getAllProduct() {
        return  this.productsRepository.findAll();
    }

    @Override
    public Product getProductById(long productId) {
        Optional < Product > productDB = this.productsRepository.findById(productId);
        if(productDB.isPresent()){
            return productDB.get();
        }else {
            throw new ResourceNotFoundException(RESOURCE_NOT_FOUND + productId);
        }
    }

    @Override
    public void deleteProduct(long id) {
        Optional < Product > productDB = this.productsRepository.findById(id);
        if(productDB.isPresent()){
            this.productsRepository.delete(productDB.get());
        }else {
            throw new ResourceNotFoundException(RESOURCE_NOT_FOUND + id);
        }
    }
}
