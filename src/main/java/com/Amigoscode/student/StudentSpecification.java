package com.Amigoscode.student;

import com.Amigoscode.specification.EntitySpecification;
import com.Amigoscode.specification.key.JoinEnum;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification extends EntitySpecification<Student> {
    public StudentSpecification() {
        addJoin(JoinEnum.ENROLMENT);
        //addJoin(JoinEnum.MENTOR);
    }

    public static Specification<Student> StudentBySubject(Long subjId) {
        return (root, query, builder)
                ->
                builder.and(builder.equal(root.join("enrolmentList").get("subject").get("id"), subjId));

    }
}






