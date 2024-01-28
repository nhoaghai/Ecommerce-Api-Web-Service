package server.project_module05.service.shopping_cart;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import server.project_module05.model.dto.request.shopping_cart.ShoppingCartRequest;
import server.project_module05.model.dto.response.shopping_cart.ShoppingCartResponse;
import server.project_module05.model.entity.ShoppingCart;
import server.project_module05.repository.IProductRepository;
import server.project_module05.repository.IShoppingCartRepository;
import server.project_module05.repository.IUserRepository;
import server.project_module05.security.principle.UserDetail;
import server.project_module05.service.login_registor.IUserService;
import server.project_module05.service.product.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
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

        ShoppingCart isShoppingCartExits = shoppingCartRepository.findByUserUserIdAndProductProductId(userDetail.getId(), shoppingCartRequest.getProductId());
        // sửa
        if (isShoppingCartExits != null){
            isShoppingCartExits.setOrderQuantity(isShoppingCartExits.getOrderQuantity()+shoppingCartRequest.getQuantity());
            return modelMapper.map(isShoppingCartExits,ShoppingCartResponse.class);
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
            return modelMapper.map(newShoppingCart, ShoppingCartResponse.class);
        }
    }

}
