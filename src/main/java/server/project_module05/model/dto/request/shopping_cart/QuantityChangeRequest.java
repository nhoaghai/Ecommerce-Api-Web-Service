package server.project_module05.model.dto.request.shopping_cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuantityChangeRequest {
    private Integer quantity;
}
