package server.project_module05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.project_module05.model.entity.Product;
import server.project_module05.model.entity.ShoppingCart;

import java.util.List;

@Repository
public interface IShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findAllByUserUserId(Long userId);

    ShoppingCart findByUserUserIdAndProductProductId(Long userId, Long productId);
}
