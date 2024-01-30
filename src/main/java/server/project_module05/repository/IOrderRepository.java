package server.project_module05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.project_module05.model.entity.Order;
import server.project_module05.model.entity.User;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUser(User user);

    Order findBySerialNumberAndUser(String serialNumber, User user);

}
