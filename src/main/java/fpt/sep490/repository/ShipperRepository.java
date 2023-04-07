package fpt.sep490.repository;

import fpt.sep490.entity.Shipper;
import fpt.sep490.entity.Shop;
import fpt.sep490.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShipperRepository extends JpaRepository<Shipper, Long> {
    Page<Shipper> findShippersByShop(Shop shop, Pageable pageable);

    List<Shipper> findShippersByShop(Shop shop);

    List<Shipper> findShippersByShopAndIsActiveAndAndIsShipping(Shop shop, boolean isActive, boolean isShipping);

    Optional<Shipper> findShipperByUser(User user);
}
