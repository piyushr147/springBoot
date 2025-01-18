package com.spring.springJPA.searchCriteria.student;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentSearch {
    private String name;
    private String location;
    private String sortBy;
    private int order = 0;
}
