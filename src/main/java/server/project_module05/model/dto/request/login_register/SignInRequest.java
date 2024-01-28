package server.project_module05.model.dto.request.login_register;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignInRequest {
    private String userName;
    private String password;
}
