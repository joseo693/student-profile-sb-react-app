package com.sb_react_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sb_react_app.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);
}
