package com.publishing.house.bookcatalog.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.publishing.house.bookcatalog.model.Author;
import com.publishing.house.bookcatalog.model.Book;
import com.publishing.house.bookcatalog.model.BookDTO;
import com.publishing.house.bookcatalog.model.Review;

import lombok.extern.slf4j.Slf4j;


@Repository
@Slf4j
public class BookRepository {

    private final EntityManager entityManager;

    @Autowired
    public BookRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public List<Book> getAllBooks() {
        return entityManager.createQuery("from Book").getResultList();
    }

    @Transactional
    public Book saveBook(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Transactional
    public Book updateBook(Book book) {
        entityManager.merge(book);
        return book;
    }

    @Transactional
    public Book deleteBookByIsbn(Long isbn) {
        final Book book = entityManager.find(Book.class, isbn);
        entityManager.remove(book);
        return book;
    }

    public Set<Book> getAllBooksByAuthor(Author author) {
        entityManager.refresh(author);
        return author.getBooks();
    }

    @Transactional
    public List<BookDTO> getBooksWithRating() {
        final List<Book> allBooks = getAllBooks();
        final List<BookDTO> resultList = new ArrayList<>();
        for (final Book book : allBooks) {
            final Set<Review> reviews = book.getReviews();
            final List<Integer> rating = reviews.stream().mapToInt(Review::getRating).boxed().collect(Collectors.toList());
            resultList.add(new BookDTO(book, rating));
        }
        return resultList;
    }

}
