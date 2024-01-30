package server.project_module05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.project_module05.model.entity.Category;
import server.project_module05.model.entity.Product;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Product findByProductId(Long productId);
    List<Product> findAllByProductNameContainingOrDescriptionContaining(String productName, String description);
    List<Product> findByCategory(Category category);


}
