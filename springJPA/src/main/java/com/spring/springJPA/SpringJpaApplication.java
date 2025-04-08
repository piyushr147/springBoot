package com.spring.springJPA;

import com.spring.springJPA.entity.*;
import com.spring.springJPA.enums.IdentityType;
import com.spring.springJPA.NonJpa.CourseRepository;
import com.spring.springJPA.NonJpa.StudentRepository;
import com.spring.springJPA.jpa.UserJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableCaching
public class SpringJpaApplication implements CommandLineRunner{


	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}

	public Logger logger = LoggerFactory.getLogger(SpringJpaApplication.class);

	@Autowired
	public StudentRepository studentRepository;

	@Autowired
	public CourseRepository courseRepository;
	@Autowired
	public UserJpaRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		Student student_1 = studentRepository.findById(1);
		logger.info("JPA findById response -> {}", student_1.toString());
		logger.info("JPA insert a new Student response -> {}", studentRepository.insert(new Student("gauransh","faridabad", Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime())));
		student_1.setLocation("gurgaon");
		logger.info("JPA update a student response -> {}", studentRepository.update(student_1));
//		studentJpaRepository.deleteById(1);
		logger.info("JPA find_all response -> {}", studentRepository.find_all());
		logger.info("JPA find_by_name response -> {}", studentRepository.find_by_name("piyush"));
		studentRepository.playWithEntityManager();
		saveStudentWithIdentity();
		addReviewInCourse();
		addStudentCourse();
		addUser();
	}

	private void addUser() {
		User user = new User(1,"piyush","Piyush@1234", List.of("User","ADMIN"));
		userRepository.save(user);
	}

	public void saveStudentWithIdentity(){
		Student student = new Student("chetan","delhi",Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());
		Identity identity = new Identity("EMDPR101", IdentityType.AADHAR_CARD);
		logger.info("JPA saveStudentWithIdentity Response -> {}", studentRepository.saveStudentWithIdentity(student,identity));
	}

	public void addReviewInCourse(){
		Review review = new Review(4,"Course is good");
		courseRepository.addReviewInCourse(1,review);
		review = new Review(5,"Amazing");
		courseRepository.addReviewInCourse(2,review);

		review = new Review(1,"Not that good");
		Course course = courseRepository.findById(1);

		courseRepository.addReviewInCourse(course.getId(),review);
	}

	@Transactional
	public void addStudentCourse(){
		logger.info("checking student course ManyToMany relationship");
		Student student = studentRepository.findById(3);
		Course course = courseRepository.findById(3);
//		student.addCourse(course);
//		course.addStudent(student);
//		student = new Student("sanyam","gaziabad", Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());
//		course = new Course("Coding shuttle spring",40);
		studentRepository.addCourseInStudent(student,course);
//		courseJpaRepository.addStudentInCourse(student,course);
	}
}
