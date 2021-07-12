package com.meli.socialmeli.service;

import com.meli.socialmeli.entity.Product;
import com.meli.socialmeli.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public static boolean isNullOrBlank(String param) {
        return Objects.isNull(param) || param.trim().isEmpty();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createNewProduct(Product product) {

        return productRepository.save(product);
    }

    public void updateProduct(int id, Product product) {

        Optional<Product> productToBeUpdated = productRepository
                .findById(id);

        if(productToBeUpdated.isPresent()){
            product.setProductId(id);
        }

        productRepository.save(product);

    }

    public void deleteProduct(int id) {
        Product productToBeDeleted = productRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Não foi encontrado nenhum produto com o id: " + id));
        productRepository.delete(productToBeDeleted);
    }
}
