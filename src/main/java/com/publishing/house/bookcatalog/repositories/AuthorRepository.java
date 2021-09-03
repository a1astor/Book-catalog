package com.publishing.house.bookcatalog.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.publishing.house.bookcatalog.model.Author;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Repository
@Slf4j
public class AuthorRepository {

    private final EntityManager entityManager;

    @Autowired
    public AuthorRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Author> getAllAuthors() {
        return entityManager.createQuery("from Author").getResultList();
    }

    public Author saveAuthor(Author author) {
        entityManager.persist(author);
        return author;
    }

    public Author updateAuthor(Author author) {
        entityManager.merge(author);
        return author;
    }

    public Author deleteAuthorById(Long id) {
        final Author author = entityManager.find(Author.class, id);
        entityManager.remove(author);
        return author;
    }

    public Author getAuthorById(Long id) {
        return entityManager.find(Author.class, id);
    }
}
