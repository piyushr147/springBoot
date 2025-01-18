package com.spring.springJPA.searchCriteria.course;

import com.spring.springJPA.entity.Course;
import com.spring.springJPA.entity.Review;
import com.spring.springJPA.entity.Student;
import com.spring.springJPA.enums.SortBy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CourseSearchCriteriaImpl implements CourseSearchCriteria{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Course> searchByFilters(CourseSearch searchCriteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        Root<Course> courseRoot = cq.from(Course.class);
        Join<Course, Review> reviewJoin = courseRoot.join("reviews",JoinType.LEFT);
        Join<Course, Student> studentJoin = courseRoot.join("students",JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();

        if(searchCriteria.getSortBy() != null){
            String order = searchCriteria.getOrder();
            if(searchCriteria.getSortBy() == SortBy.RATING){
                Expression<Double> avgRating = cb.avg(reviewJoin.get("rating"));
                cq.multiselect(courseRoot,avgRating).groupBy(courseRoot.get("id")).orderBy(Objects.equals(order,"asc")?cb.asc(avgRating):cb.desc(avgRating));
            }
            if(searchCriteria.getSortBy() == SortBy.ENROLLMENT){
                Expression<Long> mostEnrolled = cb.count(studentJoin.get("id"));
                cq.multiselect(courseRoot,mostEnrolled).groupBy(courseRoot.get("id")).orderBy(Objects.equals(order,"asc")?cb.asc(mostEnrolled):cb.desc(mostEnrolled));
            }
            if(searchCriteria.getSortBy() == SortBy.DURATION){
                cq.orderBy(Objects.equals(order,"asc")?cb.asc(courseRoot.get("courseDuration")):cb.desc(courseRoot.get("courseDuration")));
            }
        }
        if(searchCriteria.getCourseName() != null && !searchCriteria.getCourseName().isEmpty()){
            predicates.add(cb.like(courseRoot.get("courseName"), "%"+searchCriteria.getCourseName()+"%"));
        }
        if((searchCriteria.getStartDuration() != 0 || searchCriteria.getEndDuration() != 100) && (searchCriteria.getStartDuration()<=searchCriteria.getEndDuration()) ){
            predicates.add(cb.between(courseRoot.get("courseDuration"),searchCriteria.getStartDuration(),searchCriteria.getEndDuration()));
        }
        cq.select(courseRoot).where(predicates.toArray(new Predicate[0]));
        TypedQuery<Course> typedQuery = entityManager.createQuery(cq);
        return typedQuery.getResultList();
    }
}
