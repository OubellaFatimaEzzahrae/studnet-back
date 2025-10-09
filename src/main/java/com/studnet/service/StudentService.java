package com.studnet.service;

import com.studnet.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student saveStudent(Student student);
    Optional<Student> getStudentById(int id);
    List<Student> getAllStudents();
    void deleteStudent(int id);
    Student assignStudentToCourse(int studentId, int courseId);
}
