package fpt.sep490.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipperResponse {
    private Long id;
    private UserDto user;
    private ShopDto shop;
    private Boolean isActive;
    private Boolean isShipping;
}
