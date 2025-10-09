package com.studnet.service;

import com.studnet.model.Course;
import com.studnet.model.Student;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    Course saveCourse(Course course);              // créer ou mettre à jour un cours
    Optional<Course> getCourseById(int id);        // récupérer un cours par ID
    List<Course> getAllCourses();                  // récupérer tous les cours
    void deleteCourse(int id);                     // supprimer un cours
    List<Student> getStudentsByCourseId(int id);   // récupérer tous les étudiants d’un cours
}
