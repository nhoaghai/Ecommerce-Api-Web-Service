package server.project_module05.service.account;

import org.springframework.stereotype.Service;
import server.project_module05.model.dto.request.account.ChangePasswordRequest;
import server.project_module05.model.dto.request.account.ChangeProfileDetailRequest;
import server.project_module05.model.dto.response.account.AccountResponse;

@Service
public interface IAccountService {
    AccountResponse findByUserId();
    AccountResponse changeUserProfileDetail(ChangeProfileDetailRequest changeProfileDetailRequest);

    void changePassword(ChangePasswordRequest changePasswordRequest);
}
