package server.project_module05.model.dto.request.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChangeProfileDetailRequest {
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private MultipartFile avatarImg;
}
