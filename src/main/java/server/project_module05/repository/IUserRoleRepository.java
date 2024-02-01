package server.project_module05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.project_module05.model.entity.Role;

import java.util.List;

@Repository
public interface IUserRoleRepository extends JpaRepository<Role, Long> {
}
