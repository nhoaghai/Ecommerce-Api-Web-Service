package server.project_module05.service.admin.implement;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import server.project_module05.model.dto.response.account.AccountResponse;
import server.project_module05.model.dto.response.page.PageResponse;
import server.project_module05.model.dto.response.product.ProductResponse;
import server.project_module05.model.dto.response.user_role.UserRoleResponse;
import server.project_module05.model.entity.Product;
import server.project_module05.model.entity.User;
import server.project_module05.repository.IUserRepository;
import server.project_module05.service.admin.IAdminUserService;

import java.util.List;

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
        return null;
    }
}
