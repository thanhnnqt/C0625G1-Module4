package org.example.bai_tap_1.service;

import org.example.bai_tap_1.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryService {
    void save(Category category);
    Category findById(Integer id);
    Page<Category> findAllByNameContaining(String keyword, Pageable pageable);
}
