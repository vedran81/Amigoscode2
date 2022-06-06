package com.example.Amigoscode.mentor;

import com.example.Amigoscode.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/mentor")
public class MentorController {

    private final MentorService mentorService;

    @Autowired
    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @GetMapping
    public List<Mentor> getAllMentors() {
        return mentorService.getMentors();
    }

    @PostMapping("add")
    public void registerNewMentor(@RequestBody Mentor mentor) {
        mentorService.addNewMentor(mentor);
    }

}
