package com.spring.springJPA.jpa;

import com.spring.springJPA.entity.Student;
import com.spring.springJPA.searchCriteria.student.StudentSearchCriteria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentJpaRepository extends JpaRepository<Student,Integer>, StudentSearchCriteria {
}
