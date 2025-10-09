package com.studnet.service.impl;

import com.studnet.model.Course;
import com.studnet.model.Student;
import com.studnet.repository.CourseRepository;
import com.studnet.repository.StudentRepository;
import com.studnet.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student assignStudentToCourse(int studentId, int courseId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Optional<Course> optionalCourse = courseRepository.findById(courseId);

        if(optionalStudent.isEmpty() || optionalCourse.isEmpty()) {
            throw new RuntimeException("Student or Course not found");
        }

        Student student = optionalStudent.get();
        Course course = optionalCourse.get();

        student.setCourse(course);           // côté Many-to-One
        course.getStudents().add(student);   // côté One-to-Many (mise à jour de la liste)

        studentRepository.save(student);
        courseRepository.save(course);

        return student;
    }
}
