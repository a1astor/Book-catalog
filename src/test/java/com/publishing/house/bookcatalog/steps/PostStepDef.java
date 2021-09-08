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

public class PostStepDef {

    private final BookHttpClient bookHttpClient;
    private Book book;
    private Long bookId;

    @Autowired
    public PostStepDef(final BookHttpClient bookHttpClient) {
        this.bookHttpClient = bookHttpClient;
    }

    @Given("Create new book entity with name {string}")
    public void createNewBookEntityWithName(String bookName) {
        book = new Book(null, bookName, 1995, new Date(), "Golum", new Date(), null, null);
    }

    @And("save book in db")
    public void saveBookInDb() {
        final ResponseEntity<Book> responseEntity = bookHttpClient.saveEntity(book);
        Assert.assertNotNull(responseEntity.getBody());
        bookId = responseEntity.getBody().getIsbn();
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Then("check if book in base and name is {string}")
    public void checkIfBookInBaseAndNameIs(String bookName) {
        ResponseEntity<Book> book = bookHttpClient.getBook(String.valueOf(bookId));
        Assert.assertEquals(HttpStatus.OK, book.getStatusCode());
        Assert.assertNotNull(book.getBody());
        Assert.assertEquals(bookName, book.getBody().getName());
    }
}
