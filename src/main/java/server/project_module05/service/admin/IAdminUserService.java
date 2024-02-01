package server.project_module05.service.admin;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import server.project_module05.model.dto.response.account.AccountResponse;
import server.project_module05.model.dto.response.page.PageResponse;
import server.project_module05.model.dto.response.user_role.UserRoleResponse;
import server.project_module05.model.entity.User;

import java.util.List;

@Service
public interface IAdminUserService {
    PageResponse<AccountResponse> getAllUser(Pageable pageable);
    void toggleUserStatus(Long userId);
    List<UserRoleResponse> getUserRole();
    PageResponse<AccountResponse> findByUserName(String keyword, Pageable pageable);
}
