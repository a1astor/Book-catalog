package com.publishing.house.bookcatalog.services;

import java.util.ArrayList;
import java.util.List;

import com.publishing.house.bookcatalog.model.Author;

public interface AuthorService {

    List<Author> getAllAuthors();

    Author saveAuthor(Author author);

    Author updateAuthor(Author author);

    Author deleteAuthorById(String id);

    Author getAuthorById(String id);
}
