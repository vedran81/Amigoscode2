package com.Amigoscode.creator;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DataCreator {

    public static  final int numMentors = 8;
    public static final int stPerYearBase = 5;
    public static List<String> firstNames = new ArrayList<>();
    static {
        firstNames.addAll(List.of("Ivan", "Luka", "Nikica", "Mario", "Davor", "Tin", "Marko", "Mirjana",
                "Mia", "Teodora", "Petra"));
    }
    public static List<String> lastNames = new ArrayList<>();
    static {
        lastNames.addAll(List.of("Petrović", "Petrač", "Horvat", "Lukin", "Bajza",
                "Kotromanić", "Miškić", "Kubat", "Drenčević", "Lobor", "Beronić", "Prelević",
                "Ujević", "Gonzo", "Šimun", "Brala", "Francisković", "Lisenko", "Franić", "Lončar",
                "Gavran", "Kraljević", "Sumarev", "Žilić", "Mateljan", "Lovey", "Brkan", "Brezak", "Jelušić"));
    }
    public static Random crand = new Random();

/*    private final MentorRepository mentorRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final EnrolmentService enrolmentService;

    @Autowired
    public DataCreator(MentorRepository mentorRepository, StudentRepository studentRepository, SubjectRepository subjectRepository, EnrolmentService enrolmentService) {
        this.mentorRepository = mentorRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.enrolmentService = enrolmentService;
    }*/













    public static void createUniversity() {
       // createSubjects();
        // createBunchOfMentors();
        // createBunchOfStudents();
    }

    public static void createSubjects() {
     /*   String subListJSON = "{\n" +
                "[\n" +
                "{\n" +
                "\"name\": \"Matematika I.\",\n" +
                "\"year\": 1\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"Informatika I.\",\n" +
                "\"year\": 1\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"Engleski jezik I.\",\n" +
                "\"year\": 1\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"Psihologija stresa\",\n" +
                "\"year\": 1\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"Matematika II.\",\n" +
                "\"year\": 2\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"Termodinamika\",\n" +
                "\"year\": 2\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"Engleski jezik II.\",\n" +
                "\"year\": 2\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"Mehanika\",\n" +
                "\"year\": 2\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"Osnove aerodinamike i mehanike leta\",\n" +
                "\"year\": 3\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"Sustavi i oprema zrakoplova I.\",\n" +
                "\"year\": 3\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"Engleski jezik III.\",\n" +
                "\"year\": 3\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"Psihofiziologija rada\",\n" +
                "\"year\": 3\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"Sustavi i oprema zrakoplova II.\",\n" +
                "\"year\": 4\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"Osnove automatskog upravljanja\",\n" +
                "\"year\": 4\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"Pogon zrakoplova I.\",\n" +
                "\"year\": 4\n" +
                "},\n" +
                "{\n" +
                "\"name\": \"Hidraulika i pneumatika\",\n" +
                "\"year\": 4\n" +
                "}\n" +
                "]\n" +
                "}";

        List<Subject> subjectList = new ArrayList<>();
        JSONDeserializer.deserializeObject(subjectList.getClass(), subListJSON);

        for (Subject s : subjectList) {
            subjectRepository.save(s);
        }*/
    }
}
