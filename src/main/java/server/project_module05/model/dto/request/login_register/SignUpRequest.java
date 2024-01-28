package server.project_module05.model.dto.request.login_register;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignUpRequest {
    private String userName;
    private String password;
    private String cfPassword;
    private String fullName;
    @Email
    private String email;
    private String phone;
    private String address;

    private List<String> roles;
}
