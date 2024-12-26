package com.spring.springJPA;

import com.spring.springJPA.entity.Person;
import com.spring.springJPA.jdbc.PersonJdbcDao;
import com.spring.springJPA.jpa.PersonJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class SpringJpaApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}

	public Logger logger = LoggerFactory.getLogger(SpringJpaApplication.class);

	@Autowired
	public PersonJpaRepository personJpaRepository;

	@Override
	public void run(String... args) throws Exception {
		Person person_101 = personJpaRepository.findById(101);
		logger.info("JPA findById response -> {}",person_101.toString());
		logger.info("JPA insert a new person response -> {}",personJpaRepository.insert(new Person("gauransh","faridabad", Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime())));
		person_101.setLocation("gurgaon");
		logger.info("JPA update a person response -> {}",personJpaRepository.update(person_101));
		personJpaRepository.deleteById(101);
	}
}
