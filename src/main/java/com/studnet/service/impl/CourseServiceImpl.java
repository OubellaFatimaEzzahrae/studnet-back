package com.studnet.service.impl;

import com.studnet.model.Course;
import com.studnet.model.Student;
import com.studnet.repository.CourseRepository;
import com.studnet.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Optional<Course> getCourseById(int id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudentsByCourseId(int id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.map(Course::getStudents)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }
}
