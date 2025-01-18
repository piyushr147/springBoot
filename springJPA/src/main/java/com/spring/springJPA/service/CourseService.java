package com.spring.springJPA.service;

import com.spring.springJPA.entity.Course;
import com.spring.springJPA.jpa.CourseJpaRepository;
import com.spring.springJPA.NonJpa.CourseRepository;
import com.spring.springJPA.searchCriteria.course.CourseSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    CourseJpaRepository courseJpaRepository;

    @Autowired
    CourseRepository courseRepository;

    public Page<Course> getAll(Pageable pageable) {
        //List<Course> courses1 = courseRepository.findAll();
        return courseJpaRepository.findAll(pageable);
    }

    @Cacheable(cacheNames = "course",key = "#courseId")
    public Optional<Course> getById(int courseId) {
        //Course course1 = courseRepository.findById(courseId);
        return courseJpaRepository.findById(courseId);
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

}
