package com.publishing.house.bookcatalog.controller;

import java.util.Collection;
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

import com.publishing.house.bookcatalog.model.Book;
import com.publishing.house.bookcatalog.model.Review;
import com.publishing.house.bookcatalog.services.BookService;
import com.publishing.house.bookcatalog.services.ReviewService;

@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final BookService bookService;

    @Autowired
    public ReviewController(final ReviewService reviewService, final BookService bookService) {
        this.reviewService = reviewService;
        this.bookService = bookService;
    }

    @GET
    public Response getAllReviews() {
        final Collection<Review> reviews = reviewService.getAllReviews();
        return Response.ok(reviews).build();
    }

    @GET
    @Path("/byBookId/{bookId}")
    public Response getReviewsByBookId(@PathParam("bookId") Long bookId) {
        Set<Review> reviewsByBookId = reviewService.getReviewsByBookId(bookId);
        return Response.ok(reviewsByBookId).build();
    }

    @GET
    @Path("/{reviewId}")
    public Response getReviewsById(@PathParam("reviewId") Long reviewId) {
        Review review = reviewService.getReviewsById(reviewId);
        return Response.ok(review).build();
    }

    @POST
    public Response saveReview(Review review) {
        review = reviewService.saveReview(review);
        return Response.ok(review).build();
    }

    @PUT
    public Response updateReview(Review review) {
        review = reviewService.updateReview(review);
        return Response.ok(review).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteReview(@PathParam("id") Long id) {
        Review review = reviewService.deleteReviewById(id);
        return Response.ok(review).build();
    }
}