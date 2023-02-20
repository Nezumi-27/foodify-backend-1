package fpt.sep490.payload;

import fpt.sep490.entity.Shop;
import fpt.sep490.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class ShipperResponse {
    private List<ShipperDtoFull> shippers;
    private PageableDto page;
}
