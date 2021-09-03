package com.publishing.house.bookcatalog.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.publishing.house.bookcatalog.model.Review;

@Repository
public class ReviewRepository {

    private EntityManager entityManager;

    public ReviewRepository(@Autowired EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Review> getAllReviews() {
        return entityManager.createQuery("from Review").getResultList();
    }

    @Transactional
    public Review saveReview(Review review) {
        entityManager.persist(review);
        return review;
    }

    @Transactional
    public Review updateReview(Review review) {
        entityManager.merge(review);
        return review;
    }

    public Review deleteReviewById(Long id) {
        return entityManager.find(Review.class, id);
    }
}
