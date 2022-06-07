package com.Amigoscode.enrolment;


import com.Amigoscode.student.Student;
import com.Amigoscode.student.StudentRepository;
import com.Amigoscode.subject.Subject;
import com.Amigoscode.subject.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EnrolmentService {

    private final EnrolmentRepository enrolmentRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;


    @Autowired
    public EnrolmentService(EnrolmentRepository enrolmentRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.enrolmentRepository = enrolmentRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    public List<Enrolment> getEnrolment() {
        return enrolmentRepository.findAll();
    }

    public void deleteEnrolment(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalStateException("Enrolment with id " + id + " does not exist");
        }
        enrolmentRepository.deleteById(id);
    }

    public void addEnrolment(Long studentId, Long subjectId) {
        if (!studentRepository.existsById(studentId) || !subjectRepository.existsById(subjectId)) {
            throw new IllegalStateException("no such student or subject");
        }
        if (enrolmentRepository.existsByStudent_IdAndSubject_Id(studentId, subjectId)) {
            throw new IllegalStateException("student already enroled to that subject");
        }
        Student st = studentRepository.findStudentById(studentId);
        Subject su = subjectRepository.findSubjectById(subjectId);


        //provjeri odgovara li godina
        if (!st.getStudyYear().equals(su.getYear())) {
            throw new IllegalStateException("subject year not matching student");
        }

        Enrolment e = new Enrolment();
        e.setStudent(st);
        e.setSubject(su);
        System.out.println("saving " + e);
        enrolmentRepository.save(e);
    }


    public Enrolment getByIds(Long studentId, Long subjectId) {
        return enrolmentRepository.findByStudent_IdAndSubject_Id(studentId, subjectId);
    }




}