package com.publishing.house.bookcatalog.services;

import java.util.List;
import java.util.Set;

import com.publishing.house.bookcatalog.model.Author;
import com.publishing.house.bookcatalog.model.Book;
import com.publishing.house.bookcatalog.model.BookDTO;

public interface BookService {
    List<Book> getAllBooks();

    Book saveBook(Book book);

    Book updateBook(Book book);

    Book deleteBookByIsbn(String isbn);

    Set<Book> getAllBookByAuthor(Author author);

    List<BookDTO> getBookWithRating();
}
