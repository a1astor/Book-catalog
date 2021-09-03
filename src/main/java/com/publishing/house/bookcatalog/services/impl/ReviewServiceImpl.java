package com.publishing.house.bookcatalog.services.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publishing.house.bookcatalog.model.Review;
import com.publishing.house.bookcatalog.repositories.ReviewRepository;
import com.publishing.house.bookcatalog.services.ReviewService;
import com.publishing.house.bookcatalog.utils.NumberUtils;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(final ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.getAllReviews();
    }

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.saveReview(review);
    }

    @Override
    public Review updateReview(Review review) {
        return reviewRepository.updateReview(review);
    }

    @Override
    public Review deleteReviewById(String id) {
        return reviewRepository.deleteReviewById(NumberUtils.parseStringToLong(id));
    }
}
