package server.project_module05.model.dto.request.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
    private String cfPassword;
}
