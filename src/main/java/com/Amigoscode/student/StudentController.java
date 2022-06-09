package com.Amigoscode.student;

import com.Amigoscode.enrolment.EnrolmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@RestController
@RequestMapping(path = "api/student")
public class StudentController {

    private final StudentService   studentService;
    private final EnrolmentService enrolmentService;


    @Autowired //govori da private final studentService treba biti "autowired" u konstruktor
    public StudentController(StudentService studentService, EnrolmentService enrolmentService) {
        this.studentService   = studentService;
        this.enrolmentService = enrolmentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = "by_year/{year}")
    public List<Student> findByYear(@PathVariable Integer year) {
        return studentService.findByYear(year);
    }


    @GetMapping(path = "by_mentor/{mentorId}")
    public List<Student> findByMentor(@PathVariable Long mentorId) {
        return studentService.findByMentorId(mentorId);
    }

    @PostMapping(path = "{studentId}/assign_mentor/{mentorId}")
    public void assignMentor(@PathVariable Long studentId, @PathVariable Long mentorId) {
        studentService.assignMentor(studentId, mentorId);
    }

    @PostMapping(path = "add")
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}/delete")
    public void deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}/edit")
    public void updateStudent(@PathVariable Long studentId, @RequestBody StudentUpdateRequest studentUpdateRequest) {
        studentService.updateStudent(studentId, studentUpdateRequest);

    }

    @PostMapping(path = "enrol/{studentId}/{subjectId}")
    public void enrolStudent(@PathVariable Long studentId, @PathVariable Long subjectId) {
        enrolmentService.addEnrolment(studentId, subjectId);
    }

    @GetMapping(path = "students_by_subject/{subjectId}")
    public List<Student> findAllBySubject(@PathVariable Long subjectId) {
        return studentService.findStudentsWithSubject(subjectId);
    }

    @GetMapping(path = "students_graded_in_daterange/{baseDate}/{range}")
    public List<Student> findAllStudentsWithGradesInDateRange(@PathVariable @RequestBody LocalDate baseDate, @PathVariable @RequestBody Period range) {
        // LocalDateTime lowBoundDate = LocalDateTime.parse(lowBound);
        // LocalDateTime upBoundDate = LocalDateTime.parse(upBound);
        return studentService.findWithGradesInDateRange(baseDate, range);
    }
}
