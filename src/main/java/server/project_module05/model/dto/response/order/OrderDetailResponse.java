package server.project_module05.model.dto.response.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import server.project_module05.model.dto.response.product.ProductResponse;
import server.project_module05.model.entity.OrderStatus;

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
    private OrderStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date createAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date updateAt;

    private List<ProductResponse> listItem;
}
