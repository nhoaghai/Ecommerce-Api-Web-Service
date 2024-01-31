package server.project_module05.model.dto.response.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import server.project_module05.model.dto.response.product.ProductResponse;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetailResponse {
    private String serialNumber;
    private BigDecimal totalPrice;
    private String note;
    private String receiveName;
    private String receiveAddress;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date receiveAt;
    List<ProductResponse> listItem;
}
