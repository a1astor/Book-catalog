package com.publishing.house.bookcatalog.services;

import java.util.Collection;
import java.util.Set;

import com.publishing.house.bookcatalog.model.Review;

public interface ReviewService {
    Collection<Review> getAllReviews();

    Review saveReview(Review review);

    Review updateReview(Review review);

    Review deleteReviewById(Long id);

    Collection<Review> getReviewsByRating(int ratings);

    Set<Review> getReviewsByBookId(Long bookId);

    Review getReviewsById(Long reviewId);
}
