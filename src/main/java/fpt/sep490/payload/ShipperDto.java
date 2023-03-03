package fpt.sep490.payload;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipperDto {
    private Long id;

    @NotEmpty(message = "Description of product must not be empty")
    private Long userId;

    @NotEmpty(message = "Shop id must not be empty")
    private Long shopId;

    @NotEmpty(message = "Shipper status (isActive) must not be empty")
    private Boolean isActive;

    @NotEmpty(message = "Shipping status (isShipping) must not be empty")
    private Boolean isShipping;
}
