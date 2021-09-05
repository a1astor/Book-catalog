package com.publishing.house.bookcatalog.repositories;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.publishing.house.bookcatalog.model.Review;

@Transactional
@Repository
public class ReviewRepository {

    private final EntityManager entityManager;

    @Autowired
    public ReviewRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Review> getAllReviews() {
        return entityManager.createQuery("from Review").getResultList();
    }

    public Review saveReview(Review review) {
        entityManager.persist(review);
        return review;
    }

    public Review updateReview(Review review) {
        entityManager.merge(review);
        return review;
    }

    public Review deleteReviewById(Long id) {
        return entityManager.find(Review.class, id);
    }

    public Collection<Review> getReviewsByRating(int rating) {
        final Query query = entityManager.createQuery("from Review as r where r.rating in (:value)");
        query.setParameter("value", rating);
       return query.getResultList();
    }

    public Review getReviewById(Long reviewId) {
        return entityManager.find(Review.class, reviewId);
    }
}
