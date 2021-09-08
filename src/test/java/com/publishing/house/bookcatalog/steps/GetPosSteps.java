package com.publishing.house.bookcatalog.steps;


import java.util.Date;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.publishing.house.bookcatalog.BookHttpClient;
import com.publishing.house.bookcatalog.model.Book;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class GetPosSteps {

    private final BookHttpClient bookHttpClient;
    private ResponseEntity<Book> book;
    private Long newBookId;

    @Autowired
    public GetPosSteps(final BookHttpClient bookHttpClient) {
        this.bookHttpClient = bookHttpClient;
    }

    @Given("check if there is book with isbn = {string} and name = {string}")
    public void checkIfThereIsBookWithIsbnAndName(final String isbn, final String bookName) {
        final ResponseEntity<Book> bookEntity = bookHttpClient.getBook(isbn);
        if (bookEntity.getBody() == null) {
            final ResponseEntity<Book> saveResponseEntity = bookHttpClient.saveEntity(new Book(null, bookName, 2020, new Date(), "getter", new Date(), null, null));
            Assert.assertEquals(HttpStatus.OK, saveResponseEntity.getStatusCode());
            newBookId = saveResponseEntity.getBody().getIsbn();
        }
    }

    @And("perfome GET for book id {string}")
    public void perfomeGETForBookId(final String bookId) {
        book = bookHttpClient.getBook(newBookId != null ? String.valueOf(newBookId) : bookId);
        Assert.assertEquals(HttpStatus.OK, book.getStatusCode());
        Assert.assertNotNull(book.getBody());
    }

    @Then("should return book with name {string}")
    public void shouldReturnBookWithName(final String bookName) {
        Assert.assertEquals(bookName, book.getBody().getName());
    }
}
