package server.project_module05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.project_module05.model.entity.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryId(Long categoryId);
}
