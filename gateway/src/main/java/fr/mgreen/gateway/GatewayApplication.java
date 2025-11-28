package fr.mgreen.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("signup-route", r -> r
                        .path("/signup")
                        .and().method(HttpMethod.POST)
                        .uri("http://orchestrator"))
                .route("login-route", r -> r
                        .path("/login")
                        .and().method(HttpMethod.POST)
                        .uri("http://auth-microservice"))
                .build();
    }
}
