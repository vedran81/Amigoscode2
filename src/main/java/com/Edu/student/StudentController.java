package com.Edu.student;

import com.Edu.enrolment.EnrolmentService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(path = "api/student/")
public class StudentController {

    private final StudentService studentService;
    private final EnrolmentService enrolmentService;
  //  private final ReqCacheRepository reqCacheRepository;


    @Autowired //govori da p^^s trebaju biti "autowired" u konstruktor
    public StudentController(StudentService studentService, EnrolmentService enrolmentService /*ReqCacheRepository reqCacheRepository*/) {
        this.studentService = studentService;
        this.enrolmentService = enrolmentService;
    //    this.reqCacheRepository = reqCacheRepository;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = "report/{stId}/{format}"/*, produces="application/pdf"*/)
    public String  GenerateReport(@PathVariable String format, @PathVariable Long stId) throws JRException, SQLException, FileNotFoundException {
    return studentService.generateStudentInfoReport(format, stId);
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

//    @GetMapping(path = "students_graded_between")
//    public List<Student> findWithGradesBetween(Integer gradeLow, Integer gradeHigh) {
//
//        return studentService.findWithGradeBetween(gradeLow, gradeHigh);
//    }

//    @Scheduled(cron = "*/5 * * * * *")
//    public void saveTopStudentGrades() {
//        List<Enrolment> enList = enrolmentService.findWithGradesBetween(4, 5);
//
//        RequestCache rc = new RequestCache();
//        String rcname = "top graded students";
//        String rcresult = "";
//        Long rcid;
//        for (Enrolment en : enList) {
//            rcresult = rcresult + en.getStudent().getLastName() + " " + en.getStudent().getFirstName() + ", " +
//                    en.getGrade() + " in \"" + en.getSubject().getName() + "\"; ";
//        }
//
//        if (reqCacheRepository.existsByReqName(rcname)) {
//            rcid = reqCacheRepository.findByReqName(rcname).getId();
//            rc.setId(rcid);
//        }
//
//        rc.setReqName(rcname);
//        rc.setReqResult(rcresult);
//        rc.setLastUpdateTS(LocalDateTime.now());
//
//        reqCacheRepository.save(rc);
//    }

//    @GetMapping(path = "student_grades_avg")
//    public ReqResult studentGradesAvg(Long stId) {
//        ReqResult res = new ReqResult("student " + stId + " grades avg", null);
//        List<Enrolment> enList;
//        enList = enrolmentService.allByStudent(stId);
//
//        IntSummaryStatistics stats = new IntSummaryStatistics();
//
//        stats = enList.stream().map(Enrolment::getGrade).collect(Collectors.summarizingInt(integer -> integer));
//
//        stats = enList.stream().collect(Collectors.summarizingInt(Enrolment::getGrade));
//
//
//
//        //stats = enList.stream().collect(Collectors.summarizingInt(Enrolment::getGrade));
//        res.setValue(String.valueOf(stats.getAverage()));
//
//        return res;
//    }
}


