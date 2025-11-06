package org.example.bai_tap_1.repository;

import org.example.bai_tap_1.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {
    Page<Category> findAllByNameContaining(String keyword, Pageable pageable);
}
