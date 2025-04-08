package com.spring.springJPA.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.springJPA.entity.Course;
import com.spring.springJPA.entity.Review;
import com.spring.springJPA.searchCriteria.course.CourseSearch;
import com.spring.springJPA.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    @Autowired
    public CourseService courseService;

    @GetMapping("/getCourses")
    public ResponseEntity<List<Course>> getAll(Pageable pageable){
        Page<Course> coursePage = courseService.getAll(pageable);
        return new ResponseEntity<>(coursePage.getContent(),HttpStatus.OK);
    }

    @GetMapping("/getCourses/searchBy")
    public ResponseEntity<List<Course>> searchBy(@RequestBody CourseSearch courseSearch){
        return new ResponseEntity<>(courseService.searchByFilters(courseSearch),HttpStatus.OK);
    }

    @GetMapping("/getCourse/{id}")
    @JsonIgnoreProperties(value = {"reviews","students"},allowGetters = true)
    public ResponseEntity<?> getById(@PathVariable("id") int courseId){
        Optional<Course> course =  courseService.getById(courseId);
        if(course.isEmpty()){
            String message = "No course with Id: " + courseId;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping("/addCourse")
    public ResponseEntity<?> addCourse(@RequestBody @Valid Course course){
        try {
            return new ResponseEntity<>(courseService.addCourse(course),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/addCourses")
    public ResponseEntity<?> addCourses(@RequestBody List<Course> course){
        try {
            return new ResponseEntity<>(courseService.addCourses(course),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/addReview/{courseId}")
    public ResponseEntity<?> addReview(@RequestBody @Valid Review review, @PathVariable("courseId") int courseId){
        try {
            boolean isAdded = courseService.addReview(review,courseId);
            if(isAdded)
                return new ResponseEntity<>("Review added successfully",HttpStatus.OK);
            else
                return new ResponseEntity<>("Review couldn't be added",HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/updateCourse")
    public ResponseEntity<Optional<Course>> updateCourse(@RequestPart @Valid Course course){
        Course updatedCourse = courseService.updateCourse(course);
        return new ResponseEntity<>(Optional.of(updatedCourse),HttpStatus.OK);
    }

    @PutMapping("/updateReview/{courseId}")
    public ResponseEntity<?> updateReview(@RequestPart @Valid Review review,@PathVariable("courseId") int courseId){
        try {
            boolean isAdded = courseService.updateReview(review,courseId);
            if(isAdded)
                return new ResponseEntity<>("Review edited successfully",HttpStatus.OK);
            else
                return new ResponseEntity<>("Review couldn't be updated",HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") int courseId){
        boolean isDeleted = courseService.deleteById(courseId);
        if(isDeleted)
            return ResponseEntity.ok("Course successfully deleted");
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found to delete");
    }
}
