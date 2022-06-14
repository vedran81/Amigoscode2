package com.Amigoscode.student;

import com.Amigoscode.enrolment.Enrolment;
import com.Amigoscode.enrolment.EnrolmentService;
import com.Amigoscode.reqcache.CacheRepository;
import com.Amigoscode.reqcache.RequestCache;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "api/student")
public class StudentController {

    private final StudentService studentService;
    private final EnrolmentService enrolmentService;
    private final CacheRepository cacheRepository;


    @Autowired //govori da p^^s trebaju biti "autowired" u konstruktor
    public StudentController(StudentService studentService, EnrolmentService enrolmentService, CacheRepository cacheRepository) {
        this.studentService = studentService;
        this.enrolmentService = enrolmentService;
        this.cacheRepository = cacheRepository;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = "/pdf")
    public String GeneratePdf() throws FileNotFoundException, JRException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(studentService.getStudents());
        JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/students2.jrxml"));

        HashMap<String, Object> map = new HashMap<>();

        JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);

        JasperExportManager.exportReportToPdfFile(report, "students.pdf");

        return "generated.";
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

    @GetMapping(path = "students_graded_between")
    public List<Student> findWithGradesBetween(Integer gradeLow, Integer gradeHigh) {

        return studentService.findWithGradeBetween(gradeLow, gradeHigh);
    }

    @Scheduled(/*cron = "0/10 * * ? * * *"*/ fixedDelay = 60000, initialDelay = 0)
    public void saveTopStudentGrades() {
        List<Enrolment> enList = enrolmentService.findWithGradesBetween(4, 5);
        //List<Student> stList = new ArrayList<>();

        //for (Enrolment e : enList) {
//            stList.add(e.getStudent());
        //      }

        RequestCache rc = new RequestCache();
        String rcname = "top graded students";
        String rcresult = "";
        Long rcid = null;
        for (Enrolment en : enList) {
            rcresult = rcresult + en.getStudent().getLastName() + " " + en.getStudent().getFirstName() + ", " +
                    en.getGrade() + " in \"" + en.getSubject().getName() + "\"; " ;
        }

        if (cacheRepository.existsByReqName(rcname)) {
            rcid = cacheRepository.findByReqName(rcname).getId();
            rc.setId(rcid);
        }

        rc.setReqName(rcname);
        rc.setReqResult(rcresult);

        cacheRepository.save(rc);
    }
}
