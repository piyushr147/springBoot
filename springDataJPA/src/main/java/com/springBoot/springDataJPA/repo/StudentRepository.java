package com.springBoot.springDataJPA.repo;

import com.springBoot.springDataJPA.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    public List<Student> findByMarks(int marks);
    public List<Student> findByName(String name);
    public List<Student> findByMarksGreaterThan(int marks);
}
