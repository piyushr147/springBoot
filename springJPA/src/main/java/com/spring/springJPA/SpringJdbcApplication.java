package com.spring.springJPA;

import com.spring.springJPA.entity.Person;
import com.spring.springJPA.jdbc.PersonJdbcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringJdbcApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcApplication.class, args);
    }

    public Logger logger = LoggerFactory.getLogger(SpringJdbcApplication.class);

    @Autowired
    public PersonJdbcDao personJdbcDao;

    @Override
    public void run(String... args) throws Exception {
        List<Person> personList = personJdbcDao.findAll();
        for(Person person: personList){
            logger.info(person.toString());
        }
        Person person = personJdbcDao.findById(101);
        logger.info(person.toString());
    }
}
