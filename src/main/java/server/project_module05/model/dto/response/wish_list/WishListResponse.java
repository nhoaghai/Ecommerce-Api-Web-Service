package server.project_module05.model.dto.response.wish_list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WishListResponse {
    private Long productId;
    private String productName;
    private String imgUrl;
}
