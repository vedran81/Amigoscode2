package com.Edu.mentor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


}

