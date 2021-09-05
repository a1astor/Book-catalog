package steps;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;

public class GetPosSteps {
    @io.cucumber.java.en.Given("Perfome GET operation fot {string}")
    public void perfomeGETOperationFot(String url) {
        given().contentType(ContentType.JSON);
    }

    @And("perfome GET for book id {string}")
    public void perfomeGETForBookId(String bookId) {
        //TODO make link for yaml
        when().get(String.format("http://localhost:8082/book/%s", bookId))
                .then().body( "name",is("a"));
    }

    @Then("should return book with name {string}")
    public void shouldReturnBookWithName(String arg0) {

    }
}
