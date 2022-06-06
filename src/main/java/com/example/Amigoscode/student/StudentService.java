package com.example.Amigoscode.student;


import com.example.Amigoscode.mentor.MentorRepository;
import com.example.Amigoscode.mentor.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service //govori da se klasa mora instancirati i da je "bean" i na taj način se povezuje s studentService u controlleru
public class StudentService {

    private final StudentRepository studentRepository;
    private final MentorRepository mentorRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository,MentorRepository mentorRepository) {
        this.studentRepository = studentRepository;
        this.mentorRepository = mentorRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);

        if (!exists) {
            throw new IllegalStateException("Student with id " + studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }

    public List<Student> findByMentorId(Long Id) {
        return studentRepository.findByMentor_Id(Id);
    }

    public void assignMentor(Long studentId, Long mentorId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " student does not exist"));

        student.setMentor(mentorRepository.findById(mentorId)
                .orElseThrow(() -> new IllegalStateException("mentor with id " + mentorId + " does not exist")));


        studentRepository.save(student);

    }

    @Transactional
    public void updateStudent(Long studentId, StudentUpdateRequest studentUpdateRequest) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " student does not exist"));

        if (studentUpdateRequest.getFirstName() != null && studentUpdateRequest.getFirstName().length() > 0 && !Objects.equals(student.getFirstName(), studentUpdateRequest.getFirstName())) {
            student.setFirstName(studentUpdateRequest.getFirstName());
        }

        if (studentUpdateRequest.getDateOfBirth() != null && !Objects.equals(student.getDateOfBirth(), studentUpdateRequest.getDateOfBirth())) {
            student.setDateOfBirth(studentUpdateRequest.getDateOfBirth());
        }

        if (studentUpdateRequest.getLastName() != null && studentUpdateRequest.getLastName().length() > 0 && !Objects.equals(student.getLastName(), studentUpdateRequest.getLastName())) {
            student.setLastName(studentUpdateRequest.getLastName());
        }

        if (studentUpdateRequest.getEmail() != null && studentUpdateRequest.getEmail().length() > 0 && !Objects.equals(student.getEmail(), studentUpdateRequest.getEmail())) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(studentUpdateRequest.getEmail());
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(studentUpdateRequest.getEmail());
        }

        if (studentUpdateRequest.getStudentStatus() != null && studentUpdateRequest.getStudentStatus().length() > 0 && !Objects.equals(student.getStudentStatus(), studentUpdateRequest.getStudentStatus())) {
            student.setStudentStatus(studentUpdateRequest.getStudentStatus());
        }

        if (studentUpdateRequest.getYearOfEnrollment() != null && !Objects.equals(student.getYearOfEnrollment(), studentUpdateRequest.getYearOfEnrollment())) {
            student.setYearOfEnrollment(studentUpdateRequest.getYearOfEnrollment());
        }


    }
}