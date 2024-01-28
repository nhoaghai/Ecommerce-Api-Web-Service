package server.project_module05.model.dto.response.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponse {
    private String userName;
    private String password;
    private Collection<? extends GrantedAuthority> roles;
    private final String type = "Bearer ";
    private String accessToken;
    private String refreshToken;
}
