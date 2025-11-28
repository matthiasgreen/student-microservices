package fr.mgreen.student_help.orchestrator.auth_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="auth-microservice")
public interface AuthClient {
    @PostMapping("/users")
    AuthGet signUp(@RequestBody AuthPost authPost);

    @DeleteMapping("/users/{id}")
    AuthGet delete(@PathVariable("id") Long id);
}
