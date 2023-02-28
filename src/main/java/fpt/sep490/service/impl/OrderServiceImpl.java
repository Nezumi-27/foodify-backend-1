package fpt.sep490.service.impl;

import fpt.sep490.entity.Address;
import fpt.sep490.entity.Order;
import fpt.sep490.entity.Shipper;
import fpt.sep490.entity.User;
import fpt.sep490.exception.FoodifyAPIException;
import fpt.sep490.exception.ResourceNotFoundException;
import fpt.sep490.payload.OrderDto;
import fpt.sep490.payload.OrderResponse;
import fpt.sep490.payload.OrderResponsePageable;
import fpt.sep490.payload.PageableDto;
import fpt.sep490.repository.AddressRepository;
import fpt.sep490.repository.OrderRepository;
import fpt.sep490.repository.ShipperRepository;
import fpt.sep490.repository.UserRepository;
import fpt.sep490.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private UserRepository userRepository;
    private ShipperRepository shipperRepository;
    private OrderRepository orderRepository;
    private AddressRepository addressRepository;
    private ModelMapper mapper;

    public OrderServiceImpl(UserRepository userRepository, ShipperRepository shipperRepository, OrderRepository orderRepository, AddressRepository addressRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.shipperRepository = shipperRepository;
        this.orderRepository = orderRepository;
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    @Override
    public OrderResponse createOrder(Long userId, OrderDto orderDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Shipper shipper = shipperRepository.findById(orderDto.getShipperId())
                .orElseThrow(() -> new ResourceNotFoundException("Shipper", "id", orderDto.getShipperId()));

        Address address = addressRepository.findById(orderDto.getAddressId())
                .orElseThrow(()-> new ResourceNotFoundException("Address", "id", orderDto.getAddressId()));

        Order order = mapper.map(orderDto, Order.class);
        order.setUser(user);
        order.setShipper(shipper);
        order.setAddress(address);

        Order newOrder = orderRepository.save(order);
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

        if(!order.getUser().getId().equals(userId)){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "This order doesn't belong to user");
        }

        order.setOrderTrackingNumber(orderDto.getOrderTrackingNumber());
        order.setPaymentMethod(orderDto.getPaymentMethod());
        order.setProductCost(orderDto.getProductCost());
        order.setShippingCost(orderDto.getShippingCost());
        order.setTotal(orderDto.getTotal());
        order.setStatus(orderDto.getStatus());

        return mapper.map(orderRepository.save(order), OrderResponse.class);
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
