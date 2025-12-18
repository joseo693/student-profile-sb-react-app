package com.sb_react_app.service;

import java.util.List;

import com.sb_react_app.model.Student;

public interface StudentService {

    List<Student> getAllStudents();
    Student createStudent(Student student);
    Student getStudentById(Long studentId);
    Student updateStudentById(Student student, Long studentId);
    void deleteStudentById(Long studentId);

}
