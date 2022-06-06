package com.example.Amigoscode.mentor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    Optional<Mentor> findMentorByEmail(String email);

    @Override
    Optional<Mentor> findById(Long id);
}
