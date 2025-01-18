package com.spring.springJPA.service;

import com.spring.springJPA.entity.Course;
import com.spring.springJPA.entity.Identity;
import com.spring.springJPA.entity.Student;
import com.spring.springJPA.jpa.StudentJpaRepository;
import com.spring.springJPA.searchCriteria.student.StudentSearch;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentJpaRepository studentJpaRepository;


    public List<Student> getAll() {
        return studentJpaRepository.findAll();
    }

    public Optional<Student> getById(int studentId) {
        return studentJpaRepository.findById(studentId);
    }

    public Student addStudent(Student student) {
        return studentJpaRepository.save(student);
    }

    public List<Student> addStudents(List<Student> students) {
        return studentJpaRepository.saveAll(students);
    }

    public Student updateStudent(Student student) {
        Optional<Student> findCourse = studentJpaRepository.findById(student.getId());
        if(findCourse.isPresent()){
            return studentJpaRepository.save(student);
        }
        return null;
    }

    public Student addOrUpdateIdentity(int studentId, Identity identity) {
        Optional<Student> student = studentJpaRepository.findById(studentId);
        if(student.isPresent()){
            student.get().setIdentity(identity);
            return studentJpaRepository.save(student.get());
        }
        return null;
    }

    public List<Student> searchByCriteria(StudentSearch searchCriteria){
        return studentJpaRepository.searchByFilters(searchCriteria);
    }

    public boolean deleteById(int studentId) {
        Optional<Student> student = studentJpaRepository.findById(studentId);
        if(student.isPresent()){
            studentJpaRepository.deleteById(studentId);
            return true;
        }
        return false;
    }

}
