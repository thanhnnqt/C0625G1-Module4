package org.example.book_manage.service;

import org.example.book_manage.Exception.InvalidCodeException;
import org.example.book_manage.Exception.NoBookAvailableException;
import org.example.book_manage.entity.Book;
import org.example.book_manage.repository.IBookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BookService implements IBookService{
    private final IBookRepository bookRepository;
    private final ConcurrentHashMap<String, Integer> rentBooks = new ConcurrentHashMap<>();
    public BookService(IBookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public Page<Book> listBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book getBookById(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy sách"));
    }

    @Override
    public String rentBook(Integer bookId) {
        Book book = getBookById(bookId);
        if (book.getQuantity() <= 0) {
            throw new NoBookAvailableException("Sách đã hết");
        }
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        String code = generateRandomCode();
        rentBooks.put(code, bookId);
        return code;
    }

    @Override
    public void returnBook(String code) {
        Integer bookId = rentBooks.remove(code);
        if (bookId == null) {
            throw new InvalidCodeException("Mã code không hợp lệ");
        }
        Book book = getBookById(bookId);
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
    }

    private String generateRandomCode() {
        Random random = new Random();
        return String.format("%05d", random.nextInt(100000));
    }

    @Override
    public Book getBookByRentCode(String code) {
        Integer bookId = rentBooks.get(code);
        if (bookId == null) {
            throw new InvalidCodeException("Mã không hợp lệ hoặc sách chưa được mượn");
        }
        return getBookById(bookId);
    }
}
