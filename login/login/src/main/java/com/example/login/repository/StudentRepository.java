package com.example.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.login.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUsername(String username);
}