package com.Amigoscode.mentor;

import com.Amigoscode.creator.DataCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.Amigoscode.creator.DataCreator.crand;

@Service
public class MentorService {

    private final MentorRepository mentorRepository;

    @Autowired
    public MentorService(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }

    public List<Mentor> getMentors() {
        return mentorRepository.findAll();
    }

    public void addNewMentor(Mentor mentor) {

        if (mentorRepository.existsByEmail(mentor.getEmail())) {
            throw new IllegalStateException("email taken");
        }

        mentorRepository.save(mentor);
    }
//----------------

    public Mentor createRandomMentor() {
        Mentor ment = new Mentor();

        do {
            ment.setFirstName(DataCreator.firstNames.get(crand.nextInt(DataCreator.firstNames.size())));
            ment.setLastName(DataCreator.lastNames.get(crand.nextInt(DataCreator.lastNames.size())));
            ment.setEmail(ment.getFirstName() + "." + ment.getLastName() + "@fakultet.hr");
        } while (mentorRepository.existsByEmail(ment.getEmail()));

        return ment;
    }

    public void createBunchOfMentors() {
        for (int i = 0; i <= DataCreator.numMentors; i++) {
            mentorRepository.save(createRandomMentor());
        }
    }
}

