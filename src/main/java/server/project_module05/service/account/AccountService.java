package server.project_module05.service.account;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import server.project_module05.model.dto.request.account.ChangePasswordRequest;
import server.project_module05.model.dto.request.account.ChangeProfileDetailRequest;
import server.project_module05.model.dto.response.account.AccountResponse;
import server.project_module05.model.entity.User;
import server.project_module05.repository.IAccountRepository;
import server.project_module05.security.principle.UserDetail;
import server.project_module05.service.UploadService;

import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {
    private final IAccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UploadService uploadService;

    @Override
    public AccountResponse findByUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        return modelMapper.map(accountRepository.findByUserId(userDetail.getId()), AccountResponse.class);
    }

    @Override
    public AccountResponse changeUserProfileDetail(ChangeProfileDetailRequest changeProfileDetailRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();

        User user = accountRepository.findByUserId(userDetail.getId());
        user.setFullName(changeProfileDetailRequest.getFullName());
        user.setEmail(changeProfileDetailRequest.getEmail());
        user.setPhone(changeProfileDetailRequest.getPhone());
        user.setAddress(changeProfileDetailRequest.getAddress());
        user.setAvatarImgUrl(uploadService.uploadFile(changeProfileDetailRequest.getAvatarImg()));
        user.setUpdateAt(new Date());
        accountRepository.save(user);
        return modelMapper.map(user, AccountResponse.class);
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = accountRepository.findByUserId(userDetail.getId());

        boolean checkOldPassword = passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword());
        if (!checkOldPassword){
            throw new RuntimeException("Old password is not correct");
        }
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        accountRepository.save(user);
    }
}
