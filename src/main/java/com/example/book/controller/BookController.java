package com.example.book.controller;

import com.example.book.exceptions.BookNotFoundException;
import com.example.book.model.Book;
import com.example.book.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book newBook = bookService.create(book);// variables that are entered into postman
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable UUID id) {
        Book book;
        try {
            book = bookService.findBookById(id);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }


    @GetMapping("/title/{title}")
    public ResponseEntity<List<Book>> getBookByTitle(@PathVariable String title) {
        List<Book> books = bookService.findBookByTitle(title);
        return new ResponseEntity<>(books, HttpStatus.OK);


    }


    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable UUID id) {
        Book newBook;
        try {
            newBook = bookService.updateBook(book, id);

        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newBook,HttpStatus.OK);


    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> patchBookById(@RequestBody Book book, @PathVariable UUID id){
       Book newBook;
       try {
           newBook = bookService.patchBookById(book, id);

       } catch (BookNotFoundException e){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
        return new ResponseEntity<>(newBook,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable UUID id){
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

