package com.Amigoscode.enrolment;

import com.Amigoscode.specification.EntitySpecification;
import com.Amigoscode.specification.SearchOperation;
import com.Amigoscode.specification.key.JoinEnum;
import com.Amigoscode.specification.key.PropertyEnum;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.Period;

public class EnrolmentSpecification extends EntitySpecification<Enrolment> {

    public EnrolmentSpecification() {
    }


    public Specification<Enrolment> allBySubject(Long subjId) {

        addCriteria(JoinEnum.SUBJECT, PropertyEnum.ID, subjId, SearchOperation.EQUAL);
        return generateSpecification();
    }

    public Specification<Enrolment> allWithGradesInDateRange(LocalDate baseDate, Period range){
        addCriteria(PropertyEnum.GRADE_TS, baseDate, SearchOperation.LESS_THAN_EQUAL);
        addCriteria(PropertyEnum.GRADE_TS, baseDate.minus(range), SearchOperation.GREATER_THAN_EQUAL);

        return generateSpecification();
    }
}
