package com.publishing.house.bookcatalog;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.publishing.house.bookcatalog.model.Book;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class BookHttpClient {

    private final String SERVER_URL = "http://localhost";
    private final String GET_ALL_BOOK = "/book";
    private final String GET_BY_ID = "/{isbn}";

    @LocalServerPort
    private int port;
    private final TestRestTemplate restTemplate = new TestRestTemplate();

    private String bookEndpoint(String endpoint) {
        return SERVER_URL + ":" + port + endpoint;
    }

    public int put(final String something) {
        return restTemplate.postForEntity(bookEndpoint(GET_ALL_BOOK), something, Void.class).getStatusCodeValue();
    }

    public Book[] getAllContents() {
        return restTemplate.getRestTemplate().getForObject(bookEndpoint(GET_ALL_BOOK), Book[].class);
    }

    public ResponseEntity<Book> getBook(String id) {
        final Map<String, String> params = new HashMap<>();
        params.put("isbn", id);
        return restTemplate.getRestTemplate().getForEntity(bookEndpoint(GET_ALL_BOOK + GET_BY_ID), Book.class, params);
    }

    public void clean() {
        restTemplate.delete(bookEndpoint(GET_ALL_BOOK));
    }

    public ResponseEntity<Book> saveEntity(final Book book) {
        return restTemplate.postForEntity(bookEndpoint(GET_ALL_BOOK), book, Book.class);
    }

    public ResponseEntity<Void> removeBook(String bookId) {
        return restTemplate.exchange(bookEndpoint(GET_ALL_BOOK + GET_BY_ID), HttpMethod.DELETE, null, Void.class, Long.valueOf(bookId));
    }

    public ResponseEntity<Void> removeBook(Long bookId) {
        return restTemplate.exchange(bookEndpoint(GET_ALL_BOOK + GET_BY_ID), HttpMethod.DELETE, null, Void.class, bookId);
    }

    public ResponseEntity<Void> updateBook(Book book) {
        HttpEntity httpEntity = new HttpEntity<>(book);
        return restTemplate.exchange(bookEndpoint(GET_ALL_BOOK), HttpMethod.PUT, httpEntity, Void.class);
    }
}