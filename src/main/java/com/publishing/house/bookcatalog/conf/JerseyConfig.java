package com.publishing.house.bookcatalog.conf;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.publishing.house.bookcatalog.controller.AuthorController;
import com.publishing.house.bookcatalog.controller.BookController;
import com.publishing.house.bookcatalog.controller.ReviewController;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(BookController.class);
        register(AuthorController.class);
        register(ReviewController.class);
    }
}
