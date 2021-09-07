package com.publishing.house.bookcatalog.steps;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;

public class GetPosSteps {

    @io.cucumber.java.en.Given("Perfome GET operation for {string}")
    public void perfomeGETOperationFor(String string) {
//        given().contentType(ContentType.JSON);

    }

    @And("perfome GET for book id {string}")
    public void perfomeGETForBookId(String bookId) {
        RestTemplate restTemplate = new RestTemplate();
        //TODO make link for yaml
        when().get(String.format("http://localhost:8081/book/%s", bookId))
                .then().body("name", is("a"));
    }

    @Then("should return book with name {string}")
    public void shouldReturnBookWithName(String arg0) {

    }
}
