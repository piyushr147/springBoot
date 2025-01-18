package com.spring.springJPA.NonJpa;

import com.spring.springJPA.entity.Course;
import com.spring.springJPA.entity.Identity;
import com.spring.springJPA.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class StudentRepository {

    @Autowired
    public EntityManager entityManager;

    Logger logger = LoggerFactory.getLogger(StudentRepository.class);

    public Student findById(int id){
        logger.info("EntityManager of StudentJpaRepository -> {}",System.identityHashCode(entityManager));
        return entityManager.find(Student.class, id);
    }

    public Student update(Student student){
        return entityManager.merge(student);
    }

    public Student insert(Student student){
        return entityManager.merge(student);
    }

    public void deleteById(int id) {
        Student student = entityManager.find(Student.class,id);
        entityManager.remove(student);
    }

    public List<Student> find_all(){
        TypedQuery<Student> typedQuery = entityManager.createNamedQuery("get_all_Students", Student.class);
        List<Student> students = typedQuery.getResultList();
        return students;
    }

    public List<Student> find_by_name(String name) {
        return entityManager.createNamedQuery("get_Student_by_name", Student.class).setParameter("name",name).getResultList();
    }

    public void playWithEntityManager(){
        Student student = new Student("jaitly","faridabad",Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());
        entityManager.persist(student);//now entity manager will keep track of this object
        student.setLocation("Nit");//this will automatically detect changes and will reflect it in database

        student.setName("himanshu");
        entityManager.detach(student);//Now changes to Student will not reflect in database as entity manager is not keeping track of this object.
        entityManager.flush();//this will manually save the changes in database, but as Student is detached it will not reflect in db.

        Student student1 = new Student("bhuwan","faridabad", Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());
        entityManager.persist(student1);
        student1.setLocation("gurgaon");

        entityManager.clear();//entity manager will not keep track of any object in this transaction and changes to Student1 location will not reflect.
        student = entityManager.merge(student);
        student1 = entityManager.merge(student1);
    }

    public Student saveStudentWithIdentity(Student student, Identity identity){
        entityManager.persist(identity);
        student.setIdentity(identity);
        entityManager.persist(student);
        return student;
    }

    public void addCourseInStudent(Student student, Course course){
        student.addCourse(course);
        course.addStudent(student);
        entityManager.merge(student);
        entityManager.merge(course);
    }
}
