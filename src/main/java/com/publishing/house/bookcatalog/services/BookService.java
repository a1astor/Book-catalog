package com.publishing.house.bookcatalog.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.publishing.house.bookcatalog.DTO.BookAndReviewDTO;
import com.publishing.house.bookcatalog.model.Author;
import com.publishing.house.bookcatalog.model.Book;
import com.publishing.house.bookcatalog.DTO.BookDTO;

public interface BookService {
    List<Book> getAllBooks();

    Book saveBook(Book book);

    Book updateBook(Book book);

    Book deleteBookByIsbn(Long isbn);

    Set<Book> getAllBookByAuthor(Author author);

    List<BookDTO> getBookWithRating();

    Book getBook(Long id);

    double getAverageRatingByBookId(Long id);

    int bulkDeleteBooksById(List<Long> listId);

    Set<Book> getBookByAuthorId(Long authorId);

    List<Book> getBooksByRating(int ratings);

    Book addReview(BookAndReviewDTO bindingData);
}
