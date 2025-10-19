package com.crio.lms.controller;

import com.crio.lms.dto.CreateStudentDto;
import com.crio.lms.dto.CreateSubjectDto;
import com.crio.lms.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<?> getStudentById(@PathVariable int studentId) {
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }

    @GetMapping("/subjects/{subjectId}")
    public ResponseEntity<?> getSubjectById(@PathVariable int subjectId) {
        return ResponseEntity.ok(studentService.getSubjectById(subjectId));
    }

    @GetMapping("/exams/{examId}")
    public ResponseEntity<?> getExamById(@PathVariable int examId) {
        return ResponseEntity.ok(studentService.getExamById(examId));
    }

    @GetMapping("/exams")
    public ResponseEntity<?> getAllExams() {
        return ResponseEntity.ok(studentService.getAllExams());
    }

    @GetMapping("/students")
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/subjects")
    public ResponseEntity<?> getAllSubjects() {
        return ResponseEntity.ok(studentService.getAllSubjects());
    }

    @PostMapping("/students")
    public ResponseEntity<?> createStudent(@Valid @RequestBody CreateStudentDto dto) {
        return ResponseEntity.status(201).body(studentService.createStudent(dto));
    }

    @PostMapping("/subjects")
    public ResponseEntity<?> createSubject(@Valid @RequestBody CreateSubjectDto dto) {
        return ResponseEntity.status(201).body(studentService.createSubject(dto));
    }

    @PostMapping("/exams/subjects/{subjectId}")
    public ResponseEntity<?> createExam(@PathVariable int subjectId) {
        return ResponseEntity.status(201).body(studentService.createExam(subjectId));
    }

    @PostMapping("/students/{studentId}/subjects/{subjectId}")
    public ResponseEntity<?> enrollSubject(@PathVariable int studentId, @PathVariable int subjectId) {
        return ResponseEntity.ok(studentService.enrollSubject(studentId, subjectId));
    }

    @PostMapping("/students/{studentId}/exams/{examId}")
    public ResponseEntity<?> enrollExam(@PathVariable int studentId, @PathVariable int examId) {
        return ResponseEntity.ok(studentService.enrollExam(studentId, examId));
    }

}
