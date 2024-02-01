package server.project_module05.model.dto.response.wish_list;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WishListResponse {
    private Long productId;
    private String productName;
    private String imgUrl;
}
