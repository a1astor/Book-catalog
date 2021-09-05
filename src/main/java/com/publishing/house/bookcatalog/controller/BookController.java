package com.publishing.house.bookcatalog.controller;

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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.publishing.house.bookcatalog.DTO.BookAndReviewDTO;
import com.publishing.house.bookcatalog.model.Author;
import com.publishing.house.bookcatalog.model.Book;
import com.publishing.house.bookcatalog.DTO.BookDTO;
import com.publishing.house.bookcatalog.services.AuthorService;
import com.publishing.house.bookcatalog.services.BookService;

@Transactional
@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/book")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BookController(final BookService bookService, final AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GET
    public Response getAllBooks() {
        List<Book> bookEntities = bookService.getAllBooks();
        return Response.ok(bookEntities).build();
    }

    @POST
    public Response saveBook(Book book) {
        book = bookService.saveBook(book);
        return Response.ok(book).build();
    }

    @PUT
    public Response updateBook(Book book) {
        book = bookService.updateBook(book);
        return Response.ok(book).build();
    }

    @DELETE
    @Path("/{isbn}")
    public Response deleteBook(@PathParam("isbn") Long isbn) {
        Book book = bookService.deleteBookByIsbn(isbn);
        return Response.ok(book).build();
    }

    @GET
    @Path("/byAuthor/{id}")
    public Response getAllBooksByAuthor(Long id) {
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

    @GET
    @Path("/{authorId}")
    public Response getBookByAuthorId(@PathParam("authorId") Long authorId) {
        Set<Book> booksByAuthor = bookService.getBookByAuthorId(authorId);
        return Response.ok(booksByAuthor).build();
    }

    @GET
    @Path("/byRating/{rating}")
    public Response getBookByrating(@PathParam("rating") Integer rating) {
        List<Book> booksByRating = bookService.getBooksByRating(rating);
        return Response.ok(booksByRating).build();
    }

    @DELETE
    @Path("/bulk/{listId}")
    public Response bulkDelete(List<Long> listId) {
        int deletedCount = bookService.bulkDeleteBooksById(listId);
        return Response.ok(deletedCount).build();
    }

    @PUT
    @Path("/bind")
    public Response bindBookAndReview(BookAndReviewDTO bindingData) {
        Book book = bookService.addReview(bindingData);
        return Response.ok(book).build();
    }
}