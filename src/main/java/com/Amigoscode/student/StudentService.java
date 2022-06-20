package com.Amigoscode.student;


import com.Amigoscode.creator.DataCreator;
import com.Amigoscode.enrolment.Enrolment;
import com.Amigoscode.enrolment.EnrolmentRepository;
import com.Amigoscode.enrolment.EnrolmentService;
import com.Amigoscode.mentor.Mentor;
import com.Amigoscode.mentor.MentorRepository;
import com.Amigoscode.subject.Subject;
import com.Amigoscode.subject.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.Amigoscode.creator.DataCreator.crand;


@Service //govori da se klasa mora instancirati i da je "bean" i na taj način se povezuje s studentService u controlleru
public class StudentService {

    private final StudentRepository studentRepository;
    private final MentorRepository mentorRepository;
    private final SubjectRepository subjectRepository;
    private final EnrolmentRepository enrolmentRepository;
    private final EnrolmentService enrolmentService;

    @Autowired
    public StudentService(StudentRepository studentRepository, MentorRepository mentorRepository, SubjectRepository subjectRepository, EnrolmentRepository enrolmentRepository, EnrolmentService enrolmentService) {
        this.studentRepository = studentRepository;
        this.mentorRepository = mentorRepository;
        this.subjectRepository = subjectRepository;
        this.enrolmentRepository = enrolmentRepository;
        this.enrolmentService = enrolmentService;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new IllegalStateException("email taken");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new IllegalStateException("No student with id " + studentId);
        }
        studentRepository.deleteById(studentId);
    }

    public List<Student> findByMentorId(Long mentorId) {
        return studentRepository.findByMentor_Id(mentorId);
    }

    public List<Student> findByYear(Integer year) {
        return studentRepository.findByStudyYear(year);
    }

    public void assignMentor(Long studentId, Long mentorId) {
        if (!studentRepository.existsById(studentId)) {
            throw new IllegalStateException("No student with id " + studentId);
        }
        if (!mentorRepository.existsById(mentorId)) {
            throw new IllegalStateException("No mentor with id " + mentorId);
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exist"));

        student.setMentor(mentorRepository.findById(mentorId)
                .orElseThrow(() -> new IllegalStateException("mentor with id " + mentorId + " does not exist")));


        studentRepository.save(student);
    }

    @Transactional
    public void updateStudent(Long studentId, StudentUpdateRequest studentUpdateRequest) {
        Student student;

        if (!studentRepository.existsById(studentId)) {
            throw new IllegalStateException("No student with id " + studentId);
        }

        student = studentRepository.getById(studentId);

        if (studentUpdateRequest.getFirstName() != null && studentUpdateRequest.getFirstName().length() > 0 && !Objects.equals(student.getFirstName(), studentUpdateRequest.getFirstName())) {
            student.setFirstName(studentUpdateRequest.getFirstName());
        }

        if (studentUpdateRequest.getDateOfBirth() != null && !Objects.equals(student.getDateOfBirth(), studentUpdateRequest.getDateOfBirth())) {
            student.setDateOfBirth(studentUpdateRequest.getDateOfBirth());
        }

        if (studentUpdateRequest.getLastName() != null && studentUpdateRequest.getLastName().length() > 0 && !Objects.equals(student.getLastName(), studentUpdateRequest.getLastName())) {
            student.setLastName(studentUpdateRequest.getLastName());
        }

        if (studentUpdateRequest.getEmail() != null && studentUpdateRequest.getEmail().length() > 0 && !Objects.equals(student.getEmail(), studentUpdateRequest.getEmail())) {
            if (studentRepository.existsByEmail(student.getEmail())) {
                throw new IllegalStateException("email taken");
            }

            student.setEmail(studentUpdateRequest.getEmail());
        }

        if (studentUpdateRequest.getStatus() != null && studentUpdateRequest.getStatus().length() > 0 && !Objects.equals(student.getStatus(), studentUpdateRequest.getStatus())) {
            student.setStatus(studentUpdateRequest.getStatus());
        }

        if (studentUpdateRequest.getStudyYear() != null && !Objects.equals(student.getStudyYear(), studentUpdateRequest.getStudyYear())) {
            student.setStudyYear(studentUpdateRequest.getStudyYear());
        }
    }

    public List<Student> findStudentsWithSubject(Long subjId) {
        List<Student> stList = new ArrayList<>();
        List<Enrolment> enList = enrolmentService.allBySubject(subjId);
        for (Enrolment en : enList) {
            stList.add(en.getStudent());
        }
        return stList;
    }

    public List<Student> findWithGradeBetween(Integer gradeLow, Integer gradeHigh) {
        List<Student> stList = new ArrayList<>();
        List<Enrolment> enList = enrolmentService.findWithGradesBetween(gradeLow, gradeHigh);
        for (Enrolment e : enList) {
            stList.add(e.getStudent());
        }
        return stList;
    }


    // ------------------------------------


    public Student createRandomStudent(Integer forStudyYear) {
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


    public void createBunchOfStudents() {


        int uniqueCombos = DataCreator.firstNames.size() * DataCreator.lastNames.size();
        System.out.println(uniqueCombos);

        for (int year = 1; year <= 4; year++) {
            int stPerYear = DataCreator.stPerYearBase + crand.nextInt(3);
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



    public SubjectRepository getSubjectRepository() {
        return subjectRepository;
    }

    public EnrolmentRepository getEnrolmentRepository() {
        return enrolmentRepository;
    }
}
