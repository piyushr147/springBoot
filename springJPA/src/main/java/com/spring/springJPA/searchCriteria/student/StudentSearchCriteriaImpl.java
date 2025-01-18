package com.spring.springJPA.searchCriteria.student;

import com.spring.springJPA.entity.Student;
import com.spring.springJPA.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class StudentSearchCriteriaImpl implements StudentSearchCriteria{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Student> searchByFilters(StudentSearch searchCriteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);

        Root<Student> root = cq.from(Student.class);
        List<Predicate> predicates = new ArrayList<>();

        if(searchCriteria.getName() != null && !searchCriteria.getName().isEmpty()){
            predicates.add(cb.like(root.get("name"), "%"+searchCriteria.getName()+"%"));
        }
        if(!searchCriteria.getSortBy().isEmpty()){
            if(searchCriteria.getSortBy().equalsIgnoreCase("name")){
                cq.orderBy(searchCriteria.getOrder()==0?cb.asc(root.get("name")):cb.desc(root.get("name")));
            }
            if(searchCriteria.getSortBy().equalsIgnoreCase("age")){
                cq.orderBy(searchCriteria.getOrder()==0?cb.asc(root.get("age")):cb.desc(root.get("age")));
            }
        }
        cq.select(root).where(predicates.toArray(new Predicate[0]));
        TypedQuery<Student> typedQuery = entityManager.createQuery(cq);
        return typedQuery.getResultList();
    }
}
