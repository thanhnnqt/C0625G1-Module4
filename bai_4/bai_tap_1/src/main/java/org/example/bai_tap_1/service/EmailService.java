package org.example.bai_tap_1.service;

import org.example.bai_tap_1.entity.Email;
import org.example.bai_tap_1.repository.EmailRepository;
import org.example.bai_tap_1.repository.IEmailRepository;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService{

    private final IEmailRepository emailRepository = new EmailRepository();

    @Override
    public Email findAll() {
        return emailRepository.findAll();
    }

    @Override
    public void update(Email email) {
        emailRepository.update(email);
    }
}
