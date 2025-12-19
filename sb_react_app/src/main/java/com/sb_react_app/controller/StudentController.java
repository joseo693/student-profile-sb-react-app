package com.sb_react_app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sb_react_app.model.Student;
import com.sb_react_app.service.StudentService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/students")
public class StudentController {

    // injecting StudentService
    private final StudentService studentService;

    // Constructor
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.FOUND);
    } 

    @PostMapping
    public Student createStudent(@RequestBody Student student) {

        return studentService.createStudent(student); 
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable Long studentId) {

        return studentService.getStudentById(studentId);
    }

    @PutMapping("/update/{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable Long studentId) {

        return studentService.updateStudentById(student, studentId); 
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudentById(@PathVariable Long studentId) {
        studentService.deleteStudentById(studentId);
    }

}
