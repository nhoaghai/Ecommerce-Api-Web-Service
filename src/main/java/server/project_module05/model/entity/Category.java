package server.project_module05.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;
    private String categoryName;
    private String description;
    private Boolean status;

    @OneToMany(mappedBy = "category", cascade=CascadeType.ALL)
    private List<Product> products;
}
