package com.publishing.house.bookcatalog.services.impl;


import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publishing.house.bookcatalog.model.Author;
import com.publishing.house.bookcatalog.model.Book;
import com.publishing.house.bookcatalog.DTO.BookDTO;
import com.publishing.house.bookcatalog.repositories.BookRepository;
import com.publishing.house.bookcatalog.services.BookService;
import com.publishing.house.bookcatalog.utils.NumberUtils;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.saveBook(book);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.updateBook(book);
    }

    @Override
    public Book deleteBookByIsbn(Long isbn) {
        return bookRepository.deleteBookByIsbn(isbn);
    }

    @Override
    public Set<Book> getAllBookByAuthor(Author author) {
        return bookRepository.getAllBooksByAuthor(author);
    }

    @Override
    public List<BookDTO> getBookWithRating() {
        return bookRepository.getBooksWithRating();
    }
}
