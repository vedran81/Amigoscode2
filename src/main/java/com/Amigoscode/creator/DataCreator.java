package com.Amigoscode.creator;

import com.Amigoscode.enrolment.Enrolment;
import com.Amigoscode.enrolment.EnrolmentService;
import com.Amigoscode.mentor.Mentor;
import com.Amigoscode.mentor.MentorRepository;
import com.Amigoscode.student.Student;
import com.Amigoscode.student.StudentRepository;
import com.Amigoscode.subject.Subject;
import com.Amigoscode.subject.SubjectRepository;
import nonapi.io.github.classgraph.json.JSONDeserializer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataCreator {

    private static final int numMentors = 8;
    private static final int stPerYearBase = 5;
    public static List<String> firstNames = new ArrayList<>();
    public static List<String> lastNames = new ArrayList<>();
    public static Random crand = new Random();
    private static MentorRepository mentorRepository;
    private static StudentRepository studentRepository;
    private static SubjectRepository subjectRepository;
    private static EnrolmentService enrolmentService;

    static {
        firstNames.addAll(List.of("Ivan", "Luka", "Nikica", "Mario", "Davor", "Tin", "Marko", "Mirjana",
                "Mia", "Teodora", "Petra"));
    }

    static {
        lastNames.addAll(List.of("Petrović", "Petrač", "Horvat", "Lukin", "Bajza",
                "Kotromanić", "Miškić", "Kubat", "Drenčević", "Lobor", "Beronić", "Prelević",
                "Ujević", "Gonzo", "Šimun", "Brala", "Francisković", "Lisenko", "Franić", "Lončar",
                "Gavran", "Kraljević", "Sumarev", "Žilić", "Mateljan", "Lovey", "Brkan", "Brezak", "Jelušić"));
    }

    public static Student createRandomStudent(Integer forStudyYear) {
        List<Mentor> mentors = mentorRepository.findAll();

        Student st = new Student();
        do {

            st.setFirstName(DataCreator.firstNames.get(crand.nextInt(DataCreator.firstNames.size())));
            st.setLastName(DataCreator.lastNames.get(crand.nextInt(DataCreator.lastNames.size())));
            st.setEmail(st.getFirstName() + "." + st.getLastName() + "@gmail.com");
        } while (studentRepository.existsByEmail(st.getEmail()));

        st.setStudyYear(forStudyYear);

        st.setDateOfBirth(LocalDate.now().minusYears(17 + forStudyYear).withMonth(crand.nextInt(13)).withDayOfMonth(crand.nextInt(29)));

        st.setStatus("-- by the very act of creation --");

        if (crand.nextInt(100) <= 75) {
            st.setMentor(mentors.get(crand.nextInt(mentors.size())));
        }

        return st;
    }


    public static void createBunchOfStudents() {


        int uniqueCombos = DataCreator.firstNames.size() * DataCreator.lastNames.size();
        System.out.println(uniqueCombos);

        for (int year = 1; year <= 4; year++) {
            int stPerYear = stPerYearBase + crand.nextInt(3);
            for (int i = 0; i <= stPerYear; i++) {
                Student student = createRandomStudent(year);
                studentRepository.save(student);
                //enroll them to subjects
                List<Subject> subjList = subjectRepository.findByYear(year);

                for (Subject s : subjList) {
                    Enrolment enrolment = enrolmentService.addEnrolment(student.getId(), s.getId());
                    // chance to grade each
                    if (crand.nextInt(100) <= 67) {
                        enrolmentService.gradeEnrolment(enrolment.getId(), crand.nextInt(4) + 2);
                    }
                }

            }
        }
    }


    public static Mentor createRandomMentor() {
        Mentor ment = new Mentor();

        do {
            ment.setFirstName(DataCreator.firstNames.get(crand.nextInt(DataCreator.firstNames.size())));
            ment.setLastName(DataCreator.lastNames.get(crand.nextInt(DataCreator.lastNames.size())));
            ment.setEmail(ment.getFirstName() + "." + ment.getLastName() + "@fakultet.hr");
        } while (mentorRepository.existsByEmail(ment.getEmail()));

        return ment;
    }

    public static void createBunchOfMentors() {
        for (int i = 0; i <= numMentors; i++) {
            mentorRepository.save(createRandomMentor());
        }
    }

    public static void createUniversity() {
        createSubjects();
        createBunchOfMentors();
        createBunchOfStudents();
    }

    public static void createSubjects() {
        String subListJSON = "{\n" +
                "\t[\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Matematika I.\",\n" +
                "\t\t\t\"year\": 1\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Informatika I.\",\n" +
                "\t\t\t\"year\": 1\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Engleski jezik I.\",\n" +
                "\t\t\t\"year\": 1\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Psihologija stresa\",\n" +
                "\t\t\t\"year\": 1\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Matematika II.\",\n" +
                "\t\t\t\"year\": 2\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Termodinamika\",\n" +
                "\t\t\t\"year\": 2\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Engleski jezik II.\",\n" +
                "\t\t\t\"year\": 2\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Mehanika\",\n" +
                "\t\t\t\"year\": 2\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Osnove aerodinamike i mehanike leta\",\n" +
                "\t\t\t\"year\": 3\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Sustavi i oprema zrakoplova I.\",\n" +
                "\t\t\t\"year\": 3\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Engleski jezik III.\",\n" +
                "\t\t\t\"year\": 3\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Psihofiziologija rada\",\n" +
                "\t\t\t\"year\": 3\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Sustavi i oprema zrakoplova II.\",\n" +
                "\t\t\t\"year\": 4\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Osnove automatskog upravljanja\",\n" +
                "\t\t\t\"year\": 4\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Pogon zrakoplova I.\",\n" +
                "\t\t\t\"year\": 4\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"Hidraulika i pneumatika\",\n" +
                "\t\t\t\"year\": 4\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";

        List<Subject> subjectList = new ArrayList<>();
        JSONDeserializer.deserializeObject(subjectList.getClass(), subListJSON);

        for (Subject s : subjectList) {
            subjectRepository.save(s);
        }
    }
}
