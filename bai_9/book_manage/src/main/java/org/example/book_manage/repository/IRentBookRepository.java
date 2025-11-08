package org.example.book_manage.repository;

import org.example.book_manage.entity.RentBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRentBookRepository extends JpaRepository<RentBook, Integer> {
    RentBook findByName(String id);
}
