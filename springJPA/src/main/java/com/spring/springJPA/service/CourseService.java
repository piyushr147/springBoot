package com.spring.springJPA.service;

import com.spring.springJPA.entity.Course;
import com.spring.springJPA.entity.Review;
import com.spring.springJPA.jpa.CourseJpaRepository;
import com.spring.springJPA.NonJpa.CourseRepository;
import com.spring.springJPA.jpa.ReviewJpaRepository;
import com.spring.springJPA.searchCriteria.course.CourseSearch;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    CourseJpaRepository courseJpaRepository;

    @Autowired
    ReviewJpaRepository reviewJpaRepository;

    @Autowired
    CourseRepository courseRepository;

    public Page<Course> getAll(Pageable pageable) {
        //List<Course> courses1 = courseRepository.findAll();
        return courseJpaRepository.findAll(pageable);
    }

    /*reviews in our courses are lazily fetched which means that another time this method will get called it will look
    for the same id in cache and will throw an lazyInitialisationException when getReviews will be called*/
    @Cacheable(cacheNames = "course",key = "#courseId")
    public Optional<Course> getById(int courseId) {
        //Course course1 = courseRepository.findById(courseId);

        Optional<Course> course = courseJpaRepository.findById(courseId);
        Hibernate.initialize(course.get().getReviews());
        return course;
    }

    public List<Course> searchByFilters(CourseSearch courseSearch) {
        return courseJpaRepository.searchByFilters(courseSearch);
    }

    public Course addCourse(Course course) {
        return courseJpaRepository.save(course);
    }

    public List<Course> addCourses(List<Course> courses) {
        return courseJpaRepository.saveAll(courses);
    }

    @CachePut(cacheNames = "course",key = "#course.id")
    public Course updateCourse(Course course) {
        Optional<Course> findCourse = courseJpaRepository.findById(course.getId());
        if(findCourse.isPresent()){
            return courseJpaRepository.save(course);
        }
        return null;
    }

    @CacheEvict(cacheNames = "course",key = "#courseId")
    public boolean deleteById(int courseId) {
        Optional<Course> course = courseJpaRepository.findById(courseId);
        if(course.isPresent()){
            courseJpaRepository.deleteById(courseId);
            return true;
        }
        return false;
    }

    public boolean addReview(Review review, int courseId) {
        Optional<Course> course = courseJpaRepository.findById(courseId);
        if(course.isPresent()){
            review.setCourse(course.get());
            course.get().getReviews().add(review);
            courseJpaRepository.save(course.get());
//            reviewJpaRepository.save(review);
            return true;
        }
        return false;
    }

    public boolean updateReview(Review review, int courseId) {
        Optional<Course> course = courseJpaRepository.findById(courseId);
        if(course.isPresent()){
            List<Review> reviews = course.get().getReviews();
            for(Review review1:reviews ){
                if(review1.getId() == review.getId()){
                    review1.setRating(review.getRating());
                    review1.setDescription(review.getDescription());
                    review1.setUpdateDateTime(LocalDateTime.now());
                }
            }
            courseJpaRepository.save(course.get());
            reviewJpaRepository.save(review);
            return true;
        }
        return false;
    }
}
