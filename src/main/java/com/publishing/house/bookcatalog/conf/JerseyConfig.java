package com.publishing.house.bookcatalog.conf;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.publishing.house.bookcatalog.endpoints.AuthorEndpoint;
import com.publishing.house.bookcatalog.endpoints.BookEndpoint;
import com.publishing.house.bookcatalog.endpoints.ReviewEndpoint;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(BookEndpoint.class);
        register(AuthorEndpoint.class);
        register(ReviewEndpoint.class);
    }
}
