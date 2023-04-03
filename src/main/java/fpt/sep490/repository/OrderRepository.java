package fpt.sep490.repository;

import fpt.sep490.entity.Order;
import fpt.sep490.entity.Shipper;
import fpt.sep490.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findOrdersByUser(User user, Pageable pageable);

    List<Order> findOrdersByUser(User user);
    Page<Order> findOrdersByUserAndStatus(User user, String status, Pageable pageable);
    Page<Order> findOrdersByShipper(Shipper shipper, Pageable pageable);
}
