package server.project_module05.service.shopping_cart;

import org.springframework.stereotype.Service;
import server.project_module05.model.dto.request.shopping_cart.ShoppingCartRequest;
import server.project_module05.model.dto.response.shopping_cart.ShoppingCartResponse;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface IShoppingCartService {
    List<ShoppingCartResponse> getAllShoppingCart();
    ShoppingCartResponse addNewShoppingCart(ShoppingCartRequest shoppingCartRequest);
}
