package server.project_module05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.project_module05.model.entity.Order;
import server.project_module05.model.entity.User;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
    Order findBySerialNumber(String serialNumber);
    Boolean existsByUser(User user);
}
