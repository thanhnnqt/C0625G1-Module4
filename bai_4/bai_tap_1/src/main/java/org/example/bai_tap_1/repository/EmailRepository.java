package org.example.bai_tap_1.repository;

import org.example.bai_tap_1.entity.Email;
import org.springframework.stereotype.Repository;

@Repository
public class EmailRepository implements IEmailRepository{
    private Email email = new Email("VietNamese", 25, true, "Da Nang");

    @Override
    public Email findAll() {
        return email;
    }

    @Override
    public void update(Email email) {
        this.email = email;
    }
}
