package org.example.book_manage.Exception;

public class InvalidCodeException extends RuntimeException{
    public InvalidCodeException(String message) {
        super(message);
    }
}
