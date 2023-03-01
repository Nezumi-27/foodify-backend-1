package fpt.sep490.payload;

import lombok.Data;

@Data
public class OrderDetailDto {
    private Long productId;
    private int quantity;
}
