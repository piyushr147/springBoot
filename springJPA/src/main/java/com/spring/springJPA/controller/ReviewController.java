package com.spring.springJPA.controller;

import com.spring.springJPA.entity.Review;
import com.spring.springJPA.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    @Autowired
    public ReviewService reviewService;

    @GetMapping("/getReviews")
    public ResponseEntity<List<Review>> getReviews(){
        return new ResponseEntity<>(reviewService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/getReview/{id}")
    public ResponseEntity<?> getReview(@PathVariable("id") int reviewId){
        return new ResponseEntity<>(reviewService.getById(reviewId),HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") int reviewId){
        boolean isDeleted = reviewService.deleteById(reviewId);
        if(isDeleted)
            return ResponseEntity.ok("Review successfully deleted");
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found to delete");
    }

}
