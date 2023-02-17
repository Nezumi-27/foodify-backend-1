package fpt.sep490.repository;

import fpt.sep490.entity.Category;
import fpt.sep490.payload.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
