package org.example.form_dang_ky.repository;

import org.example.form_dang_ky.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
    Page<User> findAllByNameContaining(String keyword, Pageable pageable);
}
