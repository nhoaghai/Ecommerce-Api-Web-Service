package server.project_module05.service.login_registor;

import org.springframework.stereotype.Service;
import server.project_module05.model.dto.request.login_register.SignInRequest;
import server.project_module05.model.dto.request.login_register.SignUpRequest;
import server.project_module05.model.dto.response.login.UserResponse;

import java.util.List;

@Service
public interface IUserService {
    List<UserResponse> findAll();
    UserResponse findById(Long id);
    UserResponse signIn(SignInRequest signInRequest);
    void signUp(SignUpRequest signUpRequest);
}
