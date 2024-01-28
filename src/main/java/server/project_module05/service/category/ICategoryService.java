package server.project_module05.service.category;

import org.springframework.stereotype.Service;
import server.project_module05.model.dto.response.category.CategoryResponse;

import java.util.List;

@Service
public interface ICategoryService {
    List<CategoryResponse> findAll();
}
