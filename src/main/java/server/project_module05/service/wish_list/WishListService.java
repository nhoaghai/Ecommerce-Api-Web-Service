package server.project_module05.service.wish_list;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import server.project_module05.model.dto.request.wish_list.WishListRequest;
import server.project_module05.model.dto.response.wish_list.WishListResponse;
import server.project_module05.model.entity.WishList;
import server.project_module05.repository.IProductRepository;
import server.project_module05.repository.IUserRepository;
import server.project_module05.repository.IWishListRepository;
import server.project_module05.security.principle.UserDetail;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListService implements IWishListService{
    private final IWishListRepository wishListRepository;
    private final IProductRepository productRepository;
    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public void addNewWishList(WishListRequest wishListRequest) {
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        WishList wishList = new WishList();
        wishList.setProduct(productRepository.findByProductId(wishListRequest.getProductId()));
        wishList.setUser(userRepository.findByUserId(userDetail.getId()));
        wishListRepository.save(wishList);
    }

    @Override
    public List<WishListResponse> getAllWishList() {
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return wishListRepository.findAllByUser(userRepository.findByUserId(userDetail.getId())).stream()
                .map(wishList -> modelMapper.map(wishList, WishListResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long wishListId) {
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Boolean isWishListExist = wishListRepository.existsByWishListIdAndUser(wishListId, userRepository.findByUserId(userDetail.getId()));
        if (!isWishListExist){
            throw new RuntimeException("Could not find id");
        }else {
            wishListRepository.deleteById(wishListId);
        }
    }
}
