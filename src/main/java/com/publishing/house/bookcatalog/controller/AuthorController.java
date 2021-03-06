package com.publishing.house.bookcatalog.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.publishing.house.bookcatalog.DTO.AuthorDTO;
import com.publishing.house.bookcatalog.model.Author;
import com.publishing.house.bookcatalog.services.AuthorService;


@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/author")
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(@Autowired final AuthorService authorService) {
        this.authorService = authorService;
    }

    @GET
    public Response getAllAuthors() {
        final List<Author> authors = authorService.getAllAuthors();
        return Response.ok(authors).build();
    }

    @POST
    public Response saveAuthor(Author author) {
        author = authorService.saveAuthor(author);
        return Response.ok(author).build();
    }

    @PUT
    public Response updateAuthor(Author author) {
        author = authorService.updateAuthor(author);
        return Response.ok(author).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") final Long id) {
        Author author = authorService.deleteAuthorById(id);
        return Response.ok(author).build();
    }

    @DELETE
    @Path("/withOutBooks/{id}")
    public Response deleteAuthorWithOutbooks(@PathParam("id") Long id) {
        Author author = authorService.deleteAuthorWithOutBooksById(id);
        return Response.ok(author).build();
    }

    @DELETE
    @Path("/bulk/{listId}")
    public Response bulkDelete(@PathParam("listId") final List<Long> listId) {
        int deletedCount = authorService.bulkDeleteAuthorById(listId);
        return Response.ok(deletedCount).build();
    }

    @GET
    @Path("/{id}")
    public Response getAuthorById(@PathParam("id") Long id) {
        Author author = authorService.getAuthorById(id);
        return Response.ok(author).build();
    }

    @GET
    @Path("/withAverageRating")
    public Response getAuthorsWithAverageRating() {
        List<AuthorDTO> authorsWithAverageRating = authorService.getAuthorsWithAverageRating();
        return Response.ok(authorsWithAverageRating).build();
    }
}