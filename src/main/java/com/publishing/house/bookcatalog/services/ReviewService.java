package com.publishing.house.bookcatalog.services;

import java.util.List;

import com.publishing.house.bookcatalog.model.Review;

public interface ReviewService {
    List<Review> getAllReviews();

    Review saveReview(Review review);

    Review updateReview(Review review);

    Review deleteReviewById(Long id);
}
