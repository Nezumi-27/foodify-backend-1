package fpt.sep490.repository;

import fpt.sep490.entity.Category;
import fpt.sep490.entity.Product;
import fpt.sep490.payload.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findCategoriesByIdIn(List<Long> categoryIds);

    List<Category> findCategoriesByNameIn(List<String> categoryNames);

    boolean existsByName(String name);
    Optional<Category> findByName(String name);
}
