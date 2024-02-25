package com.fixedcode.securerestapiilibrary.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<Book> getAllBooks();
    Optional<Book> findById(Long bookId);
    Book add(Book book);
    Book update(Book book);
    void delete(Long bookId);
}
