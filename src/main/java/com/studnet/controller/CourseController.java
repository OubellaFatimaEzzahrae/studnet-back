package com.studnet.controller;

import com.studnet.entity.Major;
import com.studnet.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//Solution de dépannage
/**
 * @todo Trouver les différents solutions pour éviter ce genre de prblems */
@CrossOrigin(origins = "http://localhost:4200")  // autorise Angular
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Major>> getAllMajors() {
        List<Major> majors = courseService.getAllMajors();
        return ResponseEntity.ok(majors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Major> getMajorById(@PathVariable int id) {
        return courseService.getMajorById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Major> saveMajor(@RequestBody Major major) {
        Major savedMajor = courseService.saveCourse(major);
        return new ResponseEntity<>(savedMajor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Major> updateMajor(@PathVariable int id, @RequestBody Major major) {
        return courseService.getMajorById(id)
                .map(existingMajor -> {
                    existingMajor.setCode(major.getCode());
                    existingMajor.setDescription(major.getDescription());
                    Major updated = courseService.saveCourse(existingMajor);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMajor(@PathVariable int id) {
        courseService.deleteMajor(id);
        return ResponseEntity.noContent().build();
    }



}
