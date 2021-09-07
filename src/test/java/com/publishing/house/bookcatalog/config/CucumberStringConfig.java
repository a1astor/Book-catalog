package com.publishing.house.bookcatalog.config;

import org.springframework.boot.test.context.SpringBootTest;

import com.publishing.house.bookcatalog.BookCatalogApplication;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = BookCatalogApplication.class)
public class CucumberStringConfig {
}
