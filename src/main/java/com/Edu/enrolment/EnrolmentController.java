package com.Edu.enrolment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/enrolment")
public class EnrolmentController {

    private final EnrolmentService enrolmentService;

    @Autowired
    public EnrolmentController(EnrolmentService enrolmentService) {
        this.enrolmentService = enrolmentService;
    }

    @GetMapping
    public List<Enrolment> getEnrolment() {
        return enrolmentService.getEnrolment();
    }

    @GetMapping(path = "{studentId}/{subjectId}")
    public Enrolment getEnrolment(@PathVariable Long studentId, @PathVariable Long subjectId) {
        return enrolmentService.getByIds(studentId, subjectId);
    }

    @GetMapping(path = "with_grades_between/{gradeLow}/{gradeHigh}")
    public List<Enrolment> findByGradesBetween(@PathVariable Integer gradeLow, Integer gradeHigh) {
        return enrolmentService.findWithGradesBetween(gradeLow, gradeHigh);
    }

    @DeleteMapping(path = "{enrolId}/delete")
    public void deleteEnrolment(@PathVariable Long enrolId) {
        enrolmentService.deleteEnrolment(enrolId);
    }


}
