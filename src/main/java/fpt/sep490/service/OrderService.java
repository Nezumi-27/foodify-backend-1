package fpt.sep490.service;

import fpt.sep490.payload.OrderDto;
import fpt.sep490.payload.OrderResponse;
import fpt.sep490.payload.OrderResponsePageable;
import fpt.sep490.payload.StringBoolObject;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(Long userId, OrderDto orderDto);

    OrderResponsePageable getAllOrders(int pageNo, int pageSize, String sortBy, String sortDir);

    OrderResponsePageable getOrdersByUserId(Long userId, int pageNo, int pageSize, String sortBy, String sortDir);

    OrderResponsePageable getOrdersByUserIdAndStatus(Long userId, String status, int pageNo, int pageSize, String sortBy, String sortDir);

    OrderResponsePageable getOrdersByShipperId(Long shipperId, int pageNo, int pageSize, String sortBy, String sortDir);

    OrderResponsePageable getOrdersByShopId(Long shopId, int pageNo, int pageSize, String sortBy, String sortDir);

    OrderResponse getOrderById(Long userId, Long orderId);

    OrderResponse updateOrder(Long userId, Long orderId, OrderDto orderDto);

    OrderResponse updateOrderStatus(Long userId, Long orderId, String status);

    Integer countOrdersByDistrict(String districtName);

    Integer countShopOrdersByDistrict(Long shopId, String districtName);

    Long countShopRevenueByDay(Long shopId, int day);

    StringBoolObject deleteOrder(Long userId, Long orderId);
}
