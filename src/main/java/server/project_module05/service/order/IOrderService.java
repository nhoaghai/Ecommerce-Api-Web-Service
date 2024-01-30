package server.project_module05.service.order;

import server.project_module05.model.dto.response.order.OrderDetailResponse;
import server.project_module05.model.dto.response.order.OrderResponse;

import java.util.List;

public interface IOrderService {
    List<OrderResponse> findAllOrder();

    OrderDetailResponse findOrderBySerialNumber(String serialNumber);
}
