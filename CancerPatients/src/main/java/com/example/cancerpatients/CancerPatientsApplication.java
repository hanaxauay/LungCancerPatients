package com.example.cancerpatients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:databaseContext.xml"})
public class CancerPatientsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CancerPatientsApplication.class, args);
	}

}
