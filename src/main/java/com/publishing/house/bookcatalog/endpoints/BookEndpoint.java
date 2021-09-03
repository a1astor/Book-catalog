package com.publishing.house.bookcatalog.endpoints;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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
import org.springframework.transaction.annotation.Transactional;

import com.publishing.house.bookcatalog.model.Author;
import com.publishing.house.bookcatalog.model.Book;
import com.publishing.house.bookcatalog.model.BookDTO;
import com.publishing.house.bookcatalog.services.AuthorService;
import com.publishing.house.bookcatalog.services.BookService;

@Transactional
@Component
@Path("/book")
public class BookEndpoint {

    private BookService bookService;
    private AuthorService authorService;

    @Autowired
    public BookEndpoint(final BookService bookService, final AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks() {
        List<Book> bookEntities = bookService.getAllBooks();
        GenericEntity<List<Book>> list = new GenericEntity<>(bookEntities) {
        };
        return Response.ok(list).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveBook(Book book) {
        book = bookService.saveBook(book);
        return Response.ok(book).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book) {
        book = bookService.updateBook(book);
        return Response.ok(book).build();
    }

    @DELETE
    @Path("{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBook(@PathParam("isbn") String isbn) {
        Book book = bookService.deleteBookByIsbn(isbn);
        return Response.ok(book).build();
    }

    @GET
    @Path("/byAuthor{id}")
    public Response getAllBooksByAuthor(String id) {
        Author author = authorService.getAuthorById(id);
        Set<Book> books = bookService.getAllBookByAuthor(author);
        return Response.ok(books).build();
    }

    @GET
    @Path("/withRating")
    public Response getBooksWithRating() {
        List<BookDTO> bookWithRating = bookService.getBookWithRating();
        return Response.ok(bookWithRating).build();
    }
}