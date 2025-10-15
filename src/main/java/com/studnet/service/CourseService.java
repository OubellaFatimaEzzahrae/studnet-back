package com.studnet.service;

import com.studnet.entity.Major;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    Major saveCourse(Major major);              // créer ou mettre à jour un cours
    Optional<Major> getMajorById(int id);        // récupérer un cours par ID
    List<Major> getAllMajors();                  // récupérer tous les cours
    void deleteMajor(int id);                     // supprimer un cours
}
