package com.example.cancerpatients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CancerPatientsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CancerPatientsApplication.class, args);
	}

}
