package server.project_module05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.project_module05.model.entity.Order;
import server.project_module05.model.entity.OrderDetail;
import server.project_module05.model.entity.OrderDetailId;

import java.util.List;

public interface IOrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {

    List<OrderDetail> findAllByOrder(Order order);

}
