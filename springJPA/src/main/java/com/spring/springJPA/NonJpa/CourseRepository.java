package com.spring.springJPA.NonJpa;

import com.spring.springJPA.entity.Course;
import com.spring.springJPA.entity.Review;
import com.spring.springJPA.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CourseRepository {

    @PersistenceContext
    EntityManager entityManager;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Course> findAll(){
        logger.info("EntityManager of CourseJpaRepository -> {}",System.identityHashCode(entityManager));
        return entityManager.createQuery("select c from Course c", Course.class).getResultList();
    }

    public Course findById(int id){
        logger.info("EntityManager of CourseJpaRepository -> {}",System.identityHashCode(entityManager));
        return entityManager.find(Course.class,id);
    }

    public void deleteById(int id) {
        Course course = entityManager.find(Course.class,id);
        entityManager.remove(course);
    }

    public void addReviewInCourse(int id,Review review){
        logger.info("checking OneToMany and ManyToOne bidirectional flow in course and review class");
        Course course = entityManager.find(Course.class,id);
        course.addReview(review);
        review.setCourse(course);
        entityManager.persist(review);
    }

    public void addStudentInCourse(Student student, Course course){
        entityManager.merge(course);
        entityManager.merge(student);
        course.addStudent(student);
    }
}
