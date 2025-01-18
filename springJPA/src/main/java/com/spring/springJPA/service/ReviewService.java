package com.spring.springJPA.service;

import com.spring.springJPA.entity.Course;
import com.spring.springJPA.entity.Review;
import com.spring.springJPA.jpa.ReviewJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    public ReviewJpaRepository reviewJpaRepository;

    public List<Review> getAll() {
        return reviewJpaRepository.findAll();
    }

    public Optional<Review> getById(int reviewId) {
        return reviewJpaRepository.findById(reviewId);
    }

    public Review addReview(Review review) {
        return reviewJpaRepository.save(review);
    }

    public Review updateReview(Review review) {
        Optional<Review> findReview = reviewJpaRepository.findById(review.getId());
        if(findReview.isPresent()){
            return reviewJpaRepository.save(review);
        }
        return null;
    }

    public boolean deleteById(int reviewId) {
        Optional<Review> review = reviewJpaRepository.findById(reviewId);
        if(review.isPresent()){
            reviewJpaRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}