package com.fixedcode.securerestapiilibrary.Book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks()
    {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    @GetMapping("/book/{id}")
    public Optional<Book> getById(@PathVariable Long id)
    {
        return bookService.findById(id);
    }
    @PostMapping("/add")
    public ResponseEntity<Book> add(@RequestBody Book book)
    {
        return new ResponseEntity<>(bookService.add(book), CREATED);
    }
    @PostMapping("/update")
    public ResponseEntity<Book> update(@RequestBody Book book)
    {
        return new ResponseEntity<>(bookService.update(book), OK);
    }
    @DeleteMapping("/book/delete/{id}")
    public void delete(@PathVariable Long id)
    {
        bookService.delete(id);
    }
}
