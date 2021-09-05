package com.publishing.house.bookcatalog.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.publishing.house.bookcatalog.DTO.AuthorDTO;
import com.publishing.house.bookcatalog.model.Author;
import com.publishing.house.bookcatalog.model.Book;
import com.publishing.house.bookcatalog.model.Review;
import com.publishing.house.bookcatalog.repositories.AuthorRepository;
import com.publishing.house.bookcatalog.repositories.BookRepository;
import com.publishing.house.bookcatalog.services.AuthorService;
import com.publishing.house.bookcatalog.services.BookService;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.getAllAuthors();
    }

    @Override
    public Author saveAuthor(final Author author) {
        return authorRepository.saveAuthor(author);
    }

    @Override
    public Author updateAuthor(final Author author) {
        return authorRepository.updateAuthor(author);
    }

    @Override
    public Author deleteAuthorById(final Long id) {
        return authorRepository.deleteAuthorById(id);
    }

    @Override
    public Author getAuthorById(final Long id) {
        return authorRepository.getAuthorById(id);
    }

    @Transactional
    @Override
    public List<AuthorDTO> getAuthorsWithAverageRating() {
        final List<AuthorDTO> result = new ArrayList<>();
        final List<Author> allAuthors = authorRepository.getAllAuthors();
        for (final Author author : allAuthors) {
            final OptionalDouble average = author.getBooks()
                    .stream()
                    .mapToDouble(book -> book.getReviews().stream().mapToDouble(Review::getRating).average().orElse(0))
                    .average();
            result.add(new AuthorDTO(author, average.orElse(0.0)));
        }
        return result;
    }

    @Transactional
    @Override
    @Nullable
    public Author deleteAuthorWithOutBooksById(final Long id) {
        Author author = authorRepository.getAuthorById(id);
        if (author.getBooks().isEmpty()) {
            authorRepository.deleteAuthorById(id);
        }
        return null;
    }

    @Override
    public int bulkDeleteAuthorById(List<Long> ids) {
        return authorRepository.bulkRemoveById(ids);
    }
}
