package server.project_module05.service.admin.implement;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import server.project_module05.model.dto.response.account.AccountResponse;
import server.project_module05.model.dto.response.page.PageResponse;
import server.project_module05.model.dto.response.user_role.UserRoleResponse;
import server.project_module05.model.entity.Role;
import server.project_module05.model.entity.User;
import server.project_module05.repository.IRoleRepository;
import server.project_module05.repository.IUserRepository;
import server.project_module05.security.principle.UserDetail;
import server.project_module05.service.admin.IAdminUserService;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminUserService implements IAdminUserService {
    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public PageResponse<AccountResponse> getAllUser(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        List<AccountResponse> data = page
                .getContent().stream()
                .map(acc -> modelMapper.map(acc, AccountResponse.class))
                .toList();
        PageResponse<AccountResponse> pageResponse = new PageResponse<>();
        pageResponse.setData(data);
        pageResponse.setTotalPages(page.getTotalPages());
        pageResponse.setSize(page.getSize());
        pageResponse.setPageNumber(page.getNumber());
        pageResponse.setSort(page.getSort().toString());
        return pageResponse;
    }

    @Override
    public void toggleUserStatus(Long userId) {
        User user = userRepository.findByUserId(userId);
        user.setStatus(!user.getStatus());
        userRepository.save(user);
    }

    @Override
    public List<UserRoleResponse> getUserRole() {
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Role> roles = userRepository.findByUserId(userDetail.getId()).getRoles();
        return roles.stream()
                .map(role -> modelMapper.map(role, UserRoleResponse.class))
                .toList()
                .stream()
                .sorted(Comparator.comparing(UserRoleResponse::getRoleId))
                .collect(Collectors.toList());
    }

    @Override
    public PageResponse<AccountResponse> findByUserName(String keyword, Pageable pageable) {
        Page<User> searchResult = userRepository.findAllByUserNameContainingOrFullNameContaining(keyword,keyword,pageable);
        if (searchResult.getContent().isEmpty()){
            throw new RuntimeException("Could not find any user matching");
        }
        List<AccountResponse> data = searchResult.stream().map(user -> modelMapper.map(user, AccountResponse.class)).toList();
        PageResponse<AccountResponse> pageResponse = new PageResponse<>();
        pageResponse.setData(data);
        pageResponse.setTotalPages(searchResult.getTotalPages());
        pageResponse.setPageNumber(searchResult.getNumber());
        pageResponse.setSize(searchResult.getSize());
        pageResponse.setSort(searchResult.getSort().toString());
        return pageResponse;
    }




}
