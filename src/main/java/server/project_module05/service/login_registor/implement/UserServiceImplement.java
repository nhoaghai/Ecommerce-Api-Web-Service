package server.project_module05.service.login_registor.implement;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import server.project_module05.model.dto.request.login_register.SignInRequest;
import server.project_module05.model.dto.request.login_register.SignUpRequest;
import server.project_module05.model.dto.response.login.UserResponse;
import server.project_module05.model.entity.Role;
import server.project_module05.model.entity.RoleName;
import server.project_module05.model.entity.User;
import server.project_module05.repository.IRoleRepository;
import server.project_module05.repository.IUserRepository;
import server.project_module05.security.jwt.JwtProvider;
import server.project_module05.security.principle.UserDetail;
import server.project_module05.service.login_registor.IUserService;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements IUserService {
    private final ModelMapper modelMapper;
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user,UserResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse findById(Long id) {
        return modelMapper.map(userRepository.findById(id).orElseThrow(()->
                new RuntimeException("Không tìm thấy id")), UserResponse.class);
    }

    @Override
    public UserResponse signIn(SignInRequest signInRequest) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    signInRequest.getUserName(), signInRequest.getPassword()
            ));
        }catch (Exception e){
            throw new RuntimeException("UserName or password incorrect");
        }
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        String accessToken = jwtProvider.generateAccessToken(userDetail);
        String refreshToken = jwtProvider.generateRefreshToken(userDetail);
        return UserResponse.builder()
                .userName(userDetail.getUsername())
                .password(userDetail.getPassword())
                .roles(userDetail.getAuthorities())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void signUp(SignUpRequest signUpRequest) {
        Set<Role> roleSet = new HashSet<>();
        if (signUpRequest.getRoles() == null) {
            roleSet.add(roleRepository.findByRoleName(RoleName.USER).orElseThrow(() -> new RuntimeException("Role name không tìm thấy")));
        } else {
            signUpRequest.getRoles().forEach(s -> {
                switch (s) {
                    case "admin":
                        roleSet.add(roleRepository.findByRoleName(RoleName.ADMIN).orElseThrow(() -> new RuntimeException("Role name không tìm thấy")));
                    case "user":
                    default:
                        roleSet.add(roleRepository.findByRoleName(RoleName.USER).orElseThrow(() -> new RuntimeException("Role name không tìm thấy")));
                }
            });
        }
        User user = modelMapper.map(signUpRequest, User.class);
        user.setRoles(roleSet);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(true);
        user.setCreateAt(new Date());
        userRepository.save(user);
    }
}
