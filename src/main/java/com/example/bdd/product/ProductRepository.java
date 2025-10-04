package com.example.bdd.product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product p);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    void deleteById(Long id);
    void deleteAll();
}