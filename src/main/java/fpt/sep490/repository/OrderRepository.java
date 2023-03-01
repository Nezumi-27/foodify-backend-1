package fpt.sep490.repository;

import fpt.sep490.entity.Order;
import fpt.sep490.entity.Shipper;
import fpt.sep490.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findOrdersByUser(User user, Pageable pageable);
    Page<Order> findOrdersByShipper(Shipper shipper, Pageable pageable);
}
