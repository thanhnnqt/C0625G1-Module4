package org.example.book_manage.service;

import org.example.book_manage.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookService {
    Page<Book> listBooks(Pageable pageable);
    Book getBookById(Integer id);
    String rentBook(Integer bookId);
    void returnBook(String code);
    Book getBookByRentCode(String code);
}
