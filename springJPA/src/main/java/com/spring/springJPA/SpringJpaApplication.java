package com.spring.springJPA;

import com.spring.springJPA.entity.Course;
import com.spring.springJPA.entity.Identity;
import com.spring.springJPA.entity.Review;
import com.spring.springJPA.entity.Student;
import com.spring.springJPA.jpa.CourseJpaRepository;
import com.spring.springJPA.jpa.StudentJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@SpringBootApplication
public class SpringJpaApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}

	public Logger logger = LoggerFactory.getLogger(SpringJpaApplication.class);

	@Autowired
	public StudentJpaRepository studentJpaRepository;

	@Autowired
	public CourseJpaRepository courseJpaRepository;

	@Override
	public void run(String... args) throws Exception {
		Student student_1 = studentJpaRepository.findById(1);
		logger.info("JPA findById response -> {}", student_1.toString());
		logger.info("JPA insert a new Student response -> {}", studentJpaRepository.insert(new Student("gauransh","faridabad", Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime())));
		student_1.setLocation("gurgaon");
		logger.info("JPA update a student response -> {}", studentJpaRepository.update(student_1));
//		studentJpaRepository.deleteById(1);
		logger.info("JPA find_all response -> {}", studentJpaRepository.find_all());
		logger.info("JPA find_by_name response -> {}", studentJpaRepository.find_by_name("piyush"));
		studentJpaRepository.playWithEntityManager();
		saveStudentWithIdentity();
		addReviewInCourse();
	}
	public void saveStudentWithIdentity(){
		Student student = new Student("chetan","delhi",Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());
		Identity identity = new Identity("EMDPR101","Aadhar");
		logger.info("JPA saveStudentWithIdentity Response -> {}",studentJpaRepository.saveStudentWithIdentity(student,identity));
	}

	public void addReviewInCourse(){
		Review review = new Review(4,"Course is good");
		courseJpaRepository.addReviewInCourse(1,review);
		review = new Review(5,"Amazing");
		courseJpaRepository.addReviewInCourse(2,review);
	}
}
