package fpt.sep490.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderDetailDto {
    @NotEmpty(message = "Product Id must not be empty")
    private Long productId;

    @NotEmpty(message = "Quantity must not be empty")
    private int quantity;
}
