package com.medical.record.system;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class MedicalRecordSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalRecordSystemApplication.class, args);
	}

}
