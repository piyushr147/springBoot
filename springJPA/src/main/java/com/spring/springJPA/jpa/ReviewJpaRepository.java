package com.spring.springJPA.jpa;

import com.spring.springJPA.entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ReviewJpaRepository {

    @PersistenceContext
    EntityManager entityManager;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Review findById(int id){
        logger.info("EntityManager of ReviewJpaRepository -> {}",System.identityHashCode(entityManager));
        return entityManager.find(Review.class,id);
    }

    public void deleteById(int id){
        logger.info("EntityManager of ReviewJpaRepository -> {}",System.identityHashCode(entityManager));
        Review review = entityManager.find(Review.class,id);
        entityManager.remove(review.getId());
    }
}
