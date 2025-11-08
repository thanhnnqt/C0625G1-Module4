package org.example.book_manage.Exception;

public class NoBookAvailableException extends RuntimeException{
    public NoBookAvailableException(String message) {
        super(message);
    }
}
