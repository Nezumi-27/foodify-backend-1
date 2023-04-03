package fpt.sep490.repository;

import fpt.sep490.entity.Order;
import fpt.sep490.entity.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    Page<OrderDetail> findOrderDetailsByOrder(Order order, Pageable pageable);

    List<OrderDetail> findOrderDetailsByOrder(Order order);
}
