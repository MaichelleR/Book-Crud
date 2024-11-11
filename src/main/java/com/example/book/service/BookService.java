package com.example.book.service;

import com.example.book.exceptions.BookNotFoundException;
import com.example.book.model.Book;
import com.example.book.repository.BookRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {// constructor
        this.bookRepository = bookRepository;
    }



    public Book create(Book book) {// Book book is coming from the controller
        return bookRepository.save(book); // save the item into the datebase
    }

    public List<Book> getAllBooks() {// searches all objects in the database
        // call the instance of the repository made at the top of the service
        return bookRepository.findAll(); //find all objects in a table

    }

    public Book findBookById(UUID id) {// this id comes from the controller
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isEmpty()){
            throw new BookNotFoundException("A book with id:"+ id + "was not found");
        }
        return optionalBook.get();
    }


    public List<Book> findBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Book updateBook(Book book, UUID id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isEmpty()){
            throw new BookNotFoundException("A book with id:" + id + "was not found");
        }
        book.setId(id);
        return bookRepository.save(book);

    }

    public Book patchBookById(Book book, UUID id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isEmpty()){
            throw new BookNotFoundException("A book with id:" + id + "was not found");
        }
        Book updatedBook = optionalBook.get(); // .get() is the method that you use to retrieve an object out of an optional
        if(book.getTitle() != null){
            updatedBook.setTitle(book.getTitle());
        }
        if (book.getAuthor() != null) {
            updatedBook.setAuthor(book.getAuthor());
        }
        if (book.getGenre() != null){
            updatedBook.setGenre(book.getGenre());
        }
        if(book.getNumberOfPages() != null){
            updatedBook.setNumberOfPages(book.getNumberOfPages());
        }
        if(book.getRating() != null){
            updatedBook.setRating(book.getRating());
        }
        if(book.getHasRead() != null){
            updatedBook.setHasRead(book.getHasRead());
        }
        return bookRepository.save(updatedBook);
    }


    public void deleteBookById(UUID id) {// not returning a new object just removing it from the database
        bookRepository.deleteById(id);

    }
}
