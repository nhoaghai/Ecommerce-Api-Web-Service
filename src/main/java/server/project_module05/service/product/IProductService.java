package server.project_module05.service.product;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import server.project_module05.model.dto.response.page.PageResponse;
import server.project_module05.model.dto.response.product.ProductResponse;
import server.project_module05.model.entity.Product;

import java.util.List;

@Service
public interface IProductService {

   Product findById(Long id) throws Exception;
   PageResponse<ProductResponse> getAllProducts(Pageable pageable);
   List<ProductResponse> findAllByProductNameOrDescription(String keyword);

   List<ProductResponse> getAllProductsByCategoryId(Long categoryId);
   ProductResponse getProductDetail(Long productId);
}
