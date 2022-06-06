package com.example.Amigoscode.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return new CommandLineRunner() {
            public void run(String... args) throws Exception {
                if (repository.count() == 0) {
                    Student mariam = new Student("Mariam",
                            "Jamal",
                            "mariam@gmail.com",
                            LocalDate.of(2003, NOVEMBER, 11),
                            "freshment",
                            2022);

                    Student alex = new Student("Alex",
                            "Ivancic",
                            "alex@gmail.com",
                            LocalDate.of(2002, DECEMBER, 1),
                            "part-time",
                            2020);

                    repository.saveAll(List.of(mariam, alex));
                }

            }
        };
    }


}