package com.spring.springJPA;

import com.spring.springJPA.entity.Course;
import com.spring.springJPA.entity.Identity;
import com.spring.springJPA.entity.Review;
import com.spring.springJPA.entity.Student;
import com.spring.springJPA.jpa.CourseJpaRepository;
import com.spring.springJPA.jpa.StudentJpaRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = SpringJpaApplication.class)
class SpringJpaApplicationTests {

	Logger logger = LoggerFactory.getLogger(SpringJpaApplicationTests.class);

	@Autowired
	public EntityManager entityManager;

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
	public void jpaTest3(){
		logger.info("checking OneToMany and ManyToOne bidirectional flow in course and review class");
		Course course = entityManager.find(Course.class,1);
		logger.info("Course response -> {}",course);
		Review review = new Review(4,"Course is good");
		course.addReview(review);
		review.setCourse(course);
		logger.info("List of reviews in course -> {}",course.getReviewList());
//		entityManager.persist(review);
	}

	@Test
	@Transactional
	public void jpaTest4(){
		logger.info("fetching reviews from a course");
		Course course = courseJpaRepository.findById(1);
		logger.info("course reviews -> {}",course.getReviewList());
	}

	@Test
	@Transactional
	public void jpaTest5(){
		logger.info("fetching course from a review");
		Review review = entityManager.find(Review.class,1);
		logger.info("course -> {}",review.getCourse().toString());
	}
}
