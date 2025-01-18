package com.spring.springJPA.jpql;

import com.spring.springJPA.SpringJpaApplication;
import com.spring.springJPA.entity.Course;
import com.spring.springJPA.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringJpaApplication.class)
public class JpqlTest {

    @PersistenceContext
    EntityManager entityManager;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void jpqlTest1(){
        logger.info("Query for courses with no student");
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c where c.students is empty", Course.class);
        logger.info("Response -> {}",query.getResultList());
    }

    @Test
    public void jpqlTest2(){
        logger.info("Query for courses with 2 or greater student");
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c where size(c.students) >= 2", Course.class);
        logger.info("Response -> {}",query.getResultList());
    }

    @Test
    public void jpqlTest3(){
        logger.info("Query for student with a passport pattern");
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s where s.identity.number like '%EMDPR%'", Student.class);
        logger.info("Response -> {}",query.getResultList());
    }

    @Test
    public void jpqlTest4(){
        logger.info("Query for student course join");
        Query query = entityManager.createQuery("select c,s from Course c join c.students s");
    }
}
