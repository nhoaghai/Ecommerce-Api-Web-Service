package server.project_module05.model.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class OrderDetailId implements Serializable {
    private Long orderId;
    private Long productId;

}