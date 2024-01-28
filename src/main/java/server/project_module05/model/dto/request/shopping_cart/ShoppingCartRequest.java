package server.project_module05.model.dto.request.shopping_cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShoppingCartRequest {
    private Long productId;
    private Integer quantity;
}
