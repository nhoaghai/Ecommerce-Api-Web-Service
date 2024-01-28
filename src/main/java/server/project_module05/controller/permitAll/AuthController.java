package server.project_module05.controller.permitAll;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.project_module05.model.dto.request.login_register.SignInRequest;
import server.project_module05.model.dto.request.login_register.SignUpRequest;
import server.project_module05.service.login_registor.implement.UserServiceImplement;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api.myservice.com/v1/auth")
public class AuthController {

    private final UserServiceImplement userService;
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest){
        userService.signUp(signUpRequest);
        return ResponseEntity.ok("Register Successful!");
    }

    @PostMapping("sign-in")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(userService.signIn(signInRequest));
    }
}
