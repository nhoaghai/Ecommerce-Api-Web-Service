package server.project_module05.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.project_module05.model.dto.response.order.OrderDetailResponse;
import server.project_module05.model.dto.response.order.OrderResponse;
import server.project_module05.service.order.IOrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api.myservice.com/v1/user/history")
public class OrderController {
    private final IOrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAllOrders(){
        return ResponseEntity.ok(orderService.findAllOrder());
    }

    @GetMapping("/{serialNumber}")
    public ResponseEntity<OrderDetailResponse> handleOrderDetail(@PathVariable String serialNumber){
        return ResponseEntity.ok(orderService.findOrderBySerialNumber(serialNumber));
    }
}
