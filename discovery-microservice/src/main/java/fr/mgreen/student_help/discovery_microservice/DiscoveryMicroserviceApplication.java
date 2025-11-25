package fr.mgreen.student_help.discovery_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryMicroserviceApplication {

	public static void main(String[] args) {
        System.out.println();
		SpringApplication.run(DiscoveryMicroserviceApplication.class, args);
	}

}
