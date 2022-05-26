package com.Amigoscode.specification.key;

public enum JoinEnum {
/*
    ADDRESS("address"),
    COMPANY_CARRIER("companyCarrier"),
    COMPANY_LOCATION("companyLocation"),
    CONTACT_PERSON("contactPerson"),
    CONTACT_PERSONS("contactPersons"),
    COMPANY_OWNER("companyOwner"),
    LOCATION_EXPERT("locationExpert"),
    MAIN_LOCATION("mainLocation"),
    MAUSER_BRANCH("mauserBranch"),
    MICROS_USER("microsUser"),
    RECO_SITE("recoSite"),
    REGION("region"),
    RETURN_PROGRAM("returnProgram"),
    RETURN_PROGRAMS("returnPrograms"),
    SALES_MANAGER_1("salesManager1");
*/
    STUDENT("student"),
    SUBJECT("subject"),
    MENTOR("mentor"),
    ENROLMENT("enrolment");


    private String name;

    JoinEnum(String name){
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
}
