package server.project_module05.model.dto.request.order;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import server.project_module05.model.entity.OrderStatus;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderRequest {

    private String serialNumber;
    private BigDecimal totalPrice;
    private String note;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
