package com.Amigoscode.student;

import com.Amigoscode.specification.EntitySpecification;
import com.Amigoscode.specification.SearchCriteria;
import com.Amigoscode.specification.SearchOperation;
import com.Amigoscode.specification.key.JoinEnum;
import com.Amigoscode.specification.key.PropertyEnum;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification extends EntitySpecification<Student> {
    public StudentSpecification() {
        addJoin(JoinEnum.ENROLMENTS);
    }

    public Specification<Student> StudentBySubject (Long subjId){
        addCriteria(new SearchCriteria(PropertyEnum.ENROLMENTS, subjId, SearchOperation.EQUAL));
        return generateSpecification();
    }


}


