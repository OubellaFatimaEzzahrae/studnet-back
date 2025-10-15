package com.studnet.controller;

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

    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
    // TODO: Séparer en deux endpoints distincts pour respecter REST :
    // - POST /students pour création (id null)
    // - PUT /students/{id} pour mise à jour (id existant)
        Student saved = studentService.saveStudent(student);
        HttpStatus status = (student.getId() == null) ? HttpStatus.CREATED : HttpStatus.OK;
        return new ResponseEntity<>(saved, status);
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
