package com.spring.springJPA.searchCriteria.course;

import com.spring.springJPA.enums.SortBy;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CourseSearch {
    private int startDuration = 0;
    private int endDuration = 100;
    private String courseName;
    private SortBy SortBy;
    private String order = "dsc";
}
