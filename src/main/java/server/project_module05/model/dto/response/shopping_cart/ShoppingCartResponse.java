package server.project_module05.model.dto.response.shopping_cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ShoppingCartResponse {
    private Long shoppingCartId;
    private String productName;
    private BigDecimal unitPrice;
    private Integer orderQuantity;
}
