package org.example.product_manage.service;

import org.example.product_manage.entity.Product;
import org.example.product_manage.repository.IProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService {
    private final IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> findAllByNameContaining(String keyword, Pageable pageable) {
        return productRepository.findAllByNameContaining(keyword, pageable);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }
}
