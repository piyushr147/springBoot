package com.spring.springJPA.controller;

import com.spring.springJPA.entity.Identity;
import com.spring.springJPA.entity.Student;
import com.spring.springJPA.searchCriteria.student.StudentSearch;
import com.spring.springJPA.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/getStudents")
    public ResponseEntity<List<Student>> getStudents(){
        return new ResponseEntity<>(studentService.getAll(), HttpStatus.FOUND);
    }

    @GetMapping("/getStudents/searchBy")
    public ResponseEntity<List<Student>> searchBy(@RequestBody StudentSearch studentSearch){
        return new ResponseEntity<>(studentService.searchByCriteria(studentSearch),HttpStatus.OK);
    }

    @GetMapping("/getStudent/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int studentId){
        Optional<Student> student = studentService.getById(studentId);
        if(student.isEmpty()){
            String message = "No student found with Id: " + studentId;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/addStudent")
    public ResponseEntity<?> addStudent(@RequestBody @Valid Student student){
        try {
            return new ResponseEntity<>(studentService.addStudent(student),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/addStudents")
    public ResponseEntity<?> addStudents(@RequestBody List<Student> students){
        try {
            return new ResponseEntity<>(studentService.addStudents(students),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/updateCourse")
    public ResponseEntity<Optional<Student>> updateStudent(@RequestPart @Valid Student student){
        Student updatedStudent = studentService.updateStudent(student);
        return new ResponseEntity<>(Optional.of(updatedStudent),HttpStatus.OK);
    }

    @PostMapping("/updateIdentity/{id}")
    public ResponseEntity<?> editIdentity(@PathVariable("id") int studentId,@RequestBody @Valid Identity identity){
        try {
            return new ResponseEntity<>(studentService.addOrUpdateIdentity(studentId,identity),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int studentId){
        boolean isDeleted = studentService.deleteById(studentId);
        if(isDeleted)
            return ResponseEntity.ok("Student successfully deleted");
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found to delete");
    }
}
