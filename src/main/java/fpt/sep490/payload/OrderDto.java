package fpt.sep490.payload;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class OrderDto {
    private Long id;

    @NotEmpty(message = "Tracking number must not be empty")
    private String orderTrackingNumber;

//    @NotEmpty(message = "User Id must not be empty")
//    private Long userId;

    private Long shipperId;

    @NotEmpty(message = "Payment method must not be empty")
    private String paymentMethod;

    @NotEmpty
    private List<OrderDetailDto> orderDetails;

//    @NotEmpty(message = "Product cost must not be empty")
//    private Long productCost;

    @NotEmpty(message = "Shipping cost must not be empty")
    private Long shippingCost;

    @NotEmpty(message = "Status must not be empty")
    private String status;

    private String address;

    private String lat;
    private String lng;
}
