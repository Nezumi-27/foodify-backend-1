package fpt.sep490.repository;

import fpt.sep490.entity.Shipper;
import fpt.sep490.entity.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipperRepository extends JpaRepository<Shipper, Long> {
    Page<Shipper> findShippersByShop(Shop shop, Pageable pageable);

    List<Shipper> findShippersByShop(Shop shop);
}
