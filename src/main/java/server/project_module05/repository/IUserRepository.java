package server.project_module05.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.project_module05.model.entity.User;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
    User findByUserId(Long userId);
    Page<User> findAllByUserNameContainingOrFullNameContaining(String userName, String fullName, Pageable pageable);

}
