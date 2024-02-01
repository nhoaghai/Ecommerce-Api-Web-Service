package server.project_module05.model.dto.response.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import server.project_module05.model.dto.response.product.ProductResponse;

import java.math.BigDecimal;
import java.util.Calendar;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createAt;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar receiveAt;
    List<ProductResponse> listItem;
}
