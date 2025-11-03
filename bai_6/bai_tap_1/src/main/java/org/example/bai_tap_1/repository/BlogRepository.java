package org.example.bai_tap_1.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.example.bai_tap_1.entity.Blog;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BlogRepository implements IBlogRepository {

    private static final List<Blog> blogList = new ArrayList<>();

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Blog> findAll() {
        TypedQuery<Blog> typedQuery = entityManager.createQuery("from Blog", Blog.class);
        return typedQuery.getResultList();
    }

    @Transactional
    @Override
    public boolean add(Blog blog) {
        try {
            entityManager.persist(blog);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Transactional
    @Override
    public boolean update(Blog blog) {
        try{
            if (blog.getId() == 0){
                entityManager.persist(blog);
            } else {
                entityManager.merge(blog);
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
        Blog blog1 = entityManager.find(Blog.class, id);
        if (blog1 != null) {
            entityManager.remove(blog1);
        }
    }

    @Transactional
    @Override
    public List<Blog> search(String keyword) {
        String sql = "SELECT * FROM blog WHERE LOWER(name) LIKE LOWER(:keyword)";
        TypedQuery<Blog> query = (TypedQuery<Blog>) entityManager
                .createNativeQuery(sql, Blog.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();
    }

    @Transactional
    @Override
    public Blog findById(int id) {
        return entityManager.find(Blog.class, id);
    }
}
