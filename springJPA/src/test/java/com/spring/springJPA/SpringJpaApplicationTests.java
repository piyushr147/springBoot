package com.spring.springJPA;

import com.spring.springJPA.entity.Course;
import com.spring.springJPA.entity.Identity;
import com.spring.springJPA.entity.Review;
import com.spring.springJPA.entity.Student;
import com.spring.springJPA.jpa.CourseJpaRepository;
import com.spring.springJPA.NonJpa.CourseRepository;
import com.spring.springJPA.jpa.StudentJpaRepository;
import com.spring.springJPA.NonJpa.StudentRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = SpringJpaApplication.class)
class SpringJpaApplicationTests {

	Logger logger = LoggerFactory.getLogger(SpringJpaApplicationTests.class);

	@Autowired
	public EntityManager entityManager;

	@Autowired
	public StudentRepository studentRepository;

	@Autowired
	public CourseRepository courseRepository;

	@Autowired
	public StudentJpaRepository studentJpaRepository;

	@Autowired
	public CourseJpaRepository courseJpaRepository;

	@Test
	void contextLoads() {
	}

	@Test
	@Transactional //using this will increase the scope of to jpaTest1 otherwise after entityManger.find the transaction would end resulting in lazy initialisation error on calling student.getIdentity()
	public void jpaTest1(){
		logger.info(String.valueOf(System.identityHashCode(entityManager)));
		logger.info("test for one-to-one eager and lazy loading");
		entityManager.getTransaction().begin();
		Student student = entityManager.find(Student.class,4);
		logger.info("find by id response -> {}",student);
		logger.info("getIdentity response -> {}",student.getIdentity());
		entityManager.getTransaction().commit();
		logger.info("getIdentity response2 -> {}",student.getIdentity());
	}

	@Test
	public void jpaTest2(){
		logger.info("checking Bidirectional mapping by fetching student(owner) from identity");
		Identity identity = entityManager.find(Identity.class,1);
		logger.info("Identity response -> {}",identity.toString());
		//now fetching the student record, if everything is correct then a student sql query must be executed
		logger.info("student response -> {}",identity.getStudent().toString());
	}

	@Test
    @Transactional
	@DirtiesContext
	public void jpaTest3(){
		logger.info("checking OneToMany and ManyToOne bidirectional flow in course and review class");
		Course course = entityManager.find(Course.class,1);
		logger.info("Course response -> {}",course);
		Review review = new Review(4,"Course is good");
		course.addReview(review);
		review.setCourse(course);
		logger.info("List of reviews in course -> {}",course.getReviews());
//		entityManager.persist(review);
	}

	@Test
	@Transactional
	public void jpaTest4(){
		logger.info("fetching reviews from a course");
		Course course = courseRepository.findById(1);
		logger.info("course reviews -> {}",course.getReviews());
	}

	@Test
	@Transactional
	public void jpaTest5(){
		logger.info("fetching course from a review");
		Review review = entityManager.find(Review.class,1);
		logger.info("course -> {}",review.getCourse().toString());
	}

	@Test
	@Transactional
	@DirtiesContext
	public void jpaTest6(){
		Student student = studentRepository.findById(3);
		Course course = courseRepository.findById(3);
		student.addCourse(course);
		course.addStudent(student);
		entityManager.persist(student);
		entityManager.persist(course);
	}

	@Test
	public void jpaTest7(){
		logger.info("find by id through JpaRepository");
		Optional<Course> course = courseJpaRepository.findById(1);
		logger.info("Response of findByID -> {}",course);
	}

	@Test
	public void jpaTest8(){
		logger.info("Sorting through JpaRepository");
		Sort sort = Sort.by("courseDuration").descending().and(Sort.by("courseName").ascending());
		List<Course> courses = courseJpaRepository.findAll(sort);
		logger.info("Response of sorted courses -> {}",courses);
	}

	@Test
	public void jpaTest9(){
		logger.info("Pagenation of students through JpaRepository");
		Sort sort = Sort.by("name","birthDate").ascending();
		Pageable pageable = PageRequest.of(0,3,sort);
		Page<Student> students = studentJpaRepository.findAll(pageable);
		logger.info("Response of pagenation -> {}",students.getContent());
	}
}
