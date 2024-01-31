package server.project_module05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.project_module05.model.entity.Address;
import server.project_module05.model.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Long> {
    Address findByUser(User user);
    Boolean existsByAddressIdAndUser(Long addressId, Optional<User> user);
    List<Address> findAllByUser(User user);
}
