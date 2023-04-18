package fpt.sep490.service.impl;

import com.google.type.LatLng;
import fpt.sep490.entity.Order;
import fpt.sep490.entity.OrderDetail;
import fpt.sep490.entity.Product;
import fpt.sep490.entity.Shop;
import fpt.sep490.entity.map.Location;
import fpt.sep490.exception.FoodifyAPIException;
import fpt.sep490.exception.ResourceNotFoundException;
import fpt.sep490.payload.*;
import fpt.sep490.repository.OrderDetailRepository;
import fpt.sep490.repository.OrderRepository;
import fpt.sep490.repository.ProductRepository;
import fpt.sep490.repository.ShopRepository;
import fpt.sep490.service.GeocodeService;
import fpt.sep490.service.OrderDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    private ProductRepository productRepository;

    private GeocodeService geocodeService;
    private ModelMapper mapper;
    private final ShopRepository shopRepository;

    public OrderDetailServiceImpl(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, ProductRepository productRepository, GeocodeService geocodeService, ModelMapper mapper,
                                  ShopRepository shopRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
        this.geocodeService = geocodeService;
        this.mapper = mapper;
        this.shopRepository = shopRepository;
    }

    @Override
    public OrderDetailResponse createOrderDetail(Long orderId, OrderDetailDto orderDetailDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));

        Product product = productRepository.findById(orderDetailDto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", orderDetailDto.getProductId()));

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduct(product);
        orderDetail.setQuantity(orderDetailDto.getQuantity());
        orderDetail.setSubTotal(product.getCost() * orderDetailDto.getQuantity());
        orderDetail.setOrder(order);

        return mapper.map(orderDetailRepository.save(orderDetail), OrderDetailResponse.class);
    }

    @Override
    public OrderDetailResponsePageable getAllOrderDetail(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<OrderDetail> orderDetails = orderDetailRepository.findAll(pageable);
        List<OrderDetail> orderDetailsList = orderDetails.getContent();
        List<OrderDetailResponse> content = orderDetailsList.stream().map(orderDetail -> mapper.map(orderDetail, OrderDetailResponse.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(orderDetails.getNumber());
        pageableDto.setPageSize(orderDetails.getSize());
        pageableDto.setTotalElements(orderDetails.getTotalElements());
        pageableDto.setTotalPages(orderDetails.getTotalPages());
        pageableDto.setLast(orderDetails.isLast());

        OrderDetailResponsePageable response = new OrderDetailResponsePageable();
        response.setOrderDetails(content);
        response.setPage(pageableDto);
        return response;
     }

    @Override
    public OrderDetailResponsePageable getOrderDetailsByOrderId(Long orderId, int pageNo, int pageSize, String sortBy, String sortDir) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new ResourceNotFoundException("Order", "id", orderId));

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<OrderDetail> orderDetails = orderDetailRepository.findOrderDetailsByOrder(order, pageable);
        List<OrderDetail> orderDetailsList = orderDetails.getContent();
        List<OrderDetailResponse> content = orderDetailsList.stream().map(orderDetail -> mapper.map(orderDetail, OrderDetailResponse.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(orderDetails.getNumber());
        pageableDto.setPageSize(orderDetails.getSize());
        pageableDto.setTotalElements(orderDetails.getTotalElements());
        pageableDto.setTotalPages(orderDetails.getTotalPages());
        pageableDto.setLast(orderDetails.isLast());

        OrderDetailResponsePageable response = new OrderDetailResponsePageable();
        response.setOrderDetails(content);
        response.setPage(pageableDto);
        return response;
    }

    @Override
    public OrderDetailResponse getOrderDetailById(Long orderId, Long orderDetailId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new ResourceNotFoundException("Order", "id", orderId));

        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new ResourceNotFoundException("Details", "id", orderDetailId));

        if(!orderDetail.getOrder().getId().equals(orderId)){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Details doesn't belong to order");
        }

        return mapper.map(orderDetail, OrderDetailResponse.class);
    }

    @Override
    public OrderDetailResponse updateOrderDetail(Long orderId, Long orderDetailId, OrderDetailDto orderDetailDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new ResourceNotFoundException("Order", "id", orderId));

        Product product = productRepository.findById(orderDetailDto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", orderDetailDto.getProductId()));

        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new ResourceNotFoundException("Details", "id", orderDetailId));

        if(!orderDetail.getOrder().getId().equals(orderId)){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Details doesn't belong to order");
        }

        orderDetail.setProduct(product);
        orderDetail.setQuantity(orderDetailDto.getQuantity());
        orderDetail.setSubTotal(product.getCost() * orderDetailDto.getQuantity());
        return mapper.map(orderDetailRepository.save(orderDetail), OrderDetailResponse.class);
    }

    @Override
    public void deteleOrderDetail(Long orderId, Long orderDetailId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new ResourceNotFoundException("Order", "id", orderId));

        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new ResourceNotFoundException("Details", "id", orderDetailId));

        if(!orderDetail.getOrder().getId().equals(orderId)) {
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Details doesn't belong to order");
        }

        orderDetailRepository.delete(orderDetail);
    }
}
