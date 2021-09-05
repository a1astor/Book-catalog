package com.publishing.house.bookcatalog.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
    public AuthorRepository(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Author> getAllAuthors() {
        return entityManager.createQuery("from Author").getResultList();
    }

    public Author saveAuthor(final Author author) {
        entityManager.persist(author);
        return author;
    }

    public Author updateAuthor(final Author author) {
        entityManager.merge(author);
        return author;
    }

    public Author deleteAuthorById(final Long id) {
        final Author author = entityManager.find(Author.class, id);
        entityManager.remove(author);
        return author;
    }

    public Author getAuthorById(final Long id) {
        return entityManager.find(Author.class, id);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public int bulkRemoveById(List<Long> listId) {
        final Query query = entityManager.createQuery("delete from Author e where e.authorId in (:ids)");
        query.setParameter("ids", listId);
        return query.executeUpdate();
    }
}
