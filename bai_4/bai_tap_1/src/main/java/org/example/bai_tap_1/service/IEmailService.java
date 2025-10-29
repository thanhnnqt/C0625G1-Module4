package org.example.bai_tap_1.service;

import org.example.bai_tap_1.entity.Email;

public interface IEmailService {
    Email findAll();
    void update(Email email);
}
