package com.crio.lms.services;


import com.crio.lms.dto.CreateStudentDto;
import com.crio.lms.dto.CreateSubjectDto;
import com.crio.lms.entity.Exam;
import com.crio.lms.entity.Student;
import com.crio.lms.entity.Subject;
import com.crio.lms.exceptions.ConflictException;
import com.crio.lms.exceptions.NotFoundException;
import com.crio.lms.repositories.ExamRepository;
import com.crio.lms.repositories.StudentRepository;
import com.crio.lms.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {


    private final SubjectRepository subjectRepository;
    private final ExamRepository examRepository;
    private final StudentRepository studentRepository;

    public StudentService(SubjectRepository subjectRepository, ExamRepository examRepository, StudentRepository studentRepository) {
        this.subjectRepository = subjectRepository;
        this.examRepository = examRepository;
        this.studentRepository = studentRepository;
    }

    public Student getStudentById(int id) {

        Optional<Student> foundStudent = studentRepository.findById(id);

        if(foundStudent.isPresent())
            return foundStudent.get();

        throw new NotFoundException("Student with id " + id + " not found");
    }

    public Subject getSubjectById(int id) {

        Optional<Subject> foundSubject = subjectRepository.findById(id);

        if(foundSubject.isPresent())
            return foundSubject.get();

        throw new NotFoundException("Subject with id " + id + " not found");
    }

    public Exam getExamById(int id) {

        Optional<Exam> foundExam = examRepository.findById(id);

        if(foundExam.isPresent())
            return foundExam.get();

        throw new NotFoundException("Exam with id " + id + " not found");
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Student createStudent(CreateStudentDto dto) {
        Student newStudent = Student.builder()
                .name(dto.getName())
                .enrolledSubjects(new ArrayList<>())
                .enrolledExams(new ArrayList<>())
                .build();

        return studentRepository.save(newStudent);
    }

    public Subject createSubject(CreateSubjectDto dto) {
        Subject newSubject = Subject.builder()
                .subjectName(dto.getSubjectName())
                .build();
        return subjectRepository.save(newSubject);
    }

    public Exam createExam(int subjectId) {
        Subject foundSubject = getSubjectById(subjectId);
    
        Exam newExam = Exam.builder()
                .examName(foundSubject.getSubjectName())
                .subject(foundSubject) // Fix: link subject
                .build();
    
        return examRepository.save(newExam);
    }
    

    public Student enrollSubject(int studentId, int subjectId) {

        Subject foundSubject = getSubjectById(subjectId);
        Student foundStudent = getStudentById(studentId);

        if(foundStudent.getEnrolledSubjects().contains(foundSubject))
            throw new ConflictException("Student with id: " + studentId + " has already enrolled in subject");

        foundStudent.getEnrolledSubjects().add(foundSubject);
        return studentRepository.save(foundStudent);
    }

    public Student enrollExam(int studentId, int examId) {

        Exam foundExam = getExamById(examId);
        Student foundStudent = getStudentById(studentId);

        System.out.println(foundStudent.getEnrolledExams());

        if(foundStudent.getEnrolledExams().contains(foundExam))
            throw new ConflictException("Student with id: " + studentId + " has already enrolled for this particular exam with id: " + examId);

        foundStudent.getEnrolledExams().add(foundExam);
        return studentRepository.save(foundStudent);
    }

}
