package fpt.sep490.repository;

import fpt.sep490.entity.Category;
import fpt.sep490.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findProductsByCategoriesIn(List<Category> categories, Pageable pageable);

    Page<Product> findDistinctByCategoriesIn(List<Category> categories, Pageable pageable);
    Page<Product> findProductsByShopId(Long shopId, Pageable pageable);
}
