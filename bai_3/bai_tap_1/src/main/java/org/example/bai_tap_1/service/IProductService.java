package org.example.bai_tap_1.service;

import org.example.bai_tap_1.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    void add(Product product);

    void update(Product product);

    void delete(int id);

    Product findById(int id);

    List<Product> search(String keyword);
}
