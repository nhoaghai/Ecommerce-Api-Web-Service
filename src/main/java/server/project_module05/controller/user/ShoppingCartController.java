package server.project_module05.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.project_module05.model.dto.request.shopping_cart.ShoppingCartRequest;
import server.project_module05.model.dto.response.shopping_cart.ShoppingCartResponse;
import server.project_module05.service.shopping_cart.ShoppingCartService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api.myservice.com/v1/user/shopping-cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    @GetMapping
    public ResponseEntity<List<ShoppingCartResponse>> getAllShoppingCart(){
        return new ResponseEntity<>(shoppingCartService.getAllShoppingCart(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ShoppingCartResponse> AddNewShoppingCart(ShoppingCartRequest shoppingCartRequest){
        return ResponseEntity.ok().body(shoppingCartService.addNewShoppingCart(shoppingCartRequest));
    }
}
