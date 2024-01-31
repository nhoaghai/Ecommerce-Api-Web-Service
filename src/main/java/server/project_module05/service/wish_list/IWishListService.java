package server.project_module05.service.wish_list;

import org.springframework.stereotype.Service;
import server.project_module05.model.dto.request.wish_list.WishListRequest;
import server.project_module05.model.dto.response.wish_list.WishListResponse;

import java.util.List;

@Service
public interface IWishListService {
    void addNewWishList(WishListRequest wishListRequest);
    List<WishListResponse> getAllWishList();
    void deleteById(Long wishListId);
}
