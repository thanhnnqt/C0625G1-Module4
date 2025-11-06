package org.example.form_dang_ky.service;

import org.example.form_dang_ky.dto.UserDto;
import org.example.form_dang_ky.entity.User;
import org.example.form_dang_ky.repository.IUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public Page<User> findAllByNameContaining(String keyword, Pageable pageable) {
        return userRepository.findAllByNameContaining(keyword, pageable);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }
}
