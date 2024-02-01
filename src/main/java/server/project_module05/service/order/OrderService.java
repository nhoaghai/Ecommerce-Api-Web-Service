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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService{
    private final IOrderRepository orderRepository;
    private final IOrderDetailRepository orderDetailRepository;
    private final IUserRepository userRepository;
    private final IProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<OrderResponse> getAllOrder() {
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Order> orders = orderRepository.findByUser(userRepository.findByUserId(userDetail.getId()));
        if (orders == null){
            throw new RuntimeException("Could not find order");
        }
        return orders.stream().map(order -> modelMapper.map(order,OrderResponse.class)).collect(Collectors.toList());
    }

    @Override
    public OrderDetailResponse getOrderDetailBySerialNumber(String serialNumber) {
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Boolean isOrderExits = orderRepository.existsByUser(userRepository.findByUserId(userDetail.getId()));
        if (!isOrderExits){
            throw new RuntimeException("Order is not exits");
        }else{
            Order order = orderRepository.findBySerialNumber(serialNumber);
            List<OrderDetail> orderDetails = orderDetailRepository.findByOrder(order);
            List<Product> products = new ArrayList<>();
            for (OrderDetail detail : orderDetails) {
                Long productId = detail.getProduct().getProductId();
                products.add(productRepository.findByProductId(productId));
            }
            List<ProductResponse> listItem = products.stream()
                    .map(pro -> modelMapper.map(pro, ProductResponse.class))
                    .toList();
            OrderDetailResponse orderResponse = modelMapper.map(order, OrderDetailResponse.class);
            Calendar receiveAt = Calendar.getInstance();
            receiveAt.add(Calendar.DATE,3);
            orderResponse.setReceiveAt(receiveAt);
            orderResponse.setListItem(listItem);
            return orderResponse;
        }
    }
}
