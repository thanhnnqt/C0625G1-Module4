package org.example.bai_tap_1.repository;


import org.example.bai_tap_1.entity.AppUser;
import org.example.bai_tap_1.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleResposiotry extends JpaRepository<UserRole,Long> {
    List<UserRole> findByAppUser(AppUser appUser);
}
