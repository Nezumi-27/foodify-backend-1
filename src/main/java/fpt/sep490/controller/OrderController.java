package fpt.sep490.controller;

import fpt.sep490.payload.OrderDto;
import fpt.sep490.payload.OrderResponse;
import fpt.sep490.payload.OrderResponsePageable;
import fpt.sep490.service.OrderService;
import fpt.sep490.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("CRUD Apis for Order Resource")
@RestController
@RequestMapping()
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation("Create new Order")
    @PostMapping("/api/users/{userId}/orders")
    public ResponseEntity<OrderResponse> createOrder(@PathVariable(value = "userId") Long userId,
                                                     @RequestBody OrderDto orderDto){
        return new ResponseEntity<>(orderService.createOrder(userId, orderDto), HttpStatus.CREATED);
    }

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

    @ApiOperation("Get Order By Id")
    @GetMapping("/api/users/{userId}/orders/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable(value = "userId") Long userId,
                                                      @PathVariable(value = "orderId") Long orderId){
        return ResponseEntity.ok(orderService.getOrderById(userId, orderId));
    }

    @ApiOperation("Update Order By Id")
    @PutMapping("/api/users/{userId}/orders/{orderId}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable(value = "userId") Long userId,
                                                      @PathVariable(value = "orderId") Long orderId,
                                                     @RequestBody OrderDto orderDto){
        return ResponseEntity.ok(orderService.updateOrder(userId, orderId, orderDto));
    }

    @ApiOperation("Update Order Status")
    @PutMapping("/api/users/{userId}/orders/{orderId}/status/{status}")
    public ResponseEntity<OrderResponse> updateOrderStatus(@PathVariable(value = "userId") Long userId,
                                                     @PathVariable(value = "orderId") Long orderId,
                                                     @RequestParam(value = "status") String status){
        return ResponseEntity.ok(orderService.updateOrderStatus(userId, orderId, status));
    }

    @ApiOperation("Delete Order By Id")
    @DeleteMapping("/api/users/{userId}/orders/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable(value = "userId") Long userId,
                                                      @PathVariable(value = "orderId") Long orderId){
        return ResponseEntity.ok("Order deleted successfully");
    }

}
