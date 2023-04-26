package fpt.sep490.repository;

import fpt.sep490.entity.Order;
import fpt.sep490.entity.OrderDetail;
import fpt.sep490.entity.Shipper;
import fpt.sep490.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findOrdersByUser(User user, Pageable pageable);
    List<Order> findOrdersByUser(User user);
    List<Order> findOrdersByAddressContaining(String contain);
    Page<Order> findOrdersByUserAndStatus(User user, String status, Pageable pageable);
    Page<Order> findOrdersByShipper(Shipper shipper, Pageable pageable);
    Page<Order> findOrdersByStatus(String status, Pageable pageable);
    Page<Order> findOrdersByOrderTrackingNumberContaining(String oTN, Pageable pageable);
    Page<Order> findOrdersByShipperAndStatus(Shipper shipper, String status, Pageable pageable);
    Page<Order> findOrdersByShipperAndStatusIn(Shipper shipper, List<String> statuses, Pageable pageable);
    Page<Order> findDistinctByOrderDetailsIn(List<OrderDetail> orderDetails, Pageable pageable);
    List<Order> findDistinctByOrderDetailsIn(List<OrderDetail> orderDetails);
}
