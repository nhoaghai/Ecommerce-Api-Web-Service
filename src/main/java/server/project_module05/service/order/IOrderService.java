package server.project_module05.service.order;

import org.springframework.stereotype.Service;
import server.project_module05.model.dto.response.order.OrderDetailResponse;
import server.project_module05.model.dto.response.order.OrderResponse;

import java.util.List;

@Service
public interface IOrderService {
    List<OrderResponse> getAllOrder();
    OrderDetailResponse getOrderDetailBySerialNumber(String serialNumber);
}
