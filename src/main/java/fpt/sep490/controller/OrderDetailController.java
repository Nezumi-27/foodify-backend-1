package fpt.sep490.controller;

import fpt.sep490.payload.OrderDetailDto;
import fpt.sep490.payload.OrderDetailResponse;
import fpt.sep490.payload.OrderDetailResponsePageable;
import fpt.sep490.service.OrderDetailService;
import fpt.sep490.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api("REST APIs for Order Details")
@RestController
@RequestMapping
public class OrderDetailController {
    private OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ApiOperation("Create new Order Detail")
    @PostMapping("/api/orders/{orderId}/details")
    public ResponseEntity<OrderDetailResponse> createOrderDetail(@PathVariable(value = "orderId") Long orderId,
                                                                 @RequestBody OrderDetailDto orderDetailDto){
        return new ResponseEntity<>(orderDetailService.createOrderDetail(orderId, orderDetailDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP','SHIPPER')")
    @ApiOperation("Get all Order Details")
    @GetMapping("/api/orders/details")
    public OrderDetailResponsePageable getAllOrderDetails(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_ORDER_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return orderDetailService.getAllOrderDetail(pageNo, pageSize, sortBy, sortDir);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP','SHIPPER')")
    @ApiOperation("Get Order Details by Order")
    @GetMapping("/api/orders/{orderId}/details")
    public OrderDetailResponsePageable getOrderDetailsByOrder(
            @PathVariable(value = "orderId") Long orderId,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_ORDER_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return orderDetailService.getOrderDetailsByOrderId(orderId ,pageNo, pageSize, sortBy, sortDir);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP','SHIPPER')")
    @ApiOperation("Get Order Details By Id")
    @GetMapping("/api/orders/{orderId}/details/{detailId}")
    public ResponseEntity<OrderDetailResponse> getOrderDetailById(@PathVariable(value = "orderId") Long orderId,
                                                                  @PathVariable(value = "detailId") Long detailId){
        return ResponseEntity.ok(orderDetailService.getOrderDetailById(orderId, detailId));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP')")
    @ApiOperation("Update Order Details By Id")
    @PutMapping("/api/orders/{orderId}/details/{detailId}")
    public ResponseEntity<OrderDetailResponse> updateOrderDetail(@PathVariable(value = "orderId") Long orderId,
                                                                 @PathVariable(value = "detailId") Long detailId,
                                                                 @RequestBody OrderDetailDto detailDto){
        return ResponseEntity.ok(orderDetailService.updateOrderDetail(orderId, detailId, detailDto));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP')")
    @ApiOperation("Delete Order Details By Id")
    @DeleteMapping("/api/orders/{orderId}/details/{detailId}")
    public ResponseEntity<String> deleteOrderDetail(@PathVariable(value = "orderId") Long orderId,
                                                    @PathVariable(value = "detailId") Long detailId){
        orderDetailService.deteleOrderDetail(orderId, detailId);
        return ResponseEntity.ok("Order Details deleted successfully");
    }

}
