package com.Amigoscode.datautils;

import com.Amigoscode.enrolment.Enrolment;
import com.Amigoscode.enrolment.EnrolmentRepository;
import com.Amigoscode.enrolment.EnrolmentService;
import com.Amigoscode.mentor.Mentor;
import com.Amigoscode.mentor.MentorRepository;
import com.Amigoscode.student.Student;
import com.Amigoscode.student.StudentRepository;
import com.Amigoscode.subject.Subject;
import com.Amigoscode.subject.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(path = "util/datautils")
public class DataCreatorController implements CommandLineRunner {

    public static final int numMentors = 8;
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

    private final MentorRepository mentorRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final EnrolmentService enrolmentService;
    private final EnrolmentRepository enrolmentRepository;

    @Autowired
    public DataCreatorController(MentorRepository mentorRepository, StudentRepository studentRepository, SubjectRepository subjectRepository, EnrolmentService enrolmentService, EnrolmentRepository enrolmentRepository) {
        this.mentorRepository = mentorRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.enrolmentService = enrolmentService;
        this.enrolmentRepository = enrolmentRepository;
    }


    @GetMapping(path = "/university")
    public void createUniversity() {
        cleanUpDeleteOld();
        // createSubjects(); -- we don't touch them
        createBunchOfMentors();
        createBunchOfStudents();
    }

    public void createSubjects() {

        // commented as they're already in the base


    }

    public Mentor createRandomMentor() {
        Mentor ment = new Mentor();
        System.out.println("LOG---- creating a rando mentor");
        do {
            ment.setFirstName(firstNames.get(crand.nextInt(firstNames.size())));
            ment.setLastName(lastNames.get(crand.nextInt(lastNames.size())));
            ment.setEmail(ment.getFirstName() + "." + ment.getLastName() + "@fakultet.hr");
            System.out.println("LOG----trying for email " + ment.getEmail());
        } while (mentorRepository.existsByEmail(ment.getEmail()));
        System.out.println("done");
        return ment;
    }

    //@GetMapping(path = "/buncha_mentors")

    public void cleanUpDeleteOld() {
        System.out.println("LOG----- --- cleanup ----");
        System.out.println("LOG---- deleting enrolments");
        enrolmentRepository.deleteAll();
        //enrolmentRepository.d
        System.out.println("LOG---- deleting students");
        studentRepository.deleteAll();
        /// subjectRepository.deleteAll(); --- not touching subjects
        System.out.println("LOG---- deleting mentors");
        mentorRepository.deleteAll();
    }

    public void createBunchOfMentors() {
        System.out.println("LOG---- creating new mentor batch");
        for (int i = 0; i < numMentors; i++) {
            mentorRepository.save(createRandomMentor());
        }
    }

    public Student createRandomStudent(Integer forStudyYear) {
        List<Mentor> mentors = mentorRepository.findAll();
        System.out.println("LOG---- creating a rando stud");
        Student st = new Student();
        do {
            st.setFirstName(firstNames.get(crand.nextInt(firstNames.size())));
            st.setLastName(lastNames.get(crand.nextInt(lastNames.size())));
            st.setEmail(st.getFirstName() + "." + st.getLastName() + "@gmail.com");
            System.out.println("LOG---- trying for email " + st.getEmail());
        } while (studentRepository.existsByEmail(st.getEmail()));
        System.out.println("LOG---- done");

        st.setStudyYear(forStudyYear);

        st.setDateOfBirth(LocalDate.now().minusYears(17 + forStudyYear).withMonth(1 + crand.nextInt(12)).withDayOfMonth(1 + crand.nextInt(25)));

        st.setStatus("dc");

        if (crand.nextInt(100) <= 75) {
            System.out.print("LOG---- setting a random mentor -- ");
            st.setMentor(mentors.get(crand.nextInt(mentors.size())));
            System.out.println(st.getMentor().getFirstName() + " " + st.getMentor().getLastName() + " assigned");
        }
        return st;
    }

    //@GetMapping(path = "/buncha_students")
    public void createBunchOfStudents() {
        for (int year = 1; year <= 4; year++) {
            int stPerYear = stPerYearBase + crand.nextInt(3);
            System.out.println("LOG---- creating " + stPerYear + " students on " + year + ". year");
            for (int i = 0; i < stPerYear; i++) {
                Student student = createRandomStudent(year);
                studentRepository.save(student);
                //enroll them to subjects
                List<Subject> subjList = subjectRepository.findByYear(year);
                System.out.println("LOG---- enrolling the poor soul");
                for (Subject s : subjList) {
                    Enrolment enrolment = enrolmentService.addEnrolment(student.getId(), s.getId());
                    System.out.println("LOG---- ...to " + s.getName());
                    // chance to grade each
                    if (crand.nextInt(100) <= 67) {
                        System.out.print("LOG---- grading them, too... ");
                        enrolmentService.gradeEnrolment(enrolment.getId(), crand.nextInt(4) + 2);
                        System.out.println(" - got " + enrolment.getGrade() + ", oh baby");
                    }
                }

            }
        }
    }

    @Override
    public void run(String... args) {
        //   createUniversity();
    }
}
