package org.example.product_manage.service;

import org.example.product_manage.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IProductService {
    Page<Product> findAllByNameContaining(String keyword, Pageable pageable);
    Iterable<Product> findAll();
    void save(Product song);
    Optional<Product> findById(Integer id);
    void addProduct(Product user);
    void deleteById(Integer id);
}
