package com.meli.socialmeli.repository;

import com.meli.socialmeli.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
