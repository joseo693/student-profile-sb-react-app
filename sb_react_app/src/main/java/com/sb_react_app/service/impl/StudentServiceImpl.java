package com.sb_react_app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exception.StudentAlreadyExistsException;
import com.exception.StudentNotFoundException;
import com.sb_react_app.model.Student;
import com.sb_react_app.repository.StudentRepository;
import com.sb_react_app.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    // injecting StudentRepository
    private final StudentRepository studentRepository;
    
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {

        return studentRepository.findAll();
    }

    @Override
    public Student createStudent(Student student) {
         
        // checking to see if the student already exists
        if( studentAlreadyExists(student.getEmail()) ) {
            throw new StudentAlreadyExistsException(student.getEmail() + " already exists!");
        }

        Student savedStudent = studentRepository.save(student);
        return savedStudent;
    }

    @Override
    public Student getStudentById(Long studentId) {
        // studentRepository extends JpaRepository, which contains a findById()
        // .orElseThrow() - If the student ID doesn’t exist in the database, this throws a custom exception
        Student retrievedStudent = studentRepository.findById(studentId)
            .orElseThrow( () -> new StudentNotFoundException("Student with id : " + studentId + " not found"));

        return retrievedStudent;
    }

    @Override
    public Student updateStudentById(Student student, Long studentId) {
        Student updateStudent = studentRepository.findById(studentId)
                                    .map(st -> {
                                        // updating neccessary properties 
                                        st.setFirstName(student.getFirstName());
                                        st.setLastName(student.getLastName());
                                        st.setEmail(student.getEmail());
                                        st.setDepartment(student.getDepartment());
                                        return studentRepository.save(st);
                                    }).orElseThrow(() -> new StudentNotFoundException("Student with id : " + studentId + " not found"));
        return updateStudent;                    
    }

    @Override
    public void deleteStudentById(Long studentId) {
        if( !studentRepository.existsById(studentId) ){
            throw new StudentNotFoundException("Student with id : " + studentId + " not found");
        }
        
    }

    public boolean studentAlreadyExists(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }
}
