package fpt.sep490.payload;

import fpt.sep490.entity.Address;
import fpt.sep490.entity.Shipper;
import fpt.sep490.entity.User;
import lombok.Data;

import java.util.Set;

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
