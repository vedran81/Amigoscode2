package com.Amigoscode;

import com.Amigoscode.creator.DataCreator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AmigoscodeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AmigoscodeApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		DataCreator.createUniversity();
	}
}
