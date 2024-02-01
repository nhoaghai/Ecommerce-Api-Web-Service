package server.project_module05.model.dto.response.user_role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRoleResponse {
    private Long roleId;
    private String roleName;
}
