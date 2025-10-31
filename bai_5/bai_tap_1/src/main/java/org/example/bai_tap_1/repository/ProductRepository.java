package org.example.bai_tap_1.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.example.bai_tap_1.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository implements IProductRepository{

    private static final List<Product> productList = new ArrayList<>();
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findAll() {
        TypedQuery<Product> typedQuery = entityManager.createQuery("from Product", Product.class);
        return typedQuery.getResultList();
    }

    @Transactional
    @Override
    public boolean add(Product product) {
        try{
            entityManager.persist(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Transactional
    @Override
    public boolean update(Product product) {
        try {
            if (product.getId() == 0) {
                entityManager.persist(product);
            } else {
                entityManager.merge(product);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    @Override
    public void delete(int id) {
        Product product1 = entityManager.find(Product.class, id);
        if (product1 != null) {
            entityManager.remove(product1);
        }
    }

    @Transactional
    @Override
    public Product findById(int id) {
        return entityManager.find(Product.class,id);
    }

    @Transactional
    @Override
    public List<Product> search(String keyword) {
        String sql = "SELECT * FROM product WHERE LOWER(name) LIKE LOWER(:keyword)";
        TypedQuery<Product> query = (TypedQuery<Product>) entityManager
                .createNativeQuery(sql, Product.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();
    }
}
