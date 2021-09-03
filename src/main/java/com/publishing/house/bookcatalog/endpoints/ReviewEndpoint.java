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

import com.publishing.house.bookcatalog.model.Review;
import com.publishing.house.bookcatalog.services.ReviewService;

@Component
@Path("/review")
public class ReviewEndpoint {

    private ReviewService reviewService;

    @Autowired
    public ReviewEndpoint(final ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        GenericEntity<List<Review>> list = new GenericEntity<>(reviews) {
        };
        return Response.ok(list).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveReview(Review review) {
        review = reviewService.saveReview(review);
        return Response.ok(review).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateReview(Review review) {
        review = reviewService.updateReview(review);
        return Response.ok(review).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteReview(@PathParam("id") String id) {
        Review review = reviewService.deleteReviewById(id);
        return Response.ok(review).build();
    }
}