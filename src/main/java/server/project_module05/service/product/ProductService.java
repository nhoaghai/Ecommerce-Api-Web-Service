package server.project_module05.service.product;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import server.project_module05.exception.ProductException;
import server.project_module05.model.dto.response.category.CategoryResponse;
import server.project_module05.model.dto.response.page.PageResponse;
import server.project_module05.model.dto.response.product.ProductResponse;
import server.project_module05.model.entity.Category;
import server.project_module05.model.entity.Product;
import server.project_module05.repository.ICategoryRepository;
import server.project_module05.repository.IProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ModelMapper modelMapper;
    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;

    @Override
    public Product findById(Long id) throws Exception {
        return productRepository.findById(id).orElseThrow(()-> new Exception("Could not find id"));
    }

    @Override
    public PageResponse<ProductResponse> getAllProducts(Pageable pageable) {
        Page<Product> page = productRepository.findAll(pageable);
        List<ProductResponse> data = page
                .getContent().stream()
                .map(pro -> modelMapper.map(pro, ProductResponse.class))
                .toList();
        PageResponse<ProductResponse> pageResponse = new PageResponse<>();
        pageResponse.setData(data);
        pageResponse.setTotalPages(page.getTotalPages());
        pageResponse.setSize(page.getSize());
        pageResponse.setPageNumber(page.getNumber());
        pageResponse.setSort(page.getSort().toString());
        return pageResponse;
    }

    @Override
    public List<ProductResponse> findAllByProductNameOrDescription(String keyword) {
        return productRepository.findAllByProductNameContainingOrDescriptionContaining(keyword, keyword).stream()
                .map(pro -> modelMapper.map(pro, ProductResponse.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<ProductResponse> getAllProductsByCategoryId(Long categoryId) {
        Category category = categoryRepository.findByCategoryId(categoryId);
        List<Product> products = productRepository.findByCategory(category);
        return products.stream()
                .map(pro -> modelMapper.map(pro, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductDetail(Long productId) {
        Product product = productRepository.findByProductId(productId);
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .unitPrice(product.getUnitPrice())
                .imgUrl(product.getImgUrl())
                .build();
    }
}
