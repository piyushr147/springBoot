package com.spring.springJPA.searchCriteria.student;

import com.spring.springJPA.entity.Student;

import java.util.List;

public interface StudentSearchCriteria {

    List<Student> searchByFilters(StudentSearch search);
}
