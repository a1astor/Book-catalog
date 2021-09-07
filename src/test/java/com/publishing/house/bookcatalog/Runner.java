package com.publishing.house.bookcatalog;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
//        features = "src/test/java/com/publishing/house/bookcatalog/feature"
        features = "src/test/resources/feature"
        , glue = {"com/publishing/house/bookcatalog/steps", "com/publishing/house/bookcatalog/config"}
        , monochrome = true
        , plugin = {"pretty", "html:target/cucumber"}
)
@SpringBootTest
@TestPropertySource("/application-test.yaml")
public class Runner {

}