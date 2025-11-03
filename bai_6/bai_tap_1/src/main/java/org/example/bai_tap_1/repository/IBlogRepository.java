package org.example.bai_tap_1.repository;

import org.example.bai_tap_1.entity.Blog;

import java.util.List;

public interface IBlogRepository {
    List<Blog> findAll();
    boolean add(Blog blog);
    boolean update(Blog blog);
    void delete(int id);
    List<Blog> search(String keyword);
    Blog findById(int id);
}
