package server.project_module05.controller.permitAll;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.project_module05.model.dto.response.page.PageResponse;
import server.project_module05.model.dto.response.product.ProductResponse;
import server.project_module05.model.entity.Product;
import server.project_module05.service.product.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api.myservice.com/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<PageResponse<ProductResponse>> getAllProduct(Pageable pageable){
        return new ResponseEntity<>(productService.getAllProducts(pageable),HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> getAllByProductNameOrDescription(@RequestParam String searchName){
        return new ResponseEntity<>(productService.findAllByProductNameOrDescription(searchName), HttpStatus.OK);
    }
    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<List<ProductResponse>> getAllProductByCategoryId(@PathVariable Long categoryId){
        return new ResponseEntity<>(productService.getAllProductsByCategoryId(categoryId),HttpStatus.OK);
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductDetails(@PathVariable Long productId){
        return new ResponseEntity<>(productService.getProductDetail(productId),HttpStatus.OK);
    }
}
