package com.Amigoscode.mentor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {

    Mentor findByEmail(String email);

    boolean existsByEmail(String email);


}
