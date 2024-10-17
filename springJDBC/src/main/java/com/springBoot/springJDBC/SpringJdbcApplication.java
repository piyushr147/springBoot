package com.springBoot.springJDBC;

import com.springBoot.springJDBC.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.springBoot.springJDBC.model.Student;
import java.util.List;

@SpringBootApplication
public class SpringJdbcApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringJdbcApplication.class, args);
		Student s = context.getBean(Student.class);
		s.setRollNo(104);
		s.setMarks(88);
		s.setName("sachin");
		StudentService studentService = context.getBean(StudentService.class);
		studentService.addStudent(s);

		List<Student> listofStudents = studentService.getStudents();
        for (Student listofStudent : listofStudents) {
            System.out.println(listofStudent.getName());
        }
	}

}
