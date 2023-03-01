package fpt.sep490.payload;

import lombok.Data;

@Data
public class OrderDto {
    private Long id;
    private String orderTrackingNumber;
    private Long userId;
    private Long shipperId;
    private String paymentMethod;
    private Long productCost;
    private Long shippingCost;
    private Long total;
    private String status;
    private Long addressId;
}
