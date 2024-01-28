package server.project_module05.controller.permitAll;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.project_module05.model.dto.response.category.CategoryResponse;
import server.project_module05.service.category.implement.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api.myservice.com/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }
}
