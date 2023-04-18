package fpt.sep490.payload;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipperResponse {
    private Long id;
    private UserDto user;
    private ShopDto shop;
//    private Set<OrderDto> orders;
    private Boolean isActive;
    private Boolean isShipping;
}
