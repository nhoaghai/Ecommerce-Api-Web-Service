package server.project_module05.model.dto.response.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderResponse {
    private String serialNumber;
    private BigDecimal totalPrice;
    private String note;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;
}
