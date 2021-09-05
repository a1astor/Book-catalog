package com.publishing.house.bookcatalog.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.publishing.house.bookcatalog.model.Author;
import com.publishing.house.bookcatalog.model.Book;
import com.publishing.house.bookcatalog.DTO.BookDTO;
import com.publishing.house.bookcatalog.model.Review;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


@Transactional
@Repository
@Slf4j
public class BookRepository {

    private final EntityManager entityManager;

    @Autowired
    public BookRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Book> getAllBooks() {
        return entityManager.createQuery("from Book as b").getResultList();
    }

    public Book saveBook(final Book book) {
        entityManager.persist(book);
        return book;
    }

    public Book updateBook(final Book book) {
        entityManager.merge(book);
        return book;
    }

    public Book deleteBookByIsbn(final Long isbn) {
        final Book book = entityManager.find(Book.class, isbn);
        entityManager.remove(book);
        return book;
    }

    public Set<Book> getAllBooksByAuthor(final Author author) {
        entityManager.refresh(author);
        return author.getBooks();
    }

    public Book getBookById(final Long id) {
        return entityManager.find(Book.class, id);
    }

    public int bulkRemoveById(List<Long> listId) {
        final Query query = entityManager.createQuery("delete from Book e where e.isbn in (:ids)");
        query.setParameter("ids", listId);
        return query.executeUpdate();
    }

    public Book addReviewToBook(Long isbnId, Review reviewId) {
        Book book = entityManager.find(Book.class, isbnId);
        if (book != null) {
            book.addReview(reviewId);
        }
        return book;
    }
}
