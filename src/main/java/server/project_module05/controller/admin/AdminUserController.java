package server.project_module05.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.project_module05.model.dto.response.account.AccountResponse;
import server.project_module05.model.dto.response.page.PageResponse;
import server.project_module05.model.dto.response.user_role.UserRoleResponse;
import server.project_module05.service.admin.IAdminUserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api.myservice.com/v1/admin/users")
public class AdminUserController {
    private final IAdminUserService adminUserService;
    @GetMapping
    public ResponseEntity<PageResponse<AccountResponse>> getAllUser(Pageable pageable){
        return ResponseEntity.ok().body(adminUserService.getAllUser(pageable));
    }
    @PutMapping("/{userId}")
    public ResponseEntity<?> toggleUserStatus(@PathVariable Long userId){
        adminUserService.toggleUserStatus(userId);
        return ResponseEntity.ok("Block/UnBlock user successfully");
    }
    @GetMapping("/search")
    public ResponseEntity<PageResponse<AccountResponse>> findUserByName(@RequestHeader String search, Pageable pageable){
        return ResponseEntity.ok().body(adminUserService.findByUserName(search,pageable));
    }

    @GetMapping("/roles")
    public ResponseEntity<List<UserRoleResponse>> findAllRole(){
        return ResponseEntity.ok(adminUserService.getUserRole());
    }
}
