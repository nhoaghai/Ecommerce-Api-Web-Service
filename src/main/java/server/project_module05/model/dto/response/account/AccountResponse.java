package server.project_module05.model.dto.response.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountResponse {
    private String userName;
    private String email;
    private String fullName;
    private String avatarImgUrl;
    private String phone;
    private String address;
}
