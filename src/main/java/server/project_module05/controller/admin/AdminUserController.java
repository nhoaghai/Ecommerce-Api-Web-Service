package server.project_module05.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.project_module05.model.dto.response.account.AccountResponse;
import server.project_module05.model.dto.response.page.PageResponse;
import server.project_module05.service.admin.IAdminUserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api.myservice.com/v1/admin/user")
public class AdminUserController {
    private final IAdminUserService adminUserService;
    @GetMapping
    public ResponseEntity<PageResponse<AccountResponse>> getAllUser(Pageable pageable){
        return ResponseEntity.ok().body(adminUserService.getAllUser(pageable));
    }
    @PutMapping("/{userId}")
    public ResponseEntity<?> toggleUserStatus(@PathVariable Long userId){
        adminUserService.toggleUserStatus(userId);
        return ResponseEntity.ok("Delete successfully");
    }
}
