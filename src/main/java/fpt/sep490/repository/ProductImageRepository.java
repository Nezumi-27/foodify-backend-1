package fpt.sep490.repository;

import fpt.sep490.entity.ProductImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    Page<ProductImage> getProductImagesByProductId(Long productId, Pageable pageable);
}
