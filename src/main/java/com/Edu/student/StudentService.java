package com.Edu.student;


import com.Edu.enrolment.Enrolment;
import com.Edu.enrolment.EnrolmentRepository;
import com.Edu.enrolment.EnrolmentService;
import com.Edu.mentor.MentorRepository;
import com.Edu.subject.SubjectRepository;
import net.sf.jasperreports.engine.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.*;


@Service //govori da se klasa mora instancirati i da je "bean" i na taj način se povezuje s studentService u controlleru
public class StudentService {

    private final StudentRepository studentRepository;
    private final MentorRepository mentorRepository;
    private final SubjectRepository subjectRepository;
    private final EnrolmentRepository enrolmentRepository;
    private final EnrolmentService enrolmentService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    public StudentService(StudentRepository studentRepository, MentorRepository mentorRepository, SubjectRepository subjectRepository, EnrolmentRepository enrolmentRepository, EnrolmentService enrolmentService) {
        this.studentRepository = studentRepository;
        this.mentorRepository = mentorRepository;
        this.subjectRepository = subjectRepository;
        this.enrolmentRepository = enrolmentRepository;
        this.enrolmentService = enrolmentService;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new IllegalStateException("email taken");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new IllegalStateException("No student with id " + studentId);
        }
        studentRepository.deleteById(studentId);
    }

    public List<Student> findByMentorId(Long mentorId) {
        return studentRepository.findByMentor_Id(mentorId);
    }

    public List<Student> findByYear(Integer year) {
        return studentRepository.findByStudyYear(year);
    }

    public void assignMentor(Long studentId, Long mentorId) {
        if (!studentRepository.existsById(studentId)) {
            throw new IllegalStateException("No student with id " + studentId);
        }
        if (!mentorRepository.existsById(mentorId)) {
            throw new IllegalStateException("No mentor with id " + mentorId);
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exist"));

        student.setMentor(mentorRepository.findById(mentorId)
                .orElseThrow(() -> new IllegalStateException("mentor with id " + mentorId + " does not exist")));


        studentRepository.save(student);
    }

    @Transactional
    public void updateStudent(Long studentId, StudentUpdateRequest studentUpdateRequest) {
        Student student;

        if (!studentRepository.existsById(studentId)) {
            throw new IllegalStateException("No student with id " + studentId);
        }

        student = studentRepository.getById(studentId);

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
            if (studentRepository.existsByEmail(student.getEmail())) {
                throw new IllegalStateException("email taken");
            }

            student.setEmail(studentUpdateRequest.getEmail());
        }

        if (studentUpdateRequest.getStatus() != null && studentUpdateRequest.getStatus().length() > 0 && !Objects.equals(student.getStatus(), studentUpdateRequest.getStatus())) {
            student.setStatus(studentUpdateRequest.getStatus());
        }

        if (studentUpdateRequest.getStudyYear() != null && !Objects.equals(student.getStudyYear(), studentUpdateRequest.getStudyYear())) {
            student.setStudyYear(studentUpdateRequest.getStudyYear());
        }
    }

    public List<Student> findStudentsWithSubject(Long subjId) {
        List<Student> stList = new ArrayList<>();
        List<Enrolment> enList = enrolmentService.allBySubject(subjId);
        for (Enrolment en : enList) {
            stList.add(en.getStudent());
        }
        return stList;
    }

    public List<Student> findWithGradeBetween(Integer gradeLow, Integer gradeHigh) {
        List<Student> stList = new ArrayList<>();
        List<Enrolment> enList = enrolmentService.findWithGradesBetween(gradeLow, gradeHigh);
        for (Enrolment e : enList) {
            stList.add(e.getStudent());
        }
        return stList;
    }


    // ------------------------------------

    public String generateStudentInfoReport(@NotNull String reportFormat, Long studentId) throws FileNotFoundException, JRException, SQLException {

        File file = ResourceUtils.getFile("classpath:\\studentInfo.jrxml");
        String abspath = file.getAbsolutePath();
        JasperReport jasperReport = JasperCompileManager.compileReport(abspath);
        Map<String, Object> parameters = new HashMap<>();
        Student st = studentRepository.findStudentById(studentId);
        parameters.put("infostudent_id", studentId);
        parameters.put("email", st.getEmail());
        //LocalDate bdate = st.getDateOfBirth();
        parameters.put("date_of_birth", st.getDateOfBirth().toString());
        parameters.put("last_name", st.getLastName());
        parameters.put("first_name", st.getFirstName());
        parameters.put("study_year", st.getStudyYear());
        parameters.put("status", st.getStatus());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
        String path = "w:";
                // "System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "Reports";
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\StudentInfoReport " + st.getId() +  ".html" );
        } else if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\StudentInfoReport " + st.getId() + ".pdf");
        } else {
            return "Unsupported format " + reportFormat;
        }


        return "Report generated in path: " + path;
    }
}
