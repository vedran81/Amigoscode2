package com.Edu.enrolment;


import com.Edu.datautils.DataCreatorController;
import com.Edu.student.Student;
import com.Edu.student.StudentRepository;
import com.Edu.subject.Subject;
import com.Edu.subject.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.time.LocalDateTime.now;


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

    public void gradeEnrolment(Long enrId, Integer grade) {
        Enrolment e = enrolmentRepository.findEnrolmentById(enrId);
        e.setGrade(grade);
        e.setGradeTimeStamp(now().minusDays(DataCreatorController.crand.nextInt(60)));
        enrolmentRepository.save(e);
    }

    public Enrolment addEnrolment(Long studentId, Long subjectId) {
        if (!studentRepository.existsById(studentId) || !subjectRepository.existsById(subjectId)) {
            throw new IllegalStateException("no such student or subject");
        }
        if (enrolmentRepository.existsByStudent_IdAndSubject_Id(studentId, subjectId)) {
            throw new IllegalStateException("student already enrolled to that subject");
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
        e.setEnrolTimeStamp(now().withMonth(9));
        if (LocalDate.now().getMonth().getValue() < 9) {
            e.setEnrolTimeStamp(e.getEnrolTimeStamp().minusYears(1));
        }
        System.out.println("saving " + e);
        enrolmentRepository.save(e);
        return e;
    }


    public Enrolment getByIds(Long studentId, Long subjectId) {
        return enrolmentRepository.findByStudent_IdAndSubject_Id(studentId, subjectId);
    }

    public List<Enrolment> allBySubject(Long subjId) {
        EnrolmentSpecification enSpec = new EnrolmentSpecification();
        return enrolmentRepository.findAll(enSpec.allBySubject(subjId));
    }

    public List<Enrolment> findWithGradesBetween(Integer gradeLow, Integer gradeHigh) {
        EnrolmentSpecification enSpec = new EnrolmentSpecification();
        return enrolmentRepository.findAll(enSpec.allWithGradesBetween(gradeLow, gradeHigh));
    }

    public List<Enrolment> allByStudent(Long stId) {
        EnrolmentSpecification enSpec = new EnrolmentSpecification();
        return enrolmentRepository.findAll(enSpec.allByStudent(stId));
    }
}