package fr.mgreen.student_help.profile_microservice;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProfileMicroserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProfileMicroserviceApplication.class, args);
	}

}
