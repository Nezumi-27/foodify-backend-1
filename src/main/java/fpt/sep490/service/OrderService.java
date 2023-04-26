package fpt.sep490.service;

import fpt.sep490.payload.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(Long userId, OrderDto orderDto);
    OrderResponsePageable getAllOrders(int pageNo, int pageSize, String sortBy, String sortDir);
    OrderResponsePageable getOrdersByUserId(Long userId, int pageNo, int pageSize, String sortBy, String sortDir);
    OrderResponsePageable getOrdersByUserIdAndStatus(Long userId, String status, int pageNo, int pageSize, String sortBy, String sortDir);
    OrderResponsePageable getOrdersByStatus(String status, int pageNo, int pageSize, String sortBy, String sortDir);
    OrderResponsePageable getOrdersByShipperId(Long shipperId, int pageNo, int pageSize, String sortBy, String sortDir);
    OrderResponsePageable getOrdersByShipperIdAndStatus(Long shipperId, String status, int pageNo, int pageSize, String sortBy, String sortDir);
    OrderResponsePageable getOrdersByShipperIdAndStatuses(Long shipperId, List<String> statuses, int pageNo, int pageSize, String sortBy, String sortDir);
    OrderResponsePageable getOrdersByShopId(Long shopId, int pageNo, int pageSize, String sortBy, String sortDir);
    OrderResponsePageable findOrdersByTrackingNumber(String oTN, int pageNo, int pageSize, String sortBy, String sortDir);
    OrderResponse getOrderById(Long userId, Long orderId);
    OrderResponse updateOrder(Long userId, Long orderId, OrderDto orderDto);
    OrderResponse updateOrderStatus(Long userId, Long orderId, String status);
    OrderResponse updateOrderShipper(Long userId, Long orderId, Long shipperId);
    ShippingResponse findDistanceAndShippingCost(String address, Long shopId);
    Integer countOrdersByDistrict(String districtName);
    Integer countShopOrdersByDistrict(Long shopId, String districtName);
    Long countShopRevenueByDay(Long shopId, int day);
    StringBoolObject deleteOrder(Long userId, Long orderId);
}
