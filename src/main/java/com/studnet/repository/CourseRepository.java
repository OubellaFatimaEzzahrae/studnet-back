package com.studnet.repository;

import com.studnet.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Major, Integer> {

}
