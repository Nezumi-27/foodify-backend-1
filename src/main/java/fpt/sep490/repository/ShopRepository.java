package fpt.sep490.repository;

import fpt.sep490.entity.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    Page<Shop> findShopsByIsEnabled(boolean isEnabled, Pageable pageable);
}
