package org.example.bai_tap_1.service;

import org.example.bai_tap_1.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBlogService{
    void save(Blog blog);
    Blog findById(Integer id);
    Page<Blog> findAllByNameContaining(String keyword, Pageable pageable);
}