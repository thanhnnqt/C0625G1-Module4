package org.example.bai_tap_1.repository;

import org.example.bai_tap_1.entity.Email;

public interface IEmailRepository {
    Email findAll();
    void update(Email email);
}
