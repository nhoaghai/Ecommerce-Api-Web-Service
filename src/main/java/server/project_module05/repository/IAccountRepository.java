package server.project_module05.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.project_module05.model.entity.User;

@Repository
public interface IAccountRepository extends JpaRepository<User,Long> {
    User findByUserId(Long userId);
}
