package fpt.sep490.payload;

import lombok.Data;

@Data
public class OrderDetailResponse {
    private Long id;
    private ProductResponse product;
    private int quantity;
    private Long subtotal;
    private OrderResponse order;
}