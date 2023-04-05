package fpt.sep490.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern="HH:mm:ss dd-MM-yyyy", timezone = "GMT+7")
    private Timestamp orderTime;
    private String status;
    private String address;
    private String lat;
    private String lng;
    private UserDto user;
    private ShipperResponse shipper;
    private Set<OrderDetailResponse> orderDetails;
}
