package org.example.demo2.service;

import org.example.demo2.entity.Product;
import org.example.demo2.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService implements IProductService{

    public ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findById(int id) {
        return this.productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public void delete(int id) {
        this.productRepository.delete(id);
    }

    @Override
    public List<Product> search(String keyword) {
        return this.productRepository.search(keyword);
    }
}
