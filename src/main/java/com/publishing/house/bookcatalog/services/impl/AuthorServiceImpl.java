package com.publishing.house.bookcatalog.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publishing.house.bookcatalog.model.Author;
import com.publishing.house.bookcatalog.repositories.AuthorRepository;
import com.publishing.house.bookcatalog.services.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(final AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.getAllAuthors();
    }

    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.saveAuthor(author);
    }

    @Override
    public Author updateAuthor(Author author) {
        return authorRepository.updateAuthor(author);
    }

    @Override
    public Author deleteAuthorById(Long id) {
        return authorRepository.deleteAuthorById(id);
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.getAuthorById(id);
    }
}
