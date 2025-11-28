package fr.mgreen.student_help.orchestrator.profile_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "profile-microservice")
public interface ProfileClient {
    @PostMapping("/users")
    ProfileGet addUser(@RequestBody ProfilePost profilePost);
}
