package com.publishing.house.bookcatalog.services.impl;


import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publishing.house.bookcatalog.model.Book;
import com.publishing.house.bookcatalog.model.Review;
import com.publishing.house.bookcatalog.repositories.BookRepository;
import com.publishing.house.bookcatalog.repositories.ReviewRepository;
import com.publishing.house.bookcatalog.services.BookService;
import com.publishing.house.bookcatalog.services.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    @Autowired
    public ReviewServiceImpl(final ReviewRepository reviewRepository, final BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.getAllReviews();
    }

    @Override
    public Review saveReview(final Review review) {
        return reviewRepository.saveReview(review);
    }

    @Override
    public Review updateReview(final Review review) {
        return reviewRepository.updateReview(review);
    }

    @Override
    public Review deleteReviewById(final Long id) {
        return reviewRepository.deleteReviewById(id);
    }

    @Override
    public Collection<Review> getReviewsByRating(final int rating) {
        return reviewRepository.getReviewsByRating(rating);
    }

    @Transactional
    @Override
    public Set<Review> getReviewsByBookId(final Long bookId) {
        final Book book = bookRepository.getBookById(bookId);
        return book.getReviews();
    }

    @Override
    public Review getReviewsById(Long reviewId) {
        return reviewRepository.getReviewById(reviewId);
    }
}
