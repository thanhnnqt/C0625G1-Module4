package org.example.bai_tap_1.repository;


import org.example.bai_tap_1.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUserName(String username);
}
