package com.spring.springJPA;

import com.spring.springJPA.jdbc.StudentJdbcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJdbcApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcApplication.class, args);
    }

    public Logger logger = LoggerFactory.getLogger(SpringJdbcApplication.class);

    @Autowired
    public StudentJdbcDao studentJdbcDao;

    @Override
    public void run(String... args) throws Exception {
//        List<Student> StudentList = StudentJdbcDao.findAll();
//        for(Student Student: StudentList){
//            logger.info(Student.toString());
//        }
//        Student Student = StudentJdbcDao.findById(101);
//        logger.info(Student.toString());
    }
}
