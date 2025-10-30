package org.example.bai_tap_1.repository;

import org.example.bai_tap_1.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository implements IProductRepository{

    private static final List<Product> productList = new ArrayList<>();
    static {
        productList.add(new Product(1, "Samsung S22 Ultra", 22000000, "SmartPhone", "Korea"));
        productList.add(new Product(2, "Iphone 15", 25000000, "SmartPhone", "USA"));
        productList.add(new Product(3, "MSI", 42000000, "Laptop", "USA"));
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public void add(Product product) {
        productList.add(product);
    }

    @Override
    public void update(Product product) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == product.getId()) {
                productList.set(i, product);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        productList.removeIf(p -> p.getId() == id);
    }

    @Override
    public Product findById(int id) {
        for (Product p : productList) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Product> search(String keyword) {
        List<Product> result = new ArrayList<>();
        for (Product p : productList) {
            if (p.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(p);
            }
        }
        return result;
    }
}
