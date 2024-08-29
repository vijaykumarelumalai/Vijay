package com.example.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.login.model.Student;
import com.example.login.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student findByUsername(String username) {
        return studentRepository.findByUsername(username);
    }

    public void saveStudent(Student student) {
        // In a real application, you should hash the password before saving
        studentRepository.save(student);
    }

    public boolean authenticate(String username, String password) {
        Student student = findByUsername(username);
        // In a real application, you should compare hashed passwords
        return student != null && student.getPassword().equals(password);
    }
}