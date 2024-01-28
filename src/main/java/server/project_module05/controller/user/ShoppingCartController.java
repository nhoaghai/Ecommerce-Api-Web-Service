package server.project_module05.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.project_module05.model.dto.request.shopping_cart.QuantityChangeRequest;
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
    public ResponseEntity<ShoppingCartResponse> AddNewShoppingCart(@RequestBody ShoppingCartRequest shoppingCartRequest){
        return ResponseEntity.ok().body(shoppingCartService.addNewShoppingCart(shoppingCartRequest));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ShoppingCartResponse> handleChangeOrderQuantity(@PathVariable Long productId, QuantityChangeRequest quantityChangeRequest){
        return ResponseEntity.ok().body(shoppingCartService.changeOrderQuantity(productId, quantityChangeRequest));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ShoppingCartResponse> handleDeleteProduct(@PathVariable Long productId){
        return null;
    }
}
