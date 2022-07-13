package com.Edu.enrolment;

import com.Edu.specification.EntitySpecification;
import com.Edu.specification.SearchOperation;
import com.Edu.specification.key.JoinEnum;
import com.Edu.specification.key.PropertyEnum;
import org.springframework.data.jpa.domain.Specification;

public class EnrolmentSpecification extends EntitySpecification<Enrolment> {

    public EnrolmentSpecification() {
    }


    public Specification<Enrolment> allBySubject(Long subjId) {

        addCriteria(JoinEnum.SUBJECT, PropertyEnum.ID, subjId, SearchOperation.EQUAL);

        return generateSpecification();
    }

    public Specification<Enrolment> allWithGradesBetween(Integer gradeLow, Integer gradeHigh) {

        addCriteria(PropertyEnum.GRADE, gradeLow, SearchOperation.GREATER_THAN_EQUAL);
        addCriteria(PropertyEnum.GRADE, gradeHigh, SearchOperation.LESS_THAN_EQUAL);

        return generateSpecification();
    }

    public Specification<Enrolment> allByStudent(Long stId) {
        addCriteria(JoinEnum.STUDENT, PropertyEnum.ID, stId, SearchOperation.EQUAL);

        return generateSpecification();
    }
}
