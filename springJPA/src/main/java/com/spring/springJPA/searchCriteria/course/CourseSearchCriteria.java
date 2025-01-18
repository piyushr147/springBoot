package com.spring.springJPA.searchCriteria.course;

import com.spring.springJPA.entity.Course;

import java.util.List;

public interface CourseSearchCriteria {

    public List<Course> searchByFilters(CourseSearch search);
}
