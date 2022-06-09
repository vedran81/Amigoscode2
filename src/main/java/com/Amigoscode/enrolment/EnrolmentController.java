package com.Amigoscode.enrolment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
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

    @GetMapping(path = "with_grades_in_datarange")
    public List<Enrolment> allByGradeInDateRange(@PathVariable @RequestBody LocalDate baseDate, @PathVariable @RequestBody Period range){
            return enrolmentService.allWithGradesInDateRange(baseDate, range);
    }

    @DeleteMapping(path = "{enrolId}/delete")
    public void deleteEnrolment(@PathVariable("enrolId") Long enrolId) {
        enrolmentService.deleteEnrolment(enrolId);
    }


}
