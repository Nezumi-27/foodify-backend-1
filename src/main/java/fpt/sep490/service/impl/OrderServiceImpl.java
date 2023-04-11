package fpt.sep490.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import fpt.sep490.entity.*;
import fpt.sep490.entity.notification.Notification;
import fpt.sep490.entity.notification.Sender;
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
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDate;
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
    private final ShopRepository shopRepository;

    public OrderServiceImpl(UserRepository userRepository, ShipperRepository shipperRepository, OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, AddressRepository addressRepository, ModelMapper mapper,
                            ProductRepository productRepository,
                            ShopRepository shopRepository) {
        this.userRepository = userRepository;
        this.shipperRepository = shipperRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.addressRepository = addressRepository;
        this.mapper = mapper;
        this.productRepository = productRepository;
        this.shopRepository = shopRepository;
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
        order.setLat(orderDto.getLat());
        order.setLng(orderDto.getLng());
        Order newOrder = orderRepository.save(order);


        for(OrderDetailDto orderDetail : orderDetails){
            Product product = productRepository.findById(orderDetail.getProductId())
                    .orElseThrow(()-> new ResourceNotFoundException("Product", "id", orderDetail.getProductId()));

            OrderDetail newOrderDetail = new OrderDetail();
            newOrderDetail.setProduct(product);
            newOrderDetail.setQuantity(orderDetail.getQuantity());
            newOrderDetail.setSubTotal((long) ((product.getCost() - product.getCost() * product.getDiscountPercent()/100)* orderDetail.getQuantity()));
            productCost = productCost + newOrderDetail.getSubTotal();
            newOrderDetail.setOrder(newOrder);
            orderDetailRepository.save(newOrderDetail);
        }

        newOrder.setProductCost(productCost);
        newOrder.setTotal(newOrder.getShippingCost() + (productCost));
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
    public OrderResponsePageable getOrdersByUserIdAndStatus(Long userId, String status, int pageNo, int pageSize, String sortBy, String sortDir) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Order> orders = orderRepository.findOrdersByUserAndStatus(user, status, pageable);
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
    public OrderResponsePageable getOrdersByShipperIdAndStatus(Long shipperId, String status, int pageNo, int pageSize, String sortBy, String sortDir) {
        Shipper shipper = shipperRepository.findById(shipperId)
                .orElseThrow(()-> new ResourceNotFoundException("Shipper", "id", shipperId));

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Order> orders = orderRepository.findOrdersByShipperAndStatus(shipper, status, pageable);
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
    public OrderResponsePageable getOrdersByShopId(Long shopId, int pageNo, int pageSize, String sortBy, String sortDir) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ResourceNotFoundException("Shop", "id", shopId));

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        List<Product> products = productRepository.findProductsByShop(shop);
        List<OrderDetail> lists = new ArrayList<>();

        for(Product product : products){
            List<OrderDetail> details = orderDetailRepository.findOrderDetailsByProduct(product);
            lists.addAll(details);
        }

        Page<Order> orders = orderRepository.findDistinctByOrderDetailsIn(lists, pageable);
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
    public OrderResponsePageable findOrdersByTrackingNumber(String oTN, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Order> orders = orderRepository.findOrdersByOrderTrackingNumberContaining(oTN, pageable);
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

        if(status.equals("COMPLETED")){
            order.getShipper().setIsShipping(false);
            Set<OrderDetail> details = order.getOrderDetails();

            for(OrderDetail detail : details){
                Product product = detail.getProduct();
                product.setSold(product.getSold() + detail.getQuantity());
                productRepository.save(product);
            }
        }
        return mapper.map(orderRepository.save(order), OrderResponse.class);
    }

    @Override
    public OrderResponse updateOrderShipper(Long userId, Long orderId, Long shipperId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));

        Shipper shipper = shipperRepository.findById(shipperId)
                .orElseThrow(()-> new ResourceNotFoundException("Shipper", "id", shipperId));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new ResourceNotFoundException("Order", "id", orderId));

        if(!order.getUser().getId().equals(userId)){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "This order doesn't belong to user");
        }

        order.setShipper(shipper);
        shipper.setIsShipping(true);
        shipperRepository.save(shipper);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer AAAAMcAdgF0:APA91bE9OPI9SBgvFV_8KijtnWEQ5nx4PyOhrY61u8BRv5xKnPzhiqCqcLvz4WVYSgVNLHWjUiOJBaxhIiwhwB6YsAPXDn1aNfbKx-q8FvypzW7lmiKC8vOxFpYUAh8YaItk4Vf-eZ_F");

        String url = "https://fcm.googleapis.com/fcm/send";
        String fcmToken = shipper.getUser().getFcmToken();
        if (fcmToken.startsWith("\"") && fcmToken.endsWith("\"")) {
            fcmToken = fcmToken.substring(1, fcmToken.length() - 1);
        }

        Sender sender = new Sender("", new Notification());
        sender.setTo(fcmToken);
        sender.getNotification().setTitle("Đơn hàng mới");
        sender.getNotification().setBody("Bạn vừa nhận được một đơn hàng mới. Vui lòng kiểm tra ứng dụng của bạn");
        sender.getNotification().setImage("https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Admin%2Fbell-mobile.jpg?alt=media&token=f00a2dce-2556-4d32-894a-c26e4f18db6b");
        sender.getNotification().setSound("notificationsound.mp3");
        sender.getNotification().setAndroidChannelId("foodify-notification");

        HttpEntity<Sender> request = new HttpEntity<>(sender, headers);
        ResponseEntity<Sender> response = restTemplate.postForEntity(url, request, Sender.class);

        return mapper.map(orderRepository.save(order), OrderResponse.class);
    }

    @Override
    public Integer countOrdersByDistrict(String districtName) {
        List<Order> orders = orderRepository.findOrdersByAddressContaining(districtName);
        return orders.toArray().length;
    }

    @Override
    public Integer countShopOrdersByDistrict(Long shopId, String districtName) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ResourceNotFoundException("Shop", "id", shopId));

        List<Product> products = productRepository.findProductsByShop(shop);
        List<OrderDetail> lists = new ArrayList<>();

        for(Product product : products){
            List<OrderDetail> details = orderDetailRepository.findOrderDetailsByProduct(product);
            lists.addAll(details);
        }

        List<Order> orderList = orderRepository.findDistinctByOrderDetailsIn(lists);

        Integer count = 0;

        for(Order order : orderList){
            if(order.getAddress().contains(districtName)){
                count++;
            }
        }

        return count;
    }

    @Override
    public Long countShopRevenueByDay(Long shopId, int day) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ResourceNotFoundException("Shop", "id", shopId));

        List<Product> products = productRepository.findProductsByShop(shop);
        List<OrderDetail> lists = new ArrayList<>();

        for(Product product : products){
            List<OrderDetail> details = orderDetailRepository.findOrderDetailsByProduct(product);
            lists.addAll(details);
        }

        List<Order> orderList = orderRepository.findDistinctByOrderDetailsIn(lists);

        LocalDate today = LocalDate.now();
        LocalDate daysAgo = today.minusDays(day);

        Timestamp daysAgoTS = Timestamp.valueOf(daysAgo.atStartOfDay());

        List<Order> recentOrders = orderList.stream()
                .filter(order -> order.getOrderTime().after(daysAgoTS)).collect(Collectors.toList());

        Long revenue = 0L;

        for(Order order : recentOrders){
            if(order.getStatus().equals("COMPLETED")) {
                revenue = revenue + order.getTotal();
            }
        }
        return revenue;
    }

    @Override
    public StringBoolObject deleteOrder(Long userId, Long orderId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new ResourceNotFoundException("Order", "id", orderId));

        if(!order.getUser().getId().equals(userId)){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "This order doesn't belong to user");
        }

        orderRepository.delete(order);

        return new StringBoolObject("isDeleted", true);
    }
}
