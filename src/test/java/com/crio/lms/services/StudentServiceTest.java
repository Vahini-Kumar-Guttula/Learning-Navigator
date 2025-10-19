package com.crio.lms.services;

import com.crio.lms.dto.CreateStudentDto;
import com.crio.lms.dto.CreateSubjectDto;
import com.crio.lms.entity.Exam;
import com.crio.lms.entity.Student;
import com.crio.lms.entity.Subject;
import com.crio.lms.exceptions.NotFoundException;
import com.crio.lms.repositories.ExamRepository;
import com.crio.lms.repositories.StudentRepository;
import com.crio.lms.repositories.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private ExamRepository examRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student1;
    private Student student2;

    private Subject subject1;
    private Subject subject2;

    private Exam exam1;
    private Exam exam2;

    @BeforeEach
    public void setUp() {

        student1 = Student.builder()
                .id(1)
                .name("student1")
                .build();

        student2 = Student.builder()
                .id(2)
                .name("student2")
                .build();

        subject1 = Subject.builder()
                .id(1)
                .subjectName("subject1")
                .build();

        subject2 = Subject.builder()
                .id(1)
                .subjectName("subject2")
                .build();

        exam1 = Exam.builder()
                .id(1)
                .examName("subject1")
                .build();

        exam2 = Exam.builder()
                .id(2)
                .examName("subject2")
                .build();
    }

    @Test
    public void StudentService_GetStudentById_ReturnsStudentIfExists() {

        when(studentRepository.findById(1)).thenReturn(Optional.of(student1));
        Student returnedStudent = studentService.getStudentById(1);

        assertThat(returnedStudent).isEqualTo(student1);
        verify(studentRepository).findById(1);
    }

    @Test
    public void StudentService_GetStudentById_ThrowsExceptionIfStudentNotExists(){

        when(studentRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> studentService.getStudentById(1));
        verify(studentRepository).findById(1);
    }

    @Test
    public void StudentService_GetSubjectById_ReturnsSubjectIfExists() {

        when(subjectRepository.findById(1)).thenReturn(Optional.of(subject1));
        Subject returnedSubject = studentService.getSubjectById(1);

        assertThat(returnedSubject).isEqualTo(subject1);
        verify(subjectRepository).findById(1);
    }

    @Test
    public void StudentService_GetSubjectById_ThrowsExceptionIfSubjectNotExists() {

        when(subjectRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> studentService.getSubjectById(1));
        verify(subjectRepository).findById(1);
    }


    @Test
    public void StudentService_GetExamById_ReturnsExamIfExists() {

        when(examRepository.findById(1)).thenReturn(Optional.of(exam1));
        Exam returnedExam = studentService.getExamById(1);

        assertThat(returnedExam).isEqualTo(exam1);
        verify(examRepository).findById(1);
    }

    @Test
    public void StudentService_GetSubjectById_ThrowsExceptionIfExamNotExists() {

        when(examRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> studentService.getExamById(1));
        verify(examRepository).findById(1);
    }

    @Test
    public void StudentService_GetAllStudents_ReturnsAllStudents() {

        when(studentRepository.findAll()).thenReturn(Arrays.asList(student1, student2));
        List<Student> actual = studentService.getAllStudents();

        assertThat(actual).isEqualTo(Arrays.asList(student1, student2));
        verify(studentRepository).findAll();
    }

    @Test
    public void StudentService_GetAllSubject_ReturnsAllSubject() {

        when(subjectRepository.findAll()).thenReturn(Arrays.asList(subject1, subject2));
        List<Subject> actual = studentService.getAllSubjects();

        assertThat(actual).isEqualTo(Arrays.asList(subject1, subject2));
        verify(subjectRepository).findAll();
    }

    @Test
    public void StudentService_GetAllExams_ReturnsAllExams() {

        when(examRepository.findAll()).thenReturn(Arrays.asList(exam1, exam2));
        List<Exam> actual = studentService.getAllExams();

        assertThat(actual).isEqualTo(Arrays.asList(exam1, exam2));
        verify(examRepository).findAll();
    }

    @Test
    public void StudentService_CreateStudent_ReturnsStudent() {
        when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student1);
        CreateStudentDto dto = new CreateStudentDto("student1");

        Student actual = studentService.createStudent(dto);

        assertThat(actual).isEqualTo(student1);
        verify(studentRepository).save(Mockito.any(Student.class));
    }

    @Test
    public void StudentService_CreateSubject_ReturnsSubject() {
        when(subjectRepository.save(Mockito.any(Subject.class))).thenReturn(subject1);
        CreateSubjectDto dto = new CreateSubjectDto("subject1");

         Subject actual = studentService.createSubject(dto);

        assertThat(actual).isEqualTo(subject1);
        verify(subjectRepository).save(Mockito.any(Subject.class));
    }

    @Test
    public void StudentService_CreateExam_ReturnsExam() {

        when(subjectRepository.findById(1)).thenReturn(Optional.of(subject1));
        when(examRepository.save(Mockito.any(Exam.class))).thenReturn(exam1);

        Exam exam = studentService.createExam(1);
        assertThat(exam).isEqualTo(exam1);

        verify(examRepository).save(Mockito.any(Exam.class));
        verify(subjectRepository).findById(1);
    }

}
