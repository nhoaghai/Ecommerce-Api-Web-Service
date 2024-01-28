package server.project_module05.model.dto.response.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductResponse {
    private Long productId;
    private String productName;
    private String description;
    private BigDecimal unitPrice;
    private String imgUrl;
}
