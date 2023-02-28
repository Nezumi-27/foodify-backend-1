package fpt.sep490.service;

import fpt.sep490.payload.OrderDto;
import fpt.sep490.payload.OrderResponse;
import fpt.sep490.payload.OrderResponsePageable;

public interface OrderService {
    OrderResponse createOrder(Long userId, OrderDto orderDto);

    OrderResponsePageable getAllOrders(int pageNo, int pageSize, String sortBy, String sortDir);

    OrderResponsePageable getOrdersByUserId(Long userId, int pageNo, int pageSize, String sortBy, String sortDir);

    OrderResponsePageable getOrdersByShipperId(Long shipperId, int pageNo, int pageSize, String sortBy, String sortDir);

    OrderResponse getOrderById(Long userId, Long orderId);

    OrderResponse updateOrder(Long userId, Long orderId, OrderDto orderDto);

    OrderResponse updateOrderStatus(Long userId, Long orderId, String status);

    void deleteOrder(Long userId, Long orderId);
}
