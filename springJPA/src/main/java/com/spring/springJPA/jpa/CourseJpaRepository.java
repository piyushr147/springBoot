package com.spring.springJPA.jpa;

import com.spring.springJPA.entity.Course;
import com.spring.springJPA.searchCriteria.course.CourseSearchCriteria;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseJpaRepository extends JpaRepository<Course, Integer>, CourseSearchCriteria {

    @EntityGraph(attributePaths = {"reviews"})
    Optional<Course> findById(int id);

    @Query("SELECT c from Course c WHERE " + "LOWER(c.courseName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Course> findByKeyword(String keyword);
}
