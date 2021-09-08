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


public class PutStepDef {

    private final BookHttpClient bookHttpClient;
    private ResponseEntity<Book> bookEntity;

    @Autowired
    public PutStepDef(final BookHttpClient bookHttpClient) {
        this.bookHttpClient = bookHttpClient;
    }

    @Given("Check if book  is in data base with id {string}")
    public void checkIfBookIsInDataBaseWithId(final String bookId) {
        bookEntity = bookHttpClient.getBook(bookId);
        if (bookEntity.getBody() == null) {
            final ResponseEntity<Book> saveResponseEntity = bookHttpClient.saveEntity(new Book(null, "book name", 2020, new Date(), "getter", new Date(), null, null));
            Assert.assertEquals(HttpStatus.OK, saveResponseEntity.getStatusCode());
            Assert.assertNotNull(saveResponseEntity.getBody());
            bookEntity = saveResponseEntity;
        }
        Assert.assertNotNull(bookEntity);
    }

    @And("update book name to {string}")
    public void updateBookNameTo(final String bookName) {
        bookEntity.getBody().setName(bookName);
        final ResponseEntity<Void> voidResponseEntity = bookHttpClient.updateBook(bookEntity.getBody());
        Assert.assertEquals(HttpStatus.OK, voidResponseEntity.getStatusCode());
    }

    @Then("check if book in  data base have name {string}")
    public void checkIfBookInDataBaseWithIdHaveName(final String bookName) {
        final ResponseEntity<Book> entity = bookHttpClient.getBook(String.valueOf(bookEntity.getBody().getIsbn()));
        Assert.assertNotNull(entity);
        Assert.assertNotNull(entity.getBody());
        Assert.assertEquals(bookName, entity.getBody().getName());
    }
}
