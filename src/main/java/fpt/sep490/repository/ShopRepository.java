package fpt.sep490.repository;

import fpt.sep490.entity.Shop;
import fpt.sep490.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    Page<Shop> findShopsByIsEnabled(boolean isEnabled, Pageable pageable);

    Page<Shop> findShopsByNameContaining(String name, Pageable pageable);
    Optional<Shop> findShopByUser(User user);
}
