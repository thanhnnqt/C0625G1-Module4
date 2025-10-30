package org.example.bai_tap_1.service;

import org.example.bai_tap_1.entity.Email;
import org.example.bai_tap_1.repository.IEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService{
    @Autowired
    private final IEmailRepository emailRepository;

    public EmailService(IEmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @Override
    public Email findAll() {
        return emailRepository.findAll();
    }

    @Override
    public void update(Email email) {
        emailRepository.update(email);
    }
}
