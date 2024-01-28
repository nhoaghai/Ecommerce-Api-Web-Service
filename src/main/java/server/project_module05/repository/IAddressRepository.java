package server.project_module05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.project_module05.model.entity.Address;
import server.project_module05.model.entity.User;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Long> {
    Address findByAddressIdAndUser(Long addressId, User user);
}
