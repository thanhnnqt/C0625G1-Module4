package org.example.bai_tap_1.service;

import org.example.bai_tap_1.entity.Blog;

import java.util.List;

public interface IBlogService {
    List<Blog> findAll();
    boolean add(Blog blog);
    boolean update(Blog blog);
    void delete(int id);
    List<Blog> search(String keyword);
    Blog findById(int id);
}
