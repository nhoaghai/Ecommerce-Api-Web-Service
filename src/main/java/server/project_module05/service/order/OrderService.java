package server.project_module05.service.order;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import server.project_module05.model.dto.response.order.OrderDetailResponse;
import server.project_module05.model.dto.response.order.OrderResponse;
import server.project_module05.model.dto.response.product.ProductResponse;
import server.project_module05.model.entity.Order;
import server.project_module05.model.entity.OrderDetail;
import server.project_module05.model.entity.Product;
import server.project_module05.repository.IOrderDetailRepository;
import server.project_module05.repository.IOrderRepository;
import server.project_module05.repository.IProductRepository;
import server.project_module05.repository.IUserRepository;
import server.project_module05.security.principle.UserDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService{

    private final IUserRepository userRepository;
    private final IOrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final IOrderDetailRepository orderDetailRepository;
    private final IProductRepository productRepository;
    @Override
    public List<OrderResponse> findAllOrder() {
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Order> orders = orderRepository.findAllByUser(userRepository.findByUserId(userDetail.getId()));
        if (orders == null) {
            throw new RuntimeException("Could not find orders");
        }
        return orders.stream().map(order -> modelMapper.map(order, OrderResponse.class)).collect(Collectors.toList());
    }

    @Override
    public OrderDetailResponse findOrderBySerialNumber(String serialNumber) {
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Order order = orderRepository.findBySerialNumberAndUser(serialNumber, userRepository.findByUserId(userDetail.getId()));
        if(order == null){
            throw new RuntimeException("Could not find order");
        }
        OrderDetailResponse orderDetailResponse = modelMapper.map(order, OrderDetailResponse.class);
        List<OrderDetail> orderDetail = orderDetailRepository.findAllByOrder(order);
        List<Product> productList = new ArrayList<>();
        for (OrderDetail detail : orderDetail) {
            Product product = productRepository.findByProductId(detail.getProduct().getProductId());
            productList.add(product);
        }
        List<ProductResponse> productResponses = productList.stream().map(product -> modelMapper.map(product, ProductResponse.class)).toList();
        orderDetailResponse.setListItem(productResponses);
        return orderDetailResponse;
    }
}
