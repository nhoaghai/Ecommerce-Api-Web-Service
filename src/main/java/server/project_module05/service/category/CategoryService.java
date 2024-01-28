package server.project_module05.service.category;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import server.project_module05.model.dto.response.category.CategoryResponse;
import server.project_module05.model.dto.response.login.UserResponse;
import server.project_module05.repository.ICategoryRepository;
import server.project_module05.service.category.ICategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final ModelMapper modelMapper;
    private final ICategoryRepository categoryRepository;
    @Override
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream()
                .map(category -> modelMapper.map(category, CategoryResponse.class))
                .collect(Collectors.toList());
    }
}
