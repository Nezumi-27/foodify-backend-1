package fpt.sep490.controller;

import fpt.sep490.payload.*;
import fpt.sep490.service.OrderService;
import fpt.sep490.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("CRUD Apis for Order Resource")
@RestController
@RequestMapping()
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ApiOperation("Create new Order")
    @PostMapping("/api/users/{userId}/orders")
    public ResponseEntity<OrderResponse> createOrder(@PathVariable(value = "userId") Long userId,
                                                     @RequestBody OrderDto orderDto){
        return new ResponseEntity<>(orderService.createOrder(userId, orderDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP', 'SHIPPER')")
    @ApiOperation("Get All Orders")
    @GetMapping("/api/orders")
    public OrderResponsePageable getAllOrders(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_ORDER_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return orderService.getAllOrders(pageNo, pageSize, sortBy, sortDir);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP', 'SHIPPER')")
    @ApiOperation("Get Orders by User")
    @GetMapping("/api/users/{userId}/orders")
    public OrderResponsePageable getOrdersByUser(
            @PathVariable(value = "userId") Long userId,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_ORDER_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return orderService.getOrdersByUserId(userId, pageNo, pageSize, sortBy, sortDir);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP', 'SHIPPER')")
    @ApiOperation("Get Orders by User And Status")
    @GetMapping("/api/users/{userId}/orders/status/{status}")
    public OrderResponsePageable getOrdersByUserAndStatus(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "status") String status,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_ORDER_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return orderService.getOrdersByUserIdAndStatus(userId, status, pageNo, pageSize, sortBy, sortDir);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP', 'SHIPPER')")
    @ApiOperation("Get Orders by Shipper")
    @GetMapping("/api/shippers/{shipperId}/orders")
    public OrderResponsePageable getOrdersByShipper(
            @PathVariable(value = "shipperId") Long shipperId,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_ORDER_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return orderService.getOrdersByShipperId(shipperId, pageNo, pageSize, sortBy, sortDir);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP', 'SHIPPER')")
    @ApiOperation("Get Orders by Status")
    @GetMapping("/api/orders/get")
    public OrderResponsePageable getOrdersByStatus(
            @RequestParam(value = "status") String status,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_ORDER_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return orderService.getOrdersByStatus(status, pageNo, pageSize, sortBy, sortDir);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP', 'SHIPPER')")
    @ApiOperation("Get Orders by Shipper And Status")
    @GetMapping("/api/shippers/{shipperId}/orders/status")
    public OrderResponsePageable getOrdersByShipperAndStatus(
            @PathVariable(value = "shipperId") Long shipperId,
            @RequestParam(value = "status") String status,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_ORDER_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return orderService.getOrdersByShipperIdAndStatus(shipperId, status, pageNo, pageSize, sortBy, sortDir);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP', 'SHIPPER')")
    @ApiOperation("Get Orders by Shipper And Statuses (Latest)")
    @GetMapping("/api/shippers/{shipperId}/orders/statuses")
    public OrderResponsePageable getOrdersByShipperAndStatuses(
            @PathVariable(value = "shipperId") Long shipperId,
            @RequestParam(value = "status") List<String> statuses,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_ORDER_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return orderService.getOrdersByShipperIdAndStatuses(shipperId, statuses, pageNo, pageSize, sortBy, sortDir);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP', 'SHIPPER')")
    @ApiOperation("Get Orders by Shop")
    @GetMapping("/api/shops/{shopId}/orders")
    public OrderResponsePageable getOrdersByShop(
            @PathVariable(value = "shopId") Long shopId,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_ORDER_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return orderService.getOrdersByShopId(shopId, pageNo, pageSize, sortBy, sortDir);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP', 'SHIPPER')")
    @ApiOperation("Get Order By Id")
    @GetMapping("/api/users/{userId}/orders/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable(value = "userId") Long userId,
                                                      @PathVariable(value = "orderId") Long orderId){
        return ResponseEntity.ok(orderService.getOrderById(userId, orderId));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP', 'SHIPPER')")
    @ApiOperation("Update Order By Id")
    @PutMapping("/api/users/{userId}/orders/{orderId}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable(value = "userId") Long userId,
                                                      @PathVariable(value = "orderId") Long orderId,
                                                     @RequestBody OrderDto orderDto){
        return ResponseEntity.ok(orderService.updateOrder(userId, orderId, orderDto));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP', 'SHIPPER')")
    @ApiOperation("Update Order Status")
    @PutMapping("/api/users/{userId}/orders/{orderId}/status")
    public ResponseEntity<OrderResponse> updateOrderStatus(@PathVariable(value = "userId") Long userId,
                                                     @PathVariable(value = "orderId") Long orderId,
                                                     @RequestParam(value = "status") String status){
        return ResponseEntity.ok(orderService.updateOrderStatus(userId, orderId, status));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP', 'SHIPPER')")
    @ApiOperation("Update Order Shipper")
    @PutMapping("/api/users/{userId}/orders/{orderId}/shipper")
    public ResponseEntity<OrderResponse> updateOrderShipper(@PathVariable(value = "userId") Long userId,
                                                           @PathVariable(value = "orderId") Long orderId,
                                                           @RequestParam(value = "shipperId") Long shipperId){
        return ResponseEntity.ok(orderService.updateOrderShipper(userId, orderId, shipperId));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP', 'SHIPPER')")
    @ApiOperation("Delete Order By Id")
    @DeleteMapping("/api/users/{userId}/orders/{orderId}")
    public ResponseEntity<StringBoolObject> deleteOrder(@PathVariable(value = "userId") Long userId,
                                                        @PathVariable(value = "orderId") Long orderId){
        return ResponseEntity.ok(this.orderService.deleteOrder(userId, orderId));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP', 'SHIPPER')")
    @ApiOperation("Count Order By District")
    @GetMapping("/api/orders/count")
    public ResponseEntity<Integer> countOrderByDistrict(@RequestParam(value = "districtName") String districtName) {
        return ResponseEntity.ok(this.orderService.countOrdersByDistrict(districtName));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP', 'SHIPPER')")
    @ApiOperation("Count Shop Order By District")
    @GetMapping("/api/orders/shop/{shopId}/count")
    public ResponseEntity<Integer> countShopOrderByDistrict(
            @PathVariable(value = "shopId") Long shopId,
            @RequestParam(value = "districtName") String districtName) {
        return ResponseEntity.ok(this.orderService.countShopOrdersByDistrict(shopId, districtName));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP', 'SHIPPER')")
    @ApiOperation("Find Order By Tracking Number")
    @GetMapping("/api/orders/search")
    public ResponseEntity<OrderResponsePageable> findOrderByTrackingNumber(
            @RequestParam(value = "trackingNumber") String trackingNumber,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_ORDER_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return ResponseEntity.ok(this.orderService.findOrdersByTrackingNumber(trackingNumber, pageNo, pageSize, sortBy, sortDir));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP', 'SHIPPER')")
    @ApiOperation("Find Order Distance and Shipping Cost")
    @GetMapping("/api/orders/cost")
    public ResponseEntity<ShippingResponse> countDistance(
            @RequestParam("address") String address,
            @RequestParam("shopId") Long shopId
    ) {
        return ResponseEntity.ok(this.orderService.findDistanceAndShippingCost(address, shopId));
    }
}
