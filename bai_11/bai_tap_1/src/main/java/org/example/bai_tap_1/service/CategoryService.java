package org.example.bai_tap_1.service;

import org.example.bai_tap_1.entity.Category;
import org.example.bai_tap_1.repository.ICategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService{

    private final ICategoryRepository categoryRepository;
    public CategoryService(ICategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Category> findAllByNameContaining(String keyword, Pageable pageable) {
        return categoryRepository.findAllByNameContaining(keyword, pageable);
    }
}
