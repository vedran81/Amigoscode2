package com.Amigoscode.enrolment;

import com.Amigoscode.specification.EntitySpecification;
import com.Amigoscode.specification.SearchOperation;
import com.Amigoscode.specification.key.JoinEnum;
import com.Amigoscode.specification.key.PropertyEnum;
import org.springframework.data.jpa.domain.Specification;

public class EnrolmentSpecification extends EntitySpecification<Enrolment> {

    public EnrolmentSpecification() {
    }


    public Specification<Enrolment> allBySubject(Long subjId) {

        addCriteria(JoinEnum.SUBJECT, PropertyEnum.ID, subjId, SearchOperation.EQUAL);
        return generateSpecification();
    }
}
