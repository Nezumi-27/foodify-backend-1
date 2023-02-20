package fpt.sep490.payload;

import fpt.sep490.entity.Shop;
import fpt.sep490.entity.User;
import lombok.Data;

@Data
public class ShipperDtoFull {
    private Long id;
    private UserDto user;
    private ShopDto shop;
}
