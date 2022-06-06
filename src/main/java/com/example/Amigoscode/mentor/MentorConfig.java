package com.example.Amigoscode.mentor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MentorConfig {

    @Bean
    CommandLineRunner commandLineRunnerMentor (MentorRepository repository) {
        return new CommandLineRunner() {
            public void run(String... args) throws Exception {
                if (repository.count() == 0) {
                    Mentor janko = new Mentor("Janko", "Mladic", "janko.mladic@gmail.com");

                    Mentor helena = new Mentor("Helena", "Jankic", "helena.jankic@gmail.com");

                    repository.saveAll(List.of(janko, helena));
                }
            }
        };
    }

}