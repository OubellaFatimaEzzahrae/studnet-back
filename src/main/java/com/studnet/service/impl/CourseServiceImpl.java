package com.studnet.service.impl;

import com.studnet.entity.Major;
import com.studnet.entity.Student;
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
    public Major saveCourse(Major major) {
        return courseRepository.save(major);
    }

    @Override
    public Optional<Major> getMajorById(int id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<Major> getAllMajors() {
        return courseRepository.findAll();
    }

    @Override
    public void deleteMajor(int id) {
        courseRepository.deleteById(id);
    }

}
