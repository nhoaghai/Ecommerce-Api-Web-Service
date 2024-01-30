package server.project_module05.model.dto.response.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import server.project_module05.model.entity.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderResponse {

    private String serialNumber;
    private BigDecimal totalPrice;
    private String note;
    private OrderStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date createAt;
}
