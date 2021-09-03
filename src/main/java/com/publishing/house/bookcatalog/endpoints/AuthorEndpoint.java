package com.publishing.house.bookcatalog.endpoints;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.publishing.house.bookcatalog.model.Author;
import com.publishing.house.bookcatalog.services.AuthorService;


@Component
@Path("/author")
public class AuthorEndpoint {

    private AuthorService authorService;

    public AuthorEndpoint(@Autowired AuthorService authorService) {
        this.authorService = authorService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        GenericEntity<List<Author>> list = new GenericEntity<>(authors) {
        };
        return Response.ok(list).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveAuthor(Author author) {
        author = authorService.saveAuthor(author);
        return Response.ok(author).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAuthor(Author author) {
        author = authorService.updateAuthor(author);
        return Response.ok(author).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBook(@PathParam("id") String id) {
        Author author = authorService.deleteAuthorById(id);
        return Response.ok(author).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookById(@PathParam("id") String id) {
        Author author = authorService.getAuthorById(id);
        return Response.ok(author).build();
    }
}