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

public class DeleteStepDef {

    private final BookHttpClient bookHttpClient;
    private Long isbn;

    @Autowired
    public DeleteStepDef(final BookHttpClient bookHttpClient) {
        this.bookHttpClient = bookHttpClient;
    }

    @Given("Add book to data base and stored it isbn")
    public void addBookToDataBaseAndStoredItIsbn() {
        final ResponseEntity<Book> savedBook = bookHttpClient.saveEntity(new Book(null, "this book will be delete", 2020, new Date(), "delete man", new Date(), null, null));
        Assert.assertEquals(HttpStatus.OK, savedBook.getStatusCode());
        Assert.assertNotNull(savedBook.getBody());
        isbn = savedBook.getBody().getIsbn();
    }

    @And("delete book with stored isbn")
    public void deleteBookWithStoredIsbn() {
        final ResponseEntity<Void> voidResponseEntity = bookHttpClient.removeBook(isbn);
        Assert.assertEquals(HttpStatus.OK, voidResponseEntity.getStatusCode());
    }

    @Then("check that book is not in data base")
    public void checkThatBookIsNotInDataBase() {
        final ResponseEntity<Book> book = bookHttpClient.getBook(String.valueOf(isbn));
        Assert.assertNull(book.getBody());
    }
}
