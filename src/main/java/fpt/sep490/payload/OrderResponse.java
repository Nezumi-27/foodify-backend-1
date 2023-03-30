package fpt.sep490.payload;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
public class OrderResponse {
    private Long id;
    private String orderTrackingNumber;
    private String paymentMethod;
    private Long productCost;
    private Long shippingCost;
    private Long total;
    private Timestamp orderTime;
    private String status;
    private String address;
    private UserDto user;
    private ShipperResponse shipper;
    private Set<OrderDetailResponse> orderDetails;
}
