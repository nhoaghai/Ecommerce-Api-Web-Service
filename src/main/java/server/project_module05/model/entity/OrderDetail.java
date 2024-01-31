package server.project_module05.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetail implements Serializable {

    @EmbeddedId
    private OrderDetailId orderDetailId;
    private String name;
    @Column(precision = 10, scale = 2)
    private BigDecimal unitPrice;
    private Integer orderQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JsonIgnore
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JsonIgnore
    private Product product;
}
