package com.meli.socialmeli.service;

import com.meli.socialmeli.entity.Product;
import com.meli.socialmeli.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product createNewProduct(Product product) {
        if (    isNullOrBlank(product.getProductName())
                || isNullOrBlank(product.getBrand())
                || isNullOrBlank(product.getColor())
                || isNullOrBlank(product.getNotes())
                || isNullOrBlank(product.getType())
        ) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Não pode haver valores nulos ou vazios no corpo da requisição");
        }
        return productRepository.save(product);
    }

    public static boolean isNullOrBlank(String param) {
        return Objects.isNull(param) || param.trim().isEmpty();
    }
}
