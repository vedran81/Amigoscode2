package com.Edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EduDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduDemoApplication.class, args);
	}


}
