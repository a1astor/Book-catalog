package com.publishing.house.bookcatalog.services.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publishing.house.bookcatalog.model.Author;
import com.publishing.house.bookcatalog.model.Book;
import com.publishing.house.bookcatalog.DTO.BookDTO;
import com.publishing.house.bookcatalog.model.Review;
import com.publishing.house.bookcatalog.repositories.AuthorRepository;
import com.publishing.house.bookcatalog.repositories.BookRepository;
import com.publishing.house.bookcatalog.repositories.ReviewRepository;
import com.publishing.house.bookcatalog.services.AuthorService;
import com.publishing.house.bookcatalog.services.BookService;
import com.publishing.house.bookcatalog.services.ReviewService;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public BookServiceImpl(final BookRepository bookRepository, final AuthorRepository authorRepository, final ReviewRepository reviewRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public Book saveBook(final Book book) {
        return bookRepository.saveBook(book);
    }

    @Override
    public Book updateBook(final Book book) {
        return bookRepository.updateBook(book);
    }

    @Override
    public Book deleteBookByIsbn(final Long isbn) {
        return bookRepository.deleteBookByIsbn(isbn);
    }

    @Override
    public Set<Book> getAllBookByAuthor(final Author author) {
        return bookRepository.getAllBooksByAuthor(author);
    }

    @Transactional
    @Override
    public List<BookDTO> getBookWithRating() {
        final List<Book> allBooks = bookRepository.getAllBooks();
        final List<BookDTO> resultList = new ArrayList<>();
        for (final Book book : allBooks) {
            final Set<Review> reviews = book.getReviews();
            final List<Integer> rating = reviews.stream().mapToInt(Review::getRating).boxed().collect(Collectors.toList());
            resultList.add(new BookDTO(book, rating));
        }
        return resultList;
    }

    @Override
    public Book getBook(final Long id) {
        return bookRepository.getBookById(id);
    }

    @Transactional
    @Override
    public double getAverageRatingByBookId(final Long id) {
        Book book = bookRepository.getBookById(id);
        return book.getReviews().stream().mapToDouble(Review::getRating).average().orElse(0);
    }

    @Override
    public int bulkDeleteBooksById(final List<Long> listId) {
        return bookRepository.bulkRemoveById(listId);
    }

    @Transactional
    @Override
    public Set<Book> getBookByAuthorId(final Long authorId) {
        return authorRepository.getAuthorById(authorId).getBooks();
    }

    @Transactional
    @Override
    public List<Book> getBooksByRating(final int ratings) {
        final List<Book> books = new LinkedList<>();
        final Collection<Review> reviews = reviewRepository.getReviewsByRating(ratings);
        for (final Review review : reviews) {
            final Book book = review.getBook();
            if (!books.contains(book)) {
                books.add(book);
            }
        }
        return books;
    }
}
