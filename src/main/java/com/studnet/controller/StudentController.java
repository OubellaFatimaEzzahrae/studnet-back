package com.studnet.controller;

import com.studnet.entity.Major;
import com.studnet.entity.Student;
import com.studnet.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Création d'un nouvel étudiant
    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        Student saved = studentService.saveStudent(student);
        student.setId(null);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Integer id,
            @RequestBody Student student) {

        return studentService.getStudentById(id)
                .map(existingStudent -> {
                    existingStudent.setFirstName(student.getFirstName());
                    existingStudent.setLastName(student.getLastName());
                    existingStudent.setBirthday(student.getBirthday());
                    existingStudent.setMajor(student.getMajor());
                    Student updated = studentService.saveStudent(existingStudent);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

   /* @PostMapping("/{studentId}/course/{courseId}")
    public ResponseEntity<Student> assignStudentToCourse(@PathVariable int studentId,
                                                         @PathVariable int courseId) {
        Student updatedStudent = studentService.assignStudentToCourse(studentId, courseId);
        return ResponseEntity.ok(updatedStudent);
    }*/
}
