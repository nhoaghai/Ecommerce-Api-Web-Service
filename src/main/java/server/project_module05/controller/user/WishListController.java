package server.project_module05.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.project_module05.model.dto.request.wish_list.WishListRequest;
import server.project_module05.model.dto.response.wish_list.WishListResponse;
import server.project_module05.service.wish_list.IWishListService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api.myservice.com/v1/user/wish-list")
public class WishListController {
    private final IWishListService wishListService;
    @PostMapping
    public ResponseEntity<?> addNewWishList(@RequestBody WishListRequest wishListRequest){
        wishListService.addNewWishList(wishListRequest);
        return ResponseEntity.ok("Add successfully");
    }
    @GetMapping
    public ResponseEntity<List<WishListResponse>> getAllWishList(){
        return ResponseEntity.ok().body(wishListService.getAllWishList());
    }
    @DeleteMapping("/{wishListId}")
    public ResponseEntity<?> handleDeleteWishList(@PathVariable Long wishListId){
        wishListService.deleteById(wishListId);
        return ResponseEntity.ok("Delete successfully");
    }
}
