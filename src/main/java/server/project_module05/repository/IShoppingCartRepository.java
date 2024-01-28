package server.project_module05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.project_module05.model.entity.Product;
import server.project_module05.model.entity.ShoppingCart;
import server.project_module05.model.entity.User;

import java.util.List;

@Repository
public interface IShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    List<ShoppingCart> findAllByUserUserId(Long userId);

    ShoppingCart findByUserAndProduct(User user, Product product);
}
