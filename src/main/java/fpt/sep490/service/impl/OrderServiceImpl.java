package fpt.sep490.service.impl;

import fpt.sep490.entity.*;
import fpt.sep490.exception.FoodifyAPIException;
import fpt.sep490.exception.ResourceNotFoundException;
import fpt.sep490.payload.*;
import fpt.sep490.repository.*;
import fpt.sep490.service.OrderService;
import fpt.sep490.utils.AppConstants;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private UserRepository userRepository;
    private ShipperRepository shipperRepository;
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    private AddressRepository addressRepository;
    private ModelMapper mapper;
    private final ProductRepository productRepository;

    public OrderServiceImpl(UserRepository userRepository, ShipperRepository shipperRepository, OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, AddressRepository addressRepository, ModelMapper mapper,
                            ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.shipperRepository = shipperRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.addressRepository = addressRepository;
        this.mapper = mapper;
        this.productRepository = productRepository;
    }

    @Override
    public OrderResponse createOrder(Long userId, OrderDto orderDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));


        List<OrderDetailDto> orderDetails = orderDto.getOrderDetails();
        List<String> productDisable = new ArrayList<>();

        for(OrderDetailDto orderDetail : orderDetails){
            Product product = productRepository.findById(orderDetail.getProductId())
                    .orElseThrow(()-> new ResourceNotFoundException("Product", "id", orderDetail.getProductId()));
            if(!product.getIsEnabled()) {
                productDisable.add(product.getName());
            }
        }
        if(!productDisable.isEmpty()){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Product " + productDisable + " has been disabled");
        }


        Long productCost= 0L;
        Order order = new Order();
        order.setOrderTrackingNumber(orderDto.getOrderTrackingNumber());
        order.setPaymentMethod(orderDto.getPaymentMethod());
        order.setStatus(String.valueOf(AppConstants.ORDER_STATUS.AWAITING));
        order.setShippingCost(orderDto.getShippingCost());
        order.setProductCost(0L);
        order.setTotal(orderDto.getShippingCost() + productCost);
        order.setUser(user);
        order.setAddress(orderDto.getAddress());
        Order newOrder = orderRepository.save(order);


        for(OrderDetailDto orderDetail : orderDetails){
            Product product = productRepository.findById(orderDetail.getProductId())
                    .orElseThrow(()-> new ResourceNotFoundException("Product", "id", orderDetail.getProductId()));

            OrderDetail newOrderDetail = new OrderDetail();
            newOrderDetail.setProduct(product);
            newOrderDetail.setQuantity(orderDetail.getQuantity());
            newOrderDetail.setSubTotal(product.getCost() * orderDetail.getQuantity());
            productCost = productCost + newOrderDetail.getSubTotal();
            newOrderDetail.setOrder(newOrder);
            orderDetailRepository.save(newOrderDetail);
        }

        newOrder.setProductCost(productCost);
        newOrder.setTotal(newOrder.getShippingCost() + productCost);
        newOrder = orderRepository.save(newOrder);

        return mapper.map(newOrder, OrderResponse.class);
    }

    @Override
    public OrderResponsePageable getAllOrders(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Order> orders = orderRepository.findAll(pageable);
        List<Order> orderList = orders.getContent();
        List<OrderResponse> content = orderList.stream().map(order -> mapper.map(order, OrderResponse.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(orders.getNumber());
        pageableDto.setPageSize(orders.getSize());
        pageableDto.setTotalElements(orders.getTotalElements());
        pageableDto.setTotalPages(orders.getTotalPages());
        pageableDto.setLast(orders.isLast());

        OrderResponsePageable orderResponsePageable = new OrderResponsePageable();
        orderResponsePageable.setOrders(content);
        orderResponsePageable.setPage(pageableDto);
        return orderResponsePageable;
    }

    @Override
    public OrderResponsePageable getOrdersByUserId(Long userId, int pageNo, int pageSize, String sortBy, String sortDir) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Order> orders = orderRepository.findOrdersByUser(user, pageable);
        List<Order> orderList = orders.getContent();
        List<OrderResponse> content = orderList.stream().map(order -> mapper.map(order, OrderResponse.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(orders.getNumber());
        pageableDto.setPageSize(orders.getSize());
        pageableDto.setTotalElements(orders.getTotalElements());
        pageableDto.setTotalPages(orders.getTotalPages());
        pageableDto.setLast(orders.isLast());

        OrderResponsePageable orderResponsePageable = new OrderResponsePageable();
        orderResponsePageable.setOrders(content);
        orderResponsePageable.setPage(pageableDto);
        return orderResponsePageable;
    }

    @Override
    public OrderResponsePageable getOrdersByShipperId(Long shipperId, int pageNo, int pageSize, String sortBy, String sortDir) {
        Shipper shipper = shipperRepository.findById(shipperId)
                .orElseThrow(()-> new ResourceNotFoundException("Shipper", "id", shipperId));

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Order> orders = orderRepository.findOrdersByShipper(shipper, pageable);
        List<Order> orderList = orders.getContent();
        List<OrderResponse> content = orderList.stream().map(order -> mapper.map(order, OrderResponse.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(orders.getNumber());
        pageableDto.setPageSize(orders.getSize());
        pageableDto.setTotalElements(orders.getTotalElements());
        pageableDto.setTotalPages(orders.getTotalPages());
        pageableDto.setLast(orders.isLast());

        OrderResponsePageable orderResponsePageable = new OrderResponsePageable();
        orderResponsePageable.setOrders(content);
        orderResponsePageable.setPage(pageableDto);
        return orderResponsePageable;
    }

    @Override
    public OrderResponse getOrderById(Long userId, Long orderId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new ResourceNotFoundException("Order", "id", orderId));

        if(!order.getUser().getId().equals(userId)){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "This order doesn't belong to user");
        }
        return mapper.map(order, OrderResponse.class);
    }

    @Override
    public OrderResponse updateOrder(Long userId, Long orderId, OrderDto orderDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new ResourceNotFoundException("Order", "id", orderId));

        Shipper shipper = shipperRepository.findById(orderDto.getShipperId())
                .orElseThrow(() -> new ResourceNotFoundException("Shipper", "id",orderDto.getShipperId()));


        if(!order.getUser().getId().equals(userId)){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "This order doesn't belong to user");
        }

        List<OrderDetailDto> orderDetails = orderDto.getOrderDetails();
        Long productCost = 0L;

        order.setAddress(orderDto.getAddress());
        order.setOrderTrackingNumber(orderDto.getOrderTrackingNumber());
        order.setPaymentMethod(orderDto.getPaymentMethod());
        order.setShippingCost(orderDto.getShippingCost());
        order.setStatus(orderDto.getStatus());
        order.setShipper(shipper);

        Set<OrderDetail> oldOrderDetailLists = order.getOrderDetails();
        for(OrderDetail oldOrderDetail : oldOrderDetailLists){
            orderDetailRepository.delete(oldOrderDetail);
        }

        System.out.println("Debug here");
        Order orderUpdated = orderRepository.save(order);

        for(OrderDetailDto orderDetail : orderDetails){
            Product product = productRepository.findById(orderDetail.getProductId())
                    .orElseThrow(()-> new ResourceNotFoundException("Product", "id", orderDetail.getProductId()));


            OrderDetail newOrderDetail = new OrderDetail();
            newOrderDetail.setProduct(product);
            newOrderDetail.setQuantity(orderDetail.getQuantity());
            newOrderDetail.setSubTotal(product.getCost() * orderDetail.getQuantity());
            productCost = productCost + newOrderDetail.getSubTotal();
            newOrderDetail.setOrder(orderUpdated);
            orderDetailRepository.save(newOrderDetail);
        }

        orderUpdated.setProductCost(productCost);
        orderUpdated.setTotal(order.getShippingCost() + productCost);

        return mapper.map(orderRepository.save(orderUpdated), OrderResponse.class);
    }

    @Override
    public OrderResponse updateOrderStatus(Long userId, Long orderId, String status) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new ResourceNotFoundException("Order", "id", orderId));

        if(!order.getUser().getId().equals(userId)){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "This order doesn't belong to user");
        }

        order.setStatus(status);
        return mapper.map(orderRepository.save(order), OrderResponse.class);
    }

    @Override
    public void deleteOrder(Long userId, Long orderId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new ResourceNotFoundException("Order", "id", orderId));

        if(!order.getUser().getId().equals(userId)){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "This order doesn't belong to user");
        }

        orderRepository.delete(order);
    }
}
