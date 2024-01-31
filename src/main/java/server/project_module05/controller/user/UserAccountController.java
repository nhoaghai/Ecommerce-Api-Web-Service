package server.project_module05.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.project_module05.model.dto.request.account.ChangePasswordRequest;
import server.project_module05.model.dto.request.account.ChangeProfileDetailRequest;
import server.project_module05.model.dto.response.account.AccountResponse;
import server.project_module05.service.account.IAccountService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api.myservice.com/v1/user/account")
public class UserAccountController {
    private final IAccountService accountService;
    @GetMapping
    public ResponseEntity<AccountResponse> getUserAccount(){
        return ResponseEntity.ok().body(accountService.findByUserId());
    }

    @PutMapping
    public ResponseEntity<?> handleChangeProfileDetail(@ModelAttribute ChangeProfileDetailRequest changeProfileDetailRequest){
        return ResponseEntity.ok().body(accountService.changeUserProfileDetail(changeProfileDetailRequest));
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> handleChangePassword(@RequestBody ChangePasswordRequest changePasswordRequest){
        accountService.changePassword(changePasswordRequest);
        return ResponseEntity.ok("Change password successfully");

    }
}
