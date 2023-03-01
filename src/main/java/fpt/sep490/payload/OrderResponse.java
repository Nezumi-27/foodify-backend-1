package fpt.sep490.payload;

import lombok.Data;

@Data
public class OrderResponse {
    private Long id;
    private String orderTrackingNumber;
    private String paymentMethod;
    private Long productCost;
    private Long shippingCost;
    private Long total;
    private String status;
    private UserDto user;
    private ShipperResponse shipper;
    private AddressDto address;
}
