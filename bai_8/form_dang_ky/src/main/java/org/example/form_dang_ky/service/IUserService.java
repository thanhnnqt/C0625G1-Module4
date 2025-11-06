package org.example.form_dang_ky.service;

import org.example.form_dang_ky.dto.UserDto;
import org.example.form_dang_ky.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    Page<User> findAllByNameContaining(String keyword, Pageable pageable);
    void save(User user);
    User findById(Integer id);
    void addUser(User user);
}
