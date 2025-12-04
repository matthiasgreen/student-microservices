package fr.mgreen.student_help.orchestrator.user_orchestration;

import fr.mgreen.student_help.orchestrator.auth_client.AuthClient;
import fr.mgreen.student_help.orchestrator.auth_client.AuthPost;
import fr.mgreen.student_help.orchestrator.profile_client.ProfileClient;
import fr.mgreen.student_help.orchestrator.profile_client.ProfileGet;
import fr.mgreen.student_help.orchestrator.profile_client.ProfilePost;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserOrchestrationResource {
    @Autowired
    private AuthClient authClient;

    @Autowired
    private ProfileClient profileClient;

    @PostMapping("/users")
    public ProfileGet signUp(@Valid @RequestBody UserPost userPost) {
        // Post signup to auth
        var authPost = new AuthPost(userPost.username(), userPost.password());
        var authGet = authClient.signUp(authPost);
        // Post profile creation
        try {
            var profilePost = new ProfilePost(
                    authGet.id(),
                    userPost.username(),
                    userPost.firstName(),
                    userPost.lastName(),
                    userPost.email(),
                    userPost.institution(),
                    userPost.course(),
                    userPost.skills(),
                    userPost.availabilities()
            );
            return profileClient.addUser(profilePost);
        } catch (Exception e) {
            System.out.println("test");
            var u = authClient.delete(authGet.id());
            System.out.println(u);
            throw e;
        }
    }
}
