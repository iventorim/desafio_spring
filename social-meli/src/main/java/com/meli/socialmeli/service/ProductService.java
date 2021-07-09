package com.meli.socialmeli.service;

import com.meli.socialmeli.entity.Product;
import com.meli.socialmeli.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createNewProduct(Product product) {
        if (Objects.isNull(product.getProductId())
                || isNullOrBlank(product.getProductName())
                || isNullOrBlank(product.getBrand())
                || isNullOrBlank(product.getColor())
                || isNullOrBlank(product.getNotes())
                || isNullOrBlank(product.getType())
        ) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Não pode haver valores nulos ou vazios no corpo da requisição");
        }

        boolean productAlreadyExists = productRepository.findById(product.getProductId()).stream().anyMatch(p -> p.getProductId() == product.getProductId());
        if (productAlreadyExists) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Já existe cadastro para produto com este ID");
        }
        productRepository.save(product);
    }

    public static boolean isNullOrBlank(String param) {
        return Objects.isNull(param) || param.trim().isEmpty();
    }
}
