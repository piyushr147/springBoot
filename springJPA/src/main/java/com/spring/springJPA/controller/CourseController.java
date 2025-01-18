package com.spring.springJPA.controller;

import com.spring.springJPA.entity.Course;
import com.spring.springJPA.searchCriteria.course.CourseSearch;
import com.spring.springJPA.service.CourseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    public ResponseEntity<?> getById(@PathVariable("id") int courseId){
        Optional<Course> course =  courseService.getById(courseId);
        if(course.isEmpty()){
            String message = "No course with Id: " + courseId;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping("/addCourse")
    public ResponseEntity<?> addCourse(@RequestBody Course course){
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

    @PutMapping("/updateCourse")
    public ResponseEntity<Optional<Course>> updateCourse(@RequestPart Course course){
        Course updatedCourse = courseService.updateCourse(course);
        return new ResponseEntity<>(Optional.of(updatedCourse),HttpStatus.OK);
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
