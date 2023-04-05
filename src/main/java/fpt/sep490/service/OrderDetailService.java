package fpt.sep490.service;

import fpt.sep490.payload.OrderDetailDto;
import fpt.sep490.payload.OrderDetailResponse;
import fpt.sep490.payload.OrderDetailResponsePageable;
import fpt.sep490.payload.OrderDto;

public interface OrderDetailService {
    OrderDetailResponse createOrderDetail(Long orderId, OrderDetailDto orderDetailDto);

    OrderDetailResponsePageable getAllOrderDetail(int pageNo, int pageSize, String sortBy, String sortDir);

    OrderDetailResponsePageable getOrderDetailsByOrderId(Long orderId, int pageNo, int pageSize, String sortBy, String sortDir);

    OrderDetailResponse getOrderDetailById(Long orderId, Long orderDetailId);

    OrderDetailResponse updateOrderDetail(Long orderId, Long orderDetailId, OrderDetailDto orderDetailDto);

    void deteleOrderDetail(Long orderId, Long orderDetailId);
}
