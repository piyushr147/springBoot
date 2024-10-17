package com.springBoot.springDataJPA;

import com.springBoot.springDataJPA.model.Student;
import com.springBoot.springDataJPA.repo.StudentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDataJpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringDataJpaApplication.class, args);
		StudentRepository studentRepository = context.getBean(StudentRepository.class);

		Student s1 = context.getBean(Student.class);
		Student s2 = context.getBean(Student.class);
		Student s3 = context.getBean(Student.class);

		s1.setRollNo(100);
		s1.setName("piyush");
		s1.setMarks(100);

		s2.setRollNo(101);
		s2.setName("keshav");
		s2.setMarks(0);

		s3.setRollNo(102);
		s3.setName("sachin");
		s3.setMarks(80);

//		System.out.println(studentRepository.findAll());
//		System.out.println(studentRepository.findById(103).orElse(new Student()));
		System.out.println(studentRepository.findByMarks(100));
		System.out.println(studentRepository.findByMarksGreaterThan(0));
		System.out.println(studentRepository.findByName("keshav"));

		s2.setMarks(60);
		studentRepository.save(s2);

	}
}
