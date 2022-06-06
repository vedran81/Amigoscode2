package com.example.Amigoscode.mentor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Mentor> mentorByEmail = mentorRepository.findMentorByEmail(mentor.getEmail());

        if(mentorByEmail.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        mentorRepository.save(mentor);
    }

}
