package com.Amigoscode.specification.key;

public enum PropertyEnum {
    /*
    ABBR("abbr"),
    ADDRESS_LINE1("addressLine1"),
    ADDRESS_LINE2("addressLine2"),
    CITY("city"),
    ID("id"),
    NAME("name"),
    NAME_FIRST("nameFirst"),
    NAME_LAST("nameLast"),
    STATUS("status");
*/
    ID("id"),
    NAME("name"),
    YEAR("year"),
    STUDY_YEAR("studyYear"),
    NAME_FIRST("firstName"),
    NAME_LAST("lastName"),
    EMAIL("email"),
    ENROLMENTS("enrolment"),
    SUBJECT("subject"),
    STUDENT("student"),
    GRADE("grade");


    private String name;

    PropertyEnum(String name){
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
}
