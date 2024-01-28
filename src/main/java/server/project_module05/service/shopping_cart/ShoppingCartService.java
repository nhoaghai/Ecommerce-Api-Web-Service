package server.project_module05.service.shopping_cart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import server.project_module05.model.dto.request.shopping_cart.QuantityChangeRequest;
import server.project_module05.model.dto.request.shopping_cart.ShoppingCartRequest;
import server.project_module05.model.dto.response.shopping_cart.ShoppingCartResponse;
import server.project_module05.model.entity.ShoppingCart;
import server.project_module05.repository.IProductRepository;
import server.project_module05.repository.IShoppingCartRepository;
import server.project_module05.repository.IUserRepository;
import server.project_module05.security.principle.UserDetail;


import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static java.rmi.server.LogStream.log;


@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingCartService implements IShoppingCartService {
    private final ModelMapper modelMapper;
    private final IShoppingCartRepository shoppingCartRepository;
    private final IUserRepository userRepository;
    private final IProductRepository productRepository;

    @Override
    public List<ShoppingCartResponse> getAllShoppingCart() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();

        return shoppingCartRepository.findAllByUserUserId(userDetail.getId()).stream()
                .map(shoppingCart -> {
                    ShoppingCartResponse shoppingCartResponse = modelMapper.map(shoppingCart, ShoppingCartResponse.class);
                    shoppingCartResponse.setProductName(shoppingCart.getProduct().getProductName());
                    shoppingCartResponse.setUnitPrice(shoppingCart.getProduct().getUnitPrice());
                    return shoppingCartResponse;
                }).toList();
    }

    @Override
    public ShoppingCartResponse addNewShoppingCart(ShoppingCartRequest shoppingCartRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();

        ShoppingCart isShoppingCartExits = shoppingCartRepository.findByUserAndProduct(userRepository.findByUserId(userDetail.getId()), productRepository.findByProductId(shoppingCartRequest.getProductId()));
        // sửa
        if (isShoppingCartExits != null){
            isShoppingCartExits.setOrderQuantity(isShoppingCartExits.getOrderQuantity()+shoppingCartRequest.getQuantity());
            shoppingCartRepository.save(isShoppingCartExits);
            return ShoppingCartResponse.builder()
                    .shoppingCartId(isShoppingCartExits.getShoppingCartId())
                    .productName(isShoppingCartExits.getProduct().getProductName())
                    .unitPrice(isShoppingCartExits.getProduct().getUnitPrice())
                    .orderQuantity(isShoppingCartExits.getOrderQuantity())
                    .build();
        }
        // thêm mới
        else{
            ShoppingCart newShoppingCart =
                    new ShoppingCart(
                            null,
                            shoppingCartRequest.getQuantity(),
                            userRepository.findByUserId(userDetail.getId()),
                            productRepository.findByProductId(shoppingCartRequest.getProductId()));
            shoppingCartRepository.save(newShoppingCart);
            return ShoppingCartResponse.builder()
                    .shoppingCartId(newShoppingCart.getShoppingCartId())
                    .productName(newShoppingCart.getProduct().getProductName())
                    .unitPrice(newShoppingCart.getProduct().getUnitPrice())
                    .orderQuantity(newShoppingCart.getOrderQuantity())
                    .build();
        }
    }

    @Override
    public ShoppingCartResponse changeOrderQuantity(Long productId ,QuantityChangeRequest quantityChangeRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();

        ShoppingCart isShoppingCartExits = shoppingCartRepository.findByUserAndProduct(userRepository.findByUserId(userDetail.getId()), productRepository.findByProductId(productId));

        try{
            isShoppingCartExits.setOrderQuantity(quantityChangeRequest.getQuantity());
            shoppingCartRepository.save(isShoppingCartExits);
            return ShoppingCartResponse.builder()
                    .shoppingCartId(isShoppingCartExits.getShoppingCartId())
                    .productName(isShoppingCartExits.getProduct().getProductName())
                    .unitPrice(isShoppingCartExits.getProduct().getUnitPrice())
                    .orderQuantity(isShoppingCartExits.getOrderQuantity())
                    .build();
        }
        catch (Exception e){
            throw new RuntimeException("Can not find shopping cart");
        }
    }

    @Override
    public void deleteProduct(Long productId) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();

        ShoppingCart isShoppingCartExits = shoppingCartRepository.findByUserAndProduct(userRepository.findByUserId(userDetail.getId()), productRepository.findByProductId(productId));
        try {
            shoppingCartRepository.delete(isShoppingCartExits);
            log("Delete successfully!");
        }catch (Exception e){
            throw new Exception("Not found id");
        }
    }
}
