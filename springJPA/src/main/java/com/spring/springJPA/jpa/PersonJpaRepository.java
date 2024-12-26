package com.spring.springJPA.jpa;

import com.spring.springJPA.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class PersonJpaRepository {

    @PersistenceContext
    public EntityManager entityManager;

    public Person findById(int id){
        return entityManager.find(Person.class, 101);
    }

    public Person update(Person person){
        return entityManager.merge(person);
    }

    public Person insert(Person person){
        return entityManager.merge(person);
    }

    public void deleteById(int id) {
        Person person = entityManager.find(Person.class,id);
        entityManager.remove(person);
    }
}
