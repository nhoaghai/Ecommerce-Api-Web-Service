package server.project_module05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.project_module05.model.entity.User;
import server.project_module05.model.entity.WishList;

import java.util.List;

@Repository
public interface IWishListRepository extends JpaRepository<WishList, Long> {
    List<WishList> findAllByUser(User user);
    Boolean existsByWishListIdAndUser(Long wishListId, User user);
}
